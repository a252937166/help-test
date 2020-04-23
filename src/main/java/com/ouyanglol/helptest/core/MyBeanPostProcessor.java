package com.ouyanglol.helptest.core;

import com.ouyanglol.helptest.annotation.EnableMock;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ouyangduning
 */
@Component
@AutoConfigureAfter({BaseContext.class})
@ConditionalOnBean(annotation = EnableMock.class)
public class MyBeanPostProcessor implements ApplicationContextAware, BeanPostProcessor {

    private final BaseContext baseContext;

    private ApplicationContext applicationContext;

    public MyBeanPostProcessor(BaseContext baseContext) {
        this.baseContext = baseContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Map<Class,Object> map = baseContext.mockMap;
        Class beanClass = bean.getClass();
        if (map.containsKey(beanClass)) {
            return map.get(beanClass);
        }
        if (map.containsKey(beanClass.getSuperclass())) {
            return map.get(beanClass.getSuperclass());
        }
        Class interfaceKey = getInterfaceKey(map, Arrays.asList(beanClass.getInterfaces()));
        if (interfaceKey != null) {
            return map.get(interfaceKey);
        }

        return bean;
    }

    private Class getInterfaceKey(Map<Class,Object> map, List<Class> interfaces) {
        if (CollectionUtils.isEmpty(map) || CollectionUtils.isEmpty(interfaces)) {
            return null;
        }
        for (Class clazz : interfaces) {
            if (map.containsKey(clazz)) {
                return clazz;
            }
        }
        return null;
    }
}