package com.jamp.io.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jamp.io.model.pojo.SessionData;
import com.jamp.io.model.pojo.User;

@Aspect
@Component
public class SecurityAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(LoggerAspect.class);
	
	@Autowired
	SessionData sessionData;
	
	@Around(value = "execution(* com.jamp.io.web.UserController.*(..))")
	public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		String arguments = "";
		for(Object o : methodArgs) {
			arguments += " " + o;
		}
		if(sessionData.getUser() == null) {
			LOGGER.warn("Unauthorized user access");
			throw new SecurityException("Unauthorized");
		}
		
		LOGGER.info("Call DAO method " + method + ", with args: " + arguments);
		Object result = joinPoint.proceed();
		LOGGER.info("DAO method " + method + ", returns: " + result);
		return result;
	}

//	@Around("execution(* com.jamp.io.model.dao..addUser(..))")
//	public Object setCreated(ProceedingJoinPoint joinPoint) throws Throwable {
//		String method = joinPoint.getSignature().getName();
//		Object[] methodArgs = joinPoint.getArgs();
//		LOGGER.info("New user");
//		((User)methodArgs[0]).setCreated(new Date());
//		if(((User)methodArgs[0]).getId() != 1l && sessionData.getUser() != null) {
//			((User)methodArgs[0]).setCreatedBy(sessionData.getUser());	
//		}
//		return joinPoint.proceed();
//	}
	
	@Around("execution(* com.jamp.io.model..updateUser(..))")
	public Object setUpdated(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		LOGGER.info("New updated");
		((User)methodArgs[0]).setLastUpdate(new Date());
		((User)methodArgs[0]).setLastUpdatedBy(sessionData.getUser());
		return joinPoint.proceed();
	}
}
