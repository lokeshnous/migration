package com.advanceweb.afc.jb.jobsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.jobsearch.JobSearchActivityDAO;

/**
 * <code> JobSearchActivityService </code> is a implementation for Service class. 
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 *  
 */
@Service("articleSearchService")
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
	public void applyJob(long jobId) {		
		jobSearchActivityDAO.applyJob(jobId);
		
	}

	public JobSearchActivityDAO getJobSearchActivityDAO() {
		return jobSearchActivityDAO;
	}
	
	public void setJobSearchActivityDAO(JobSearchActivityDAO jobSearchActivityDAO) {
		this.jobSearchActivityDAO = jobSearchActivityDAO;
	}

}
