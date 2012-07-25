package com.advanceweb.afc.jb.job.dao;

import com.advanceweb.afc.jb.common.SaveOrApplyJobDTO;
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
	 * save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	void saveOrApplyJob(SaveOrApplyJobDTO jobDTO);

	/**
	 * To Save the save job details to DB
	 * 
	 * @param searchedJobDTO
	 */
	void saveTheJob(SearchedJobDTO searchedJobDTO);

}
