/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	 * Delete the subscription of user by user Id
	 * 
	 * @param userId
	 * @return
	 */
	void deleteSubscriptionsById(int userId);

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
	 * Gets the sub emailer list.
	 *
	 * @return the sub emailer list
	 */
	List<DropDownDTO> getSubEmailerList();

	/**
	 * Method to save the selected facility subscriptions to the DB
	 * 
	 * @param listSubsDTO
	 * @param facilityId
	 * @return
	 */
	boolean saveFacilitySubscription(List<UserSubscriptionsDTO> listSubsDTO,
			int facilityId);

	/**
	 * Gets the subscriptionscheck.
	 *
	 * @param userId the user id
	 * @return the subscriptionscheck
	 */
	List<DropDownDTO> getSubscriptionscheck(int userId);

	/**
	 * Gets the subscriptionsdigital.
	 *
	 * @param userId the user id
	 * @return the subscriptionsdigital
	 */
	List<DropDownDTO> getSubscriptionsdigital(int userId);

	/**
	 * Gets the subscriptionsletter.
	 *
	 * @param userId the user id
	 * @return the subscriptionsletter
	 */
	List<DropDownDTO> getSubscriptionsletter(int userId);
	
	/**
	 * Gets the subscriptions emailer.
	 *
	 * @param userId the user id
	 * @return the subscriptions emailer
	 */
	List<DropDownDTO> getSubscriptionsEmailer(int userId);

	/**
	 * Get the subscription list which selected during registration for logged
	 * in user
	 * 
	 * @param userId
	 * @return
	 */
	List<UserSubscriptionsDTO> getSelectedSub(int userId);

	/**
	 * Gets the parent id.
	 *
	 * @param facilityId the facility id
	 * @return the parent id
	 */
	int getParentId(int facilityId);

	/**
	 * This method is to get the publications based on the profession
	 * 
	 * @param professionId
	 * @return
	 */
	List<List<DropDownDTO>> getPublications(int professionId);

}