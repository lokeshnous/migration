package com.advanceweb.afc.jb.jobseeker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;
import com.advanceweb.afc.jb.job.service.SaveSearchService;
import com.advanceweb.afc.jb.jobseeker.dao.SaveSearchDAO;

/**
 * Method to save the searched jobs
 * 
 * @author bharatiu
 * @Version 1.0
 * @Since 10th July, 2012
 */

@Service("saveSearchService")
public class SaveSearchServiceImpl implements SaveSearchService {

	@Autowired
	private SaveSearchDAO saveSearchDAO;

	public void saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		saveSearchDAO.saveSearchedJObs(saveSearchedJobsDTO);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		return saveSearchDAO.viewMySavedSearches(userId);
	}

	/**
	 * This method is called to fetch selected save serach record
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearchRecord(int userId,
			String searchName) {
		return saveSearchDAO.viewMySavedSearchRecord(userId, searchName);
	}

	/**
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedSearch(int saveSearchId) {

		return saveSearchDAO.deleteSavedSearch(saveSearchId);
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public AdmSaveSearch editSavedSearch(int saveSearchId) {

		return saveSearchDAO.editSavedSearch(saveSearchId);
	}

	/**
	 * This Method saves modified notify me data to the adm_save_search table
	 * 
	 * @param searchedJobsDTOs
	 * @return
	 */
	@Override
	public boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs) {
		return saveSearchDAO.saveModifiedData(searchedJobsDTOs);
	}

	/**
	 * Added to delete the first saved search from the DB and allow the user to
	 * create new search
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteFirstSearch(int userId) {
		return saveSearchDAO.deleteFirstSearch(userId);
	}
}
