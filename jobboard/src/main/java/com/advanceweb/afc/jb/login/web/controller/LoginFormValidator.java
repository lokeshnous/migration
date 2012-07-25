package com.advanceweb.afc.jb.login.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.LoginFormDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

@Repository("loginFormValidator")
public class LoginFormValidator {

	/**
	 * Validation for login form
	 * @param form
	 * @param loginFormDTOForUser
	 * @return
	 */
	public boolean validateLoginValues(LoginForm form,
			LoginFormDTO loginFormDTOForUser) {

		// we need to first check for the role id of logged in user.
		// if value for role id stored in adm_user_role table is same as
		// property file value then validate for the email and password of user
		if (form != null  && loginFormDTOForUser != null) {
			if (form.getRoleId() == loginFormDTOForUser.getRoleId()) { 
				if ((form.getEmailAddress().equals(loginFormDTOForUser.getEmailAddress()))
						&& (form.getPassword().equals(loginFormDTOForUser.getPassword()))) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Validation for forgot password
	 * @param form
	 * @param userDetailsLoginFormDTO
	 * @return
	 */
	public boolean validateEmailValues(LoginForm form,
			LoginFormDTO userDetailsLoginFormDTO) {

		if (form != null && userDetailsLoginFormDTO != null) {
			if (form.getEmailAddress().equals(userDetailsLoginFormDTO.getEmailAddress())) {
				return true;
			}
		}
		return false;

	}
}
