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
	
	public void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO);
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId);
	public boolean deleteSavedSearch(int saveSearchId);
	public JpSaveSearch editSavedSearch(int saveSearchId);

}
