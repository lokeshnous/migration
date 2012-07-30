package com.advanceweb.afc.jb.user;

import com.advanceweb.afc.jb.common.ProfileDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:43 PM
 */
public interface ProfileRegistration {



	/**
	 * 
	 * @param profileDTO
	 */
	public boolean createNewProfile(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(int profileId);

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean modifyProfile(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	public ProfileDTO viewProfile(int profileId);
	
	
	/**
	 * 
	 * @param profileDTO
	 */
	public boolean changePassword(ProfileDTO profileDTO);
	
	/**
	 * 
	 * @param profileDTO
	 */
	public boolean validatePassword(ProfileDTO profileDTO);

}