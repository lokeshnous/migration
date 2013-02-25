/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AdmFacilityContactDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:44 PM
 */
public interface EmployerRegistrationDAO {

	/**
	 * This method is used to create a User(Employer) in job board.
	 * 
	 * @param Object of EmployerProfileDTO
	 * @return Object of UserDTO
	 */
	UserDTO createUser(EmployerProfileDTO empDTO);

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

	/**
	 * Gets the employee data.
	 *
	 * @param userId the user id
	 * @param contactType the contact type
	 * @return the employee data
	 */
	List<AdmFacilityContact> getEmployeeData(int userId, String contactType);

	/**
	 * 
	 * @param userId
	 *            userId.
	 * @return userid.
	 */
	AdmFacilityContactDTO getEmployeePrimaryKey(int userId,
			String contactType);

	/**
	 * This method is used to edit and update  a User(Employer) in job board.
	 * 
	 * @param Object of AccountProfileDTO
	 * @param int admfacilityid
	 * @param int userId
	 * @param  String billing
	 * @return boolean
	 */
	
	boolean editUser(AccountProfileDTO apd, int admfacilityid,
			int userId, String billing);

	/**
	 * Validate profile attributes.
	 *
	 * @param jobseekerId the jobseeker id
	 * @return true, if successful
	 */
	boolean validateProfileAttributes(int jobseekerId); 
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	
	FacilityDTO getNSCustomerIDFromAdmFacility(int id);
	

}