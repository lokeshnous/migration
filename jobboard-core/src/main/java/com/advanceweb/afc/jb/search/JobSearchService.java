package com.advanceweb.afc.jb.search;

import java.util.Map;

import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface JobSearchService{
	
	/**
	 * This method is used to do the Job Search by taking the following parameters.
	 * @param searchName	represents the type of the job search 
	 * @param paramMap		contains the input parameters from the UI
	 * @param rows			represents how many rows will be displayed
	 * @param start			represents the starting point of the search
	 * @return JobSearchResultDTO
	 */
	
	JobSearchResultDTO jobSearch(String searchName, Map<String, String> paramMap, long start, long rows) throws JobBoardServiceException, JobBoardDataException;
	

}
