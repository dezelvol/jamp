package com.jamp.io.aop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(99)
public class LoggerAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(LoggerAspect.class);
	
	@Around("execution(* com.jamp.io.web..*(..))")
	public Object LogControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println(111);
		String method = joinPoint.getSignature().getName();
		Object methodArgs = joinPoint.getArgs();
		LOGGER.info("Call method " + method + "with args: " + methodArgs);
		Object result = joinPoint.proceed();
		LOGGER.info("Method " + method + "returns: " + result);
		return result;
	}
	
//	@AfterThrowing(value = "execution(* com.jamp.io.web..*(..))", throwing="t")
//	public void LogException(ProceedingJoinPoint joinPoint, Throwable t) {
//		String method = joinPoint.getSignature().getName();
//		LOGGER.error("Call method " + method + "thrown exception: " + t);
//	}
}
