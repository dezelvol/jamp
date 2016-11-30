package com.jamp.io;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jamp.io.data.UsersService;

@Configuration
public class BeanConfig {
	
	@Bean
	public UsersService usersService() {
		return new UsersService();
	}
}
