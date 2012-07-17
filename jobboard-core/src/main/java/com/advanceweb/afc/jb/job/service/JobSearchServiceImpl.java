package com.advanceweb.afc.jb.job.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.search.engine.solr.JobSearchDeleagate;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchResultDTO;
import org.springframework.stereotype.Service;

@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService{

	@Autowired
	private JobSearchDeleagate jobSearchDeleagate;
	
	@Override
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long rows, final long start) {
		JobSearchResultDTO jobSearchResultDTO = jobSearchDeleagate.jobSearch(searchName, paramMap, rows, start);
		return jobSearchResultDTO;
	}

}
