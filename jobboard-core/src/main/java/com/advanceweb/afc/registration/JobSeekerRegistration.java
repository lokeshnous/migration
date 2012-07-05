package com.advanceweb.afc.registration;

import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.common.JobSeekerProfileDTO;
import com.advanceweb.afc.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.common.ProfileDTO;
import com.advanceweb.afc.common.ResumeDTO;
import com.advanceweb.afc.data.registration.JobSeekerRegistrationDAO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class JobSeekerRegistration implements ProfileRegistration {

	public JobSeekerProfileDTO jobSeekerProfileDTO;
	@Autowired
	public JobSeekerRegistrationDAO jobSeekerRegistrationDAO;
	public ResumeDTO resumeDTO;

	public JobSeekerRegistration() {

	}

	/**
	 * This method is called to save job seeker registration
	 * infromation into the DB.
	 * 
	 * @param profileDTO
	 */

	@Override
	public boolean createNewProfile(ProfileDTO profileDTO) {
		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
		jobSeekerRegistrationDAO.createNewJobSeeker(jobSeekerRegistrationDTO);
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	@Override
	public boolean deleteProfile(long profileId) {
		return false;
	}

	@Override
	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param profileDTO
	 */
	@Override
	public boolean modifyProfile(ProfileDTO profileDTO) {
		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
		long jobseekerId = 0;
		jobSeekerRegistrationDAO.getJobSeekerDetails(jobseekerId);
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	@Override
	public ProfileDTO viewProfile(long profileId) {
		return null;
	}

}