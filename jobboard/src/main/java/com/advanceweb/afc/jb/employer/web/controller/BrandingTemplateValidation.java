package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;


/**
 * 
 * @author Harsha
 *
 * @Version 1.0
 * @Since 22nd August, 2012
 */
@Component("brandingTemplateValidation")
public class BrandingTemplateValidation {
	
	
	private @Value("${imageSizeLimit}")
	long imageSizeLimit;
	
	private @Value("${videoSizeLimit}")
	long videoSizeLimit;
	
	private static final String STR_NOTEMPTY = "NotEmpty";
		
	  public boolean supports(Class<?> form) {
		    return BrandingTemplateValidation.class.isAssignableFrom(form);
		  }
	
	  	/**
		 * Validating the Main image file
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateMainImage(BrandingTemplateForm brandingTemplateForm, Errors errors){
		
			int imageLength = brandingTemplateForm.getMainImagePath().length();
			String fileExtension = brandingTemplateForm.getMainImagePath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_GIF) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_TIF)))
			{
				errors.rejectValue("mainImageFileData", STR_NOTEMPTY, "Please select the appropriate Image");
			}
			
			long imageSize = brandingTemplateForm.getMainImageFileData().getSize();
			if (imageSize==0 || imageSize>imageSizeLimit)
			{
				errors.rejectValue("mainImageFileData", STR_NOTEMPTY, "Please select the appropriate Image size");
			}
			
		}
		
		/**
		 * Validating the Logo image file
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateLogo(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
			int imageLength = brandingTemplateForm.getLogoPath().length();
			String fileExtension = brandingTemplateForm.getLogoPath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_GIF) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_TIF)))
			{
				errors.rejectValue("logoFileData", STR_NOTEMPTY, "Please select the appropriate Logo");
			}
			
			long imageSize = brandingTemplateForm.getLogoFileData().getSize();
			if (imageSize==0 || imageSize>imageSizeLimit)
			{
				errors.rejectValue("logoFileData", STR_NOTEMPTY, "Please select the appropriate Logo size");
			}
		}
		
		
		/**
		 * Validating the Additional image
		 * @param brandingTemplateForm
		 * @param errors
		 * @return String
		 */
		public String validateAddImage(BrandingTemplateForm brandingTemplateForm) {
			
			int imageLength;
			String fileExtension;
	
			for (AddImageForm image : brandingTemplateForm.getListAddImages()) {

				if(image.getAddImageFileData().getSize() > 0)
				{	
					imageLength = image.getMediaPath().length();
					fileExtension = image.getMediaPath().substring(imageLength - 4,	imageLength);
					if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_GIF) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_TIF))) 
					{
						return ("Please select the appropriate Additional Image");
					}
				}
			}
	
			return null;
	
		}
	
		/**
		 * Validating the Additional image file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return String
		 */
		public String validateAddImageSize(BrandingTemplateForm brandingTemplateForm) {
	
			long imageSize;
	
			for (AddImageForm image : brandingTemplateForm.getListAddImages()) {
				if(image.getAddImageFileData().getSize() > 0)
				{
					imageSize = image.getAddImageFileData().getSize();
					if (imageSize > imageSizeLimit) {
						return ("Please select the appropriate Additional Image Size");
					}
				}
			}
	
			return null;
	
		}
		
		
		/**
		 * Validating the video
		 * @param brandingTemplateForm
		 * @param errors
		 * @return String
		 */
		public String validateVideo(BrandingTemplateForm brandingTemplateForm) {
			
			int videoLength;
			String fileExtension;
	
			for (VideoForm video : brandingTemplateForm.getListVideos()) {
				if(video.getVideoFileData().getSize() > 0)
				{
					videoLength = video.getMediaPath().length();
					fileExtension = video.getMediaPath().substring(videoLength - 4,	videoLength);
					if (!(fileExtension.contains(MMJBCommonConstants.VIDEO_TYPE_MOV) || fileExtension.contains(MMJBCommonConstants.VIDEO_TYPE_MPG))) {
						return ("Please select the appropriate Video");
					}
				}
			}
	
			return null;
	
		}
	
		/**
		 * Validating the video file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return String
		 */
		public String validateVideoSize(BrandingTemplateForm brandingTemplateForm) {
	
			long videoSize;
	
			for (VideoForm video : brandingTemplateForm.getListVideos()) {
				if(video.getVideoFileData().getSize() > 0)
				{
					videoSize = video.getVideoFileData().getSize();
					if (videoSize > videoSizeLimit) {
						return ("Please select the appropriate Video Size");
					}
				}
			}
	
			return null;
	
		}
		
				
	/**
	 * Validating form for Silver customers
	 * @param target
	 * @param errors
	 */
	public void validateSilver(Object target, Errors errors) {
		
		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;
		if(null == brandingTemplateForm.getChosenLogo() || brandingTemplateForm.getLogoFileData().getSize() > 0)
		{
			validateLogo(brandingTemplateForm, errors);
		}
		if (!errors.hasErrors() && null == brandingTemplateForm.getChosenMainImage() || brandingTemplateForm.getMainImageFileData().getSize() > 0)
		{
			validateMainImage(brandingTemplateForm, errors);
		}
		
	}	
	
	/**
	 * Validating form for Non Silver customers
	 * @param target
	 * @param errors
	 */
	public String validateNonSilver(Object target, Errors errors) {

		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;
		String validationMessage;

//		Validation for Additional Images
		validationMessage = validateAddImage(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage))
		{
			return validationMessage;
		}
		
		validationMessage = validateAddImageSize(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage))
		{
			return validationMessage;
		}

		
//		Validation for Videos
		validationMessage = validateVideo(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage))
		{
			return validationMessage;
		}
		
		validationMessage = validateVideoSize(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage))
		{
			return validationMessage;
		}
		
		return null;
		
		
	}
	
	
	
}
