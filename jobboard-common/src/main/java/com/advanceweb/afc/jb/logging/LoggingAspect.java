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
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);
	
	private static final String ENTERED = "Entered Into ";
	
	private static final String EXCEPTION_OCCURED = "An exception has been thrown in ";
	
	private static final String MESSAGE = "Error Message is: ";
	
	private static final String DOT = ".";
	
	private static final String BRACES = "()";

	@Before("logController() || logDAO()")
	public void logBefore(JoinPoint joinPoint) {
		LOGGER.trace(ENTERED
				+ joinPoint.getTarget().getClass().getName() + DOT
				+ joinPoint.getSignature().getName() + BRACES);
	}

//	@After("loggingOperation()")
//	public void logAfter(JoinPoint joinPoint) {
//		LOGGER.trace("Exited  From "
//				+ joinPoint.getTarget().getClass().getName() + DOT
//				+ joinPoint.getSignature().getName() + BRACES);
//	}
//
//	@AfterReturning(pointcut = "loggingOperation()", returning = "result")
//	public void logAfterReturning(JoinPoint joinPoint, Object result) {
//		LOGGER.trace("Entered Into "
//				+ joinPoint.getTarget().getClass().getName() + DOT
//				+ joinPoint.getSignature().getName() + BRACES);
//		LOGGER.trace("Result : " + result);
//
//	}

//	@AfterThrowing(pointcut = "execution(* com.advanceweb.afc.jb.*.*(..))", throwing = "error")
	@AfterThrowing(pointcut="logController() || logDAO()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		LOGGER.trace(EXCEPTION_OCCURED
				+ joinPoint.getTarget().getClass().getName() + DOT
				+ joinPoint.getSignature().getName() + BRACES);
		LOGGER.trace(MESSAGE + error.getMessage());
	}

	/*
	 * @Around("execution(* com.advanceweb.afc.jb.employer.web.controller.*.*(..))"
	 * ) public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
	 * logger.trace("Entered Into "
	 * +joinPoint.getTarget().getClass().getName()+DOT
	 * +joinPoint.getSignature().getName()+BRACES); }
	 */

//	 @Pointcut("within(com.advanceweb.afc.jb..*) AND execution(* *(..))")
	@Pointcut("within(com.advanceweb.afc.jb.employer.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.jobseeker.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.agency.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.admin.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.pgi.web.controller.*)" )
	 protected void logController() {
		 
	 }
	 
	@Pointcut("within(com.advanceweb.afc.jb.employer.dao.*) || " +
			"within(com.advanceweb.afc.jb.jobseeker.dao.*) || " +
			"within(com.advanceweb.afc.jb.agency.dao.*) || " +
			"within(com.advanceweb.afc.jb.admin.dao.*) || " +
			"within(com.advanceweb.afc.jb.pgi.dao.*)" )
	 protected void logDAO() {
		 
	 }
	
}