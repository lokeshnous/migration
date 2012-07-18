package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.employer.dao.EmployerBrandingTemplatesDAO;

/**
 * <code> EmployerBrandingTemplatesService </code> is a Service class for
 * EmployerBrandingTemplates.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Service("employerBrandingTemplates")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployerBrandingTemplatesService implements
		EmployerBrandingTemplates {

	@Autowired
	private EmployerBrandingTemplatesDAO employerBrandingTemplatesDAO;

	/**
	 * Fetch the job posting Branding Templates
	 */
	@Override
	public List<EmployerBrandingTemplatesDTO> fetchJobPostingBrandingTemplates(
			MerUserDTO merUserDTO) {
		List<EmployerBrandingTemplatesDTO> templatesDTOs = employerBrandingTemplatesDAO
				.fetchJobPostingBrandingTemplates(merUserDTO);
		return templatesDTOs;
	}

	/**
	 * Create the job posting Branding Template.
	 */
	@Override
	public Boolean createJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = employerBrandingTemplatesDAO
				.createJobPostingBrandingTemplates(brandingTemplatesDTO);
		return status;		
	}

	/**
	 * View the job posting Branding Template.
	 */
	@Override
	public EmployerBrandingTemplatesDTO viewJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		EmployerBrandingTemplatesDTO employerBrandingTemplatesDTO = null;
		employerBrandingTemplatesDTO = employerBrandingTemplatesDAO
				.viewJobPostingBrandingTemplates(brandingTemplatesDTO);
		return employerBrandingTemplatesDTO;
	}

	/**
	 * Edit the job posting Branding Template.
	 */
	@Override
	public EmployerBrandingTemplatesDTO editJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		EmployerBrandingTemplatesDTO employerBrandingTemplatesDTO = null;
		employerBrandingTemplatesDTO = employerBrandingTemplatesDAO
				.editJobPostingBrandingTemplates(brandingTemplatesDTO);
		return employerBrandingTemplatesDTO;
	}

	/**
	 * Delete the job posting Branding Template.
	 */
	@Override
	public Boolean deleteJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		Boolean status = null;
		status = employerBrandingTemplatesDAO
				.deleteJobPostingBrandingTemplates(brandingTemplatesDTO);
		return status;	
	}


}
