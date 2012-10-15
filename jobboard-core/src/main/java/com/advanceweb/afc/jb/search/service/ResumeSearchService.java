package com.advanceweb.afc.jb.search.service;

import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

public interface ResumeSearchService {
	/**
	 * This method is used to do the Job Search by taking the following
	 * parameters.
	 * 
	 * @param searchName
	 *            represents the type of the job search
	 * @param paramMap
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 */

	ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException;

	/**
	 * This method is used to get the resume search list from DB.
	 * @param searchName
	 * @param paramMap
	 * @param start
	 * @param rows
	 * @return List<ResumeDTO>
	 * @throws JobBoardServiceException
	 */

	public List<ResumeDTO> resumeSearchFromDB(String searchString)
			throws JobBoardServiceException;
	
}
