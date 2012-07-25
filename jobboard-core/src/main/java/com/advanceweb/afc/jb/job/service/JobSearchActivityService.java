package com.advanceweb.afc.jb.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SaveOrApplyJobDTO;
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
	 * view searched job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public SearchedJobDTO viewJobDetails(long jobId) {

		return jobSearchActivityDAO.viewJobDetails(jobId);

	}

	/**
	 * save or apply the job for logged in user
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void saveOrApplyJob(SaveOrApplyJobDTO jobDTO) {
		jobSearchActivityDAO.saveOrApplyJob(jobDTO);
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
