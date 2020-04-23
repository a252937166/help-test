package com.ouyanglol.helptest.annotation;

import java.lang.annotation.*;

/**
 * @author ouyangduning
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableMock {
}
