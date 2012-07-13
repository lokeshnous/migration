package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

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
	public JpSaveSearch transformSaveSearch(
			SaveSearchedJobsDTO saveSearchedJobsDTO) {
		JpSaveSearch jpSaveSearch = new JpSaveSearch();
		jpSaveSearch.setLoginID(saveSearchedJobsDTO.getLoginID());
		jpSaveSearch.setUrl(saveSearchedJobsDTO.getUrl());
		jpSaveSearch.setUrlName(saveSearchedJobsDTO.getUrlName());
		jpSaveSearch.setCreateDate(saveSearchedJobsDTO.getCreatedDate());
		return jpSaveSearch;
	}

	
	public List<SaveSearchedJobsDTO> transformJpSaveSearchToSaveSearchedJobsDTO(
			List<JpSaveSearch> jpSaveSearchList) {
		List<SaveSearchedJobsDTO> saveSearchedJobsDTOList = new ArrayList<SaveSearchedJobsDTO>();
		for (JpSaveSearch jpSaveSearch : jpSaveSearchList) {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setJpSaveSearchId(jpSaveSearch
					.getJpSaveSearchId());
			saveSearchedJobsDTO.setUrl(jpSaveSearch.getUrl());
			saveSearchedJobsDTO.setUrlName(jpSaveSearch.getUrlName());
			saveSearchedJobsDTO.setModifyDate(jpSaveSearch.getModifyDate());
			saveSearchedJobsDTO.setEmailFrequency(jpSaveSearch
					.getEmailFrequency());
			saveSearchedJobsDTOList.add(saveSearchedJobsDTO);
		}

		return saveSearchedJobsDTOList;
	}

}
