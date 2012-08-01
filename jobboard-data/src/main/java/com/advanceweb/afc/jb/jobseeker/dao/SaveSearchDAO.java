package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;

public interface SaveSearchDAO {

	// To Save the searched job details 
	void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO );
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);
	List<SaveSearchedJobsDTO> viewMySavedSearchRecord(int userId, String searchName);
	boolean deleteSavedSearch(int saveSearchId);
	AdmSaveSearch editSavedSearch(int saveSearchId);
}
