package com.advanceweb.afc.jb.login.service;

import java.util.List;

import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.data.entities.AdmUserRole;
import com.advanceweb.afc.jb.data.entities.MerUser;


/**
 * This Interface defines the required methods for login form
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginService {
    
	/**
	 * This Method is to get the userId and roleId based on
	 * user email and password
	 * @param emailAddress
	 * @param password
	 * @return
	 */
	LoginFormDTO validateLoginFormValues(String emailAddress,
			String password);
	
	/**
	 * This method is to get the user email details
	 * @param emailAddress
	 */
	LoginFormDTO getUserEmailDetails(String emailAddress);
	
	MerUser getUser(String email);
	List<AdmUserRole> getUserRole(int userId);
}
