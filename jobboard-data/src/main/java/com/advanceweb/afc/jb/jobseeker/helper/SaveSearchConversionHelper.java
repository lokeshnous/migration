package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.AdmSaveSearch;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */
@Repository("saveSearchConversionHelper")
public class SaveSearchConversionHelper {

	/**
	 * This method is called to convert saveSearchedJobsDTO to Save Search
	 * Entity
	 * 
	 * @param saveSearchedJobsDTO
	 * @return JpSaveSearch
	 */
	public AdmSaveSearch transformSaveSearch(
			SaveSearchedJobsDTO saveSearchedJobsDTO) {
		AdmSaveSearch admSaveSearch = new AdmSaveSearch(); 
		admSaveSearch.setUserID(saveSearchedJobsDTO.getUserID());
		admSaveSearch.setUrl(saveSearchedJobsDTO.getUrl());
		admSaveSearch.setSearchName(saveSearchedJobsDTO.getSearchName());
		admSaveSearch.setCreateDate(saveSearchedJobsDTO.getCreatedDate());
		return admSaveSearch;
	}

	/**
	 * This method converts from entity to DTO class
	 * 
	 * @param admSaveSearchList
	 * @return
	 */
	public List<SaveSearchedJobsDTO> transformJpSaveSearchToSaveSearchedJobsDTO(
			List<AdmSaveSearch> admSaveSearchList) {
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = new ArrayList<SaveSearchedJobsDTO>();
		for (AdmSaveSearch admSaveSearch : admSaveSearchList) {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setSaveSearchID(admSaveSearch.getSaveSearchId());
			saveSearchedJobsDTO.setUrl(admSaveSearch.getUrl());
			saveSearchedJobsDTO.setSearchName(admSaveSearch.getSearchName());
			saveSearchedJobsDTO.setEmailFrequency(admSaveSearch.getEmailFrequency());
			saveSearchedJobsDTO.setCreatedDate(admSaveSearch.getCreateDate());
			saveSearchedJobsDTO.setModifyDate(DateUtils.convertSQLDateToStdDate(admSaveSearch.getModifyDate().toString()));
			saveSearchedJobsDTO.setDeletedDate(admSaveSearch.getDeletedDate());		
			saveSearchedJobsDTOList.add(saveSearchedJobsDTO);
		}
		return saveSearchedJobsDTOList;
	}

}
