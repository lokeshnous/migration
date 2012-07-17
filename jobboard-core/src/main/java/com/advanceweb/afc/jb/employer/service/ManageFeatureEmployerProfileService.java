package com.advanceweb.afc.jb.employer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
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
	public void saveEmployerProfile(CompanyProfileDTO companyProfileDTO) {
		manageFeatureEmployerProfileDAO.saveEmployerProfile(companyProfileDTO);

	}

	@Override
	public CompanyProfileDTO getEmployerDetails(long employerId) {
		
		return manageFeatureEmployerProfileDAO.getEmployerDetails(employerId);
	}

}
