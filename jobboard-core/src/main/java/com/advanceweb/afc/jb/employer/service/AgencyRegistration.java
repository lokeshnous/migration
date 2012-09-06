package com.advanceweb.afc.jb.employer.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.employer.dao.AgencyRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("agencyRegistration")
public class AgencyRegistration implements ProfileRegistration {
	private static final Logger LOGGER = Logger.getLogger(AgencyRegistration.class);
	@Autowired
	public AgencyRegistrationDAO agencyRegistrationDAO;
	
	/**
	 * 
	 */

	/**
	 * 
	 * @param profileDTO
	 */
	public UserDTO createEmployer(ProfileDTO profileDTO) {
		try {
			AgencyProfileDTO agencyProfileDTO = (AgencyProfileDTO) profileDTO;
			return agencyRegistrationDAO.createNewAgency(agencyProfileDTO);
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

	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProfileDTO getProfileAttributes() {
		return agencyRegistrationDAO.getProfileAttributes();
	}
	
	@Override
	public boolean validateProfileAttributes(int jobseekerId) {
		
		return agencyRegistrationDAO.validateProfileAttributes(jobseekerId);
	}
	

}