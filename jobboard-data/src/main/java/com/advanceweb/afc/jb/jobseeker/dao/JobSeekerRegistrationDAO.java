package com.advanceweb.afc.jb.jobseeker.dao;

import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.data.domain.JobSeeker;

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
	public boolean createNewJobSeeker(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO);

	/**
	 * 
	 * @param jobSeekerId
	 */
	public boolean deleteJobSeeker(int jobSeekerId);

	/**
	 * 
	 * @param jobSeekerId
	 */
	public JobSeekerRegistrationDTO getJobSeekerDetails(int jobSeekerId);

	/**
	 * 
	 * @param jobSeeker
	 */
	public boolean updateJobSeekerDetails(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);
	
	/**
	 * To change password
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public boolean jsChangePassword(JobSeekerRegistrationDTO jobSeekerRegistrationDTO);

}