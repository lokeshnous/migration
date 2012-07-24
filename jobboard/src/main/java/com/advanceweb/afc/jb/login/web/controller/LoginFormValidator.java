package com.advanceweb.afc.jb.login.web.controller;

import java.util.List;

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

	public boolean validateLoginValues(LoginForm form,
			LoginFormDTO loginFormDTOForUser) {

		// we need to first check for the role id of logged in user.
		// if value for role id stored in adm_user_role table is same as
		// property file value then validate for the email and password of user
		if (form != null && loginFormDTOForUser != null) {
			if (form.getRoleId() == loginFormDTOForUser.getRoleId()) { // change this 1 to DB value
				if ((form.getEmailAddress().equals(loginFormDTOForUser.getEmailAddress()))
						&& (form.getEmailAddress().equals(loginFormDTOForUser.getPassword()))) {
					return true;
				}
			}

		}
		//false means login failed
		return false;

	}

}
