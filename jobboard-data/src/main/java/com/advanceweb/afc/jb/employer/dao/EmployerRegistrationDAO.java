package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface EmployerRegistrationDAO {

	/**
	 * 
	 * @param employer
	 */
	MerUserDTO createNewEmployer(EmployerProfileDTO empDTO);

	/**
	 * 
	 * @param employerId
	 */
	boolean deleteEmployer(long employerId);

	/**
	 * 
	 * @param employerId
	 */
	EmployerProfileDTO getEmployerDetails(int employerId);

	/**
	 * Updating employer details
	 * 
	 * @param empDTO
	 * @return
	 */
	boolean updateEmployerDetails(EmployerProfileDTO empDTO);

	/**
	 * To change the password
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	boolean changePassword(EmployerProfileDTO empDTO);

	/**
	 * To change password
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	EmployerProfileDTO getProfileAttributes();

	/**
	 * @param email
	 * @return
	 */
	boolean validateEmail(String email);

	List<AdmFacilityContact> getEmployeeData(int userId, String contactType);

	/**
	 * 
	 * @param userId
	 *            userId.
	 * @return userid.
	 */
	List<AdmFacilityContact> getEmployeePrimaryKey(int userId,
			String contactType);

	/**
	 * 
	 * @param apd
	 *            apd.
	 * @param admfacilityid
	 *            admfacilityid.
	 */

	
	public void editEmployeeAccount(AccountProfileDTO apd, int admfacilityid,
			int userId, String billing);



}