package com.advanceweb.afc.jb.jobs;


import java.util.List;

import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobsDTO;
import com.advanceweb.afc.jb.common.email.MMEmail;
import com.advanceweb.afc.jb.search.JobSearch;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:21:08 PM
 */
@Service
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