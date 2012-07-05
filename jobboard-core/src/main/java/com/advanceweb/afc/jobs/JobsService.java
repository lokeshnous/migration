package com.advanceweb.afc.jobs;


import java.util.List;

import com.advanceweb.afc.common.JobsDTO;
import com.advanceweb.afc.common.email.MMEmail;
import com.advanceweb.afc.search.JobSearch;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:21:08 PM
 */
public class JobsService implements Jobs {

	private MMEmail mmMailService;
	private JobSearch jobSearchAdapter;
	public MMEmail m_MMEmail;
	public JobSearch m_JobSearch;

	public JobsService(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param jobsDTO
	 */
	public boolean createNewJob(JobsDTO jobsDTO){
		return false;
	}

	/**
	 * 
	 * @param jobApplicationDTO
	 */
	public boolean applyForJob(JobApplicationDTO jobApplicationDTO){
		return false;
	}

	public List<JobsDTO> retrieveJobPostings(){
		return null;
	}

}