package com.advanceweb.afc.jb.search.service;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * <code> JobSearchService </code> is a Service class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
public interface JobSearchService {

	/**
	 * This method is used to do the Job Search by taking the following
	 * parameters.
	 * 
	 * @param paramMap
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 */
	
	JobSearchResultDTO jobSearch(Map<String, String> paramMap, long start, long rows)
					throws JobBoardServiceException;
	
	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	JobDTO viewJobDetails(long jobId);

	/**
	 * validating job for save/apply.
	 * 
	 * @param jobDTO
	 * @param userId
	 * @return <true> if job is saved/Applied <false> not saved/Applied
	 */
	AppliedJobDTO fetchSavedOrAppliedJob(JobDTO jobDTO,
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
	 * saves the job for logged in user
	 * 
	 * @param jobDTO
	 */
	void saveJob(JobDTO jobDTO);

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
	 * @param jobId
	 * @return List<JobPostDTO> object
	 */

	List<JobPostDTO> getRecentJobsPostedByEmployer(long facilityID, long jobID);

	/**
	 * This method is used to remove the data in database
	 * 
	 * @return
	 */
	 public void removeClearAll(int userId);
	 /**
		 * This method is used to check existing session in application
		 * 
		 * @return
		 */
	 public void inserSessinfo(String session_id,int userId);
	
	 //here implementation work after descsion
	/* public List<VstSessioninfo> getSessionId(String newSession_id);
	 public void insertSessionId(Integer sessioninfo_id);*/
	 
	 
}
