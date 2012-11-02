package com.advanceweb.afc.jb.resume.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.employer.helper.ResumeSearchConversionHelper;

/**
 * This class has been created as a service interface for getting the resume 
 * list from the DB.
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 15th October 2012
 */

@Repository("resumeSearchDAO")
public class ResumeSearchDAOImpl implements ResumeSearchDAO{
	
	private static final Logger LOGGER = Logger.getLogger("ResumeSearchDAOImpl.class");
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private ResumeSearchConversionHelper resSearchConversionHelper;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	/**
	 * This method is used to get the resume details from the DB
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	public List<ResumeDTO> getResumeSearchDetails(String searchString){
			
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
		@SuppressWarnings("unchecked")
		List<ResBuilderResume> resBuilderResumeList = hibernateTemplate.find("select rbr from ResBuilderResume rbr, ResBuilderEmployment rbe where " +
				" rbr.builderResumeId=rbe.resBuilderResume.builderResumeId and rbr.resUploadResumeId in ( select rur.uploadResumeId from ResUploadResume rur, "+
				" ResResumeProfile rrp where rur.uploadResumeId=rrp.resumeId and rrp.attribValue like '%"+searchString+"%' and rrp.resResumeAttrib= "+
				" (select resumeAttribId from ResResumeAttrib where name='JobTitle'))");
		
		for(ResBuilderResume obj: resBuilderResumeList){
			ResumeDTO resumeDTO = new ResumeDTO();
			if(obj.getResPublishResume() != null){
				resumeDTO.setPublishResumeId(obj.getResPublishResume().getPublishResumeId());
			}
			resumeDTO.setUploadResumeId(obj.getResUploadResumeId());
			resumeDTO.setResumeName(obj.getResumeName());
			resumeDTO.setFullName(obj.getFirstName()+" "+ obj.getLastName());
			resumeDTO.setCity(obj.getCity());
			resumeDTO.setState(obj.getState());
			resumeDTO.setExperience(obj.getResBuilderEmployments().get(0).getEmploymentYears());
			resumeDTO.setEmploymentType(obj.getResBuilderEmployments().get(0).getEmploymentType());
			resumeDTO.setPostDt(obj.getCreateDt());
			resumeDTOList.add(resumeDTO);
		}
		LOGGER.info("Size of resume list = "+resumeDTOList.size());
		return resumeDTOList;
	}
	
	@Override
	public List<SaveSearchedJobsDTO> mySavedResumeSearches(int userId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId=? and e.createDt is not NULL and e.deleteDt is NULL",
						userId);
		return resSearchConversionHelper
				.transformAdmSaveSearchToDTO(searchResults);
	}

	@Override
	public List<SaveSearchedJobsDTO> editSavedResumeSearch(int searchId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchResults = hibernateTemplate.find(
				"from AdmSaveSearch where saveSearchId=? ", searchId);
		return resSearchConversionHelper
				.transformAdmSaveSearchToDTO(searchResults);
	}

	@Override
	public boolean deleteSavedResume(int saveSearchId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> search = hibernateTemplate.find("from AdmSaveSearch where saveSearchId = ?",
				saveSearchId);
		AdmSaveSearch list = search.get(0);
		list.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(list);
		return true;
	}


	@Override
	public boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs) {
		SaveSearchedJobsDTO searchedJobsDTO = new SaveSearchedJobsDTO();
		for (int i = 0; i < searchedJobsDTOs.size(); i++) {
			searchedJobsDTO = (SaveSearchedJobsDTO) searchedJobsDTOs.get(i);
			@SuppressWarnings("unchecked")
			List<AdmSaveSearch> searchResults = hibernateTemplate
					.find("from AdmSaveSearch where saveSearchId=?",
							searchedJobsDTO.getSaveSearchID());
			AdmSaveSearch saveSearch = searchResults.get(0);
			saveSearch.setEmailFrequency(searchedJobsDTO.getEmailFrequency());
			hibernateTemplate.update(saveSearch);
		}
		return true;
	}


	@Override
	public boolean validateSearchName(String searchName, int userId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch where searchName=? and userId=? and delete_dt is  NULL",
						searchName, userId);
		if (searchResults.isEmpty()) {
			return false;
		}
		return true;
	}


	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId=? and e.createDt is not NULL and e.deleteDt is NULL",
						userId);
		return resSearchConversionHelper
				.transformAdmSaveSearchToDTO(searchResults);
	}


	@Override
	public boolean deleteFirstSearch(int userId) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchResults = hibernateTemplate
				.find("from AdmSaveSearch e where e.userId=? and e.createDt is not NULL and e.deleteDt is NULL",
						userId);
		AdmSaveSearch admSaveSearch = searchResults.get(0);
		admSaveSearch.setDeleteDt(new Date());
		hibernateTemplate.saveOrUpdate(admSaveSearch);
		return true;
	}


	@Override
	public void saveSearchedResumes(SaveSearchedJobsDTO searchedJobsDTO) {
		// Transforming the saveSearchedJobsDTO to Save Search Entity
		AdmSaveSearch searchResults = resSearchConversionHelper
				.transformSaveSearch(searchedJobsDTO);
		hibernateTemplate.saveOrUpdate(searchResults);
	}


	@Override
	public boolean updateSearchDetails(SaveSearchedJobsDTO searchedJobsDTO) {
		@SuppressWarnings("unchecked")
		List<AdmSaveSearch> searchList = hibernateTemplate.find("from AdmSaveSearch where saveSearchId = ?",
				searchedJobsDTO.getSaveSearchID());
		AdmSaveSearch search = searchList.get(0);
		search.setUrl(searchedJobsDTO.getUrl());
		search.setModifyDt(new Date());
		search.setSearchName(searchedJobsDTO.getSearchName());
		search.setUserId(searchedJobsDTO.getUserID());
		search.setSaveSearchId(searchedJobsDTO.getSaveSearchID());
		hibernateTemplate.saveOrUpdate(search);
		return true;
	}
}
