package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.UserDTO;



/**
 * This is a interface to call the NetSuite service layer from the Job board.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 10 Sept 2012
 * 
 */

public interface ManageFeaturedEmployerDelegate {
	
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
	 * @return List<String>
	 */
	
	List<String> getNSCustomerPackages(int nsCustomerID);

	/**
	 * This method is used to get the net suite customer details
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	
	UserDTO getNSCustomerDetails(int nsCustomerID);

	/**
	 * This method is used to get Featured employer package start/End dates
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	
	UserDTO getNSFeatureDates(int nsCustomerID);

	
}
