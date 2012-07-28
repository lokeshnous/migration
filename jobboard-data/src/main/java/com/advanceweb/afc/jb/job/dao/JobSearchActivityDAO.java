package com.advanceweb.afc.jb.job.dao;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
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
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	AppliedJobDTO fetchSavedOrAppliedJob(SearchedJobDTO searchedJobDTO,
			int userId);

	/**
	 * create save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	boolean saveOrApplyJob(AppliedJobDTO jobDTO);

	/**
	 * update save or apply the job for logged in user
	 * 
	 * @param jobDTO
	 * @return
	 */
	boolean updateSaveOrApplyJob(AppliedJobDTO jobDTO);

	/**
	 * To Save the save job details to DB
	 * 
	 * @param searchedJobDTO
	 */
	void saveTheJob(SearchedJobDTO searchedJobDTO);

}
