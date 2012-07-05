package com.advanceweb.afc.data.domain;

import com.advanceweb.afc.data.registration.JobSeekerRegistrationDAO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:20 PM
 */
public class JobSeeker {

	public JobSeekerRegistrationDAO jobSeekerRegistrationDAO;

	public JobSeeker() {

	}

	@Override
	public void finalize() throws Throwable {

	}

	public JobSeekerRegistrationDAO getJobSeekerRegistrationDAO() {
		return jobSeekerRegistrationDAO;
	}

	public void setJobSeekerRegistrationDAO(
			JobSeekerRegistrationDAO jobSeekerRegistrationDAO) {
		this.jobSeekerRegistrationDAO = jobSeekerRegistrationDAO;
	}

}