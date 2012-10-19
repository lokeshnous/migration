package com.advanceweb.afc.jb.employer.service;


import java.util.List;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * 
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 13 July 2012
 * 
 * 
 */
public interface ManageFeatureEmployerProfile {

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param companyProfileDTO
	 */
	boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO);

	CompanyProfileDTO getEmployerDetails(long employerId);
	
	List<EmployerProfileDTO> getEmployerAccountDetails(long employerId);

	List<CompanyProfileDTO> getEmployerList();
	List<CompanyProfileDTO> getEmployerList(int startRow, int endRow);
	Long getEmployerListCount();
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	
	int getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return Object of UserDTO
	 */
	
	UserDTO getNSCustomerDetails(int nsCustomerID);
	
}