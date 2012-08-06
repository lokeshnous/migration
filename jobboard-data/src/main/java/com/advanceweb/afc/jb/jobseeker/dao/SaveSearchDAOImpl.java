package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.SaveSearchResults;
import com.advanceweb.afc.jb.jobseeker.helper.SaveSearchConversionHelper;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("saveSearchDAO")
public class SaveSearchDAOImpl implements SaveSearchDAO {

	@Autowired
	private SaveSearchConversionHelper saveSearchConversionHelper;

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	// To Save the save searched job details to DB
	public void saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		SaveSearchResults searchResults = saveSearchConversionHelper
				.transformSaveSearch(saveSearchedJobsDTO);
		hibernateTemplate.saveOrUpdate(searchResults);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		List<SaveSearchResults> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId = " + userId);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(searchResults);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearchRecord(int userId,
			String searchName) {
		List<SaveSearchResults> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userID = " + userId
						+ " and e.searchName = " + searchName);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(searchResults);
	}

	/**
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param jpSaveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedSearch(int saveSearchId) {
		SaveSearchResults searchResults = new SaveSearchResults();
		searchResults.setSaveSearchId(saveSearchId);
		hibernateTemplate.delete(searchResults);
		return true;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return jpSaveSearch
	 */
	@Override
	public SaveSearchResults editSavedSearch(int saveSearchId) {
		SaveSearchResults searchResults = hibernateTemplate.get(
				SaveSearchResults.class, saveSearchId);
		return searchResults;
	}

	/**
	 * This Method saves modified notify me data to the adm_save_search table
	 * 
	 * @param searchedJobsDTOs
	 * @return
	 */
	public boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs) {
		SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();
		for (int i = 0; i < searchedJobsDTOs.size(); i++) {
			searchedJobsDTO = (SaveSearchedJobsDTO) searchedJobsDTOs.get(i);
			SaveSearchResults searchResults = (SaveSearchResults) hibernateTemplate
					.load(SaveSearchResults.class,
							searchedJobsDTO.getSaveSearchID());
			searchResults.setEmailFrequency(searchedJobsDTO.getEmailFrequency());
			hibernateTemplate.update(searchResults);
		}
		return true;
	}

}
