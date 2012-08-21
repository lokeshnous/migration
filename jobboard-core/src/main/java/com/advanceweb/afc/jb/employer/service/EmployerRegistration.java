package com.advanceweb.afc.jb.employer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.employer.dao.EmployerRegistrationDAO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("employerRegistration")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
public class EmployerRegistration implements ProfileRegistration {
	
	@Autowired
	public EmployerRegistrationDAO employerRegistrationDAO;

	public EmployerRegistration(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param profileDTO
	 */
	public MerUserDTO createNewProfile(ProfileDTO profileDTO){
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerRegistrationDAO.createNewEmployer(empProfileDTO);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(int profileId){
		return false;
	}

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean modifyProfile(ProfileDTO profileDTO){
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	public ProfileDTO viewProfile(int profileId){
		return null;
	}

	@Override
	public boolean changePassword(ProfileDTO profileDTO) {
		try {
			EmployerProfileDTO empProfileDTO = (EmployerProfileDTO) profileDTO;
			return employerRegistrationDAO.changePassword(empProfileDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public ProfileDTO getProfileAttributes() {
		return employerRegistrationDAO.getProfileAttributes();
	}

}