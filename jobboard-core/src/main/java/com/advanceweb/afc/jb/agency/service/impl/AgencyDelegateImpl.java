package com.advanceweb.afc.jb.agency.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.agency.service.AgencyDelegate;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.AgencyRegistrationDAO;
import com.advanceweb.afc.jb.netsuite.service.NSCustomerService;
import com.advanceweb.afc.jb.service.exception.JobBoardNetSuiteServiceException;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * This class helps to call the NetSuite service layer from the Job board for
 * Agency.
 * 
 * @author Reetesh R N
 * @version 1.0
 * @since 04 Sept 2012
 * 
 */

@Service("agencyDelegate")
public class AgencyDelegateImpl implements AgencyDelegate {

	private static final Logger LOGGER = Logger
			.getLogger(AgencyDelegateImpl.class);

	@Autowired
	private NSCustomerService nsCustomerService;

	@Autowired
	public AgencyRegistrationDAO agencyRegistrationDAO;

	/**
	 * This method is used to create a user through NetSuite and based on the
	 * status returned from Net Suite, it will create the user in the Job board.
	 * 
	 * @param Object of AgencyProfileDTO
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	public UserDTO createUser(AgencyProfileDTO agencyProfileDTO)
			throws JobBoardServiceException {

		// Get the details here and put it inside UserDTO
		UserDTO userDTO = createUserDTOFromProfileAttribute(agencyProfileDTO);

		// Added for calling WS call to NetSuite
		try {
			userDTO = nsCustomerService.createCustomer(userDTO);

			LOGGER.info("CustomerID from JSON for Agency===>"
					+ userDTO.getNsCustomerID());
			agencyProfileDTO.getMerUserDTO().setNsCustomerID(
					userDTO.getNsCustomerID());

			userDTO = agencyRegistrationDAO.createUser(agencyProfileDTO);

		} catch (JobBoardNetSuiteServiceException e) {
			throw new JobBoardServiceException(
					"Error occurred while getting the response from NetSuite."
							+ e);

		}

		return userDTO;

	}

	/**
	 * This method is used to create a user Object from AgencyProfileDTO object.
	 * 
	 * @param Object of AgencyProfileDTO
	 * @return Object of UserDTO
	 * @throws JobBoardServiceException
	 */

	private UserDTO createUserDTOFromProfileAttribute(
			AgencyProfileDTO agencyProfileDTO) {
		UserDTO userDTO = new UserDTO();
		List<ProfileAttribDTO> proAttDTOList = agencyProfileDTO.getAttribList();

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
		return userDTO;
	}

}
