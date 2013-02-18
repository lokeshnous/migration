package com.advanceweb.afc.jb.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.login.dao.LoginFormDAO;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

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

	/**
	 * This method is called to fetch logged in user details
	 * 
	 * @param loginFormDTO
	 * @return
	 */
	@Override
	public LoginDTO validateLoginFormValues(String emailAddress, String password) {
		return loginFormDAO.validateLoginFormValues(emailAddress, password);
	}

	/**
	 * @purpose This method is to get the user email details
	 * @param emailAddress
	 * @return
	 */
	@Override
	public LoginDTO getUserEmailDetails(String emailAddress) {
		return loginFormDAO.getUserEmailDetails(emailAddress);
	}

	/**
	 * This method to get the active job Posting
	 * 
	 * @param facilityId
	 * 
	 */

	@Override
	public int getactivejobposting(int facilityId)
			throws JobBoardServiceException {
		// TODO Auto-generated method stub
		return loginFormDAO.getactivejobposting(facilityId);
	}

}
