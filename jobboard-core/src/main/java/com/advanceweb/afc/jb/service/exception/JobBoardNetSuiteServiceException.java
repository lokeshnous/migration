/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.service.exception;


public class JobBoardNetSuiteServiceException extends JobBoardServiceException{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Creates a JobBoardNetSuiteServiceException with a message passed as the
	 * parameter. The message parameter will be set as the exception message.
	 * 
	 * @param message
	 *            This value will be set as the exception message.
	 */
	public JobBoardNetSuiteServiceException(String message) {
		super(message);
	}

	/**
	 * Creates a JobBoardNetSuiteServiceException object from an exiting exception
	 * object.
	 * 
	 * @param exce
	 *            The exception from which the new exception object is created.
	 */

	public JobBoardNetSuiteServiceException(Exception exce) {
		super(exce);
	}
	
}
