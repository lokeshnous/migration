package com.advanceweb.afc.jb.job.dao;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
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
	 * apply for job
	 * 
	 * @param applyJobDTO
	 * @return
	 */
	void applyJob(ApplyJobDTO applyJobDTO);

	/**
	 * To Save the save job details to DB
	 * 
	 * @param searchedJobDTO
	 */
	public void saveTheJob(SearchedJobDTO searchedJobDTO);

}
