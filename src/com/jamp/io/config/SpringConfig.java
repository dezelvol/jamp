package com.jamp.io.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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


    @Bean
    public Validator basicValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource);
        return validator;
    }
}
