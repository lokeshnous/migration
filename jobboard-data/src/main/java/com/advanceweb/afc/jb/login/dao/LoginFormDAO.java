package com.advanceweb.afc.jb.login.dao;

import java.util.Date;
import java.util.List;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.data.entities.JpJobStat;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 19th July 2012
 */

public interface LoginFormDAO {
	/**
	 * This method to validate the userId and roleID of logged in user
	 * 
	 * @param emailAddress
	 * @param password
	 * @return
	 */
	LoginDTO validateLoginFormValues(String emailAddress, String password);

	/**
	 * This method to get the user details
	 * 
	 * @param emailAddress
	 * @return
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

	List<JpJobStat> employerMetrics(Date startFrom, Date endFrom,
			int selEmployerId);

	/**
	 * This method is to get active job Posting
	 * 
	 * @param facilityId
	 * @return
	 * @throws JobBoardServiceException
	 */
	int getactivejobposting(int facilityId);
}
