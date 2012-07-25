package com.advanceweb.afc.jb.login.dao;

import com.advanceweb.afc.jb.common.LoginFormDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginFormDAO {
	/**
	 * This method to validate the userId and roleID of logged in user
	 * @param emailAddress
	 * @param password
	 * @return
	 */
	LoginFormDTO validateLoginFormValues(String emailAddress,
			String password);
	
	/**
	 * This method to get the user details
	 * @param emailAddress
	 * @return
	 */
	LoginFormDTO getUserEmailDetails(String emailAddress);
}
