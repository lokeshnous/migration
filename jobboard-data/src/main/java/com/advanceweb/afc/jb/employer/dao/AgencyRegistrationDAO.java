/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface AgencyRegistrationDAO {

	/**
	 * 
	 * @param agency
	 */
	UserDTO createUser(AgencyProfileDTO agency);

	/**
	 * 
	 * @param agencyId
	 */
	boolean deleteAgency(long agencyId);

	/**
	 * 
	 * @param agencyId
	 */
	AgencyProfileDTO getAgencyDetails(long agencyId);

	/**
	 * 
	 * @param agency
	 */
	boolean updateAgencyDetails(AgencyProfileDTO agency);

	/**
	 * @return
	 */
	AgencyProfileDTO getProfileAttributes();

	/**
	 * Validate profile attributes.
	 *
	 * @param jobseekerId the jobseeker id
	 * @return true, if successful
	 */
	boolean validateProfileAttributes(int jobseekerId);		

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	boolean validateEmail(String email);

	

}