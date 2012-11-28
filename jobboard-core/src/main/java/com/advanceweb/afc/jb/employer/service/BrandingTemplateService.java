package com.advanceweb.afc.jb.employer.service;

import java.util.List;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;

/**
 * <code>EmpBrandTemp</code>This interface help to manage job posting Branding
 * Template
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public interface BrandingTemplateService {

	/**
	 * Fetch the job posting Branding Templates
	 * 
	 * @param merUserDTO
	 * @return
	 */
	List<BrandingTemplateDTO> getBrandingTemplate(int userId);

	/**
	 * Create the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	Boolean createEmpBrandTemp(BrandingTemplateDTO brandingTemplatesDTO);

	/**
	 * View the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
	 */
	BrandingTemplateDTO viewEmpBrandTemp(
			BrandingTemplateDTO brandingTemplatesDTO);

	/**
	 * Edit the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return
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
	 * @return
	 */
	boolean deleteBrandingTemplate(int templateId, int deleteUserId);
	
	/**
	 * This method checks if any active job is using the template
	 * 
	 * @param templateId
	 * @return boolean
	 */
	boolean checkTemplateUsage(int templateId);
	
	/**
	 * This method checks if the template limit has exceeded the limit
	 * 
	 * @param facilityId
	 * @return boolean
	 */
	boolean checkTemplateLimit(int facilityId);
	
	/**
	 * This method checks if the template Name already exists
	 * 
	 * @param facilityId
	 * @param templateName
	 * 
	 * @return boolean
	 */
	boolean checkTemplateName(int facilityId, String templateName);
	
	/**
	 * Fetch the employer Branding information
	 * 
	 * @param facilityId
	 * @return List<String>
	 */
	List<String> getBrandingInformation(int facilityId);

	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	int getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * This method is used the get the Branding Template Purchase information
	 * @param facilityId
	 * @return boolean
	 */
	boolean getBrandPurchaseInfo(int facilityId);
	
	/**
	 * This method is used the get the Branding Template Package information
	 * @param productId
	 * @return boolean
	 */
	boolean getBrandPackage(int productId);
	
	

}