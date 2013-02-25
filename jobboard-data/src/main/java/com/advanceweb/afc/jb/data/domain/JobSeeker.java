/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.domain;

import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:20 PM
 */
public class JobSeeker {

	/** The job seeker registration dao. */
	public JobSeekerRegistrationDAO jobSeekerRegistrationDAO;

	/**
	 * Gets the job seeker registration dao.
	 *
	 * @return the job seeker registration dao
	 */
	public JobSeekerRegistrationDAO getJobSeekerRegistrationDAO() {
		return jobSeekerRegistrationDAO;
	}

	/**
	 * Sets the job seeker registration dao.
	 *
	 * @param jobSeekerRegistrationDAO the new job seeker registration dao
	 */
	public void setJobSeekerRegistrationDAO(
			JobSeekerRegistrationDAO jobSeekerRegistrationDAO) {
		this.jobSeekerRegistrationDAO = jobSeekerRegistrationDAO;
	}

}