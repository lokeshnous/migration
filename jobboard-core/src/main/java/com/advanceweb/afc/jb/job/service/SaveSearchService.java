package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public interface SaveSearchService {

	/**
	 * This method is called to save the find job searches
	 * 
	 * @param userId
	 * @return
	 */
	void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO);

	/**
	 * This method is called to display Saved Searches
	 * 
	 * @param userId
	 * @return
	 */
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);

	/**
	 * This method is called to view the particular Saved Searches
	 * 
	 * @param userId
	 * @return
	 */
	List<SaveSearchedJobsDTO> viewMySavedSearchRecord(int userId,
			String searchName);

	/**
	 * This method is called to edit the Saved Searches
	 * 
	 * @param searchId
	 * @return
	 */
	List<SaveSearchedJobsDTO> editSavedSearch(int searchId);

	/**
	 * This Method saves modified notify me data to the adm_save_search table
	 * 
	 * @param searchedJobsDTOs
	 * @return
	 */
	boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs);

	/**
	 * To check whether search name is already exist or not
	 * 
	 * @param searchName
	 * @return
	 */
	boolean validateSearchName(String searchName, int userId);

	/**
	 * This method is called to delete the Saved Searches
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteSavedSearch(int saveSearchId);

	/**
	 * This method is called to delete the first search if user is trying to
	 * create more than 5th search
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteFirstSearch(int userId);

	/**
	 * This method is used to update the saved search details.
	 * 
	 * @param SaveSearchedJobsDTO
	 * @return boolean
	 */

	boolean updateSearchDetails(SaveSearchedJobsDTO saveSearchedJobsDTO);

}
