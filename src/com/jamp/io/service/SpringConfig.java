package com.jamp.io.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
	//basePackages = 
		{"com.jamp.io.service"}
//	excludeFilters = @Configuration
	)
public class SpringConfig {
	{
		System.out.println("SpringConfig");
	}
	
}
