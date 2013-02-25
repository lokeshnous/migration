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

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface SearchParamBuilder {

	/**
	 * Builds the params.
	 *
	 * @param searchParams the search params
	 * @param inputParams the input params
	 * @return the list
	 * @throws JobBoardServiceException the job board service exception
	 */
	List<SearchParamDTO> buildParams(List<SearchParamDTO> searchParams,
			Map<String, String> inputParams) throws JobBoardServiceException;
}
