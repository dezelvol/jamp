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

import com.jamp.io.jms.JmsMessageProducer;

@Aspect
@Component
public class LoggerAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
	
	@Autowired
	JmsMessageProducer jmsMessageProducer; 
	
	@Around("execution(* com.jamp.io.model.dao..*(..))")
	public Object LogControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		Object[] methodArgs = joinPoint.getArgs();
		String arguments = "";
		for(Object o : methodArgs) {
			arguments += " " + o;
		}
		
		String msg = "Call DAO method " + method + ", with args: " + arguments;
		LOGGER.info(msg);
		Object result = joinPoint.proceed();
		LOGGER.info("DAO method " + method + ", returns: " + result);
		return result;
	}
	
	@AfterThrowing(value = "execution(* com.jamp.io.model.dao..*(..))", throwing="t")
	public void LogException(JoinPoint joinPoint, Throwable t) {
		String method = joinPoint.getSignature().getName();
		LOGGER.error("Call method " + method + ", thrown exception: " + t);
	}
}
