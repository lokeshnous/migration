package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.ManageFeaturedEmployerProfileDAO;

/**
 * <code> ManageFeaturedEmployerProfileService </code> is a Service class.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 13 July 2012
 * 
 * 
 */
@Service("manageFeatureEmployerProfile")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ManageFeatureEmployerProfileService implements
		ManageFeaturedEmployerProfile {

	@Autowired
	private ManageFeaturedEmployerProfileDAO manageFeaturedEmployerProfileDAO;
	
	@Autowired
	private ManageFeaturedEmployerDelegate manageFeaturedEmployerDelegate;

	/**
	 * Saving Manage Featured Employer Profile
	 */
	@Override
	public boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {
		return manageFeaturedEmployerProfileDAO.saveEmployerProfile(companyProfileDTO);

	}

	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		
		return manageFeaturedEmployerProfileDAO.getEmployerDetails(employerId);
	}

	@Override
	public List<CompanyProfileDTO> getEmployerList() {
		
		return manageFeaturedEmployerProfileDAO.getEmployerList();
	}

	@Override
	public List<EmployerProfileDTO> getEmployerAccountDetails(long employerId) {
		return manageFeaturedEmployerProfileDAO.getEmployerAccountDetails(employerId);
	}
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	public int getNSCustomerIDFromAdmFacility(int admFacilityID){
		return manageFeaturedEmployerDelegate.getNSCustomerIDFromAdmFacility(admFacilityID);
		
	}
	
	/**
	 * This method is used to get the net suite customer purchased packages
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return List<String>
	 */
	
	public List<String> getNSCustomerPackages(int nsCustomerID){
		return  manageFeaturedEmployerDelegate.getNSCustomerPackages(nsCustomerID);
	}

	/**
	 * This method is used to get the net suite customer details
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	
	public UserDTO getNSCustomerDetails(int nsCustomerID){
		return  manageFeaturedEmployerDelegate.getNSCustomerDetails(nsCustomerID);
	}
	
	/**
	 * This method is used to get Featured employer package start/End dates
	 * based on adm facility id.
	 * 
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	
	public UserDTO getNSFeatureDates(int nsCustomerID){
		return  manageFeaturedEmployerDelegate.getNSFeatureDates(nsCustomerID);
	}
	/**
	 * Get the employer List by start row and end row
	 * 
	 */
	@Override
	public List<CompanyProfileDTO> getEmployerList(int startRow, int endRow) {
		return manageFeaturedEmployerProfileDAO.getEmployerList(startRow, endRow);
	}

	/**
	 * Get the Employer List count
	 */
	@Override
	public Long getEmployerListCount() {
		return manageFeaturedEmployerProfileDAO.getEmployerListCount();
	}
	

}
