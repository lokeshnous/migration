/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("employerRegistration")
public class EmployerRegistration implements ProfileRegistration,
		EmloyerRegistartionService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("EmployerRegistration.class");
	
	/** The employer registration dao. */
	@Autowired
	public EmployerRegistrationDAO employerRegistrationDAO;

	/** The employer delegate. */
	@Autowired
	private EmployerDelegate employerDelegate;

	/**
	 * /**
	 * 
	 * @param profileDTO
	 * @throws JobBoardServiceException
	 */
	public UserDTO createUser(ProfileDTO profileDTO) {
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerDelegate.createUser(empProfileDTO);
		} catch (JobBoardServiceException e) {
			LOGGER.error("Error occurred while interaction with NetSuite.. Please try again.",e);
			return null;
		}

	}

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(int profileId) {
		return false;
	}

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean modifyProfile(ProfileDTO profileDTO) {
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	public ProfileDTO viewProfile(int profileId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#changePassword(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerRegistrationDAO.changePassword(empProfileDTO);
		} catch (Exception e) {
			LOGGER.error("Error occurred while interaction with NetSuite.. Please try again.",e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validatePassword(com.advanceweb.afc.jb.common.ProfileDTO)
	 */
	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validateEmail(java.lang.String)
	 */
	@Override
	public boolean validateEmail(String email) {
		return employerRegistrationDAO.validateEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#getProfileAttributes()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProfileDTO getProfileAttributes() {
		return employerRegistrationDAO.getProfileAttributes();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService#getEmployeeData(int, java.lang.String)
	 */
	@Override
	public List<AdmFacilityContact> getEmployeeData(int userId,
			String contactType) {
		List<AdmFacilityContact> accountProfileDTO = new ArrayList<AdmFacilityContact>();
		try {

			accountProfileDTO = employerRegistrationDAO.getEmployeeData(userId,
					contactType);
		} catch (Exception e) {
			LOGGER.error("Error for employee registration edit",e);
		}
		return accountProfileDTO;
	}

	/**
	 * This method is used for edit and update a User(Employer) in Job board.
	 * 
	 * @param Object
	 *            of AccountProfileDTO
	 * @param int admFacilityid
	 * @param int userId
	 * @param String
	 *            billing
	 * @return boolean
	 */
	public boolean editUser(AccountProfileDTO apd, int admFacilityid,
			int userId, String billing) throws JobBoardServiceException {

		boolean isUpdate = false;

		try {
			if(MMJBCommonConstants.PRIMARY.equals(billing)){
			
			isUpdate = employerDelegate.editUser(apd, admFacilityid, userId,
					billing);
			}else{
				isUpdate = employerRegistrationDAO.editUser(apd,
						admFacilityid, userId, billing);
			}

		} catch (JobBoardServiceException jbe) {
			LOGGER.error("Error occurred while interaction with NetSuite.. Please try again.",jbe);

		}

		return isUpdate;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.employer.service.EmloyerRegistartionService#getEmployeePrimaryKey(int, java.lang.String)
	 */
	@Override
	public AdmFacilityContactDTO getEmployeePrimaryKey(int userId,
			String contactType) {
		AdmFacilityContactDTO accountProfileDTO = new AdmFacilityContactDTO();
		try {

			accountProfileDTO = employerRegistrationDAO.getEmployeePrimaryKey(
					userId, contactType);
		} catch (Exception e) {
			LOGGER.error("Error for employee registration edit",e);
		}
		return accountProfileDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validateProfileAttributes(int)
	 */
	@Override
	public boolean validateProfileAttributes(int jobseekerId) {

		return employerRegistrationDAO.validateProfileAttributes(jobseekerId);
	}


}