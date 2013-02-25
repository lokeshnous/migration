/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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

	/** The mm mail service. */
	private MMEmail mmMailService;
	
	/** The job search adapter. */
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

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.Job#retrieveJobPostings()
	 */
	public List<JobDTO> retrieveJobPostings(){
		return null;
	}

}