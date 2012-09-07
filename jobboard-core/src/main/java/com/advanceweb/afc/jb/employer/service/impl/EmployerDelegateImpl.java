package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
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
		UserDTO userDTO = new UserDTO();
		/*List<ProfileAttribDTO> proAttDTOList = empProfileDTO.getAttribList();
		
		for(ProfileAttribDTO proAttDTO : proAttDTOList){
			
			LOGGER.info(proAttDTO.getStrLabelName()+"="+proAttDTO.getStrLabelValue());
			if(proAttDTO.getStrLabelName().contains("First Name")){
				userDTO.setFirstName(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Middle Name")){
				userDTO.setMiddleName(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Last Name")){
				userDTO.setLastName(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Street Address")){
				userDTO.setStreetAddress(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Zip Code")){
				userDTO.setZipCode(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("City")){
				userDTO.setCity(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("State / Province")){
				userDTO.setState(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Country")){
				userDTO.setCountry(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("E-Mail Address")){
				userDTO.setEmailId(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Position Title")){
				userDTO.setPosition(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Company")){
				userDTO.setCompany(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Primary Phone")){
				userDTO.setPrimaryPhone(proAttDTO.getStrLabelValue());
			}
			if(proAttDTO.getStrLabelName().contains("Secondary Phone")){
				userDTO.setSecondaryPhone(proAttDTO.getStrLabelValue());
			}
			
			
			
		}
		
		 
		
		//Added for calling WS call to NetSuite
		
		String customerID = nsCustomerService.createCustomer(userDTO);
		
		System.out.println("customerID===>"+customerID);
		
		if(customerID.contains("error")){
			LOGGER.info("Error occurred while getting the response from NetSuite.");
			throw new JobBoardServiceException("Error occurred while getting the response from NetSuite.");
		}else{*/
			userDTO = employerRegistrationDAO.createEmployerProfile(empProfileDTO);
		//}
		
		return userDTO;
	}
	
	

}
