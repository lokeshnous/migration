package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;
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
		AdmSaveSearch jpSaveSearch = saveSearchConversionHelper
				.transformSaveSearch(saveSearchedJobsDTO);
		hibernateTemplate.saveOrUpdate(jpSaveSearch);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		List<AdmSaveSearch> admSaveSearchList = hibernateTemplate
				.find("from AdmSaveSearch where userID = " + userId);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(admSaveSearchList);
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
		List<AdmSaveSearch> admSaveSearchList = hibernateTemplate
				.find("from AdmSaveSearch e where e.userID = " + userId
						+ " and e.searchName = " + searchName);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(admSaveSearchList);
	}

	/**
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param jpSaveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedSearch(int saveSearchId) {
		AdmSaveSearch admSaveSearch = new AdmSaveSearch();
		admSaveSearch.setSaveSearchId(saveSearchId);
		hibernateTemplate.delete(admSaveSearch);
		return true;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return jpSaveSearch
	 */
	@Override
	public AdmSaveSearch editSavedSearch(int saveSearchId) {
		AdmSaveSearch jpSaveSearch = hibernateTemplate.get(AdmSaveSearch.class,
				saveSearchId);
		return jpSaveSearch;
	}

}
