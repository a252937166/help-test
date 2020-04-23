package com.ouyanglol.helptest.config;

import com.ouyanglol.helptest.annotation.EnableMock;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yipin on 2017/6/28.
 * RocketMQ配置文件
 */
@Configuration
@ConditionalOnBean(annotation = EnableMock.class)
@EnableConfigurationProperties(MockHelpProperties.class)
@ComponentScan(value = {"com.ouyanglol.helptest.*"})
public class AutoConfiguration implements ApplicationContextAware {

    protected ConfigurableApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

}