package com.advanceweb.afc.jb.employer.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddImageDTO;
import com.advanceweb.afc.jb.common.BrandingTemplateDTO;
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
			JpTemplate jpBrandingTemp) {
		// EmpBrandTempDTO merJpBrandTempDTO = new EmpBrandTempDTO();
		// merJpBrandTempDTO
		// .setJpBrandTempId(merJpBrandingTemp.getJpBrandTempId());
		// merJpBrandTempDTO.setDescription(merJpBrandingTemp.getDescription());
		// merJpBrandTempDTO.setEmployerId(merJpBrandingTemp.getUserId());
		// merJpBrandTempDTO.setImagePath(merJpBrandingTemp.getImagePath());
		// merJpBrandTempDTO.setLogoPath(merJpBrandingTemp.getLogoPath());
		// merJpBrandTempDTO.setColor(merJpBrandingTemp.getColor());
		// merJpBrandTempDTO.setCreatedDate(merJpBrandingTemp.getCreatedDate());
		// Date updateDate = merJpBrandingTemp.getUpdatedDate();
		// String strUpdateDate = null;
		// if (updateDate != null) {
		// strUpdateDate = DateUtils.convertSQLDateToStdDateString(updateDate
		// .toString());
		// }
		// merJpBrandTempDTO.setUpdatedDate(strUpdateDate);
		// return merJpBrandTempDTO;

		BrandingTemplateDTO jpBrandTempDTO = new BrandingTemplateDTO();
		jpBrandTempDTO.setJpBrandTempId(jpBrandingTemp.getTemplateId());
		jpBrandTempDTO.setTemplateName(jpBrandingTemp.getTemplateName());
		jpBrandTempDTO.setCompanyOverview(jpBrandingTemp.getCompanyOverview());

		// To be removed after mapping

		// jpBrandTempDTO.setEmployerId(jpBrandingTemp.getMerUser().getUserId());
		jpBrandTempDTO.setMainImagePath(jpBrandingTemp.getMainImagePath());
		jpBrandTempDTO.setLogoPath(jpBrandingTemp.getLogoPath());
		jpBrandTempDTO.setColor(jpBrandingTemp.getColorPalette());
		jpBrandTempDTO.setCreatedDate(jpBrandingTemp.getCreateDt());
		// Date updateDate = jpBrandingTemp.getUpdatedDate();
		// String strUpdateDate = null;
		// if (updateDate != null) {
		// strUpdateDate = DateUtils.convertSQLDateToStdDateString(updateDate
		// .toString());
		// }
		// jpBrandTempDTO.setUpdatedDate(strUpdateDate);
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
	// {
	// MerJpBrandingTemp merJpBrandTemp = new MerJpBrandingTemp();
	// merJpBrandTemp
	// .setJpBrandTempId(brandingTemplatesDTO.getJpBrandTempId());
	// merJpBrandTemp.setDescription(brandingTemplatesDTO.getDescription());
	// merJpBrandTemp.setUserId((int) brandingTemplatesDTO.getEmployerId());
	// merJpBrandTemp.setImagePath(brandingTemplatesDTO.getImagePath());
	// merJpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
	// merJpBrandTemp.setColor(brandingTemplatesDTO.getColor());
	// merJpBrandTemp.setCreatedDate(brandingTemplatesDTO.getCreatedDate());
	// String strUpdateDate = brandingTemplatesDTO.getUpdatedDate();
	// java.sql.Date updateDate = null;
	// if (strUpdateDate != null) {
	// updateDate = DateUtils.convertDateStringToSQLDate(strUpdateDate);
	// }
	// merJpBrandTemp.setUpdatedDate(updateDate);
	// return merJpBrandTemp;

	{
		JpTemplate jpBrandTemp = new JpTemplate();
		jpBrandTemp.setTemplateId(brandingTemplatesDTO.getJpBrandTempId());
		jpBrandTemp.setTemplateName(brandingTemplatesDTO.getTemplateName());
		jpBrandTemp.setCompanyOverview(brandingTemplatesDTO
				.getCompanyOverview());

		// To be removed after mapping
		// MerUser merUser = new MerUser();
		// merUser.setUserId(brandingTemplatesDTO.getEmployerId());
		// jpBrandTemp.setMerUser(merUser);
		jpBrandTemp.setMainImagePath(brandingTemplatesDTO.getMainImagePath());
		jpBrandTemp.setLogoPath(brandingTemplatesDTO.getLogoPath());
		jpBrandTemp.setColorPalette(brandingTemplatesDTO.getColor());
		jpBrandTemp.setCreateDt(brandingTemplatesDTO.getCreatedDate());
		jpBrandTemp.setCreateUserId(brandingTemplatesDTO.getEmployerId());
		// String strUpdateDate = brandingTemplatesDTO.getUpdatedDate();
		// java.sql.Date updateDate = null;
		// if (strUpdateDate != null) {
		// updateDate = DateUtils.convertDateStringToSQLDate(strUpdateDate);
		// }
		// jpBrandTemp.setUpdatedDate(updateDate);

		AdmFacility facility = new AdmFacility();
		facility.setFacilityId(brandingTemplatesDTO.getFacilityId());
		
		jpBrandTemp.setAdmFacility(facility);

		// JP job does not seem to be requiring to persist
		// JpJob jpJob1 = new JpJob();
		// jpJob1.setJobId(13100);
		// JpJob jpJob2 = new JpJob();
		// jpJob2.setJobId(13101);
		// jpBrandTemp.getJpJobs().add(jpJob1);
		// jpBrandTemp.getJpJobs().add(jpJob2);

		
		// Create dummy media
//		if (!brandingTemplatesDTO.getIsSilverCustomer()) {
//			JpTemplateMedia media1 = new JpTemplateMedia();
//			media1.setMediaPath(brandingTemplatesDTO.getAddImagePath());
//			media1.setMediaType("Additional Image");
//
//			JpTemplateMedia media2 = new JpTemplateMedia();
//			media2.setMediaPath(brandingTemplatesDTO.getVideoPath());
//			media2.setMediaType("Video");
//
//			ArrayList<JpTemplateMedia> mediaList = new ArrayList<JpTemplateMedia>();
//			mediaList.add(media1);
//			mediaList.add(media2);
//			jpBrandTemp.setJpTemplateMedias(mediaList);
//
//			media1.setJpTemplate(jpBrandTemp);
//			media2.setJpTemplate(jpBrandTemp);
//
//			// Create dummy testimony
//			JpTemplateTestimonial testimony1 = new JpTemplateTestimonial();
//			// testimony1.setTestimonial("Testimonial 1");
//			// testimony1.setTestimonial(brandingTemplatesDTO.getTestimony());
//			// JpTemplateTestimonial testimony2 = new JpTemplateTestimonial();
//			// testimony2.setTestimonial("Testimonial 2");
//
//			ArrayList<JpTemplateTestimonial> testimonyList = new ArrayList<JpTemplateTestimonial>();
//			testimonyList.add(testimony1);
//			// testimonyList.add(testimony2);
//			jpBrandTemp.setJpTemplateTestimonials(testimonyList);
//
//			testimony1.setJpTemplate(jpBrandTemp);
//			// testimony2.setJpTemplate(jpBrandTemp);
//		}
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
				
//				dto.setBuilderPhoneId(entity.getBuilderPhoneId());
						
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
				AddImageDTO dto = new AddImageDTO();
				
				dto.setMediaPath(entity.getMediaPath());
				dto.setMediaType(entity.getMediaType());
						
				listAddImageDTO.add(dto);
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
				VideoDTO dto = new VideoDTO();
				
				dto.setMediaPath(entity.getMediaPath());
				dto.setMediaType(entity.getMediaType());
						
				listVideoDTO.add(dto);
			}
		}
		return listVideoDTO;
	}

}
