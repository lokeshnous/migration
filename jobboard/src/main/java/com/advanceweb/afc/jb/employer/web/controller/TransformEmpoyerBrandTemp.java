package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;

/**
 * 
 * @author Harsha
 *
 * @Version 1.0
 * @Since 22nd August, 2012
 */

@Repository("transformEmpoyerBrandTemp")
public class TransformEmpoyerBrandTemp {

	public BrandingTemplateDTO createEmpBrandTempDTO(BrandingTemplateForm brandingTemplateForm) {

		BrandingTemplateDTO dto = new BrandingTemplateDTO();

		

		// int userId = (Integer)session.getAttribute(MMJBCommonConstants.USER_ID);
		dto.setEmployerId(26);
		dto.setFacilityId(1);
		
		dto.setLogoPath(brandingTemplateForm.getLogoPath());
		dto.setColor(brandingTemplateForm.getColor());
		dto.setCreatedDate(new Date());
		dto.setTemplateName(brandingTemplateForm.getTemplateName());
		dto.setCompanyOverview(brandingTemplateForm.getCompanyOverview());
		dto.setMainImagePath(brandingTemplateForm.getMainImagePath());
		dto.setLogoFileData(brandingTemplateForm.getLogoFileData());
		dto.setMainImageFileData(brandingTemplateForm.getMainImageFileData());
		
//		Multi media section
		dto.setTestimony(brandingTemplateForm.getTestimony());
		dto.setAddImagePath(brandingTemplateForm.getAddImagePath());
		dto.setAddImageFileData(brandingTemplateForm.getAddImageFileData());
		dto.setVideoPath(brandingTemplateForm.getVideoPath());
		dto.setVideoFileData(brandingTemplateForm.getVideoFileData());
		
		dto.setIsSilverCustomer(brandingTemplateForm.getIsSilverCustomer());
		
		return dto;
	}

}
