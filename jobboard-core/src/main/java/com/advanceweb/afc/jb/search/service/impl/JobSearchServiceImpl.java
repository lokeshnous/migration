package com.advanceweb.afc.jb.search.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.engine.solr.JobSearchDelegate;
import com.advanceweb.afc.jb.search.service.JobSearchService;
import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.job.dao.JobSearchDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * <code> JobSearchServiceImpl </code> is a implementation for Service class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Service("jobSearchService")
public class JobSearchServiceImpl implements JobSearchService {

	@Autowired
	private JobSearchDelegate jobSearchDelegate;

	@Autowired
	private JobSearchDAO jobSearchDAO;

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
	public JobSearchResultDTO jobSearch(final String searchName,
			final Map<String, String> paramMap, final long start,
			final long rows) throws JobBoardServiceException {
		return jobSearchDelegate.jobSearch(searchName, paramMap, start, rows);
	}

	/**
	 * validating job for save/apply.
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AppliedJobDTO fetchSavedOrAppliedJob(SearchedJobDTO searchedJobDTO,
			int userId) {

		return jobSearchDAO.fetchSavedOrAppliedJob(searchedJobDTO, userId);

	}

	/**
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public SearchedJobDTO viewJobDetails(long jobId) {

		return jobSearchDAO.viewJobDetails(jobId);

	}

	/**
	 * create save or apply the job for logged in user
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean saveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		status = jobSearchDAO.saveOrApplyJob(jobDTO);
		return status;
	}

	/**
	 * update save or apply the job for logged in user
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateSaveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		status = jobSearchDAO.updateSaveOrApplyJob(jobDTO);
		return status;
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param searchedJobDTO
	 */
	@Override
	public void saveJob(SearchedJobDTO searchedJobDTO) {
		jobSearchDAO.saveTheJob(searchedJobDTO);
	}

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	@Override
	public JobApplyTypeDTO applyJobDetails(int jobId) {
		JobApplyTypeDTO jobApplyTypeDTO = jobSearchDAO.applyJobDetails(jobId);
		return jobApplyTypeDTO;
	}

}
