package com.advanceweb.afc.registration;


import com.advanceweb.afc.common.EmployerProfileDTO;
import com.advanceweb.afc.common.ProfileDTO;
import com.advanceweb.afc.data.common.helpers.RegistrationConversionHelper;
import com.advanceweb.afc.data.registration.EmployerRegistrationDAO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public class EmployerRegistration implements ProfileRegistration {

	private EmployerProfileDTO employerProfileDTO;
	public EmployerProfileDTO m_EmployerProfileDTO;
	public RegistrationConversionHelper m_RegistrationConversionHelper;
	public EmployerRegistrationDAO m_EmployerRegistrationDAO;

	public EmployerRegistration(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean createNewProfile(ProfileDTO profileDTO){
		return false;
	}

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(long profileId){
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
	public ProfileDTO viewProfile(long profileId){
		return null;
	}

}