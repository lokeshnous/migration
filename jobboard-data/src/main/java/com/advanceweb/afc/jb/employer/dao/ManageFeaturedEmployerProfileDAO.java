package com.advanceweb.afc.jb.employer.dao;


import java.util.List;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;

/**
 * Created EmployerJobPostingDAO
 * 
 * @author sharadk
 * 
 */
public interface ManageFeaturedEmployerProfileDAO {

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
	 * @return List<FacilityDTO>
	 */
	List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID);
	

}
