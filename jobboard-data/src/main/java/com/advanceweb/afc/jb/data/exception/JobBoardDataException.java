package com.advanceweb.afc.jb.data.exception;

import com.advanceweb.afc.jb.exception.JobBoardException;

/**
 * JobBoarDataException represents all exception thrown in the job board data
 * layer.
 * 
 * @author sukeshnambiar
 * 
 */
public class JobBoardDataException extends JobBoardException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a JobBoardDataException with a message passed as the parameter.
	 * The message parameter will be set as the exception message.
	 * 
	 * @param message
	 *            This value will be set as the exception message.
	 */
	public JobBoardDataException(String message) {
		super(message);
	}

	/**
	 * Creates a JobBoardDataException object from an exiting exception object.
	 * 
	 * @param ex
	 *            The exception from which the new exceptio object is created.
	 */

	public JobBoardDataException(Exception exception) {
		super(exception);
	}

}
