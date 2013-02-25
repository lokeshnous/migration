/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common.schedulers.jobs;


/**
 * @author muraliananthr
 * 
 */
public interface JobWorker {

	/**
	 * Execute job.
	 */
	void executeJob();
	
	/**
	 * @return
	 */
	String getJobName();

}
