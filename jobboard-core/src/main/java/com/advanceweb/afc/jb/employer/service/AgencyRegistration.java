package com.advanceweb.afc.jb.employer.service;


import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.user.ProfileRegistration;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
@Service("profileAgencyRegistration")
public class AgencyRegistration implements ProfileRegistration {

	private AgencyRegistration agencyRegistration;



	public AgencyRegistration(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param profileDTO
	 */
	public MerUserDTO createNewProfile(ProfileDTO profileDTO){
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
	public ProfileDTO getProfileAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

}