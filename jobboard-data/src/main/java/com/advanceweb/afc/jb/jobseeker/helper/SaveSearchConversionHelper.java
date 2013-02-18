package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
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

	/**
	 * This method converts from entity to DTO class
	 * 
	 * @param admSaveSearchList
	 * @return
	 */
	public List<SaveSearchedJobsDTO> transformJpSaveSearchToSaveSearchedJobsDTO(
			List<AdmSaveSearch> admSaveSearchList) {
		List<SaveSearchedJobsDTO> searchedJobsDTOList = new ArrayList<SaveSearchedJobsDTO>();
		for (AdmSaveSearch admSaveSearch : admSaveSearchList) {
			SaveSearchedJobsDTO saveSearchedJobsDTO = new SaveSearchedJobsDTO();
			saveSearchedJobsDTO.setSaveSearchID(admSaveSearch.getSaveSearchId());
			saveSearchedJobsDTO.setUrl(admSaveSearch.getUrl());
			saveSearchedJobsDTO.setSearchName(admSaveSearch.getSearchName());
			saveSearchedJobsDTO.setEmailFrequency(admSaveSearch.getEmailFrequency());
			saveSearchedJobsDTO.setUserID(admSaveSearch.getUserId());
			
			saveSearchedJobsDTO.setCreatedDate(admSaveSearch.getCreateDt());
			if(admSaveSearch.getModifyDt() != null){
				saveSearchedJobsDTO.setModifyDate(CommonUtil.convertSQLDateToStdDateString(admSaveSearch.getModifyDt().toString()));
			}else{
				saveSearchedJobsDTO.setModifyDate(CommonUtil.convertSQLDateToStdDateString(admSaveSearch.getCreateDt().toString()));
			}
			saveSearchedJobsDTO.setDeletedDate(admSaveSearch.getDeleteDt());	
			saveSearchedJobsDTO.setRecentURL(getSplitURL(admSaveSearch.getUrl()));
			searchedJobsDTOList.add(saveSearchedJobsDTO);
		}
		return searchedJobsDTOList;
	}
	
	private String getSplitURL(String urlData) {

		StringBuffer splitURL = new StringBuffer();
		StringTokenizer stringNew = new StringTokenizer(urlData, ";");

		while (stringNew.hasMoreElements()) {

			String stringObject = (String) stringNew.nextElement();
			StringTokenizer stringAlter = new StringTokenizer(stringObject, "=");
			String key = null, value = "";
			int count = 0;

			while (stringAlter.hasMoreElements()) {
				if (count == 0) {
					key = stringAlter.nextToken();
					count = 1;
				} else {
					value = stringAlter.nextToken().replaceFirst("basic", " ");
					count = 0;
				}
				if (!("radius").equals(key)) {
					splitURL.append(value + "     ");
				}
			}
		}
		return splitURL.toString();
	}

}
