package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.UserSubscriptionsDTO;

/**
 * <code> JobSeekerSubscriptionService </code> is a Service class.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 10 July 2012
 * 
 * 
 */
public interface UserSubscriptionService {

	/**
	 * 
	 * @param jobSeekerSubscriptionsDTO
	 * @return
	 */

	boolean saveJobSeekerSubscription(List<UserSubscriptionsDTO> listSubsDTO,
			int userId);

	/**
	 * To get current subscriptions of the user
	 * 
	 * @param userId
	 * @return
	 */
	List<UserSubscriptionsDTO> getCurrentSubscriptions(int userId);

}