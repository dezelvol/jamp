package com.jamp.io.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan (
		basePackages="com.jamp.io.web"
		)
public class WebConfig extends WebMvcConfigurerAdapter {
	{
		System.out.println("WebConfig");
	}
	
	@Bean
	public ViewResolver resolver() {
		System.out.println("resolver");
		InternalResourceViewResolver res = new InternalResourceViewResolver();
		res.setPrefix("WEB-INF/view/");
		res.setSuffix(".jsp");
		return res;
	}
	
}
