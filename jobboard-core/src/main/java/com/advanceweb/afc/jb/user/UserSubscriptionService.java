package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
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

	/**
	 * To get current subscription List for Facility
	 * 
	 * @param userId
	 * @return
	 */
	List<UserSubscriptionsDTO> getCurrentFacilitySub(int facilityId);

	/**
	 * Method to get digital subscription list
	 * 
	 * @return
	 */
	List<UserSubscriptionsDTO> getDigitalSubList();

	/**
	 * Method to get e-newsLetter subscription list
	 * 
	 * @return
	 */
	List<UserSubscriptionsDTO> getEnewsLetterSubList();

	/**
	 * Method to save the selected facility subscriptions to the DB
	 * 
	 * @param listSubsDTO
	 * @param facilityId
	 * @return
	 */
	boolean saveFacilitySubscription(List<UserSubscriptionsDTO> listSubsDTO,
			int facilityId);
	
	
	List<DropDownDTO> getSubscriptionscheck(int userId);
	List<DropDownDTO> getSubscriptionsdigital(int userId);
	
	List<DropDownDTO> getSubscriptionsletter(int userId);
}