/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

/**
 * This class has been created as a service interface for getting the resume 
 * list from the DB.
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 15th October 2012
 */

public interface ResumeSearchDAO {


	/**
	 * This method is used to get the resume details from the DB
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	List<ResumeDTO> getResumeSearchDetails(String searchString, int offset, int noOfRecords);
	
	/**
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
