package com.advanceweb.afc.jb.jobseeker.service;

import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;

/**
 * <code> JobSeekerActivity </code> is a Service class.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 10 July 2012
 * 
 * 
 */
public interface JobSeekerSubscriptions {
	
	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */

	public boolean saveJobSeekerSubscription(List<JobSeekerSubscriptionsDTO> listSubsDTO, int userId);
	
	/**
	 * To get current subscriptions of the user
	 * @param userId
	 * @return
	 */
	public List<JobSeekerSubscriptionsDTO> getCurrentSubscriptions(int userId);

}