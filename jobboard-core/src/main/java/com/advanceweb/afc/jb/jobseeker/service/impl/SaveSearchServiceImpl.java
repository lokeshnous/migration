/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
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

	/** The save search dao. */
	@Autowired
	private SaveSearchDAO saveSearchDAO;

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.SaveSearchService#saveSearchedJobs(com.advanceweb.afc.jb.common.SaveSearchedJobsDTO)
	 */
	public SaveSearchedJobsDTO saveSearchedJobs(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		return saveSearchDAO.saveSearchedJObs(saveSearchedJobsDTO);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId, boolean isRecentSearch) {
		return saveSearchDAO.viewMySavedSearches(userId, isRecentSearch);
	}

	/**
	 * This method is called to fetch the saved search by search Id 
	 * 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> getSavedSearch(int saveSearchId) {
		return saveSearchDAO.getSavedSearch(saveSearchId);
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
	 * To check whether search name is already exist or not
	 * 
	 * @param searchName
	 * @return
	 */
	@Override
	public boolean validateSearchName(String searchName, int userId) {
		return saveSearchDAO.validateSearchName(searchName, userId);
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
	 * Added to delete the first saved search from the DB and allow the user to
	 * create new search
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteFirstSearch(int userId) {
		return saveSearchDAO.deleteFirstSearch(userId);
	}

	/**
	 * This method is used to update the saved search details.
	 * 
	 * @param SaveSearchedJobsDTO
	 * @return boolean
	 */
	public boolean updateSearchDetails(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		return saveSearchDAO.updateSearchDetails(saveSearchedJobsDTO);
	}

	/**
	 * This method is used to update the search name.
	 * 
	 * @param integer userId
	 * @param String  searchName
	 * @return 
	 */
	
	@Override
	public void updateSearchName(int id, String searchName) {
		// TODO Auto-generated method stub
		saveSearchDAO.updateSearchName(id, searchName);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.SaveSearchService#getsavedSearches()
	 */
	@Override
	public List<SaveSearchedJobsDTO> getsavedSearches() {
		return saveSearchDAO.getsavedSearches();
	}

	/**
	 * This method is used to save the recent search in user dash board.
	 */
	@Override
	public void saveRecentSearch(int saveSearchId, String saveSearchName) {
		saveSearchDAO.saveRecentSearch(saveSearchId, saveSearchName);
	}
}
	
