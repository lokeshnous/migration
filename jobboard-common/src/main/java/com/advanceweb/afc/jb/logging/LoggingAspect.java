/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);
	
	/** The Constant ENTERED. */
	private static final String ENTERED = "Entered Into ";
	
	/** The Constant EXCEPTION_OCCURED. */
	private static final String EXCEPTION_OCCURED = "An exception has been thrown in ";
	
	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Error Message is: ";
	
	/** The Constant DOT. */
	private static final String DOT = ".";
	
	/** The Constant BRACES. */
	private static final String BRACES = "()";

	/**
	 * Log before.
	 *
	 * @param joinPoint the join point
	 */
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
	/**
 * Log after throwing.
 *
 * @param joinPoint the join point
 * @param error the error
 */
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
	/**
	 * Log controller.
	 */
	@Pointcut("within(com.advanceweb.afc.jb.employer.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.jobseeker.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.agency.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.admin.web.controller.*) || " +
			"within(com.advanceweb.afc.jb.pgi.web.controller.*)" )
	 protected void logController() {
		 
	 }
	 
	/**
	 * Log dao.
	 */
	@Pointcut("within(com.advanceweb.afc.jb.employer.dao.*) || " +
			"within(com.advanceweb.afc.jb.jobseeker.dao.*) || " +
			"within(com.advanceweb.afc.jb.agency.dao.*) || " +
			"within(com.advanceweb.afc.jb.admin.dao.*) || " +
			"within(com.advanceweb.afc.jb.pgi.dao.*)" )
	 protected void logDAO() {
		 
	 }
	
}