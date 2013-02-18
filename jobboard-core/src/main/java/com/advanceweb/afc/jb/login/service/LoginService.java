package com.advanceweb.afc.jb.login.service;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This Interface defines the required methods for login form
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginService {

	/**
	 * This Method is to get the userId and roleId based on user email and
	 * password
	 * 
	 * @param emailAddress
	 * @param password
	 * @return
	 */
	LoginDTO validateLoginFormValues(String emailAddress, String password);

	/**
	 * This method is to get the user email details
	 * 
	 * @param emailAddress
	 */
	LoginDTO getUserEmailDetails(String emailAddress);

	/**
	 * This method is to get active job Posting
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getactivejobposting(int facilityId) throws JobBoardServiceException;

}
