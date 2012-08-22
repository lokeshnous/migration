package com.advanceweb.afc.jb.employer.dao;
import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.data.domain.Employer;
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
	public Employer getEmployerDetails(long employerId);

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
	 * to display account of employee
	 * @param userId userId.
	 * @return List.
	 */

	
	
	/**
	 * To change password
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public EmployerProfileDTO getProfileAttributes();


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