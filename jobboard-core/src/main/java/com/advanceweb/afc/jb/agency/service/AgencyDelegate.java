package com.advanceweb.afc.jb.agency.service;

import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This is a interface to call the NetSuite service layer from the Job board
 *  fro Agency.
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

public interface AgencyDelegate {

	/**
	 * This method is used to create a user through NetSuite and based on the
	 * status returned from Net Suite, it will create the user in the Job board.
	 * 
	 * @param Object of AgencyProfileDTO
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */
	
	UserDTO createUser(AgencyProfileDTO agencyProfileDTO) throws JobBoardServiceException;
	
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */
	
	int getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * This method is ude to get the customer details from net suite.
	 * @param nsCustomerID
	 * @return Object of UserDTO
	 */
	
	UserDTO getNSCustomerDetails(int nsCustomerID);

}
