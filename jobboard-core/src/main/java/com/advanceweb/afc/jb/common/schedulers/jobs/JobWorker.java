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
