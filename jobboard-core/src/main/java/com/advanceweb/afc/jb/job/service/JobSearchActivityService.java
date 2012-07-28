package com.advanceweb.afc.jb.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.job.dao.JobSearchActivityDAO;

/**
 * <code> JobSearchActivityService </code> is a implementation for Service
 * class.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Service("jobSearchActivity")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class JobSearchActivityService implements JobSearchActivity {

	@Autowired
	private JobSearchActivityDAO jobSearchActivityDAO;

	/**
	 * validating job for save/apply.
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AppliedJobDTO fetchSavedOrAppliedJob(SearchedJobDTO searchedJobDTO,
			int userId) {

		return jobSearchActivityDAO.fetchSavedOrAppliedJob(searchedJobDTO,
				userId);

	}

	/**
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public SearchedJobDTO viewJobDetails(long jobId) {

		return jobSearchActivityDAO.viewJobDetails(jobId);

	}

	/**
	 * create save or apply the job for logged in user
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean saveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		status = jobSearchActivityDAO.saveOrApplyJob(jobDTO);
		return status;
	}

	/**
	 * update save or apply the job for logged in user
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public boolean updateSaveOrApplyJob(AppliedJobDTO jobDTO) {
		boolean status = false;
		status = jobSearchActivityDAO.updateSaveOrApplyJob(jobDTO);
		return status;
	}

	/**
	 * It saves the job with the details of company name,jobTitle, CreatedDate
	 * 
	 * @param searchedJobDTO
	 */
	@Override
	public void saveJob(SearchedJobDTO searchedJobDTO) {
		jobSearchActivityDAO.saveTheJob(searchedJobDTO);
	}
}
