/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.schedulers.service;

/**
 * 
 */


/**
 * This interface defines the properties of a class that can be scheduled as a
 * job using any scheduler service.
 * 
 * @author muraliananthr
 * 
 */

public interface JobBoardSchedulerService {

	/**
	 * Any processes that will be scheduled should be configured in this method
	 */
	void execute();

}
