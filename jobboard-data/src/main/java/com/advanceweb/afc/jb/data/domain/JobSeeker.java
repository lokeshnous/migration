package com.advanceweb.afc.jb.data.domain;

import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:20 PM
 */
public class JobSeeker {

	public JobSeekerRegistrationDAO jobSeekerRegistrationDAO;

	public JobSeekerRegistrationDAO getJobSeekerRegistrationDAO() {
		return jobSeekerRegistrationDAO;
	}

	public void setJobSeekerRegistrationDAO(
			JobSeekerRegistrationDAO jobSeekerRegistrationDAO) {
		this.jobSeekerRegistrationDAO = jobSeekerRegistrationDAO;
	}

}