package com.advanceweb.afc.jb.common.schedulers.jobs;


/**
 * @author muraliananthr
 * 
 */
public interface JobWorker {

	/**
	 * Execute job.
	 */
	public void executeJob();
	
	/**
	 * @return
	 */
	public String getJobName();

}
