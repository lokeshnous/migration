package com.advanceweb.afc.jb.data.jobsearch;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
/**
 * <code> JobSearchActivityService </code> is a DAO. 
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 *  
 */
public interface JobSearchActivityDAO {

	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	SearchedJobDTO viewJobDetails(long jobId);
	
	/**
	 * apply for job by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	void applyJob(long jobId);

}
