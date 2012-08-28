package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.BrandingTemplateDTO;

import com.advanceweb.afc.jb.data.entities.AdmFacility;
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
@Repository("brandTemplateConversionHelper")
public class BrandTemplateConversionHelper {

	/**
	 * Converting EmpBrandTempDTO to merJpBrandingTemp
	 * 
	 * @param merJpBrandingTemp
	 * @return EmpBrandTempDTO
	 */
	public BrandingTemplateDTO transformEmpTempToEmpTempDTO(
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

		BrandingTemplateDTO jpBrandTempDTO = new BrandingTemplateDTO();
		jpBrandTempDTO.setJpBrandTempId(jpBrandingTemp.getTemplateId());
		jpBrandTempDTO.setTemplateName(jpBrandingTemp.getTemplateName());
		jpBrandTempDTO.setCompanyOverview(jpBrandingTemp.getCompanyOverview());
		
//		To be removed after mapping
		
//		jpBrandTempDTO.setEmployerId(jpBrandingTemp.getMerUser().getUserId());
		jpBrandTempDTO.setMainImagePath(jpBrandingTemp.getMainImagePath());
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
	public JpTemplate transformEmpTempDTOToEmpTemp( BrandingTemplateDTO brandingTemplatesDTO)
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
		jpBrandTemp.setMainImagePath(brandingTemplatesDTO.getMainImagePath());
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
		
//		JP job does not seem to be requiring to persist
//		JpJob jpJob1 = new JpJob();
//		jpJob1.setJobId(13100);
//		JpJob jpJob2 = new JpJob();
//		jpJob2.setJobId(13101);
//		jpBrandTemp.getJpJobs().add(jpJob1);
//		jpBrandTemp.getJpJobs().add(jpJob2);
		
		

//		Create dummy media
		JpTemplateMedia media1 = new JpTemplateMedia();
		media1.setMediaPath(brandingTemplatesDTO.getAddImagePath());
		media1.setMediaType("Additional Image");
		
		JpTemplateMedia media2 = new JpTemplateMedia();
		media2.setMediaPath(brandingTemplatesDTO.getVideoPath());
		media2.setMediaType("Video");
		
		ArrayList<JpTemplateMedia> mediaList = new ArrayList<JpTemplateMedia>();
		mediaList.add(media1);
		mediaList.add(media2);
		jpBrandTemp.setJpTemplateMedias(mediaList);
		
		media1.setJpTemplate(jpBrandTemp);
		media2.setJpTemplate(jpBrandTemp);
		
//		Create dummy testimony
		JpTemplateTestimonial testimony1 = new JpTemplateTestimonial();
//		testimony1.setTestimonial("Testimonial 1");
		testimony1.setTestimonial(brandingTemplatesDTO.getTestimony());
//		JpTemplateTestimonial testimony2 = new JpTemplateTestimonial();
//		testimony2.setTestimonial("Testimonial 2");
		
		ArrayList<JpTemplateTestimonial> testimonyList = new ArrayList<JpTemplateTestimonial>();
		testimonyList.add(testimony1);
//		testimonyList.add(testimony2);
		jpBrandTemp.setJpTemplateTestimonials(testimonyList);
				
		testimony1.setJpTemplate(jpBrandTemp);
//		testimony2.setJpTemplate(jpBrandTemp);
		
		return jpBrandTemp;

	}

}
