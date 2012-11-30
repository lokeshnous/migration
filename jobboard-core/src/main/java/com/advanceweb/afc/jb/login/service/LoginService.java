package com.advanceweb.afc.jb.login.service;

import java.util.Date;
import java.util.List;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.MetricsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
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

	/**
	 * This method is to get all list of Dates
	 * 
	 * @param startFrom
	 *            ,endFrom,facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	List<MetricsDTO> employerMetrics(Date startFrom, Date endFrom,
			int selEmployerId) throws JobBoardServiceException;

	/**
	 * This method is to get active job Posting
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getactivejobposting(int facilityId) throws JobBoardServiceException;

}
