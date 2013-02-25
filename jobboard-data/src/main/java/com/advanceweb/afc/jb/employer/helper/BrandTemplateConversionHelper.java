/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddImageDTO;
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.TestimonyDTO;
import com.advanceweb.afc.jb.common.VideoDTO;
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
	public BrandingTemplateDTO convertToBrandTemplateDTO(
			JpTemplate jpBrandingTemp,int count) {

		BrandingTemplateDTO jpBrandTempDTO = new BrandingTemplateDTO();
		jpBrandTempDTO.setJpBrandTempId(jpBrandingTemp.getTemplateId());
		jpBrandTempDTO.setTemplateName(jpBrandingTemp.getTemplateName());
		jpBrandTempDTO.setCompanyOverview(jpBrandingTemp.getCompanyOverview());
		jpBrandTempDTO.setMainImagePath(jpBrandingTemp.getMainImagePath());
		jpBrandTempDTO.setLogoPath(jpBrandingTemp.getLogoPath());
		jpBrandTempDTO.setColor(jpBrandingTemp.getColorPalette());
		jpBrandTempDTO.setCreatedDate(CommonUtil.convertSQLDateTimeToStdDateTime(jpBrandingTemp.getCreateDt().toString()));
		
//		Multi media section

		jpBrandTempDTO.setListTestimony(transformTemplateTestimonyToDTO(jpBrandingTemp));
		jpBrandTempDTO.setListAddImages(transformAddImageToDTO(jpBrandingTemp));
		jpBrandTempDTO.setListVideos(transformVideoToDTO(jpBrandingTemp));

		// added to set the number of time used by
		jpBrandTempDTO.setCount(count);
			
		return jpBrandTempDTO;

	}

	/**
	 * Converting merJpBrandingTemp to EmpBrandTempDTO
	 * 
	 * @param merJpBrandingTemp
	 * @return merJpBrandingTemp
	 */
	public JpTemplate convertToJPTemplate(
			BrandingTemplateDTO brandingTemplatesDTO)
	{
		JpTemplate jpBrandTemp = new JpTemplate();

		jpBrandTemp.setTemplateId(brandingTemplatesDTO.getJpBrandTempId());
		jpBrandTemp.setTemplateName(brandingTemplatesDTO.getTemplateName());
		jpBrandTemp.setCompanyOverview(brandingTemplatesDTO.getCompanyOverview());
		jpBrandTemp.setMainImagePath(brandingTemplatesDTO.getMainImagePath());
		jpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
		jpBrandTemp.setColorPalette(brandingTemplatesDTO.getColor());
		jpBrandTemp.setCreateDt(new Date());
		jpBrandTemp.setCreateUserId(brandingTemplatesDTO.getEmployerId());
		
		AdmFacility facility = new AdmFacility();
		facility.setFacilityId(brandingTemplatesDTO.getFacilityId());
		jpBrandTemp.setAdmFacility(facility);

		if (!brandingTemplatesDTO.getIsSilverCustomer()){
			List<JpTemplateTestimonial> listTestimonyEntity = transformTemplateTestimony(brandingTemplatesDTO.getListTestimony(), jpBrandTemp);
			List<JpTemplateMedia> listMediaEntity = transformVideo(brandingTemplatesDTO.getListVideos(), jpBrandTemp);
			listMediaEntity.addAll(transformAddImage(brandingTemplatesDTO.getListAddImages(), jpBrandTemp));
			
			jpBrandTemp.setJpTemplateTestimonials(listTestimonyEntity);
			jpBrandTemp.setJpTemplateMedias(listMediaEntity);
		}else{
			jpBrandTemp.setJpTemplateTestimonials(new ArrayList<JpTemplateTestimonial>());
			jpBrandTemp.setJpTemplateMedias(new ArrayList<JpTemplateMedia>());
		}
		
		return jpBrandTemp;
	}
	
	
	
	
	/**
	 * This method is called to convert List of Testimony DTO to 
	 * JPTemplateTestimonial Entity
	 * 
	 * @param listTestimonyDTO
	 * @param jpTemplate
	 * @return testimonyEntityList
	 */
	public List<JpTemplateTestimonial> transformTemplateTestimony(
		List<TestimonyDTO> listTestimonyDTO, JpTemplate jpTemplate) {
	
		List<JpTemplateTestimonial> testimonyEntityList = new ArrayList<JpTemplateTestimonial>();
		if (null != testimonyEntityList) {
			for (TestimonyDTO dto : listTestimonyDTO) {
				JpTemplateTestimonial entity = new JpTemplateTestimonial();
				
				entity.setTestimonial(dto.getTestimony());
				entity.setTemplateTestimonialId(dto.getTestimonyId());
				entity.setJpTemplate(jpTemplate);
				testimonyEntityList.add(entity);
			}
		}
		return testimonyEntityList;
	}
	

	/**
	 * This method is called to convert JPTemplateTestimonial Entity to 
	 * List of Testimony DTO  
	 *  
	 * @param templateEntity
	 * @return listTestimonyDTO
	 */
	public List<TestimonyDTO> transformTemplateTestimonyToDTO(JpTemplate templateEntity) {
		List<TestimonyDTO> listTestimonyDTO = new ArrayList<TestimonyDTO>();
		if (null != templateEntity.getJpTemplateTestimonials()) {
			for (JpTemplateTestimonial entity : templateEntity.getJpTemplateTestimonials()) {
				TestimonyDTO dto = new TestimonyDTO();
				
				dto.setTestimony(entity.getTestimonial());
				dto.setTestimonyId(entity.getTemplateTestimonialId());	
				
				listTestimonyDTO.add(dto);
			}
		}
		return listTestimonyDTO;
	}
	
	
	/**
	 * This method is called to convert List of AddImage DTO to 
	 * JPTemplateMedia Entity
	 * 
	 * @param listAddImageDTO
	 * @param jpTemplate
	 * @return addImageEntityList
	 */
	public List<JpTemplateMedia> transformAddImage(
		List<AddImageDTO> listAddImageDTO, JpTemplate jpTemplate) {
		List<JpTemplateMedia> addImageEntityList = new ArrayList<JpTemplateMedia>();
		if (null != addImageEntityList) {
			for (AddImageDTO dto : listAddImageDTO) {
				JpTemplateMedia entity = new JpTemplateMedia();
				
				entity.setMediaPath(dto.getMediaPath());
				entity.setMediaType(dto.getMediaType());
				entity.setTemplateMediaId(dto.getAddImageId());
				
				entity.setJpTemplate(jpTemplate);
				addImageEntityList.add(entity);
			}
		}
		return addImageEntityList;
	}
	

	/**
	 * This method is called to convert JPTemplateTestimonial Entity to 
	 * List of AddImage DTO  
	 *  
	 * @param templateEntity
	 * @return listAddImageDTO
	 */
	public List<AddImageDTO> transformAddImageToDTO(JpTemplate templateEntity) {
		List<AddImageDTO> listAddImageDTO = new ArrayList<AddImageDTO>();
		if (null != templateEntity.getJpTemplateMedias()) {
			for (JpTemplateMedia entity : templateEntity.getJpTemplateMedias()) {
				if(entity.getMediaType().equalsIgnoreCase("Additional Image"))
				{
				AddImageDTO dto = new AddImageDTO();
				
				dto.setMediaPath(entity.getMediaPath());
				dto.setMediaType(entity.getMediaType());
				dto.setAddImageId(entity.getTemplateMediaId());
						
				listAddImageDTO.add(dto);
				}
			}
		}
		return listAddImageDTO;
	}
	
	
	/**
	 * This method is called to convert List of Video DTO to 
	 * JPTemplateMedia Entity
	 * 
	 * @param listVideoDTO
	 * @param jpTemplate
	 * @return videoEntityList
	 */
	public List<JpTemplateMedia> transformVideo(
		List<VideoDTO> listVideoDTO, JpTemplate jpTemplate) {
		List<JpTemplateMedia> videoEntityList = new ArrayList<JpTemplateMedia>();
		if (null != videoEntityList) {
			for (VideoDTO dto : listVideoDTO) {
				JpTemplateMedia entity = new JpTemplateMedia();
				
				entity.setMediaPath(dto.getMediaPath());
				entity.setMediaType(dto.getMediaType());
				entity.setTemplateMediaId(dto.getVideoId());
				
				entity.setJpTemplate(jpTemplate);
				videoEntityList.add(entity);
			}
		}
		return videoEntityList;
	}
	

	/**
	 * This method is called to convert JPTemplateTestimonial Entity to 
	 * List of Video DTO  
	 *  
	 * @param templateEntity
	 * @return listVideoDTO
	 */
	public List<VideoDTO> transformVideoToDTO(JpTemplate templateEntity) {
		List<VideoDTO> listVideoDTO = new ArrayList<VideoDTO>();
		if (null != templateEntity.getJpTemplateMedias()) {
			for (JpTemplateMedia entity : templateEntity.getJpTemplateMedias()) {
				if(entity.getMediaType().equalsIgnoreCase("Video"))
				{
				VideoDTO dto = new VideoDTO();
				
				dto.setMediaPath(entity.getMediaPath());
				dto.setMediaType(entity.getMediaType());
				dto.setVideoId(entity.getTemplateMediaId());
				
				listVideoDTO.add(dto);
				}
			}
		}
		return listVideoDTO;
	}

}
