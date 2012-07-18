package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmployerBrandingTemplatesDTO;
import com.advanceweb.afc.jb.data.entities.MerJpBrandingTemp;

/**
 * <code>EmployerBrandingTemplateConversionHelper</code> is a Conversion Helper class
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Repository("employerBrandingTemplateConversionHelper")
public class EmployerBrandingTemplateConversionHelper {
	
	/**
	 * Converting EmployerBrandingTemplatesDTO to merJpBrandingTemp
	 * 
	 * @param merJpBrandingTemp
	 * @return EmployerBrandingTemplatesDTO
	 */
	public EmployerBrandingTemplatesDTO transformEmpTempToEmpTempDTO(
			MerJpBrandingTemp merJpBrandingTemp) {
		EmployerBrandingTemplatesDTO merJpBrandingTemplatesDTO = new EmployerBrandingTemplatesDTO();
		merJpBrandingTemplatesDTO.setDescription(merJpBrandingTemp
				.getDescription());
		merJpBrandingTemplatesDTO.setImagePath(merJpBrandingTemp
				.getImagePath());
		merJpBrandingTemplatesDTO.setLogoPath(merJpBrandingTemp.getLogoPath());
		merJpBrandingTemplatesDTO.setColor(merJpBrandingTemp.getColor());
		merJpBrandingTemplatesDTO.setCreatedDate(merJpBrandingTemp
				.getCreatedDate());
		merJpBrandingTemplatesDTO.setUpdatedDate(merJpBrandingTemp
				.getUpdatedDate());
		return merJpBrandingTemplatesDTO;

	}
	
	/**
	 * Converting merJpBrandingTemp to EmployerBrandingTemplatesDTO
	 * 
	 * @param merJpBrandingTemp
	 * @return merJpBrandingTemp
	 */
	public MerJpBrandingTemp transformEmpTempDTOToEmpTemp(
			EmployerBrandingTemplatesDTO brandingTemplatesDTO) {
		MerJpBrandingTemp merJpBrandingTemp = new MerJpBrandingTemp();
		merJpBrandingTemp.setDescription(brandingTemplatesDTO
				.getDescription());
		merJpBrandingTemp.setImagePath(brandingTemplatesDTO
				.getImagePath());
		merJpBrandingTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
		merJpBrandingTemp.setColor(brandingTemplatesDTO.getColor());
		merJpBrandingTemp.setCreatedDate(brandingTemplatesDTO
				.getCreatedDate());
		merJpBrandingTemp.setUpdatedDate(brandingTemplatesDTO
				.getUpdatedDate());
		return merJpBrandingTemp;

	}
	
	
	
}
