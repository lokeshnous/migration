package com.advanceweb.afc.jb.jobseeker.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.JobSeekerSubscriptionsDTO;

/**
 * Created JobSeekerActivity DAO
 * 
 * @author sharadk
 * 
 */
public interface JobSeekerSubscriptionsDAO {
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
