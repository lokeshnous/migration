package com.advanceweb.afc.jb.search.engine.solr;

import java.util.Map;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface JobSearchDeleagate {

	/**
	 * Does the Job Search
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
	 * @return JobSearchResultDTO
	 */
	JobSearchResultDTO jobSearch(String searchName, Map<String, String> paramMap, long start, long rows) throws JobBoardServiceException;
	
	
}
