package aop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	private final static Logger LOGGER = LogManager.getLogger(LoggerAspect.class);
	
	@Pointcut("execution(* com.jamp.io.web.*(..))")
	public Object LogControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		Object methodArgs = joinPoint.getArgs();
		System.out.println(111);
		LOGGER.log(Level.TRACE, "Call method " + method + "with args: " + methodArgs);
		Object result = joinPoint.proceed();
		LOGGER.log(Level.TRACE, "Method " + method + "returns: " + result);
		return result;
	}
	
	@AfterThrowing(value = "execution(* com.jamp.io.*(..))", throwing="t")
	public void LogException(ProceedingJoinPoint joinPoint, Throwable t) {
		String method = joinPoint.getSignature().getName();
		LOGGER.log(Level.ERROR, "Call method " + method + "thrown exception: " + t);
	}
}
