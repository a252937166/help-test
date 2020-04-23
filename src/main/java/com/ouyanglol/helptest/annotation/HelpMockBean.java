package com.ouyanglol.helptest.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author ouyangduning
 * @date 2020/4/22 17:57
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HelpMockBean {
    Class beanClass();
}
