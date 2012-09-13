package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddImageDTO;
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
import com.advanceweb.afc.jb.common.TestimonyDTO;
import com.advanceweb.afc.jb.common.VideoDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;

/**
 * 
 * @author Harsha
 * 
 * @Version 1.0
 * @Since 22nd August, 2012
 */

@Repository("transformEmpoyerBrandTemplate")
public class TransformEmployerBrandTemplate {

	/**
	 * Method to convert Branding form to Branding DTO
	 * @param brandingTemplateForm
	 * @return
	 */
	public BrandingTemplateDTO createEmpBrandTempDTO(
			BrandingTemplateForm brandingTemplateForm) {

		BrandingTemplateDTO dto = new BrandingTemplateDTO();

		if (!("".equals(brandingTemplateForm.getTemplateId()))
				&& brandingTemplateForm.getTemplateId() != null) {
			dto.setJpBrandTempId(Integer.parseInt(brandingTemplateForm
					.getTemplateId()));
		}
		
		dto.setEmployerId(brandingTemplateForm.getEmployerId());
		dto.setFacilityId(brandingTemplateForm.getFacilityId());
		dto.setLogoPath(brandingTemplateForm.getLogoPath());
		dto.setColor(brandingTemplateForm.getColor());
		dto.setCreatedDate(DateUtils.convertSQLDateTimeToStdDateTime(new Date().toString()));
		dto.setTemplateName(brandingTemplateForm.getTemplateName());
		dto.setCompanyOverview(brandingTemplateForm.getCompanyOverview());
		dto.setMainImagePath(brandingTemplateForm.getMainImagePath());
		dto.setLogoFileData(brandingTemplateForm.getLogoFileData());
		dto.setMainImageFileData(brandingTemplateForm.getMainImageFileData());

		// Multi media section
//		dto.setTestimony(brandingTemplateForm.getTestimony());
//		dto.setAddImagePath(brandingTemplateForm.getAddImagePath());
//		dto.setAddImageFileData(brandingTemplateForm.getAddImageFileData());
//		dto.setVideoPath(brandingTemplateForm.getVideoPath());
//		dto.setVideoFileData(brandingTemplateForm.getVideoFileData());

		
		if(!brandingTemplateForm.getIsSilverCustomer())
		{
		List<TestimonyDTO> listTestimonyDTO = transformTestimonyDTO(brandingTemplateForm.getListTestimony());
		dto.setListTestimony(listTestimonyDTO);
		
		List<AddImageDTO> listAddImageDTO = transformAddImageDTO(brandingTemplateForm.getListAddImages());
		dto.setListAddImages(listAddImageDTO);
		
		
		List<VideoDTO> listVideoDTO = transformVideoDTO(brandingTemplateForm.getListVideos());
		dto.setListVideos(listVideoDTO);
		
		
		
		}
		
		dto.setIsSilverCustomer(brandingTemplateForm.getIsSilverCustomer());

		return dto;
	}

	
	
	/**
	 * Method to convert Branding DTO to Branding form
	 * @param brandingTemplateDTO
	 * @return
	 */
	public BrandingTemplateForm fromBrandDTOToBrandForm(
			BrandingTemplateForm form, BrandingTemplateDTO brandingTemplateDTO) {
		form.setTemplateName(brandingTemplateDTO.getTemplateName());
		form.setMainImagePath(brandingTemplateDTO.getMainImagePath());
		form.setLogoPath(brandingTemplateDTO.getLogoPath());
		form.setColor(brandingTemplateDTO.getColor());
		form.setCreatedDate(brandingTemplateDTO.getCreatedDate());
		form.setCompanyOverview(brandingTemplateDTO.getCompanyOverview());
		form.setLogoFileData(brandingTemplateDTO.getLogoFileData());
		form.setMainImageFileData(brandingTemplateDTO.getMainImageFileData());
		
		if(!brandingTemplateDTO.getIsSilverCustomer())
		{
			List<TestimonyForm> listTestimonies = transformTestimonyDTOToForm(brandingTemplateDTO.getListTestimony());
			if(!listTestimonies.isEmpty())
			{
			form.setListTestimony(listTestimonies);		
			}
			List<AddImageForm> listAddImages = transformAddImageToForm(brandingTemplateDTO.getListAddImages());
			if(!listAddImages.isEmpty())
			{
			form.setListAddImages(listAddImages);
			}
			
			List<VideoForm> listVideos = transformVideoToForm(brandingTemplateDTO.getListVideos());
			if(!listVideos.isEmpty())
			{
			form.setListVideos(listVideos);
			}
			
		}
		
		return form;
	}
	
	
	
