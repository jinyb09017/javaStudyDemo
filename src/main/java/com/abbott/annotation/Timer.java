package com.abbott.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于注解方法，来计算方法的执行时间的
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Timer {
}
