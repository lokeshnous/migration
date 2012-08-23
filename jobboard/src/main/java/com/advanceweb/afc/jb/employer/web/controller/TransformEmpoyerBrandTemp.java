package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;

/**
 * 
 * @author Harsha
 *
 * @Version 1.0
 * @Since 22nd August, 2012
 */

@Repository("transformEmpoyerBrandTemp")
public class TransformEmpoyerBrandTemp {

	public EmpBrandTempDTO createEmpBrandTempDTO(BrandingTemplateForm brandingTemplateForm) {

		EmpBrandTempDTO dto = new EmpBrandTempDTO();

		dto.setTemplateName(brandingTemplateForm.getTemplateName());

		// int userId = (Integer)session.getAttribute(MMJBCommonConstants.USER_ID);
		dto.setEmployerId(26);
		dto.setImagePath(brandingTemplateForm.getMainImage());
		dto.setLogoPath(brandingTemplateForm.getLogoPath());
		dto.setColor(brandingTemplateForm.getColor());
		dto.setCreatedDate(new Date());
		dto.setCompanyOverview(brandingTemplateForm.getCompanyOverview());
		dto.setFacilityId(1);
		dto.setLogoFileData(brandingTemplateForm.getLogoFileData());
		dto.setMainImageFileData(brandingTemplateForm.getMainImageFileData());
		return dto;
	}

}
