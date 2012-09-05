package com.advanceweb.afc.jb.search;

import java.util.Map;

import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface ResumeSearchDelegate {

	ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;

}
