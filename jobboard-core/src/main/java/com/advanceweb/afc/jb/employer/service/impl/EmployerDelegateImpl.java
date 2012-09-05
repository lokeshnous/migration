package com.advanceweb.afc.jb.employer.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.JsonUtil;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.employer.service.EmployerDelegate;
import com.advanceweb.afc.jb.netsuite.NSCustomer;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.webservice.service.NSCustomerService;



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
	public UserDTO createEmployerProfile(EmployerProfileDTO empProfileDTO) throws JobBoardServiceException{
		
		UserDTO userDTO = null;
		//Added for calling WS call to NetSuite
		NSCustomer nsCustomer = createCustomerObject(empProfileDTO);		
		String jsonCustomer = JsonUtil.toJson(nsCustomer);
		
		LOGGER.info("Json for Customer=>"+jsonCustomer);
		
		String customerID = nsCustomerService.createCustomer(jsonCustomer.toLowerCase());
		if(customerID.contains("error")){
			LOGGER.info("Error occurred while getting the response from NetSuite.");
			throw new JobBoardServiceException("Error occurred while getting the response from NetSuite.");
		}else{
			userDTO = employerRegistrationDAO.createEmployerProfile(empProfileDTO);
		}
		return userDTO;
	}
	
	/**
	 * 
	 * @param empProfileDTO
	 * @return
	 */
	
	private NSCustomer createCustomerObject(EmployerProfileDTO empProfileDTO){
		
		NSCustomer nsCustomer = new NSCustomer();
		//custDTO.setCustomerId(460460);
		nsCustomer.setCompanyName(empProfileDTO.getMerUserDTO().getFirstName() + " " + empProfileDTO.getMerUserDTO().getLastName());
		nsCustomer.setRecordType("customer");
		return nsCustomer;
	}

}
