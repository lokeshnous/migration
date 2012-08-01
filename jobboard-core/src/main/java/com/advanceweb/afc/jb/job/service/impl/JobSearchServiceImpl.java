package com.advanceweb.afc.jb.job.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.search.JobSearchDeleagate;
import com.advanceweb.afc.jb.search.JobSearchService;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

import org.springframework.stereotype.Service;

@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService{

	@Autowired
	private JobSearchDeleagate jobSearchDeleagate;
	
	/**
	 * Does the Job Search
	 * @param searchName
	 * @param paramMap
	 * @param rows
	 * @param start
	 * @return JobSearchResultDTO
	 */
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start, final long rows) throws JobBoardServiceException, JobBoardDataException {
		JobSearchResultDTO jobSearchResultDTO = null;
		jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, start, rows);
		return jobSearchResultDTO;
	}

}
