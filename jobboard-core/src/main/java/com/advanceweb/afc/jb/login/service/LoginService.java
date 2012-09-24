package com.advanceweb.afc.jb.login.service;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserRoleDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

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
	LoginDTO validateLoginFormValues(String emailAddress, String password);

	/**
	 * This method is to get the user email details
	 * 
	 * @param emailAddress
	 */
	LoginDTO getUserEmailDetails(String emailAddress);

	UserDTO getUser(String email);

	/**
	 * This method is to get the facilityId for logged in user
	 * 
	 * @param userId
	 * @return
	 */
	EmployerInfoDTO facilityDetails(int userId);

	List<UserRoleDTO> getUserRole(int userId);

	/**
	 * This method is get the metrics details for logged in employer
	 * 
	 * @param facilityId
	 * @return
	 */
	List<MetricsDTO> getJobPostTotal(int facilityId);

	/**
	 * This method is used to get the total count of employer
	 * 
	 * @return
	 * @throws JobBoardServiceException
	 */
	long getEmployerCount() throws JobBoardServiceException;

	/**
	 * This method is to get all list of facilities
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<DropDownDTO> getFacilityGroup(int facilityId)
			throws JobBoardServiceException;

	/**
	 * This method to update the automatic generated password to DB
	 * 
	 * @param emailAddress
	 * @param tempassword
	 * @throws JobBoardServiceException
	 */
	void saveNewPWD(String emailAddress, String tempassword)
			throws JobBoardServiceException;
}
