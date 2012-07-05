package com.advanceweb.afc.data.registration;

import com.advanceweb.afc.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.data.domain.JobSeeker;

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
	public boolean deleteJobSeeker(long jobSeekerId);

	/**
	 * 
	 * @param jobSeekerId
	 */
	public JobSeeker getJobSeekerDetails(long jobSeekerId);

	/**
	 * 
	 * @param jobSeeker
	 */
	public boolean updateJobSeekerDetails(JobSeeker jobSeeker);

}