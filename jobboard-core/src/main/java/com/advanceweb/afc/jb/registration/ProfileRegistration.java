package com.advanceweb.afc.jb.registration;

import com.advanceweb.afc.jb.common.ProfileDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:43 PM
 */
public interface ProfileRegistration {

	public ProfileDTO m_ProfileDTO = null;

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean createNewProfile(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	public boolean deleteProfile(long profileId);

	/**
	 * 
	 * @param profileDTO
	 */
	public boolean modifyProfile(ProfileDTO profileDTO);

	/**
	 * 
	 * @param profileId
	 */
	public ProfileDTO viewProfile(long profileId);

}