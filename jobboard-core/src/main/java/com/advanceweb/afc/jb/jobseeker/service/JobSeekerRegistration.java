package com.advanceweb.afc.jb.jobseeker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("profileRegistration")
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
		try {
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
			return jobSeekerRegistrationDAO.createNewJobSeeker(jobSeekerRegistrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	@Override
	public boolean deleteProfile(int profileId) {
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
		return jobSeekerRegistrationDAO.updateJobSeekerDetails(jobSeekerRegistrationDTO);
	}

	/**
	 * 
	 * @param profileId
	 */
	@Override
	public ProfileDTO viewProfile(int jobseekerId) {
		return jobSeekerRegistrationDAO.getJobSeekerDetails(jobseekerId);
	}

	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
		return jobSeekerRegistrationDAO.jsChangePassword(jsRegistrationDTO);
	}	
	
	
}