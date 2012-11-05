package com.advanceweb.afc.jb.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;
import com.advanceweb.afc.jb.resume.dao.ResumeSearchDAO;
import com.advanceweb.afc.jb.search.ResumeSearchDelegate;
import com.advanceweb.afc.jb.search.ResumeSearchResultDTO;
import com.advanceweb.afc.jb.search.service.ResumeSearchService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

@Service("resumeSearchService")
public class ResumeSearchServiceImpl implements ResumeSearchService {

	@Autowired
	private ResumeSearchDelegate resumeSearchDelegate;
	
	@Autowired
	private ResumeSearchDAO resumeSearchDAO;
	
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
	public List<ResumeDTO> resumeSearchFromDB(String searchString, int offset, int noOfRecords)
			throws JobBoardServiceException {
		return resumeSearchDelegate.resumeSearchFromDB(searchString, offset,noOfRecords);
	}
	
	
	/**
	 * This method is called to fetch Saved Job Searches
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SaveSearchedJobsDTO> mySavedResumeSearches(int userId) {
		return resumeSearchDAO.mySavedResumeSearches(userId);
	}


	@Override
	public List<SaveSearchedJobsDTO> editSavedResumeSearch(int searchId) {
		return resumeSearchDAO.editSavedResumeSearch(searchId);
	}

	/**
	 * This method is called to delete a Saved Job Search
	 * 
	 * @param saveSearchId
	 * @return
	 */
	@Override
	public boolean deleteSavedResume(int saveSearchId) {
		return resumeSearchDAO.deleteSavedResume(saveSearchId);
	}

	

	@Override
	public boolean saveModifiedData(List<SaveSearchedJobsDTO> searchedJobsDTOs) {
		return resumeSearchDAO.saveModifiedData(searchedJobsDTOs);
	}


	@Override
	public boolean validateSearchName(String searchName, int userId) {
		return resumeSearchDAO.validateSearchName(searchName, userId);
	}


	@Override
	public List<SaveSearchedJobsDTO> viewMySavedSearches(int userId) {
		return resumeSearchDAO.viewMySavedSearches(userId);
	}

	@Override
	public boolean deleteFirstSearch(int userId) {
		return resumeSearchDAO.deleteFirstSearch(userId);
	}

	@Override
	public void saveSearchedResumes(SaveSearchedJobsDTO searchedJobsDTO) {
		resumeSearchDAO.saveSearchedResumes(searchedJobsDTO);
	}


	@Override
	public boolean updateSearchDetails(SaveSearchedJobsDTO searchedJobsDTO) {
		return resumeSearchDAO.updateSearchDetails(searchedJobsDTO);
	}
	
	/**
	 * This method is used to get thr total number of searched resume from DB.
	 * @return int
	 */
	public int getTotalNumberOfResume() {
		return resumeSearchDAO.getTotalNumberOfResume();
	}
}
