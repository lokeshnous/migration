package com.advanceweb.afc.jb.jobseeker.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.jobseeker.dao.JobSeekerRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("profileRegistration")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
public class JobSeekerRegistration implements ProfileRegistration {

	public JobSeekerProfileDTO jobSeekerProfileDTO;
	@Autowired
	public JobSeekerRegistrationDAO jobSeekerRegistrationDAO;
	public ResumeDTO resumeDTO;
	private static final Logger LOGGER = Logger.getLogger(JobSeekerRegistration.class);


	/**
	 * This method is called to save job seeker registration
	 * infromation into the DB.
	 * 
	 * @param profileDTO
	 */

	@Override
	public UserDTO createEmployer(ProfileDTO profileDTO) {
		try {
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
			return jobSeekerRegistrationDAO.createNewJobSeeker(jobSeekerRegistrationDTO);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	/**
	 * 
	 * @param profileId
	 */
	@Override
	public boolean deleteProfile(int profileId) {
		return false;
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

	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		JobSeekerRegistrationDTO jsRegistrationDTO = (JobSeekerRegistrationDTO) profileDTO;
		return jobSeekerRegistrationDAO.validatePassword(jsRegistrationDTO);
	}

	@Override
	public boolean validateEmail(String email) {
		return jobSeekerRegistrationDAO.validateEmail(email);
	}

	@Override
	public ProfileDTO getProfileAttributes() {
		
		return jobSeekerRegistrationDAO.getProfileAttributes();
	}	
	

	public boolean validateProfileAttributes(int jobseekerId){
		return jobSeekerRegistrationDAO.validateProfileAttributes(jobseekerId);
	}
	
}