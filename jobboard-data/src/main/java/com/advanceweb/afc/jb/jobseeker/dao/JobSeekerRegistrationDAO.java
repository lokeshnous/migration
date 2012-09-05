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
	
	boolean validatePassword(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);
	
	boolean validateEmail(String email);
	
	/**
	 * To change password
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	JobSeekerRegistrationDTO getProfileAttributes();
	
	boolean validateProfileAttributes(int jobseekerId);

}