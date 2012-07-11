package com.advanceweb.afc.jb.data.common.helpers;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.data.entities.JpSaveSearch;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public class SaveSearchConversionHelper {
	
	/**
	 * This method is called to convert saveSearchedJobsDTO to 
	 * Save Search Entity
	 * @param saveSearchedJobsDTO
	 * @return JpSaveSearch
	 */
	public JpSaveSearch transformSaveSearch(SaveSearchedJobsDTO saveSearchedJobsDTO){
		JpSaveSearch jpSaveSearch = new JpSaveSearch();
		jpSaveSearch.setLoginID(saveSearchedJobsDTO.getLoginID());
		jpSaveSearch.setUrl(saveSearchedJobsDTO.getUrl());
		jpSaveSearch.setUrlName(saveSearchedJobsDTO.getUrlName());
		jpSaveSearch.setCreateDate(saveSearchedJobsDTO.getCreatedDate());
		return jpSaveSearch;		
	}

}
