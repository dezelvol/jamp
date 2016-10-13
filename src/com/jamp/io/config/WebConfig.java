package com.jamp.io.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jamp.io.model.pojo.SessionData;
import com.jamp.io.service.UserService;
import com.jamp.io.utils.ParticipantValidator;
import com.jamp.io.utils.StringToMentorConverter;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan (basePackages={"com.jamp.io.web"})
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	UserService userService;
	
	@Bean
	public StringToMentorConverter mentorConverter() {
		return new StringToMentorConverter();
	}
	
	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver res = new InternalResourceViewResolver();
		res.setPrefix("/WEB-INF/view/");
		res.setSuffix(".jsp");
		return res;
	}
	
	@Bean
	@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SessionData registeredUser() {
		return new SessionData();
	}
    
	@Bean 
	public ParticipantValidator participantValidator() {
		return new ParticipantValidator();
	}
	
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(mentorConverter());
    }
}
