package com.advanceweb.afc.jb.jobsearch;

import com.advanceweb.afc.jb.common.SearchedJobDTO;

/**
 * <code> JobSearchActivity </code> is a Service class. 
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 *  
 */
public interface JobSearchActivity {

	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	SearchedJobDTO viewJobDetails(long jobId);
	
	/**
	 * apply job by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	void applyJob(long jobId);

}