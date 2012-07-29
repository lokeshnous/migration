package com.advanceweb.afc.jb.job.dao;


import com.advanceweb.afc.jb.data.domain.Job;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:40 PM
 */
public interface JobDAO {



	/**
	 * 
	 * @param job
	 */
	public boolean createNewJob(Job job);

	/**
	 * 
	 * @param jobId
	 */
	public boolean applyForJob(long jobId);

}