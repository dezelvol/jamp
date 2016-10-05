package com.jamp.io.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.jamp.io.aop.ProxiedBeanPostProcessor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.jamp.io.config", "com.jamp.io.aop"})
public class SpringConfig {
	@Autowired
	DataSource dataSource;
	
	@Bean
	public ProxiedBeanPostProcessor proxiedBeanPostProcessor() {
		return new ProxiedBeanPostProcessor();
	}


}
