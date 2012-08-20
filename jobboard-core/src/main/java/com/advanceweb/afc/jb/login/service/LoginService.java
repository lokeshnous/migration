package com.advanceweb.afc.jb.login.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdminUserRoleDTO;
import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

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
	LoginFormDTO validateLoginFormValues(String emailAddress, String password);

	/**
	 * This method is to get the user email details
	 * 
	 * @param emailAddress
	 */
	LoginFormDTO getUserEmailDetails(String emailAddress);

	MerUserDTO getUser(String email);

	List<AdminUserRoleDTO> getUserRole(int userId);
}
