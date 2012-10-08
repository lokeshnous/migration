package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;

/**
 * <code> JobSearchDAO </code> is a DAO.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
public interface JobSearchDAO {

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

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	JobApplyTypeDTO applyJobDetails(int jobId);

	/**
	 * This method is used to get the total number of Active jobs.
	 * 
	 * @return long
	 */
	long getTotalActiveJobs();

	/**
	 * This method will fetch the last five job details based on posted date for
	 * the selected employer.
	 * 
	 * @param
	 * @param jobId
	 * @return List<JobPostDTO> object
	 */

	List<JobPostDTO> getRecentJobsPostedByEmployer(long facilityID, long jobID);

	/**
	 * This method is used to get the browse jobs by title
	 * 
	 * @return List<SearchedJobDTO> object
	 */
	List<SearchedJobDTO> getJobsByTitle();

	/**
	 * This method is used to get the browse jobs list by Employer
	 * 
	 * @return
	 */
	List<SearchedJobDTO> getJobsByEmployer();

	/**
	 * This method is used to get the browse jobs list by location
	 * 
	 * @return
	 */
	List<SearchedJobDTO> getJobsByLocation();
	
	

}
