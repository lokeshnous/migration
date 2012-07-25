package com.advanceweb.afc.jb.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.login.dao.LoginFormDAO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Service("loginFormService")
public class LoginFormServiceImpl implements LoginFormService {

	@Autowired
	private LoginFormDAO loginFormDAO;

	/**
	 * This method is called to fetch logged in user details
	 * 
	 * @param loginFormDTO
	 * @return
	 */
	@Override
	public LoginFormDTO validateLoginFormValues(String emailAddress,
			String password) {
		return loginFormDAO.validateLoginFormValues(emailAddress, password);
	}
}
