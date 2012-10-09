package com.advanceweb.afc.jb.login.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.login.dao.LoginFormDAO;
import com.advanceweb.afc.jb.login.service.LoginService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.dao.UserDao;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = Logger
			.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginFormDAO loginFormDAO;

	@Autowired
	private UserDao userDAO;

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

	@Override
	public UserDTO getUser(String email) {
		return userDAO.getUser(email);
	}

	@Override
	public List<UserRoleDTO> getUserRole(int userId) {
		return userDAO.getUserRole(userId);
	}

	/**
	 * This method to update the automatic generated password to DB
	 * 
	 * @param emailAddress
	 * @param tempassword
	 * @throws JobBoardServiceException
	 */
	public void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardServiceException {
		try {
			userDAO.saveNewPWD(emailAddress, tempassword);
		} catch (JobBoardDataException e) {
			LOGGER.info("Error occurred while saving the generated password"
					+ e);
		}
	}

}
