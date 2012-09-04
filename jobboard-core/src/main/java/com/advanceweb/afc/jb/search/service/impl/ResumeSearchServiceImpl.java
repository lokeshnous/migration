package com.advanceweb.afc.jb.search.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.search.ResumeSearchDelegate;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Service("resumeSearchService")
public class ResumeSearchServiceImpl implements ResumeSearchService {

	@Autowired
	private ResumeSearchDelegate resumeSearchDelegate;
	
	@Override
	public ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
