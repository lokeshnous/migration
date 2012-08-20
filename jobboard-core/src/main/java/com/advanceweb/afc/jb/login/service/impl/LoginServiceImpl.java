package com.advanceweb.afc.jb.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.AdminUserRoleDTO;
import com.advanceweb.afc.jb.common.LoginFormDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.login.dao.LoginFormDAO;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginFormDAO loginFormDAO;

	@Autowired
	UserDao userDAO;

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

	/**
	 * @purpose This method is to get the user email details
	 * @param emailAddress
	 * @return
	 */
	@Override
	public LoginFormDTO getUserEmailDetails(String emailAddress) {
		return loginFormDAO.getUserEmailDetails(emailAddress);
	}

	@Override
	public MerUserDTO getUser(String email) {
		return userDAO.getUser(email);
	}

	@Override
	public List<AdminUserRoleDTO> getUserRole(int userId) {
		return userDAO.getUserRole(userId);
	}
}
