package com.advanceweb.afc.jb.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.search.ResumeSearchDelegate;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Service("resumeSearchService")
public class ResumeSearchServiceImpl implements ResumeSearchService {

	@Autowired
	private ResumeSearchDelegate resumeSearchDelegate;
	
	/**
	 * 
	 */
	public ResumeSearchResultDTO resumeSearch(String searchName,
			Map<String, String> paramMap, long start, long rows)
			throws JobBoardServiceException {
		return resumeSearchDelegate.resumeSearch(searchName, paramMap, start, rows);
	}
	

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
			throws JobBoardServiceException {
		return resumeSearchDelegate.resumeSearchFromDB(searchString);
	}
	

}
