/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.service;

//import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.agency.service.AgencyDelegate;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.AgencyRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("agencyRegistration")
public class AgencyRegistration implements ProfileRegistration {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AgencyRegistration.class);

	/** The agency registration dao. */
	@Autowired
	public AgencyRegistrationDAO agencyRegistrationDAO;

	/** The agency delegate. */
	@Autowired
	private AgencyDelegate agencyDelegate;

	/**
	 * This method is used for creating a User( agency) in Job board.
	 * 
	 * @param Object
	 *            of profileDTO
	 * @return Object of UserDTO
	 */
	public UserDTO createUser(ProfileDTO profileDTO) {
		try {
			AgencyProfileDTO agencyProfileDTO = (AgencyProfileDTO) profileDTO;
			return agencyDelegate.createUser(agencyProfileDTO);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return agencyRegistrationDAO.validateEmail(email);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#getProfileAttributes()
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProfileDTO getProfileAttributes() {
		return agencyRegistrationDAO.getProfileAttributes();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.user.ProfileRegistration#validateProfileAttributes(int)
	 */
	@Override
	public boolean validateProfileAttributes(int jobseekerId) {

		return agencyRegistrationDAO.validateProfileAttributes(jobseekerId);
	}


}