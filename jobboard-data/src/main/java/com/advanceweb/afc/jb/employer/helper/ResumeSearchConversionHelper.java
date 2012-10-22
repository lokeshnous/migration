package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;

/**
 * @author muralikc
 *
 */
@Repository("resumeSearchConversionHelper")
public class ResumeSearchConversionHelper {
	/**
	 * This method converts from entity to DTO class
	 * 
	 * @param admSaveSearchList
	 * @return
	 */
	public List<SaveSearchedJobsDTO> transformAdmSaveSearchToDTO(
			List<AdmSaveSearch> admSaveSearchList) {
		List<SaveSearchedJobsDTO> searchedJobsDTOList = new ArrayList<SaveSearchedJobsDTO>();
		for (AdmSaveSearch admSaveSearch : admSaveSearchList) {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setSaveSearchID(admSaveSearch.getSaveSearchId());
			saveSearchedJobsDTO.setUrl(admSaveSearch.getUrl());
			saveSearchedJobsDTO.setSearchName(admSaveSearch.getSearchName());
			saveSearchedJobsDTO.setKeywords(admSaveSearch.getSearchName());
			saveSearchedJobsDTO.setEmailFrequency(admSaveSearch.getEmailFrequency());
			saveSearchedJobsDTO.setCreatedDate(admSaveSearch.getCreateDt());
			
			if(admSaveSearch.getModifyDt() != null){
				saveSearchedJobsDTO.setModifyDate(CommonUtil.convertSQLDateToStdDateString(admSaveSearch.getModifyDt().toString()));
			}
			saveSearchedJobsDTO.setDeletedDate(admSaveSearch.getDeleteDt());		
			searchedJobsDTOList.add(saveSearchedJobsDTO);
		}
		return searchedJobsDTOList;
	}
	
	/**
	 * This method is called to convert saveSearchedJobsDTO to Save Search
	 * Entity
	 * 
	 * @param searchedJobsDTO
	 * @return JpSaveSearch
	 */
	public AdmSaveSearch transformSaveSearch(
			SaveSearchedJobsDTO searchedJobsDTO) {
		AdmSaveSearch admSaveSearch = new AdmSaveSearch(); 
		admSaveSearch.setUserId(searchedJobsDTO.getUserID());
		admSaveSearch.setUrl(searchedJobsDTO.getUrl());
		admSaveSearch.setSearchName(searchedJobsDTO.getSearchName());
		admSaveSearch.setCreateDt(searchedJobsDTO.getCreatedDate());
		return admSaveSearch;
	}

}
