package com.advanceweb.afc.jb.employer.service;


import java.util.List;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;

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
	void saveEmployerProfile(CompanyProfileDTO companyProfileDTO);

	CompanyProfileDTO getEmployerDetails(long employerId);
	
	List<EmployerProfileDTO> getEmployerAccountDetails(long employerId);
	
	
}