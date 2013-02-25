/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.JobPostDAOImpl;
import com.advanceweb.afc.jb.job.service.JobPostDelegate;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */


@Service("jobPostDelegate")
public class JobPostDelegateImpl implements JobPostDelegate{
	
	/** The Constant CUSTOMER_STRING. */
	private static final String CUSTOMER_STRING = "customer"; 
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JobPostDAOImpl.class);
	
	/** The ns customer service. */
	@Autowired
	private NSCustomerService nsCustomerService;
	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
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
}
