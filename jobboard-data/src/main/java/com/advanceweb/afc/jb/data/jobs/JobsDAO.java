package com.advanceweb.afc.jb.data.jobs;


import com.advanceweb.afc.jb.data.domain.Job;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:25:40 PM
 */
public interface JobsDAO {

	public Job m_Job=null;

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