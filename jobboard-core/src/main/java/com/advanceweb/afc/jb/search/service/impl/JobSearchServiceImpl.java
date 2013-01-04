package com.advanceweb.afc.jb.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.job.dao.JobSearchDAO;
import com.advanceweb.afc.jb.search.JobSearchDelegate;
import com.advanceweb.afc.jb.search.JobSearchResultDTO;
import com.advanceweb.afc.jb.search.service.JobSearchService;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class JobSearchServiceImpl implements JobSearchService {

	@Autowired
	private JobSearchDelegate jobSearchDelegate;

	@Autowired
	private JobSearchDAO jobSearchDAO;

	/**
	 * This method is used to do the Job Search by taking the following
	 * parameters.
	 * 
	 * @param paramMap
	 *            contains the input parameters from the UI
	 * @param rows
	 *            represents how many rows will be displayed
	 * @param start
	 *            represents the starting point of the search
	 * @return JobSearchResultDTO
	 */
	public JobSearchResultDTO jobSearch(
			final Map<String, String> paramMap, final long start,
			final long rows) throws JobBoardServiceException {
		return jobSearchDelegate.jobSearch(paramMap, start, rows);
	}

	/**
	 * validating job for save/apply.
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AppliedJobDTO fetchSavedOrAppliedJob(JobDTO jobDTO,
			int userId) {

		return jobSearchDAO.fetchSavedOrAppliedJob(jobDTO, userId);

	}

	/**
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public JobDTO viewJobDetails(long jobId) {

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
	 * @param jobDTO
	 */
	@Override
	public void saveJob(JobDTO jobDTO) {
		jobSearchDAO.saveTheJob(jobDTO);
	}

	/**
	 * Fetch the apply type of job
	 * 
	 * @param jobId
	 */
	@Override
	public JobApplyTypeDTO applyJobDetails(int jobId) {
		return jobSearchDAO.applyJobDetails(jobId);
	}

	/**
	 * This method is used to get the total number of Active jobs.
	 * 
	 * @return long
	 */

	public long getTotalActiveJobs() {
		return jobSearchDAO.getTotalActiveJobs();
	}

	/**
	 * This method will fetch the last five job details based on posted date for
	 * the selected employer.
	 * 
	 * @param jobId
	 * @return List<JobPostDTO> object
	 */

	public List<JobPostDTO> getRecentJobsPostedByEmployer(long facilityID,
			long jobID) {
		return jobSearchDAO.getRecentJobsPostedByEmployer(facilityID, jobID);
	}

	/**
	 * This method is used to clear the recent searches of user
	 */
	@Override
	public void clearRecentSearches(int userId, int recentSearchsLimit) {
		jobSearchDAO.clearRecentSearches(userId, recentSearchsLimit);
	}

	@Override
	public void inserSessinfo(String session_id, int userId) {
		// TODO Auto-generated method stub
		jobSearchDAO.inserSessinfo(session_id, userId);
	}
	
	

	
	
	//here implementation work after descsion
	/*@Override
	public List<VstSessioninfo> getSessionId(String newSession_id) {
		// TODO Auto-generated method stub
		return jobSearchDAO.getSessionId(newSession_id);
	}

	@Override
	public void insertSessionId(Integer sessioninfo_id) {
		// TODO Auto-generated method stub
		jobSearchDAO.insertSessionId(sessioninfo_id);
	}
*/
	
	
	

}
