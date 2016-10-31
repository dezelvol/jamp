package com.jamp.io.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jamp.io.aop.ProxiedBeanPostProcessor;

/**
 * Placeholder annotation made to test ProxiedBeanPostProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProxyThis {

}
