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
	public MerUserDTO createNewEmployer(EmployerProfileDTO empDTO);

	/**
	 * 
	 * @param employerId
	 */
	public boolean deleteEmployer(long employerId);

	/**
	 * 
	 * @param employerId
	 */
	public EmployerProfileDTO getEmployerDetails(int employerId);

	/**
	 * Updating employer details
	 * 
	 * @param empDTO
	 * @return
	 */
	public boolean updateEmployerDetails(EmployerProfileDTO empDTO);

	/**
	 * To change the password
	 * 
	 * @param empDTO
	 * @return boolean
	 */
	public boolean changePassword(EmployerProfileDTO empDTO);

	/**
	 * To change password
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public EmployerProfileDTO getProfileAttributes();

	/**
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email);


	public List<AdmFacilityContact> getEmployeeData(int userId,String contactType);
	/**
	 * 
	 * @param userId userId.
	 * @return userid.
	 */
	public List<AdmFacilityContact> getEmployeePrimaryKey(int userId,
			String contactType);
	/**
	 * 
	 * @param apd apd.
	 * @param admfacilityid admfacilityid.
	 */
	
	public void editEmployeeAccount(AccountProfileDTO apd,int admfacilityid);

	

}