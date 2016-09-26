package com.jamp.io.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jamp.io.model.pojo.User;

@Aspect
@Component
public class SecurityAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(LoggerAspect.class);
	
	@Autowired
	User registeredUser;
	
	@Around(value = "execution(* com.jamp.io.web.UserController.*(..))")
	public Object LogException(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		String arguments = "";
		for(Object o : methodArgs) {
			arguments += " " + o;
		}
		if(registeredUser.isBlank()) {
			LOGGER.warn("Unauthorized user access");
			throw new SecurityException("Unauthorized");
		}
		
		LOGGER.info("Call DAO method " + method + ", with args: " + arguments);
		Object result = joinPoint.proceed();
		LOGGER.info("DAO method " + method + ", returns: " + result);
		return result;
	}
}
