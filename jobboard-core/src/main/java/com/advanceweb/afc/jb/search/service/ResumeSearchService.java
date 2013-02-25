/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search.service;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface ResumeSearchService {
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

	ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;

	/**
	 * This method is used to get the resume search list from DB.
	 * @param searchName
	 * @param paramMap
	 * @param start
	 * @param rows
	 * @return List<ResumeDTO>
	 * @throws JobBoardServiceException
	 */

	public List<ResumeDTO> resumeSearchFromDB(String searchString, int offset, int noOfRecords)
			throws JobBoardServiceException;
	
	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	List<SaveSearchedJobsDTO> mySavedResumeSearches(int userId);

	/**
	 * This method is called to edit the Saved Searches
	 * 
	 * @param searchId
	 * @return
	 */
	List<SaveSearchedJobsDTO> editSavedResumeSearch(int searchId);

	/**
	 * @param saveSearchId
	 * @return
	 */
	boolean deleteSavedResume(int saveSearchId);

	/**
	 * @param searchedJobsDTOs
	 * @return
	 */
	boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs);

	/**
	 * @param searchName
	 * @param userId
	 * @return
	 */
	boolean validateSearchName(String searchName, int userId);

	/**
	 * @param userId
	 * @return
	 */
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);

	/**
	 * @param userId
	 * @return 
	 */
	boolean deleteFirstSearch(int userId);

	/**
	 * @param searchedJobsDTO
	 */
	void saveSearchedResumes(SaveSearchedJobsDTO searchedJobsDTO);

	/**
	 * @param searchedJobsDTO
	 * @return
	 */
	boolean updateSearchDetails(SaveSearchedJobsDTO searchedJobsDTO);
	
	/**
	 * This method is used to get thr total number of searched resume from DB.
	 * @return int
	 */
	int getTotalNumberOfResume();
}
