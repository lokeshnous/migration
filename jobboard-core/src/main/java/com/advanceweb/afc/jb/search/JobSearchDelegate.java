/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

import java.util.Map;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface JobSearchDelegate {

	/**
	 * Does the Job Search
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
	 * @return JobSearchResultDTO
	 */
	JobSearchResultDTO jobSearch(Map<String, String> paramMap, long start,
			long rows) throws JobBoardServiceException;

}
