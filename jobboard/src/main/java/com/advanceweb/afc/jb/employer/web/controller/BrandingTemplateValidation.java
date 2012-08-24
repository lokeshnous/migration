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
		}
	
		/**
		 * Validating the image file size
		 * @param brandingTemplateForm
		 * @param errors
		 * @return void
		 */
		public void validateImageSize(BrandingTemplateForm brandingTemplateForm, Errors errors){
			
			long imageSize = brandingTemplateForm.getMainImageFileData().getSize();
			if (imageSize==0 || imageSize>500000)
			{
				errors.rejectValue("mainImageFileData", "NotEmpty", "Please select the appropriate Image size less than 500KB");
			}
			imageSize = brandingTemplateForm.getLogoFileData().getSize();
			if (imageSize==0 || imageSize>500000)
			{
				errors.rejectValue("logoFileData", "NotEmpty", "Please select the appropriate Logo  size less than 500KB");
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
	}	
}
