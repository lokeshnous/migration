package com.advanceweb.afc.jb.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ApplyJobDTO;
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
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JobSearchActivityService implements JobSearchActivity {

	@Autowired
	public JobSearchActivityDAO jobSearchActivityDAO;

	JobSearchActivityService() {

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
	 * apply job
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void applyJob(ApplyJobDTO applyJobDTO) {
		jobSearchActivityDAO.applyJob(applyJobDTO);
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
