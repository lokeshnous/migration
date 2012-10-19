package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.employer.dao.ManageFeatureEmployerProfileDAO;

/**
 * <code> ManageFeatureEmployerProfileService </code> is a Service class.
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
		ManageFeatureEmployerProfile {

	@Autowired
	private ManageFeatureEmployerProfileDAO manageFeatureEmployerProfileDAO;
	
	@Autowired
	private ManageFeatureEmployerDelegate manageFeatureEmployerDelegate;

	/**
	 * Saving Manage Featured Employer Profile
	 */
	@Override
	public boolean saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {
		return manageFeatureEmployerProfileDAO.saveEmployerProfile(companyProfileDTO);

	}

	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		
		return manageFeatureEmployerProfileDAO.getEmployerDetails(employerId);
	}

	@Override
	public List<CompanyProfileDTO> getEmployerList() {
		
		return manageFeatureEmployerProfileDAO.getEmployerList();
	}

	@Override
	public List<EmployerProfileDTO> getEmployerAccountDetails(long employerId) {
		return manageFeatureEmployerProfileDAO.getEmployerAccountDetails(employerId);
	}
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	public int getNSCustomerIDFromAdmFacility(int admFacilityID){
		return manageFeatureEmployerDelegate.getNSCustomerIDFromAdmFacility(admFacilityID);
		
	}
	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	
	public UserDTO getNSCustomerDetails(int nsCustomerID){
		return  manageFeatureEmployerDelegate.getNSCustomerDetails(nsCustomerID);
	}

	/**
	 * Get the employer List by start row and end row
	 * 
	 */
	@Override
	public List<CompanyProfileDTO> getEmployerList(int startRow, int endRow) {
		return manageFeatureEmployerProfileDAO.getEmployerList(startRow, endRow);
	}

	/**
	 * Get the Employer List count
	 */
	@Override
	public Long getEmployerListCount() {
		Long count = manageFeatureEmployerProfileDAO.getEmployerListCount();
		return count;
	}
	

}
