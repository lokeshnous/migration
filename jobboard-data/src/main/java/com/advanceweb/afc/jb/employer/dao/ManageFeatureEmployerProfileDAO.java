package com.advanceweb.afc.jb.employer.dao;


import com.advanceweb.afc.jb.common.CompanyProfileDTO;

/**
 * Created EmployerJobPostingDAO
 * 
 * @author sharadk
 * 
 */
public interface ManageFeatureEmployerProfileDAO {

	/**
	 * Saving Manage Featured Employer Profile
	 * 
	 * @param companyProfileDTO
	 */
	void saveEmployerProfile(CompanyProfileDTO companyProfileDTO);
	
	CompanyProfileDTO getEmployerDetails(long employerId);

}
