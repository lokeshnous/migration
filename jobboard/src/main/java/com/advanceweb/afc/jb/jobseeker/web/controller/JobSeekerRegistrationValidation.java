/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
@Component("registerValidation")
public class JobSeekerRegistrationValidation {
	
	/** The email blank. */
	@Value("${js.email.blank}")
	private String emailBlank;
	
	/** The conform email blank. */
	@Value("${js.conformEmail.blank}")
	private String conformEmailBlank;
	
	/** The invalid email. */
	@Value("${js.invalid.email}")
	private String invalidEmail;
	
	/** The email match. */
	@Value("${js.email.match}")
	private String emailMatch;
	
	/** The pwd empty. */
	@Value("${js.password.empty}")
	private String pwdEmpty;
	
	/** The conform pass empty. */
	@Value("${js.conform.pass.empty}")
	private String conformPassEmpty;
	
	/** The pwd hint. */
	@Value("${js.pwd.hint}")
	private String pwdHint;
	
	/** The pwd not equal. */
	@Value("${js.pwd.not.equal}")
	private String pwdNotEqual;
	
	/** The pattern. */
	private Pattern pattern;
	
	/** The matcher. */
	private Matcher matcher;
	
	/** The Constant NOTEMPTY. */
	private static final String NOTEMPTY = "NotEmpty";
	
	
	  /**
  	 * Supports.
  	 *
  	 * @param form the form
  	 * @return true, if successful
  	 */
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
			 errors.rejectValue("emailId", NOTEMPTY, emailBlank);
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 errors.rejectValue("confirmEmailId", NOTEMPTY, conformEmailBlank);
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getEmailId()) 
				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 
			 if(!validateEmailPattern(registerForm.getEmailId())){
				 errors.rejectValue("emailId", NOTEMPTY, invalidEmail); 
			 }
			 
			 if(!validateEmailPattern(registerForm.getConfirmEmailId())){
				 errors.rejectValue("confirmEmailId", NOTEMPTY, invalidEmail); 
			 }
			 
			 if(!registerForm.getEmailId().equals(registerForm.getConfirmEmailId())){
				errors.rejectValue("confirmEmailId", NOTEMPTY, emailMatch);
			 }
		 }
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
			 errors.rejectValue("password", NOTEMPTY, pwdEmpty);
		 }
		 
		 if(StringUtils.isEmpty(retypePassword)){
			 errors.rejectValue("retypepassword", NOTEMPTY, conformPassEmpty);
		 }
		 
		 if(!StringUtils.isEmpty(password) 
				 && !StringUtils.isEmpty(retypePassword)){
			 
			 if(!validatePasswordPattern(password)){
				 errors.rejectValue("password", NOTEMPTY, pwdHint); 
			 }
			 
			 if(!validatePasswordPattern(retypePassword)){
				 errors.rejectValue("retypepassword", NOTEMPTY, pwdHint); 
			 }
			 
			 if(!password.equals(retypePassword)){
				errors.rejectValue("retypepassword", NOTEMPTY,pwdNotEqual);
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
		 validatePassoword(registerForm.getPassword(), registerForm.getRetypepassword(), errors);
	}	
}
