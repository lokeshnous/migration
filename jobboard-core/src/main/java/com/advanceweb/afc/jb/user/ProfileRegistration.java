/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.user;

//import java.util.List;

//import com.advanceweb.afc.jb.common.AccountProfileDTO;
//import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:43 PM
 */
public interface ProfileRegistration {

	/**
	 * This method is used for creating a User( agency) in Job board.
	 * 
	 * @param Object
	 *            of profileDTO
	 * @return Object of UserDTO
	 */

	UserDTO createUser(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	boolean deleteProfile(int profileId);

	/**
	 * 
	 * @param profileDTO
	 */
	boolean modifyProfile(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	ProfileDTO viewProfile(int profileId);

	/**
	 * 
	 * @param profileDTO
	 */
	boolean changePassword(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileDTO
	 */
	boolean validatePassword(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileDTO
	 */
	boolean validateEmail(String email);

	/**
	 * 
	 * @param profileDTO
	 */
	ProfileDTO getProfileAttributes();

	/**
	 * This method will be called after the successful login by the user, to get
	 * the profile information in case of old user
	 * 
	 * @param jobseekerId
	 * @return
	 */
	boolean validateProfileAttributes(int jobseekerId);


}