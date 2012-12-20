package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.FacilityDTO;

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
	 * Delete the job posting Branding Template.
	 * 
	 * @param brandingTemplatesDTO
	 * @return <true> If template deleted <false> If template not deleted
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
	 * Delete the job posting Branding Template.
	 * 
	 * @param facilityId
	 * @return packageId 
	 */
	int getBrandingInformation(int facilityId);
	
	/**
	 * This method is used to get the net suite customer id based on
	 * adm facility id.
	 * @param int admFacilityID
	 * @return int NSCustomerID
	 */
	
	List<FacilityDTO> getNSCustomerIDFromAdmFacility(int admFacilityID);
	
	/**
	 * This method is used the get the Branding Template Purchase information
	 * @param facilityId
	 * @return boolean
	 */
	boolean getBrandPurchaseInfo(int facilityId);
	
	/**
	 * This method is used the get the Branding Template Package information
	 * @param facilityId
	 * @return boolean
	 */
	boolean getBrandPackage(int productId);
	

}