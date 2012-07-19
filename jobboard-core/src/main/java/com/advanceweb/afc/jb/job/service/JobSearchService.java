package com.advanceweb.afc.jb.job.service;

import java.util.Map;

import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;

public interface JobSearchService{
	/**
	 * Does the Job Search
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
	 * @return JobSearchResultDTO
	 */
	
	JobSearchResultDTO jobSearch(String searchName, Map<String, String> paramMap, long start, long rows);
	

}
