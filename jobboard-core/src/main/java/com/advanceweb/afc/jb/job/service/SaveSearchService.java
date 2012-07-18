package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public interface SaveSearchService {
	
	void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO);
	List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);
	boolean deleteSavedSearch(int saveSearchId);
	JpSaveSearch editSavedSearch(int saveSearchId);

}
