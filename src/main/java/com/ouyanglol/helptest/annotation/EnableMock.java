package com.ouyanglol.helptest.annotation;

import java.lang.annotation.*;

/**
 * @author ouyangduning
 * @date 2020/4/22 21:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableMock {
}
