/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.service.exception;

import com.advanceweb.afc.jb.exception.JobBoardException;

/**
 * JobBoarServiceException represents all exception thrown in the job board data
 * layer.
 * 
 * @author sukeshnambiar
 * 
 */
public class JobBoardServiceException extends JobBoardException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a JobBoardServiceException with a message passed as the
	 * parameter. The message parameter will be set as the exception message.
	 * 
	 * @param message
	 *            This value will be set as the exception message.
	 */
	public JobBoardServiceException(String message) {
		super(message);
	}

	/**
	 * Creates a JobBoardServiceException object from an exiting exception
	 * object.
	 * 
	 * @param exce
	 *            The exception from which the new exception object is created.
	 */

	public JobBoardServiceException(Exception exce) {
		super(exce);
	}

}
