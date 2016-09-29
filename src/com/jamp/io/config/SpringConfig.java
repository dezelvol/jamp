package com.jamp.io.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.jamp.io.aop.ProxiedBeanPostProcessor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.jamp.io.config", "com.jamp.io.aop"})
public class SpringConfig {
	
	@Bean
	public ProxiedBeanPostProcessor proxiedBeanPostProcessor() {
		return new ProxiedBeanPostProcessor();
	}
}
