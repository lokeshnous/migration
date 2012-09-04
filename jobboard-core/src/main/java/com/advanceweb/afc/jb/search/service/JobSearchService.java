package com.advanceweb.afc.jb.search.service;

import java.util.Map;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
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
	 * @param searchName
	 *            represents the type of the job search
	 * @param paramMap
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 */

	JobSearchResultDTO jobSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;

	/**
	 * get job details by job ID
	 * 
	 * @param jobId
	 * @return
	 */
	SearchedJobDTO viewJobDetails(long jobId);

	/**
	 * validating job for save/apply.
	 * 
	 * @param searchedJobDTO
	 * @param userId
	 * @return <true> if job is saved/Applied <false> not saved/Applied
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
	 * saves the job for logged in user
	 * 
	 * @param searchedJobDTO
	 */
	void saveJob(SearchedJobDTO searchedJobDTO);

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	JobApplyTypeDTO applyJobDetails(int jobId);
}
