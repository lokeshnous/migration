/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.dao;

import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface JobSeekerRegistrationDAO {

	/**
	 * 
	 * @param jobSeeker
	 */
	UserDTO createNewJobSeeker(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO);

	/**
	 * 
	 * @param jobSeekerId
	 */
	boolean deleteJobSeeker(int jobSeekerId);

	/**
	 * 
	 * @param jobSeekerId
	 */
	JobSeekerRegistrationDTO getJobSeekerDetails(int jobSeekerId);

	/**
	 * 
	 * @param jobSeeker
	 */
	boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);
	
	/**
	 * To change password
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	boolean jsChangePassword(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);
	
	/**
	 * Validate password.
	 *
	 * @param jobSeekerRegistrationDTO the job seeker registration dto
	 * @return true, if successful
	 */
	boolean validatePassword(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);
	
	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	boolean validateEmail(String email);
	
	/**
	 * To change password
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	JobSeekerRegistrationDTO getProfileAttributes();
	
	/**
	 * Validate profile attributes.
	 *
	 * @param jobseekerId the jobseeker id
	 * @return true, if successful
	 */
	boolean validateProfileAttributes(int jobseekerId);

}