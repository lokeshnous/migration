package com.advanceweb.afc.jb.search.engine.solr;

import java.util.Map;

import org.apache.log4j.Logger;

import com.advanceweb.afc.jb.search.ResumeSearchDelegate;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.SearchIndex;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public class SolrResumeSearchDelegate extends AbstractSolrSearchDelegate
		implements ResumeSearchDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(SolrResumeSearchDelegate.class);

	public SolrResumeSearchDelegate(SearchIndex searchIndex) {
		super(searchIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
