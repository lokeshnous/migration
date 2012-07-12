package com.advanceweb.afc.jb.data.search;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

public interface SaveSearchDAO {

	// To Save the searched job details 
	public void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO );
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);
	public boolean deleteSavedSearch(int saveSearchId);
	public JpSaveSearch editSavedSearch(int saveSearchId);
}
