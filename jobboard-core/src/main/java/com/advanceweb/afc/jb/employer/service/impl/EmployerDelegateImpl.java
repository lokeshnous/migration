/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.employer.service.EmployerDelegate;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;


/**
 * This class helps to call the NetSuite service layer from the Job board.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

@Service("employerDelegate")
public class EmployerDelegateImpl implements EmployerDelegate {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(EmployerDelegateImpl.class);

	/** The employer registration dao. */
	@Autowired
	public EmployerRegistrationDAO employerRegistrationDAO;

	/** The ns customer service. */
	@Autowired
	private NSCustomerService nsCustomerService;

	/**
	 * This method is used to create a customer through NetSuite
	 * and based on the status returned from Net Suite, it will
	 * create the user in the Job board.
	 * 
	 * @param Object of EmployerProfileDTO
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */


	public UserDTO createUser(EmployerProfileDTO empProfileDTO)
			throws JobBoardServiceException {

		// Get the details here and put it inside UserDTO
		UserDTO userDTO = createUserDTOFromProfileAttribute(empProfileDTO);

		// Added for calling WS call to NetSuite
		try {
			userDTO = nsCustomerService.createCustomer(userDTO);

			LOGGER.debug("CustomerID from JSON for Employer===>"
					+ userDTO.getNsCustomerID());
			if (userDTO.getNsStatus() != null && userDTO.getNsStatus().equalsIgnoreCase("record already exist")) {
				LOGGER.debug("User Already Exist. Please login to continue.");
			} else {
				empProfileDTO.getMerUserDTO().setNsCustomerID(
						userDTO.getNsCustomerID());

				userDTO = employerRegistrationDAO.createUser(empProfileDTO);
			}

		} catch (JobBoardNetSuiteServiceException e) {
			throw new JobBoardServiceException(
					"Error occurred while getting the response from NetSuite."
							+ e);

		}

		return userDTO;
	}

	/**
	 * This method is used to create a user from EmployerProfileDTO.
	 * 
	 * @param Object of EmployerProfileDTO
	 * @return Object of UserDTO
	 */

	private UserDTO createUserDTOFromProfileAttribute(
			EmployerProfileDTO empProfileDTO) {
		UserDTO userDTO = new UserDTO();
		List<ProfileAttribDTO> proAttDTOList = empProfileDTO.getAttribList();

		for (ProfileAttribDTO proAttDTO : proAttDTOList) {

			if (proAttDTO.getStrLabelName().contains("First Name")) {
				userDTO.setFirstName(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Middle Name")) {
				userDTO.setMiddleName(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Last Name")) {
				userDTO.setLastName(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Street Address")) {
				userDTO.setStreetAddress(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Zip Code")) {
				userDTO.setZipCode(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("City")) {
				userDTO.setCity(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("State / Province")) {
				userDTO.setState(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Country")) {
				userDTO.setCountry(proAttDTO.getStrLabelValue());
			}

			if (proAttDTO.getStrLabelName().contains("Position Title")) {
				userDTO.setPosition(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Company")) {
				userDTO.setCompany(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Primary Phone")) {
				userDTO.setPrimaryPhone(proAttDTO.getStrLabelValue());
			}
			if (proAttDTO.getStrLabelName().contains("Secondary Phone")) {
				userDTO.setSecondaryPhone(proAttDTO.getStrLabelValue());
			}

		}
		userDTO.setEmailId(empProfileDTO.getMerUserDTO().getEmailId());
		return userDTO;
	}

	/**
	 * This method is used to edit and update a customer through NetSuite
	 * and based on the status returned from Net Suite, it will
	 * update the user in the Job board.
	 * 
	 * @param Object of AccountProfileDTO, admFacilityid, userId, billing
	 * @return boolean 
	 * @throws JobBoardServiceException
	 */

	public boolean editUser(AccountProfileDTO accountProfDTO,
			int admFacilityid, int userId, String billing)
			throws JobBoardServiceException {
		boolean isUpdate = false;

		
		FacilityDTO admFacilityDTO = employerRegistrationDAO
				.getNSCustomerIDFromAdmFacility(userId);

		UserDTO userDTO = createUserDTOFromAccountProfileDTO(accountProfDTO,
				admFacilityDTO.getNsCustomerID());

		try {
			
			userDTO = nsCustomerService.editCustomer(userDTO);

		if (userDTO.getNsStatus() != null && userDTO.getNsStatus().equalsIgnoreCase("true")) {
				LOGGER.debug("Successfully Updated Record in NetSuite.");
				isUpdate = employerRegistrationDAO.editUser(accountProfDTO,
						admFacilityid, userId, billing);
			}

				} catch (JobBoardNetSuiteServiceException e) {
			throw new JobBoardServiceException(
					"Error occurred while getting the response from NetSuite."
							+ e);

		}

		return isUpdate;

	}

	/**
	 * This method is used to create a User object from AccountProfileDTO object.
	 * 
	 * @param accountProfDTO, nsCustomerID
	 * @return Object of UserDTO
	 */

	private UserDTO createUserDTOFromAccountProfileDTO(
			AccountProfileDTO accountProfDTO, int nsCustomerID) {

		UserDTO userDTO = new UserDTO();
		userDTO.setNsCustomerID(nsCustomerID);
		userDTO.setFirstName(accountProfDTO.getFirstName());
		userDTO.setLastName(accountProfDTO.getLastName());
		userDTO.setStreetAddress(accountProfDTO.getStreet());
		userDTO.setZipCode(accountProfDTO.getZipCode());
		userDTO.setCity(accountProfDTO.getCity());
		userDTO.setState(accountProfDTO.getState());
		userDTO.setCountry(accountProfDTO.getCountry());
		userDTO.setCompany(accountProfDTO.getCompanyName());
		userDTO.setPrimaryPhone(accountProfDTO.getPhone());
		userDTO.setEmailId(accountProfDTO.getEmail());
		return userDTO;
	}

}
