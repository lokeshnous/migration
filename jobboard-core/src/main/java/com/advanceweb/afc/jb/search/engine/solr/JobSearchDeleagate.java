package com.advanceweb.afc.jb.search.engine.solr;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.JobSearchResultDTO;
import com.advanceweb.afc.jb.common.LocationDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
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
	JobSearchResultDTO jobSearch(String searchName, Map<String, String> paramMap, long start, long rows) throws JobBoardServiceException, JobBoardDataException;
	
	/**
	 * This method will do the location search for city and state
	 * to display in the auto complete box in the UI.
	 * @param keywords represents  search word for auto complete
	 * @return List<LocationDTO> which contains the city and state or postcode 
	 */
	
	List<LocationDTO> locationSearch(String keywords);
	
}
