package com.advanceweb.afc.jb.job.helper;

import org.springframework.stereotype.Repository;
import com.advanceweb.afc.jb.common.AdmBrndngTempDTO;
import com.advanceweb.afc.jb.data.entities.AdmBrandingTemp;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 20, 2012
 */
@Repository("jobPostBrandTempConvnHelper")
public class JobPostBrandTempConvnHelper {

	/**
	 * This method is called to transform DTO to Entity 
	 * @param templateDTO
	 * @return AdmBrandingTemp
	 */
	public AdmBrandingTemp transformBrndngTempDTOToAdmBrandingTemp(
			AdmBrndngTempDTO templateDTO) {

		AdmBrandingTemp brandingTemplate = new AdmBrandingTemp();
		brandingTemplate.setBrandTempId(templateDTO.getBrandTempId());
		brandingTemplate.setUserId(templateDTO.getUserId());
		brandingTemplate.setTemplateName(templateDTO.getTemplateName());
		brandingTemplate.setBrandOverview(templateDTO.getBrandOverview());
		brandingTemplate.setTemplateImage(templateDTO.getTemplateImage());
		brandingTemplate.setTemplateLogo(templateDTO.getTemplateLogo());
		brandingTemplate.setBrandColor(templateDTO.getBrandColor());
		brandingTemplate.setCreatedDate(templateDTO.getCreatedDate());
		brandingTemplate.setModifiedDate(templateDTO.getModifiedDate());

		return brandingTemplate;

	}

}
