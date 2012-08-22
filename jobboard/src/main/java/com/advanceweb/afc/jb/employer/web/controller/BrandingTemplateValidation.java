package com.advanceweb.afc.jb.employer.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Component("brandingValidation")
public class BrandingTemplateValidation {
	
	private Pattern pattern;
	private Matcher matcher;
	
	
	  public boolean supports(Class<?> form) {
		    return BrandingTemplateValidation.class.isAssignableFrom(form);
		  }
	
	  
	  
	
	  /**
		 * Validating the emailId
		 * @param registerForm
		 * @return 
		 * @return
		 */
		public void validateImage(BrandingTemplateForm brandingForm, Errors errors){
		
//			brandingForm.getMainImage().su
		}
	
	/**
	 * Validating the emailId
	 * @param registerForm
	 * @return 
	 * @return
	 */
	public void validateEmail(BrandingTemplateForm brandingForm, Errors errors){
		
//		 if(StringUtils.isEmpty(brandingForm.getEmailId())){
//			 errors.rejectValue("emailId", "NotEmpty", "Email Id Should not be empty");
//		 }
//		 
//		 if(StringUtils.isEmpty(brandingForm.getConfirmEmailId())){
//			 errors.rejectValue("confirmEmailId", "NotEmpty", "Email Id Should not be empty");
//		 }
//		 
//		 if(!StringUtils.isEmpty(brandingForm.getEmailId()) 
//				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
//			 
//			 if(!validateEmailPattern(brandingForm.getEmailId())){
//				 errors.rejectValue("emailId", "NotEmpty", "Invalid Email Id"); 
//			 }
//			 
//			 if(!validateEmailPattern(brandingForm.getConfirmEmailId())){
//				 errors.rejectValue("confirmEmailId", "NotEmpty", "Invalid Email Id"); 
//			 }
//			 
//			 if(!brandingForm.getEmailId().equals(brandingForm.getConfirmEmailId())){
//				errors.rejectValue("confirmEmailId", "NotEmpty", "Please enter the correct E-Mail Address");
//			 }
//		 }
	}
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	public boolean validateEmailPattern(String emailId){
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	public boolean validatePasswordPattern(String password){
		pattern = Pattern.compile(MMJBCommonConstants.PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}	
	
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	public boolean validateMobileNumberPattern(String mobile){
		pattern = Pattern.compile(MMJBCommonConstants.MOBILE_PATTERN);
		matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	 
	/**
	 * Validating the password as per the following format
	 * 
	 * (8-20 characters, including at least 1 number) 
	 * 
	 * @param registerForm
	 * @return
	 */
	public void validatePassoword(String password, String retypePassword, Errors errors){
		
		 if(StringUtils.isEmpty(password)){
			 errors.rejectValue("password", "NotEmpty", "Password Should not be empty");
		 }
		 
		 if(StringUtils.isEmpty(retypePassword)){
			 errors.rejectValue("retypepassword", "NotEmpty", "Password Should not be empty");
		 }
		 
		 if(!StringUtils.isEmpty(password) 
				 && !StringUtils.isEmpty(retypePassword)){
			 
			 if(!validatePasswordPattern(password)){
				 errors.rejectValue("password", "NotEmpty", "Password should contain 8-20 characters, including at least 1 number"); 
			 }
			 
			 if(!validatePasswordPattern(retypePassword)){
				 errors.rejectValue("retypepassword", "NotEmpty", "Password should contain  8-20 characters, including at least 1 number"); 
			 }
			 
			 if(!password.equals(retypePassword)){
				errors.rejectValue("retypepassword", "NotEmpty","Passwords are not equal");
			 }
		 }
	}
	
	/**
	 * Validating form
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		 
//		 JobSeekerRegistrationForm registerForm = (JobSeekerRegistrationForm) target;
//		 
//		 validateEmail(registerForm, errors);
//		 validatePassoword(registerForm.getPassword(), registerForm.getRetypepassword(), errors);
	}	
}
