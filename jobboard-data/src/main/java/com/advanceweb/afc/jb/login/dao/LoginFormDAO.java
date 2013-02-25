/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.dao;

import com.advanceweb.afc.jb.common.LoginDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginFormDAO {
	/**
	 * This method to validate the userId and roleID of logged in user
	 * 
	 * @param emailAddress
	 * @param password
	 * @return
	 */
	LoginDTO validateLoginFormValues(String emailAddress, String password);

	/**
	 * This method to get the user details
	 * 
	 * @param emailAddress
	 * @return
	 */
	LoginDTO getUserEmailDetails(String emailAddress);

	/**
	 * This method is to get active job Posting
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getactivejobposting(int facilityId);
}
