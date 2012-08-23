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
		 * @param brandingForm
		 * @param errors
		 * @return void
		 */
		public void validateImage(BrandingTemplateForm brandingForm, Errors errors){
		
			int imageLength = brandingForm.getMainImage().length();
			String fileExtension = brandingForm.getMainImage().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
			{
				errors.rejectValue("mainImage", "NotEmpty", "Please select the appropriate Image");
			}
			
			imageLength = brandingForm.getLogoPath().length();
			fileExtension = brandingForm.getLogoPath().substring(imageLength-4, imageLength);
			
			if (!(fileExtension.contains(".jpg") || fileExtension.contains(".gif") || fileExtension.contains(".png") || fileExtension.contains(".tif")))
			{
				errors.rejectValue("logoPath", "NotEmpty", "Please select the appropriate Logo");
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
		
	}	
}
