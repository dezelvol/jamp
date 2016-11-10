package com.jamp.io.aop;

import com.jamp.io.model.pojo.Auditable;
import com.jamp.io.model.pojo.SessionData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Aspect for simple logging 
 * Limits access to controllers in crud package
 * Adds metadata when user is created or updated 
 */
@Aspect
@Component
public class SecurityAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(SecurityAspect.class);
	
	@Autowired
	SessionData sessionData;
	
	/**
	 * Main security method
	 * Throws exception on unauthorized access to CRUD controller package
	 */
	@Around(value = "execution(* com.jamp.io.web.crud..*(..))")
	public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
		if(sessionData.getUser() == null) {
			LOGGER.warn("Unauthorized user access");
			throw new SecurityException("Unauthorized");
		}

		String method = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		String arguments = "";
		for(Object o : methodArgs) {
			arguments += " " + o;
		}
		LOGGER.info("Call DAO method " + method + ", with args: " + arguments);
		Object result = joinPoint.proceed();
		LOGGER.info("DAO method " + method + ", returns: " + result);
		return result;
	}

	/**
	 * Interceptor that adds metadata on object creation
	 * Covers all "add" methods in dao package
	 */
	@Around("execution(* com.jamp.io.model.dao..add*(..))")
	public Object setCreated(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] methodArgs = joinPoint.getArgs();
		// Proxy might be applied to method without args
		LOGGER.info("New " + methodArgs[0].getClass().getSimpleName());
		if(methodArgs[0] instanceof Auditable) {
			((Auditable)methodArgs[0]).setCreated(new Date());
			if(sessionData.getUser() != null) {
				((Auditable)methodArgs[0]).setCreatedBy(sessionData.getUser());	
			}
		}
		return joinPoint.proceed();
	}
	
	/**
	 * Interceptor that adds metadata on object update
	 * Covers all "add" methods in dao package,
	 * not working as update functionality not implemented
	 */
	@Around("execution(* com.jamp.io.model.dao..update*(..))")
	public Object setUpdated(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] methodArgs = joinPoint.getArgs();
		LOGGER.info("New updated");
		if(methodArgs[0] instanceof Auditable) {
			((Auditable)methodArgs[0]).setLastUpdated(new Date());
			((Auditable)methodArgs[0]).setLastUpdatedBy(sessionData.getUser());
		}
		return joinPoint.proceed();
	}
}
