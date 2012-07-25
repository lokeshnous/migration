package com.advanceweb.afc.jb.login.service;

import com.advanceweb.afc.jb.common.LoginFormDTO;


/**
 * This Interface defines the required methods for login form
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginFormService {

	LoginFormDTO validateLoginFormValues(String emailAddress,
			String password);
}
