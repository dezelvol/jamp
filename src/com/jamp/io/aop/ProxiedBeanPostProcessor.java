package com.jamp.io.aop;

import java.lang.reflect.InvocationHandler;
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
public class ProxiedBeanPostProcessor implements BeanPostProcessor {
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
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if(method.isAnnotationPresent(ProxyThis.class)) {
						LOGGER.info("Proxified");
					}
					return method.invoke(bean, args);
				}
			});
		} else
		return bean;
	}
}
