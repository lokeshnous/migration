package com.advanceweb.afc.jb.user;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ProfileDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;



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
	public MerUserDTO createNewProfile(ProfileDTO profileDTO);

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
	
	/**
	 * 
	 * @param profileDTO
	 */
	public boolean validateEmail(String email);
	
	/**
	 * 
	 * @param profileDTO
	 */
	public ProfileDTO getProfileAttributes();
	
	/**
	 * 
	 * @param userId
	 * @return List
	 */
    public List<AdmFacilityContact> getEmployeeData(int userId,String contactType);
    /**
     * 
     * @param userId
     * @return userid
     */
     public List<AdmFacilityContact> getEmployeePrimaryKey(int userId,String contactType);
    /**
     * 	
     * @param apd apd.
     * @param admfacilityid admfacilityid.
     */
    public void editEmployeeAccount(AccountProfileDTO apd,int admfacilityid);

}