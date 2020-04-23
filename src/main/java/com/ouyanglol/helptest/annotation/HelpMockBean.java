package com.ouyanglol.helptest.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author ouyangduning
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HelpMockBean {
    Class beanClass();
}
