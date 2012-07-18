package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

public interface SaveSearchDAO {

	// To Save the searched job details 
	void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO );
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);
	boolean deleteSavedSearch(int saveSearchId);
	JpSaveSearch editSavedSearch(int saveSearchId);
}
