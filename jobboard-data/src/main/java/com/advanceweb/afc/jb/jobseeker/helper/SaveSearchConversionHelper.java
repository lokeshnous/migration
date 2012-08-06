package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.SaveSearchResults;

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
	 * @param searchedJobsDTO
	 * @return JpSaveSearch
	 */
	public SaveSearchResults transformSaveSearch(
			SaveSearchedJobsDTO searchedJobsDTO) {
		SaveSearchResults admSaveSearch = new SaveSearchResults(); 
		admSaveSearch.setUserId(searchedJobsDTO.getUserID());
		admSaveSearch.setUrl(searchedJobsDTO.getUrl());
		admSaveSearch.setSearchName(searchedJobsDTO.getSearchName());
		admSaveSearch.setCreateDt(searchedJobsDTO.getCreatedDate());
		return admSaveSearch;
	}

	/**
	 * This method converts from entity to DTO class
	 * 
	 * @param admSaveSearchList
	 * @return
	 */
	public List<SaveSearchedJobsDTO> transformJpSaveSearchToSaveSearchedJobsDTO(
			List<SaveSearchResults> admSaveSearchList) {
		List<SaveSearchedJobsDTO> searchedJobsDTOList = new ArrayList<SaveSearchedJobsDTO>();
		for (SaveSearchResults admSaveSearch : admSaveSearchList) {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setSaveSearchID(admSaveSearch.getSaveSearchId());
			saveSearchedJobsDTO.setUrl(admSaveSearch.getUrl());
			saveSearchedJobsDTO.setSearchName(admSaveSearch.getSearchName());
			saveSearchedJobsDTO.setEmailFrequency(admSaveSearch.getEmailFrequency());
			saveSearchedJobsDTO.setCreatedDate(admSaveSearch.getCreateDt());
			saveSearchedJobsDTO.setModifyDate(DateUtils.convertSQLDateToStdDate(admSaveSearch.getModifyDt().toString()));
			saveSearchedJobsDTO.setDeletedDate(admSaveSearch.getDeleteDt());		
			searchedJobsDTOList.add(saveSearchedJobsDTO);
		}
		return searchedJobsDTOList;
	}

}
