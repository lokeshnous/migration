package com.advanceweb.afc.jb.employer.web.controller;

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
			
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_GIF) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_TIF)))
			{
				errors.rejectValue("mainImageFileData", "NotEmpty", "Please select the appropriate Image");
			}
			
			imageLength = brandingTemplateForm.getLogoPath().length();
			fileExtension = brandingTemplateForm.getLogoPath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_JPG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_GIF) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_PNG) || fileExtension.contains(MMJBCommonConstants.IMAGE_TYPE_TIF)))
			{
				errors.rejectValue("logoFileData", "NotEmpty", "Please select the appropriate Logo");
			}
			
			if(!brandingTemplateForm.getIsSilverCustomer())
			{
//				imageLength = brandingTemplateForm.getAddImagePath().length();
//				fileExtension = brandingTemplateForm.getAddImagePath().substring(imageLength-4, imageLength);
//				
//				if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
//				{
//					errors.rejectValue("addImageFileData", "NotEmpty", "Please select the appropriate Image");
//				}
			}
		}
	
		/**
		 * Validating the image file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateImageSize(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
//			long imageSizeLimit=500000;
						
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
			
//			if(!brandingTemplateForm.getIsSilverCustomer())
//			{
//				imageSize = brandingTemplateForm.getAddImageFileData().getSize();
//				if (imageSize==0 || imageSize>imageSizeLimit)
//				{
//					errors.rejectValue("addImageFileData", "NotEmpty", "Please select the appropriate Image size");
//				}
//			}
		}
	
		/**
		 * Validating the video
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateVideo(BrandingTemplateForm brandingTemplateForm){
			
//			int videoLength = brandingTemplateForm.getVideoPath().length();
//			String fileExtension = brandingTemplateForm.getVideoPath().substring(videoLength-4, videoLength);
//			
//			if (!(fileExtension.contains(".mov") || fileExtension.contains(".mpg") ))
//			{
////				errors.rejectValue("videoFileData", "NotEmpty", "Please select the appropriate Video");
//			}
			
		}
	
		/**
		 * Validating the video file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateVideoSize(BrandingTemplateForm brandingTemplateForm){
			
			long videoSizeLimit=90000000;
						
//			long videoSize = brandingTemplateForm.getVideoFileData().getSize();
//			if (videoSize==0 || videoSize>videoSizeLimit)
//			{
////				errors.rejectValue("videoFileData", "NotEmpty", "Please select the appropriate Video size");
//			}
			
		}
		
		/**
		 * Validating the Testimony
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public String validateTestimony(BrandingTemplateForm brandingTemplateForm){
			
			if(null != brandingTemplateForm.getListTestimony()){
				for(TestimonyForm form : brandingTemplateForm.getListTestimony()){				
					if(StringUtils.isEmpty(form.getTestimony()) ){
						return "Please fill the Testimonials";
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
		validateImage(brandingTemplateForm, errors);
		validateImageSize(brandingTemplateForm, errors);
//		if(!brandingTemplateForm.getIsSilverCustomer())
//		{
//			String validationMessage = "";
//			validateVideo(brandingTemplateForm, errors);
//			validateVideoSize(brandingTemplateForm, errors);
//			validationMessage = validateTestimony(brandingTemplateForm, errors);
//			
//			if(!StringUtils.isEmpty(validationMessage))
//				errors.rejectValue("listTestimony[${status.index}].testimony", "NotEmpty", validationMessage);
//			
//		}
		
		
	}	
	
	/**
	 * Validating form for Non Silver customers
	 * @param target
	 * @param errors
	 */
	public String validateNonSilver(Object target, Errors errors) {
		
//		validateSilver(target,errors);
//		System.out.println("INSIDE NON SILVER");
		BrandingTemplateForm brandingTemplateForm = (BrandingTemplateForm) target;
		String validationMessage = "";
//		validateVideo(brandingTemplateForm);
//		validateVideoSize(brandingTemplateForm);
		
		validationMessage = validateTestimony(brandingTemplateForm);
		if (!StringUtils.isEmpty(validationMessage))
		{
			return validationMessage;
		}
		
		return null;
		
		
	}
	
	
	
}
