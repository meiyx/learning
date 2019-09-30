package com.study.meiyx.learning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author meiyunxia
 * 表注解
 */
@Target(ElementType.TYPE)@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    String name() default "";
}