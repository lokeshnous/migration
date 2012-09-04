package com.advanceweb.afc.jb.job.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.mail.service.MMEmail;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:21:08 PM
 */
@Service
@SuppressWarnings("unused")
public class JobService implements Job {

	private MMEmail mmMailService;
	private JobSearch jobSearchAdapter;

	// Commented to fix PMD issue
	/*@Override
	public void finalize() throws Throwable {

	}*/

	/**
	 * 
	 * @param jobsDTO
	 */
	public boolean createNewJob(JobDTO jobsDTO){
		return false;
	}

	/**
	 * 
	 * @param jobApplicationDTO
	 */
	public boolean applyForJob(JobApplicationDTO jobApplicationDTO){
		return false;
	}

	public List<JobDTO> retrieveJobPostings(){
		return null;
	}

}