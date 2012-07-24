package com.advanceweb.afc.jb.employer.dao;


import java.util.List;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;

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
	boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO);
	
	CompanyProfileDTO getEmployerDetails(long employerId);
	
	List<EmployerProfileDTO> getEmployerAccountDetails(long employerId);

	List<CompanyProfileDTO> getEmployerList(); 

}
