package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.MerJpBrandingTemp;

/**
 * <code>EmpBrandTempConversionHelper</code> is a Conversion Helper class
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
@Repository("empBrandTempConversionHelper")
public class EmpBrandTempConversionHelper {

	/**
	 * Converting EmpBrandTempDTO to merJpBrandingTemp
	 * 
	 * @param merJpBrandingTemp
	 * @return EmpBrandTempDTO
	 */
	public EmpBrandTempDTO transformEmpTempToEmpTempDTO(
			MerJpBrandingTemp merJpBrandingTemp) {
		EmpBrandTempDTO merJpBrandTempDTO = new EmpBrandTempDTO();
		merJpBrandTempDTO
				.setJpBrandTempId(merJpBrandingTemp.getJpBrandTempId());
		merJpBrandTempDTO.setDescription(merJpBrandingTemp.getDescription());
		merJpBrandTempDTO.setEmployerId(merJpBrandingTemp.getUserId());
		merJpBrandTempDTO.setImagePath(merJpBrandingTemp.getImagePath());
		merJpBrandTempDTO.setLogoPath(merJpBrandingTemp.getLogoPath());
		merJpBrandTempDTO.setColor(merJpBrandingTemp.getColor());
		merJpBrandTempDTO.setCreatedDate(merJpBrandingTemp.getCreatedDate());
		String updateDate = DateUtils.convertSQLDateToStdDate(merJpBrandingTemp.getUpdatedDate().toString());
		merJpBrandTempDTO.setUpdatedDate(updateDate);
		return merJpBrandTempDTO;

	}

	/**
	 * Converting merJpBrandingTemp to EmpBrandTempDTO
	 * 
	 * @param merJpBrandingTemp
	 * @return merJpBrandingTemp
	 */
	public MerJpBrandingTemp transformEmpTempDTOToEmpTemp(
			EmpBrandTempDTO brandingTemplatesDTO) {
		MerJpBrandingTemp merJpBrandTemp = new MerJpBrandingTemp();
		merJpBrandTemp
				.setJpBrandTempId(brandingTemplatesDTO.getJpBrandTempId());
		merJpBrandTemp.setDescription(brandingTemplatesDTO.getDescription());
		merJpBrandTemp.setUserId((int) brandingTemplatesDTO.getEmployerId());
		merJpBrandTemp.setImagePath(brandingTemplatesDTO.getImagePath());
		merJpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
		merJpBrandTemp.setColor(brandingTemplatesDTO.getColor());
		merJpBrandTemp.setCreatedDate(brandingTemplatesDTO.getCreatedDate());
		java.sql.Date updateDate = DateUtils.convertStringToSQLDate(brandingTemplatesDTO.getUpdatedDate());
		merJpBrandTemp.setUpdatedDate(updateDate);
		return merJpBrandTemp;

	}

}
