package com.ouyanglol.helptest.core;


import com.ouyanglol.helptest.annotation.EnableMock;
import com.ouyanglol.helptest.annotation.HelpMockBean;
import com.ouyanglol.helptest.config.MockHelpProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Package: com.ouyang.core
 *
 * @Author: Ouyang
 * @Date: 2018/1/26
 */
@Component
@ConditionalOnBean(annotation = EnableMock.class)
public class BaseContext implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String RESOURCE_PATTERN = "**/%s/**/*.class";//默认扫描所有
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    protected ApplicationContext applicationContext;
    protected Map<Class, Object> mockMap = new ConcurrentHashMap<>();

    private final MockHelpProperties mockHelpProperties;

    public BaseContext(MockHelpProperties mockHelpProperties) {
        if (mockHelpProperties.getPackageRoot() == null) {
            mockHelpProperties.setPackageRoot("**");
        }
        this.mockHelpProperties = mockHelpProperties;
        logger.info("开始注册mock类...");
        init();
    }


    public void init() {
        this.scan();
    }

    private void scan() {
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + String.format(RESOURCE_PATTERN, ClassUtils.convertClassNameToResourcePath(mockHelpProperties.getPackageRoot()));
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
            for (Resource resource : resources) {
                try {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        Class<?> curClass = Thread.currentThread().getContextClassLoader().loadClass(className);
                        for (Field field : curClass.getDeclaredFields()) {
                            HelpMockBean annotation = field.getAnnotation(HelpMockBean.class);
                            if (annotation != null) {
                                mockMap.put(annotation.beanClass(), field.getType().newInstance());
                            }
                        }
                    }
                } catch (Error | Exception ignored) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }
}
