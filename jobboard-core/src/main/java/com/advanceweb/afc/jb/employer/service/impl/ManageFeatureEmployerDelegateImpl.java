package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.AdmFacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.ManageFeatureEmployerProfileDAO;
import com.advanceweb.afc.jb.employer.service.ManageFeatureEmployerDelegate;
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

@Service("manageFeatureEmployerDelegate")
public class ManageFeatureEmployerDelegateImpl implements
		ManageFeatureEmployerDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(ManageFeatureEmployerDelegateImpl.class);

	@Autowired
	private ManageFeatureEmployerProfileDAO manageFeatureEmployerProfileDAO;

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
		List<AdmFacilityDTO> admFacilityDTOList = manageFeatureEmployerProfileDAO
				.getNSCustomerIDFromAdmFacility(admFacilityID);
		nsCustomerID = admFacilityDTOList.get(0).getNsCustomerID();
		return nsCustomerID;
	}

	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return UserDTO
	 */

	public UserDTO getNSCustomerDetails(int nsCustomerID) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEntityId(nsCustomerID);
		userDTO.setRecordType("customer");

		try {
			
			userDTO = nsCustomerService.getNSCustomerDetails(userDTO);
			
		} catch (JobBoardNetSuiteServiceException jbns) {
			LOGGER.info("Error occurred while getting the Customer details from net suite..Please contact your administrator."
					+ jbns);
		}
		return userDTO;

	}

}
