package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;

/**
 * <code>EmpBrandTempDAO</code> is a DAO.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public interface BrandingTemplateDAO {

	/**
	 * Fetch the job posting Branding Templates
	 * 
	 * @param merUserDTO
	 * @return List of EmpBrandTempDTO
	 */
	List<BrandingTemplateDTO> getBrandingTemplate(int userId);

	/**
	 * Create the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> If template created <false> If template not created
	 */
	Boolean createEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO);

	/**
	 * View the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return EmpBrandTempDTO
	 */
	BrandingTemplateDTO viewEmpBrandTemp(
			BrandingTemplateDTO brandingTemplatesDTO);

	/**
	 * Edit the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return EmpBrandTempDTO
	 */
	BrandingTemplateDTO editBrandingTemplate(int templateId);

	/**
	 * Update the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> if successfully updated otherwise <false>
	 */
	boolean updateBrandingTemplate(BrandingTemplateDTO brandingTemplatesDTO);

	/**
	 * Delete the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> If template deleted <false> If template not deleted
	 */
	boolean deleteBrandingTemplate(int templateId);

}