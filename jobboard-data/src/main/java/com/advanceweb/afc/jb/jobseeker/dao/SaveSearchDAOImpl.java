package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;
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
		JpSaveSearch jpSaveSearch = saveSearchConversionHelper
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
		List<JpSaveSearch> jpSaveSearchList = hibernateTemplate
				.find("from JpSaveSearch where loginID = " + userId);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(jpSaveSearchList);
	}

	/**
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param jpSaveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedSearch(int jpSaveSearchId) {
		JpSaveSearch jpSaveSearch = new JpSaveSearch();
		jpSaveSearch.setJpSaveSearchId(jpSaveSearchId);
		hibernateTemplate.delete(jpSaveSearch);
		return true;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return jpSaveSearch
	 */
	@Override
	public JpSaveSearch editSavedSearch(int saveSearchId) {
		JpSaveSearch jpSaveSearch = hibernateTemplate.get(JpSaveSearch.class,
				saveSearchId);
		return jpSaveSearch;
	}

}
