package com.jamp.io.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;

import com.jamp.io.annotations.ProxyThis;

public class ProxiedBeanPostProcessor implements BeanPostProcessor {
	private Map<String, Class<?>> map = new HashMap<>();
	{System.out.println("ProxiedBeanPostProcessor");}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization");
		Class<? extends Object> beanClass = bean.getClass();
		for(Method m : beanClass.getMethods()) {
			if(m.isAnnotationPresent(ProxyThis.class)) {
				map.put(beanName, beanClass);
				break;
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization");
		if(map.containsKey(beanName)) {
			Class<?> beanClass = map.get(beanName);
			System.out.println(beanName);
			return Proxy.newProxyInstance(beanClass.getClassLoader(), ClassUtils.getAllInterfacesForClass(beanClass), new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("invoke");
					if(method.isAnnotationPresent(ProxyThis.class)) {
						System.out.println("Proxyfied");
					}
					return method.invoke(args);
				}
			});
		} else
		return bean;
	}

}
