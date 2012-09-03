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

@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);

	@Before("loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
		LOGGER.info("Entered Into "
				+ joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName() + "()");
	}

	@After("loggingOperation()")
	public void logAfter(JoinPoint joinPoint) {
		LOGGER.info("Exited  From "
				+ joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName() + "()");
	}

	@AfterReturning(pointcut = "loggingOperation()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		LOGGER.debug("Entered Into "
				+ joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName() + "()");
		LOGGER.debug("Result : " + result);

	}

	@AfterThrowing(pointcut = "execution(* com.advanceweb.afc.jb.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		LOGGER.error("An exception has been thrown in "
				+ joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName() + "()");
		LOGGER.error("Message : " + error.getMessage());
	}

	/*
	 * @Around("execution(* com.advanceweb.afc.jb.employer.web.controller.*.*(..))"
	 * ) public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	 * logger.info("Entered Into "
	 * +joinPoint.getTarget().getClass().getName()+"."
	 * +joinPoint.getSignature().getName()+"()"); }
	 */

}