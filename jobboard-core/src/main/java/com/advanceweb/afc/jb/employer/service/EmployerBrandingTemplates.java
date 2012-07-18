package com.advanceweb.afc.jb.employer.service;


import java.util.List;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

/**
 * <code>EmployerBrandingTemplate</code>This interface help 
 * to manage job posting Branding Template
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public interface EmployerBrandingTemplates {

	/**
	 * Fetch the job posting Branding Templates
	 * 
	 * @param merUserDTO
	 * @return
	 */
	List<EmployerBrandingTemplatesDTO> fetchJobPostingBrandingTemplates(
			MerUserDTO merUserDTO);

	/**
	 * Create the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	Boolean createJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * View the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	EmployerBrandingTemplatesDTO viewJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * Edit the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	EmployerBrandingTemplatesDTO editJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * Delete the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	Boolean deleteJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);
	
}