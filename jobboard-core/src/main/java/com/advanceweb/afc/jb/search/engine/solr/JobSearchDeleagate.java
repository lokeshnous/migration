package com.advanceweb.afc.jb.search.engine.solr;

import java.util.Map;

public interface JobSearchDeleagate {

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
