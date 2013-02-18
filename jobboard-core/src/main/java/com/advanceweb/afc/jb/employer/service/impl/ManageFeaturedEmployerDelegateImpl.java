package com.advanceweb.afc.jb.employer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO;
import com.advanceweb.afc.jb.employer.service.ManageFeaturedEmployerDelegate;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;

/**
 * This is the implementation class to call the NetSuite service layer from the
 * Job board.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 10 Sept 2012
 * 
 */

@Service("manageFeaturedEmployerDelegate")
public class ManageFeaturedEmployerDelegateImpl implements
		ManageFeaturedEmployerDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(ManageFeaturedEmployerDelegateImpl.class);

	private static final String CUSTOMER_STRING = "customer"; 
	
	@Autowired
	private ManageFeaturedEmployerProfileDAO manageFeaturedEmployerProfileDAO;

	@Autowired
	private NSCustomerService nsCustomerService;

	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */

	public int getNSCustomerIDFromAdmFacility(int admFacilityID) {
		int nsCustomerID = 0;
		List<FacilityDTO> admFacilityDTOList = manageFeaturedEmployerProfileDAO
				.getNSCustomerIDFromAdmFacility(admFacilityID);
		nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
		return nsCustomerID;
	}

	/**
	 * This method is used to get the net suite customer purchased packages
	 * based on customer id.
	 * 
	 * @param int admFacilityID
	 * @return List<String>
	 */

	public List<String> getNSCustomerPackages(int nsCustomerID) {
		List<String> listPackage = new ArrayList<String>();
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType(CUSTOMER_STRING);

		try {
			listPackage = nsCustomerService.getNSCustomerPackages(userDTO);
		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.error("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return listPackage;
	}

	/**
	 * This method is used to get the net suite customer details
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */

	public UserDTO getNSCustomerDetails(int nsCustomerID) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType(CUSTOMER_STRING);

		try {
			userDTO = nsCustomerService.getNSCustomerDetails(userDTO);
		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.error("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return userDTO;
	}
	
	/**
	 * This method is used to get the Featured employer package start/End dates.
	 * 
	 * If start/end date is not available for the feature package then NetSuite
	 * returns start date=current date and end date=(current date + 10 years)
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */

	public UserDTO getNSFeatureDates(int nsCustomerID) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType(CUSTOMER_STRING);

		try {
			userDTO = nsCustomerService.getNSFeatureDates(userDTO);
		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.error("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return userDTO;
	}
	
}
