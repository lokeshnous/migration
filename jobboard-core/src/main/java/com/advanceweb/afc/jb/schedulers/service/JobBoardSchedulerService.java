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
