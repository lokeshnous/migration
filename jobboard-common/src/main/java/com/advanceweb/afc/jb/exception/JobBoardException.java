/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.exception;

/**
 * JobBoarException is the base class of all exception thrown in the job board
 * application. The individual modules are expected to extend this class and
 * create specific exceptions relevant to the module part.
 * 
 * At this point, there is no additional methods defined in the
 * JobBoardException.
 * 
 * @author sukeshnambiar
 * 
 */
public class JobBoardException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor with a message passed as the parameter. The message
	 * parameter will be set as the exception message.
	 * 
	 * @param message
	 *            This value will be set as the exception message.
	 */
	public JobBoardException(String message) {
		super(message);
	}

	/**
	 * Creates a JobBoardExcetpion object from an exiting exception object.
	 * 
	 * @param ex
	 *            The exception from which the new exceptio object is created.
	 */
	public JobBoardException(Exception exception) {
		super(exception);
	}
}
