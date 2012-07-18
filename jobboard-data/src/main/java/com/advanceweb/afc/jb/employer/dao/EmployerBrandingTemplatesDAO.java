package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

/**
 * <code>EmployerBrandingTemplatesDAO</code> is a DAO.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public interface EmployerBrandingTemplatesDAO {

	/**
	 * Fetch the job posting Branding Templates
	 * 
	 * @param merUserDTO
	 * @return List of EmployerBrandingTemplatesDTO
	 */
	List<EmployerBrandingTemplatesDTO> fetchJobPostingBrandingTemplates(
			MerUserDTO merUserDTO);

	/**
	 * Create the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> If template created <false> If template not created
	 */
	Boolean createJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * View the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return EmployerBrandingTemplatesDTO
	 */
	EmployerBrandingTemplatesDTO viewJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * Edit the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return EmployerBrandingTemplatesDTO
	 */
	EmployerBrandingTemplatesDTO editJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

	/**
	 * Delete the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> If template deleted <false> If template not deleted
	 */
	Boolean deleteJobPostingBrandingTemplates(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO);

}