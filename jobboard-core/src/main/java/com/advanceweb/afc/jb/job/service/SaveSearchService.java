package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.SaveSearchResults;

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
	 * This method is called to delete the Saved Searches
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteSavedSearch(int saveSearchId);

	/**
	 * This method is called to edit the Saved Searches
	 * 
	 * @param userId
	 * @return
	 */
	SaveSearchResults editSavedSearch(int saveSearchId);

	/**
	 * This Method saves modified notify me data to the adm_save_search table
	 * 
	 * @param searchedJobsDTOs
	 * @return
	 */
	boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs);

}
