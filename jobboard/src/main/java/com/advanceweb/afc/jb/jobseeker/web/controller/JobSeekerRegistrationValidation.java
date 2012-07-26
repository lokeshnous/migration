package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("registerValidation")
public class JobSeekerRegistrationValidation {
	
	private Pattern pattern;
	private Matcher matcher;
	
	
	  public boolean supports(Class<?> form) {
		    return JobSeekerRegistrationForm.class.isAssignableFrom(form);
		  }
	
	
	/**
	 * Validating the emailId
	 * @param registerForm
	 * @return 
	 * @return
	 */
	public void validateEmail(JobSeekerRegistrationForm registerForm, Errors errors){
		
		 if(StringUtils.isEmpty(registerForm.getEmailId())){
			 errors.rejectValue("emailId", "NotEmpty", "Email Id Should not be empty");
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 errors.rejectValue("confirmEmailId", "NotEmpty", "Email Id Should not be empty");
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getEmailId()) 
				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 
			 if(!validateEmailPattern(registerForm.getEmailId())){
				 errors.rejectValue("emailId", "NotEmpty", "Invalid Email Id"); 
			 }
			 
			 if(!validateEmailPattern(registerForm.getConfirmEmailId())){
				 errors.rejectValue("confirmEmailId", "NotEmpty", "Invalid Email Id"); 
			 }
			 
			 if(!registerForm.getEmailId().equals(registerForm.getConfirmEmailId())){
				errors.rejectValue("confirmEmailId", "NotEmpty", "Email Id's are not equal");
			 }
		 }
	}
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	private boolean validateEmailPattern(String emailId){
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	private boolean validatePasswordPattern(String password){
		pattern = Pattern.compile(MMJBCommonConstants.PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
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
	public void validatePassoword(JobSeekerRegistrationForm registerForm, Errors errors){
		
		 if(StringUtils.isEmpty(registerForm.getPassword())){
			 errors.rejectValue("password", "NotEmpty", "Password Should not be empty");
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getRetypepassword())){
			 errors.rejectValue("retypepassword", "NotEmpty", "Password Should not be empty");
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getPassword()) 
				 && !StringUtils.isEmpty(registerForm.getRetypepassword())){
			 
			 if(!validatePasswordPattern(registerForm.getPassword())){
				 errors.rejectValue("password", "NotEmpty", "(Password should contain 8-20 characters, including at least 1 number)"); 
			 }
			 
			 if(!validatePasswordPattern(registerForm.getRetypepassword())){
				 errors.rejectValue("retypepassword", "NotEmpty", "(Password should contain  8-20 characters, including at least 1 number)"); 
			 }
			 
			 if(!registerForm.getPassword().equals(registerForm.getRetypepassword())){
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
		 
		 JobSeekerRegistrationForm registerForm = (JobSeekerRegistrationForm) target;
		 
		 validateEmail(registerForm, errors);
		 validatePassoword(registerForm, errors);
	}
	
	
}