	/**
	 * Method to convert Testimony form to Testimony DTO
	 * @param testimonies
	 * @return
	 */
	public List<TestimonyDTO> transformTestimonyDTO(
			List<TestimonyForm> testimonies) {

		List<TestimonyDTO> listTestimonyDTO = new ArrayList<TestimonyDTO>();

		if (null != testimonies) {

			for (TestimonyForm testimonyForm : testimonies) {

				TestimonyDTO dto = new TestimonyDTO();

				dto.setTestimony(testimonyForm.getTestimony());

				listTestimonyDTO.add(dto);

			}

		}

		return listTestimonyDTO;

	}	
	
	
	/**
	 * Method to convert Testimony DTO to Testimony Form
	 * @param testimonies
	 * @return
	 */
	public List<TestimonyForm> transformTestimonyDTOToForm(
			List<TestimonyDTO> testimonies) {

		List<TestimonyForm> listTestimonyForm = new ArrayList<TestimonyForm>();
		if (null != testimonies) {
			for (TestimonyDTO dto : testimonies) {
				TestimonyForm form = new TestimonyForm();
				form.setTestimony(dto.getTestimony());

				listTestimonyForm.add(form);
			}
		}
		return listTestimonyForm;

	}
	

	
	/**
	 * Method to convert Additional Image form to Additional Image DTO
	 * @param testimonies
	 * @return
	 */
	public List<AddImageDTO> transformAddImageDTO(List<AddImageForm> addImages) {

		List<AddImageDTO> listAddImageDTO = new ArrayList<AddImageDTO>();

		if (null != addImages) {

			for (AddImageForm addImageForm : addImages) {

//				TODO null check on addImageForm before saving
				AddImageDTO dto = new AddImageDTO();

				dto.setAddImageFileData(addImageForm.getAddImageFileData());
				dto.setMediaPath(addImageForm.getMediaPath());
				dto.setMediaType("Additional Image");
				listAddImageDTO.add(dto);

			}

		}

		return listAddImageDTO;

	}	
	
	
	/**
	 * Method to convert Additional Image DTO to Additional Image Form
	 * @param testimonies
	 * @return
	 */
	public List<AddImageForm> transformAddImageToForm(
			List<AddImageDTO> addImages) {

		List<AddImageForm> listAddImageForm = new ArrayList<AddImageForm>();
		if (null != addImages) {
			for (AddImageDTO dto : addImages) {
				AddImageForm form = new AddImageForm();
				form.setAddImageFileData(dto.getAddImageFileData());
				form.setMediaPath(dto.getMediaPath());
				form.setMediaType(dto.getMediaType());
				listAddImageForm.add(form);
			}
		}
		return listAddImageForm;

	}
	

	/**
	 * Method to convert Video form to Video DTO
	 * @param testimonies
	 * @return
	 */
	public List<VideoDTO> transformVideoDTO(List<VideoForm> videos) {

		List<VideoDTO> listVideoDTO = new ArrayList<VideoDTO>();

		if (null != videos) {

			
			for (VideoForm videoForm : videos) {
//				TODO null check on videoForm before saving
				VideoDTO dto = new VideoDTO();

				dto.setVideoFileData(videoForm.getVideoFileData());
				dto.setMediaPath(videoForm.getMediaPath());
				dto.setMediaType("Video");
				listVideoDTO.add(dto);

			}

		}

		return listVideoDTO;

	}	
	
	
	/**
	 * Method to convert Video DTO to Video Form
	 * @param testimonies
	 * @return
	 */
	public List<VideoForm> transformVideoToForm(
			List<VideoDTO> videos) {

		List<VideoForm> listVideoForm = new ArrayList<VideoForm>();
		if (null != videos) {
			for (VideoDTO dto : videos) {
				VideoForm form = new VideoForm();
				form.setVideoFileData(dto.getVideoFileData());
				form.setMediaPath(dto.getMediaPath());
				form.setMediaType(dto.getMediaType());
				listVideoForm.add(form);
			}
		}
		return listVideoForm;

	}
	
}
