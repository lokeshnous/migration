/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.agency.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

@Component("agencyRegistrationValidation")
public class AgencyRegistrationValidation {
	
	/** The email blank. */
	@Value("${age.email.blank}")
	private String emailBlank;
	
	/** The conform email blank. */
	@Value("${age.conformEmail.blank}")
	private String conformEmailBlank;
	
	/** The invalid email. */
	@Value("${age.invalid.email}")
	private String invalidEmail;
	
	/** The email match. */
	@Value("${age.email.match}")
	private String emailMatch;
	
	/** The pwd empty. */
	@Value("${age.password.empty}")
	private String pwdEmpty;
	
	/** The conform pass empty. */
	@Value("${age.conform.pass.empty}")
	private String conformPassEmpty;
	
	/** The pwd hint. */
	@Value("${age.pwd.hint}")
	private String pwdHint;
	
	/** The pwd not equal. */
	@Value("${age.pwd.not.equal}")
	private String pwdNotEqual;

	/** The pattern. */
	private Pattern pattern;
	
	/** The matcher. */
	private Matcher matcher;
    
    /** The Constant NOT_EMPTY. */
    private static final String NOT_EMPTY= "NotEmpty";
	
	/**
	 * Supports.
	 *
	 * @param form the form
	 * @return true, if successful
	 */
	public boolean supports(Class<?> form) {
		return AgencyRegistrationForm.class.isAssignableFrom(form);
	}

	/**
	 * Validating the emailId
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void validateEmail(AgencyRegistrationForm registerForm,
			Errors errors) {
		
		 if(StringUtils.isEmpty(registerForm.getEmailId())){
			 errors.rejectValue("emailId", NOT_EMPTY, emailBlank);
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 errors.rejectValue("confirmEmailId", NOT_EMPTY, conformEmailBlank);
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getEmailId()) 
				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 
			 if(!validateEmailPattern(registerForm.getEmailId())){
				 errors.rejectValue("emailId", NOT_EMPTY, invalidEmail); 
			 }
			 
			 if(!validateEmailPattern(registerForm.getConfirmEmailId())){
				 errors.rejectValue("confirmEmailId", NOT_EMPTY, invalidEmail); 
			 }
			 
			 if(!registerForm.getEmailId().equals(registerForm.getConfirmEmailId())){
				errors.rejectValue("confirmEmailId", NOT_EMPTY, emailMatch);
			 }
		 }
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	public boolean validateEmailPattern(String emailId) {
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	public boolean validatePasswordPattern(String password) {
		pattern = Pattern.compile(MMJBCommonConstants.PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	public boolean validateMobileNumberPattern(String mobile) {
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
	public void validatePassoword(String password, String confirmPassword,
			Errors errors) {

		if (StringUtils.isEmpty(password)) {
			errors.rejectValue("password", NOT_EMPTY,
					pwdEmpty);
		}

		if (StringUtils.isEmpty(confirmPassword)) {
			errors.rejectValue("confirmPassword", NOT_EMPTY,
					conformPassEmpty);
		}

		if (!StringUtils.isEmpty(password)
				&& !StringUtils.isEmpty(confirmPassword)) {

			if (!validatePasswordPattern(password)) {
				errors.rejectValue("password", NOT_EMPTY,
						pwdHint);
			}

			if (!validatePasswordPattern(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOT_EMPTY,
						pwdHint);
			}

			if (!password.equals(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOT_EMPTY,
						pwdNotEqual);
			}
		}
	}
 
	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {

		AgencyRegistrationForm registerForm = (AgencyRegistrationForm) target;

		validateEmail(registerForm, errors);
		validatePassoword(registerForm.getPassword(),
				registerForm.getConfirmPassword(), errors);
	}


}
