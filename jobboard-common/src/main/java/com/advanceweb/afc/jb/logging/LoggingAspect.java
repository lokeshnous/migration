package com.advanceweb.afc.jb.logging;

/**
 * 
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	
//	 private Log logger = LogFactory.getLog(this.getClass());
	 private static final Logger logger = Logger.getLogger(LoggingAspect.class);
	 
	 @Pointcut("within(com.advanceweb.afc.jb..*) AND execution(* *(..))")
	 protected void loggingOperation() {
		 
	 }

	
	@Before("loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Entered Into " +joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
	}

	@After("loggingOperation()")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("Exited From " +joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.advanceweb.afc.jb.*.*(..))",
			returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		logger.info("Entered Into " +joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");

	}
	
	@AfterThrowing(
			pointcut = "execution(* com.advanceweb.afc.jb.*.*(..))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		
		logger.error("An exception has been thrown in " + joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
		logger.error("Message : "+error.getMessage());
	}
	
	
	/*@Around("execution(* com.advanceweb.afc.jb.employer.web.controller.*.*(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Entered Into " +joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
	}*/
	
}