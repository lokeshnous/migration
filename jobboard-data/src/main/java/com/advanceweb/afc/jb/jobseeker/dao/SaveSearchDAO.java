/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public interface SaveSearchDAO {

	// To Save the searched job details
	/**
	 * Save searched j obs.
	 *
	 * @param saveSearchedJobsDTO the save searched jobs dto
	 * @return the save searched jobs dto
	 */
	SaveSearchedJobsDTO saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO);

	/**
	 * View my saved searches.
	 *
	 * @param userId the user id
	 * @param isRecentSearch the is recent search
	 * @return the list
	 */
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId, boolean isRecentSearch);

	/**
	 * Delete saved search.
	 *
	 * @param saveSearchId the save search id
	 * @return true, if successful
	 */
	boolean deleteSavedSearch(int saveSearchId);

	/**
	 * This method is called to fetch the saved search by search Id 
	 * 
	 * @param saveSearchId
	 * @return
	 */
	List<SaveSearchedJobsDTO> getSavedSearch(int saveSearchId);

	/**
	 * Save modified data.
	 *
	 * @param searchedJobsDTOs the searched jobs dt os
	 * @return true, if successful
	 */
	boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs);

	/**
	 * Validate search name.
	 *
	 * @param searchName the search name
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean validateSearchName(String searchName, int userId);

	/**
	 * Delete first search.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean deleteFirstSearch(int userId);

	/**
	 * This method is used to update the saved search details.
	 * 
	 * @param SaveSearchedJobsDTO
	 * @return boolean
	 */

	boolean updateSearchDetails(SaveSearchedJobsDTO saveSearchedJobsDTO);
	
	/**
	 * Update search name.
	 *
	 * @param id the id
	 * @param searchName the search name
	 */
	public void updateSearchName(int id, String searchName);

	/**
	 * Gets the saved searches.
	 *
	 * @return the saved searches
	 */
	List<SaveSearchedJobsDTO> getsavedSearches();

	/**
	 * This method is used to save the recent search in user dash board.
	 * 
	 * @param saveSearchId
	 * @param saveSearchName
	 */
	void saveRecentSearch(int saveSearchId, String saveSearchName);
}
