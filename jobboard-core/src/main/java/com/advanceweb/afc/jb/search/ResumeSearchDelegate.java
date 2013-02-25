/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface ResumeSearchDelegate {

	/**
	 * Resume search.
	 *
	 * @param searchName the search name
	 * @param paramMap the param map
	 * @param start the start
	 * @param rows the rows
	 * @return the resume search result dto
	 * @throws JobBoardServiceException the job board service exception
	 */
	ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;
	
	/**
	 * This method is temporarily beig used to retrieving the 
	 * resume search result from DB.
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	
	List<ResumeDTO> resumeSearchFromDB(String searchString, int offset, int noOfRecords)
			throws JobBoardServiceException;

}
