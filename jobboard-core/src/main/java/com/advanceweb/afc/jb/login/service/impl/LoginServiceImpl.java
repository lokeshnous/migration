package com.advanceweb.afc.jb.login.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.data.entities.JpJobStat;
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
			// TODO Auto-generated catch block
		}
	}

	/**
	 * This method to get the Date range data
	 * 
	 * @param startFrom
	 * @param endFrom
	 * @param selEmployerId
	 * 
	 */

	@Override
	public List<MetricsDTO> employerMetrics(Date startFrom, Date endFrom,
			int selEmployerId) {
		// TODO Auto-generated method stub
		return loginFormDAO.employerMetrics(startFrom, endFrom, selEmployerId);
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

	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardServiceException
	 */
	public long getEmployerCount() throws JobBoardServiceException {
		long returnVal = 0;
		try {
			returnVal = userDAO.getEmployerCount();
		} catch (JobBoardDataException jde) {
			throw new JobBoardServiceException(
					"Error occured while getting the Result from Database"
							+ jde);
		}
		return returnVal;
	}

	@Override
	public void updateSocialProfileId(int userId, String profileId,
			int profileAttrId) throws JobBoardServiceException {
		try {
			userDAO.updateSocialProfileId(userId, profileId, profileAttrId);
		} catch (JobBoardDataException e) {
			LOGGER.info("Error occurred while updating the social profile id"
					+ e);
			throw new JobBoardServiceException(
					"Error occurred while updating the social profile id" + e);

		}
	}

	@Override
	public UserDTO getUserBySocialProfileId(String socialProfileId)
			throws JobBoardServiceException {
		try {
			return userDAO.getUserBySocialProfileId(socialProfileId);
		} catch (JobBoardDataException e) {
			LOGGER.info("Error occurred while fetching the user based on the social profile id"
					+ e);
			throw new JobBoardServiceException(
					"Error occurred while fetching the user based on the social profile id"
							+ e);

		}
	}
}
