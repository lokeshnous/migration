package com.advanceweb.afc.jb.web.exception;

import com.advanceweb.afc.jb.exception.JobBoardException;

/**
 * JobBoarUIException represents all exception thrown in the job board data
 * layer.
 * 
 * @author sukeshnambiar
 * 
 */
public class JobBoardUIException extends JobBoardException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a JobBoardUIException with a message passed as the parameter. The
	 * message parameter will be set as the exception message.
	 * 
	 * @param message
	 *            This value will be set as the exception message.
	 */
	public JobBoardUIException(String message) {
		super(message);
	}

	/**
	 * Creates a JobBoardUIException object from an exiting exception object.
	 * 
	 * @param ex
	 *            The exception from which the new exceptio object is created.
	 */

	public JobBoardUIException(Exception ex) {
		super(ex);
	}

}
