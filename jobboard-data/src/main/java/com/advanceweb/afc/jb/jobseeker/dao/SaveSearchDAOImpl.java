package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.ArrayList;
import java.util.Date;
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
	public SaveSearchedJobsDTO saveSearchedJObs(SaveSearchedJobsDTO saveSearchedJobsDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		AdmSaveSearch searchResults = saveSearchConversionHelper
				.transformSaveSearch(saveSearchedJobsDTO);
		
		hibernateTemplate.save(searchResults);
		
		List<AdmSaveSearch> admSaveSearchs = new ArrayList<AdmSaveSearch>();
		admSaveSearchs.add(searchResults);
		
		List<SaveSearchedJobsDTO> searchedJobsDTOs = saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(admSaveSearchs);
		 return searchedJobsDTOs.get(0);
	}

	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId,
			boolean isRecentSearch) {

		String searchName = " e.searchName <>' ' ";
		if (isRecentSearch) {
			searchName = " e.searchName = '' ";
		}
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId=? and "
						+ searchName
						+ " and e.searchName is not null and e.createDt is not NULL and e.deleteDt is NULL order by e.createDt desc",
						userId);
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
		AdmSaveSearch search = hibernateTemplate.load(AdmSaveSearch.class,
				saveSearchId);
		search.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(search);
		return true;
	}

	/**
	 * This method is called to edit a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> editSavedSearch(int saveSearchId) {
		List<AdmSaveSearch> searchResults = hibernateTemplate.find(
				"from AdmSaveSearch where saveSearchId=? ", saveSearchId);
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(searchResults);
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
			AdmSaveSearch searchResults = (AdmSaveSearch) hibernateTemplate
					.load(AdmSaveSearch.class,
							searchedJobsDTO.getSaveSearchID());
			searchResults
					.setEmailFrequency(searchedJobsDTO.getEmailFrequency());
			hibernateTemplate.update(searchResults);
		}
		return true;
	}

	/**
	 * To check whether search name is already exist or not
	 * 
	 * @param searchName
	 * @return
	 */
	public boolean validateSearchName(String searchName, int userId) {
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch where searchName=? and userId=? and delete_dt is  NULL",
						searchName, userId);
		if (searchResults.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * Added to delete the first saved search from the DB and allow the user to
	 * create new search
	 * 
	 * @param userId
	 * @return
	 */
	public boolean deleteFirstSearch(int userId) {
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId=? and e.searchName <>' ' and e.createDt is not NULL and e.deleteDt is NULL",
						userId);
		AdmSaveSearch admSaveSearch = searchResults.get(0);
		AdmSaveSearch search = hibernateTemplate.load(AdmSaveSearch.class,
				admSaveSearch.getSaveSearchId());
		search.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(search);
		return true;
	}

	/**
	 * This method is used to update the saved search details.
	 * 
	 * @param SaveSearchedJobsDTO
	 * @return boolean
	 */

	public boolean updateSearchDetails(SaveSearchedJobsDTO saveSearchedJobsDTO) {

		AdmSaveSearch search = hibernateTemplate.load(AdmSaveSearch.class,
				saveSearchedJobsDTO.getSaveSearchID());
		search.setUrl(saveSearchedJobsDTO.getUrl());
		search.setModifyDt(new Date());
		hibernateTemplate.merge(search);
		return true;
	}

	@Override
	public void updateSearchName(int id, String searchName) {
		// TODO Auto-generated method stub
		AdmSaveSearch search = hibernateTemplate.load(AdmSaveSearch.class, id);
		search.setSearchName(searchName);

		hibernateTemplate.saveOrUpdate(search);
	}
	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> getsavedSearches() {
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where  e.searchName <>' ' and  e.userId > 0 and e.emailFrequency <>'0' and e.createDt is not NULL and e.deleteDt is NULL order by createDt desc");
		return saveSearchConversionHelper
				.transformJpSaveSearchToSaveSearchedJobsDTO(searchResults);
	}
}
