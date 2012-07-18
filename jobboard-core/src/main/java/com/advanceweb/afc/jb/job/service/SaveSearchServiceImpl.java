package com.advanceweb.afc.jb.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;
import com.advanceweb.afc.jb.jobseeker.dao.SaveSearchDAO;

/**
 * Method to save the searched jobs
 * 
 * @author bharatiu
 * @Version 1.0
 * @Since 10th July, 2012
 */
@Service("saveSearchService")
public class SaveSearchServiceImpl implements SaveSearchService{
	
	@Autowired
	private SaveSearchDAO saveSearchDAO;

	public void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO) {	
		saveSearchDAO.saveSearchedJObs(saveSearchedJobsDTO);
	}

	/**
	 * This method is called to fetch Saved Job Searches 
	 * @param userId
	 * @return
	 */
	@Override
	public  List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		return saveSearchDAO.viewMySavedSearches(userId);
	}

	/**
	 * This method is called to delete a Saved Job Search 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedSearch(int saveSearchId) {
		
		return saveSearchDAO.deleteSavedSearch(saveSearchId);
	}

	/**
	 * This method is called to edit a Saved Job Search 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public JpSaveSearch editSavedSearch(int saveSearchId) {
		
		return saveSearchDAO.editSavedSearch(saveSearchId);
	}
}
