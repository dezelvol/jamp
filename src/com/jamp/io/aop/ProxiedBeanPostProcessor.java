package com.jamp.io.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;

import com.jamp.io.annotations.ProxyThis;

/**
 * Bean post processor to intercept methods that are annotated with ProxyThis annotation
 * @see ProxyThis
 */

// Your BPP works only for interfaces which is not widely used in real life
public class ProxiedBeanPostProcessor implements BeanPostProcessor { // Better naming should be applied here:
	private static final Logger LOGGER =
			LoggerFactory.getLogger(ProxiedBeanPostProcessor.class);
	
	private Map<String, Class<?>> map = new HashMap<>();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<? extends Object> beanClass = bean.getClass();
		for(Method m : beanClass.getMethods()) {
			if(AnnotationUtils.findAnnotation(m, ProxyThis.class) != null) {
				map.put(beanName, beanClass);
				break;
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(map.containsKey(beanName)) {
			Class<?> beanClass = map.get(beanName);
			// Try to use Java 8 features like lambda expressions
			// This is not going to work with classes that does not implement interface or extend any parent class
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
				if(method.isAnnotationPresent(ProxyThis.class)) { // Annotation might be erased in this phase.
					LOGGER.info("Proxified");
				}
				return method.invoke(bean, args); // Exception propagation.
			});
		}
		return bean;
	}
}
