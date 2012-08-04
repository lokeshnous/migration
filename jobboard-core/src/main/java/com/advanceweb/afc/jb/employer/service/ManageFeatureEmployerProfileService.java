package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
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
	ManageFeatureEmployerProfileDAO manageFeatureEmployerProfileDAO;

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

}
