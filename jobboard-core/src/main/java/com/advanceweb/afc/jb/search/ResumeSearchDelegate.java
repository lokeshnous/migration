package com.advanceweb.afc.jb.search;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface ResumeSearchDelegate {

	ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;
	
	/**
	 * This method is temporarily beig used to retrieving the 
	 * resume search result from DB.
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	
	List<ResumeDTO> resumeSearchFromDB(String searchString)
			throws JobBoardServiceException;

}
