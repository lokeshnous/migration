package com.advanceweb.afc.jb.employer.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.employer.service.EmployerDelegate;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;



@Service("employerDelegate")
public class EmployerDelegateImpl implements EmployerDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(EmployerDelegateImpl.class);
	
	@Autowired
	public EmployerRegistrationDAO employerRegistrationDAO;

	@Autowired
	private NSCustomerService nsCustomerService;
	
	
	/**
	 * 
	 */
	
	@Override
	public UserDTO createEmployer(EmployerProfileDTO empProfileDTO) throws JobBoardServiceException{
		
		//Get the details here and put it inside UserDTO
		UserDTO userDTO = null;
		//Added for calling WS call to NetSuite
		
		String customerID = nsCustomerService.createCustomer(userDTO);
		
		if(customerID.contains("error")){
			LOGGER.info("Error occurred while getting the response from NetSuite.");
			throw new JobBoardServiceException("Error occurred while getting the response from NetSuite.");
		}else{
			userDTO = employerRegistrationDAO.createEmployerProfile(empProfileDTO);
		}
		
		return userDTO;
	}
	
	

}
