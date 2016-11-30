package com.jamp.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class JampbootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JampbootApplication.class, args);
	}
}
