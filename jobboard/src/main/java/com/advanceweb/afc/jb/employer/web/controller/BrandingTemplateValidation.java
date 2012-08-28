package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 
 * @author Harsha
 *
 * @Version 1.0
 * @Since 22nd August, 2012
 */
@Component("brandingTemplateValidation")
public class BrandingTemplateValidation {
	

		
	  public boolean supports(Class<?> form) {
		    return BrandingTemplateValidation.class.isAssignableFrom(form);
		  }
	
	  	/**
		 * Validating the image file
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateImage(BrandingTemplateForm brandingTemplateForm, Errors errors){
		
			int imageLength = brandingTemplateForm.getMainImagePath().length();
			String fileExtension = brandingTemplateForm.getMainImagePath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
			{
				errors.rejectValue("mainImageFileData", "NotEmpty", "Please select the appropriate Image");
			}
			
			imageLength = brandingTemplateForm.getLogoPath().length();
			fileExtension = brandingTemplateForm.getLogoPath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
			{
				errors.rejectValue("logoFileData", "NotEmpty", "Please select the appropriate Logo");
			}
			
			if(!brandingTemplateForm.getIsSilverCustomer())
			{
				imageLength = brandingTemplateForm.getAddImagePath().length();
				fileExtension = brandingTemplateForm.getAddImagePath().substring(imageLength-4, imageLength);
				
				if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
				{
					errors.rejectValue("addImageFileData", "NotEmpty", "Please select the appropriate Image");
				}
			}
		}
	
		/**
		 * Validating the image file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateImageSize(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
			long imageSizeLimit=500000;
						
			long imageSize = brandingTemplateForm.getMainImageFileData().getSize();
			if (imageSize==0 || imageSize>imageSizeLimit)
			{
				errors.rejectValue("mainImageFileData", "NotEmpty", "Please select the appropriate Image size");
			}
			imageSize = brandingTemplateForm.getLogoFileData().getSize();
			if (imageSize==0 || imageSize>imageSizeLimit)
			{
				errors.rejectValue("logoFileData", "NotEmpty", "Please select the appropriate Logo size");
			}
			
			if(!brandingTemplateForm.getIsSilverCustomer())
			{
				imageSize = brandingTemplateForm.getAddImageFileData().getSize();
				if (imageSize==0 || imageSize>imageSizeLimit)
				{
					errors.rejectValue("addImageFileData", "NotEmpty", "Please select the appropriate Image size");
				}
			}
		}
	
		/**
		 * Validating the video
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateVideo(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
			int videoLength = brandingTemplateForm.getVideoPath().length();
			String fileExtension = brandingTemplateForm.getVideoPath().substring(videoLength-4, videoLength);
			
			if (!(fileExtension.contains(".mov") || fileExtension.contains(".mpg") ))
			{
				errors.rejectValue("videoFileData", "NotEmpty", "Please select the appropriate Video");
			}
			
		}
	
		/**
		 * Validating the video file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateVideoSize(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
			long videoSizeLimit=90000000;
						
			long videoSize = brandingTemplateForm.getVideoFileData().getSize();
			if (videoSize==0 || videoSize>videoSizeLimit)
			{
				errors.rejectValue("videoFileData", "NotEmpty", "Please select the appropriate Video size");
			}
			
		}
		
	/**
	 * Validating form
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;
		validateImage(brandingTemplateForm, errors);
		validateImageSize(brandingTemplateForm, errors);
		if(!brandingTemplateForm.getIsSilverCustomer())
		{
			validateVideo(brandingTemplateForm, errors);
			validateVideoSize(brandingTemplateForm, errors);
		}
	}	
}
