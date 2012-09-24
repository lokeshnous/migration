package com.advanceweb.afc.jb.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
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
	 * This method is to get the facilityId for logged in user
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public EmployerInfoDTO facilityDetails(int userId) {
		return userDAO.facilityDetails(userId);
	}

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return metricsDTO
	 */
	public List<MetricsDTO> getJobPostTotal(int facilityId) {
		return userDAO.getJobPostTotal(facilityId);
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

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	public List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardServiceException {
		List<DropDownDTO> downDTOs = new ArrayList<DropDownDTO>();
		try {
			downDTOs = userDAO.getFacilityGroup(facilityId);
		} catch (JobBoardDataException e) {
			// TODO Auto-generated catch block
		}
		return downDTOs;
	}

	/**
	 * This method is to get facility parent id
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	public int getFacilityParent(int facilityId)
			throws JobBoardServiceException{
		int facilityParentId=0;
		try {
			facilityParentId = userDAO.getFacilityParent(facilityId);
		} catch (JobBoardDataException e) {
			// TODO Auto-generated catch block
		}
		return facilityParentId;
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
}
