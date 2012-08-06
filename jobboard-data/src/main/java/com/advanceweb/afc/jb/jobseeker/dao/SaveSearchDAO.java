package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.SaveSearchResults;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public interface SaveSearchDAO {

	// To Save the searched job details
	void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO);

	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);

	List<SaveSearchedJobsDTO> viewMySavedSearchRecord(int userId,
			String searchName);

	boolean deleteSavedSearch(int saveSearchId);

	SaveSearchResults editSavedSearch(int saveSearchId);

	boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs);
}
