package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.EmpBrandTempDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.JpTemplateMedia;
import com.advanceweb.afc.jb.data.entities.JpTemplateTestimonial;

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
			JpTemplate jpBrandingTemp) {
//		EmpBrandTempDTO merJpBrandTempDTO = new EmpBrandTempDTO();
//		merJpBrandTempDTO
//				.setJpBrandTempId(merJpBrandingTemp.getJpBrandTempId());
//		merJpBrandTempDTO.setDescription(merJpBrandingTemp.getDescription());
//		merJpBrandTempDTO.setEmployerId(merJpBrandingTemp.getUserId());
//		merJpBrandTempDTO.setImagePath(merJpBrandingTemp.getImagePath());
//		merJpBrandTempDTO.setLogoPath(merJpBrandingTemp.getLogoPath());
//		merJpBrandTempDTO.setColor(merJpBrandingTemp.getColor());
//		merJpBrandTempDTO.setCreatedDate(merJpBrandingTemp.getCreatedDate());
//		Date updateDate = merJpBrandingTemp.getUpdatedDate();
//		String strUpdateDate = null;
//		if (updateDate != null) {
//			strUpdateDate = DateUtils.convertSQLDateToStdDateString(updateDate
//					.toString());
//		}
//		merJpBrandTempDTO.setUpdatedDate(strUpdateDate);
//		return merJpBrandTempDTO;

		EmpBrandTempDTO jpBrandTempDTO = new EmpBrandTempDTO();
		jpBrandTempDTO.setJpBrandTempId(jpBrandingTemp.getTemplateId());
		jpBrandTempDTO.setTemplateName(jpBrandingTemp.getTemplateName());
		jpBrandTempDTO.setCompanyOverview(jpBrandingTemp.getCompanyOverview());
		
//		To be removed after mapping
		
//		jpBrandTempDTO.setEmployerId(jpBrandingTemp.getMerUser().getUserId());
		jpBrandTempDTO.setImagePath(jpBrandingTemp.getMainImagePath());
		jpBrandTempDTO.setLogoPath(jpBrandingTemp.getLogoPath());
		jpBrandTempDTO.setColor(jpBrandingTemp.getColorPalette());
		jpBrandTempDTO.setCreatedDate(jpBrandingTemp.getCreateDt());
//		Date updateDate = jpBrandingTemp.getUpdatedDate();
//		String strUpdateDate = null;
//		if (updateDate != null) {
//			strUpdateDate = DateUtils.convertSQLDateToStdDateString(updateDate
//					.toString());
//		}
//		jpBrandTempDTO.setUpdatedDate(strUpdateDate);
		return jpBrandTempDTO;

	}

	/**
	 * Converting merJpBrandingTemp to EmpBrandTempDTO
	 * 
	 * @param merJpBrandingTemp
	 * @return merJpBrandingTemp
	 */
	public JpTemplate transformEmpTempDTOToEmpTemp( EmpBrandTempDTO brandingTemplatesDTO)
//			 {
//		MerJpBrandingTemp merJpBrandTemp = new MerJpBrandingTemp();
//		merJpBrandTemp
//				.setJpBrandTempId(brandingTemplatesDTO.getJpBrandTempId());
//		merJpBrandTemp.setDescription(brandingTemplatesDTO.getDescription());
//		merJpBrandTemp.setUserId((int) brandingTemplatesDTO.getEmployerId());
//		merJpBrandTemp.setImagePath(brandingTemplatesDTO.getImagePath());
//		merJpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
//		merJpBrandTemp.setColor(brandingTemplatesDTO.getColor());
//		merJpBrandTemp.setCreatedDate(brandingTemplatesDTO.getCreatedDate());
//		String strUpdateDate = brandingTemplatesDTO.getUpdatedDate();
//		java.sql.Date updateDate = null;
//		if (strUpdateDate != null) {
//			updateDate = DateUtils.convertDateStringToSQLDate(strUpdateDate);
//		}
//		merJpBrandTemp.setUpdatedDate(updateDate);
//		return merJpBrandTemp;

			
			 {
		JpTemplate jpBrandTemp = new JpTemplate();
		jpBrandTemp.setTemplateId(brandingTemplatesDTO.getJpBrandTempId());
//		jpBrandTemp.setAdmFacility(brandingTemplatesDTO.getFacilityId());
		jpBrandTemp.setTemplateName(brandingTemplatesDTO.getTemplateName());
		jpBrandTemp.setCompanyOverview(brandingTemplatesDTO.getCompanyOverview());
		
//		To be removed after mapping
//		MerUser merUser = new MerUser();
//		merUser.setUserId(brandingTemplatesDTO.getEmployerId());
//		jpBrandTemp.setMerUser(merUser);
		jpBrandTemp.setMainImagePath(brandingTemplatesDTO.getImagePath());
		jpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
		jpBrandTemp.setColorPalette(brandingTemplatesDTO.getColor());
		jpBrandTemp.setCreateDt(brandingTemplatesDTO.getCreatedDate());
//		String strUpdateDate = brandingTemplatesDTO.getUpdatedDate();
//		java.sql.Date updateDate = null;
//		if (strUpdateDate != null) {
//			updateDate = DateUtils.convertDateStringToSQLDate(strUpdateDate);
//		}
//		jpBrandTemp.setUpdatedDate(updateDate);
		
		AdmFacility facility = new AdmFacility();
		facility.setFacilityId(111);
		jpBrandTemp.setAdmFacility(facility);
		
		JpJob jpJob1 = new JpJob();
		jpJob1.setJobId(13100);
		JpJob jpJob2 = new JpJob();
		jpJob2.setJobId(13101);
		ArrayList<JpJob> jpjobList = new ArrayList<JpJob>();
		jpjobList.add(jpJob1);
		jpjobList.add(jpJob2);
		jpBrandTemp.setJpJobs(jpjobList);
		
		JpTemplateMedia media1 = new JpTemplateMedia();
		media1.setTemplateMediaId(1);
		JpTemplateMedia media2 = new JpTemplateMedia();
		media2.setTemplateMediaId(2);
		ArrayList<JpTemplateMedia> mediaList = new ArrayList<JpTemplateMedia>();
		mediaList.add(media1);
		mediaList.add(media2);
		jpBrandTemp.setJpTemplateMedias(mediaList);

		JpTemplateTestimonial testimony1 = new JpTemplateTestimonial();
		testimony1.setTemplateTestimonialId(1);
		JpTemplateTestimonial testimony2 = new JpTemplateTestimonial();
		testimony2.setTemplateTestimonialId(2);
		ArrayList<JpTemplateTestimonial> testimonyList = new ArrayList<JpTemplateTestimonial>();
		testimonyList.add(testimony1);
		testimonyList.add(testimony2);
		jpBrandTemp.setJpTemplateTestimonials(testimonyList);
		
		
		
		return jpBrandTemp;

	}

}
