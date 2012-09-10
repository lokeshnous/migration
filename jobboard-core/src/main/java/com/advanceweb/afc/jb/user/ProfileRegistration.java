package com.advanceweb.afc.jb.user;

import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:43 PM
 */
public interface ProfileRegistration {

	/**
	 * This method is used for creating a User( agency) in Job board. 
	 * @param Object of profileDTO
	 * @return Object of UserDTO
	 */

	public UserDTO createUser(ProfileDTO profileDTO);

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
	 * This method will be called after the successful login by the user, 
	 * to get the profile information in case of old user
	 * 
	 * @param jobseekerId
	 * @return
	 */
	boolean validateProfileAttributes(int jobseekerId);
	

}