package com.advanceweb.afc.jb.search.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDeleagate;
import com.advanceweb.afc.jb.common.JobSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService{

	@Autowired
	private JobSearchDeleagate jobSearchDeleagate;
	
	/**
	 * This method is used to do the Job Search by taking the following parameters.
	 * @param searchName	represents the type of the job search 
	 * @param paramMap		contains the input parameters from the UI
	 * @param rows			represents how many rows will be displayed
	 * @param start			represents the starting point of the search
	 * @return JobSearchResultDTO
	 */
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start, final long rows) throws JobBoardServiceException, JobBoardDataException {
		return jobSearchDeleagate.jobSearch(searchName, paramMap, start, rows);
	}
	

}
