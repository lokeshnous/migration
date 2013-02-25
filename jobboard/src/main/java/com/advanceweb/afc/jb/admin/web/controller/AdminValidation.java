/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * @author muralikc
 * 
 */
@Component("adminValidation")
public class AdminValidation {

	/** The pattern. */
	public Pattern pattern;
	
	/** The matcher. */
	public Matcher matcher;


	/**
	 * Supports.
	 *
	 * @param form the form
	 * @return true, if successful
	 */
	public boolean supports(Class<?> form) {
		return AdminLoginForm.class.isAssignableFrom(form);
	}

	/**
	 * Validating the emailId
	 * 
	 * @param form
	 * @return
	 * @return
	 */
	public void validateEmail(AdminLoginForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getEmpOrAgencyEmail())) {
			errors.rejectValue("empOrAgencyEmail", "NotEmpty", "Please enter Employer / Agency Email address");
		}
		if (!StringUtils.isEmpty(form.getEmpOrAgencyEmail())
				&& !validateEmailPattern(form.getEmpOrAgencyEmail())) {
			errors.rejectValue("empOrAgencyEmail", "NotEmpty",
					"Invalid Employer/Agency Email address");
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
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		AdminLoginForm registerForm = (AdminLoginForm) target;
		validateEmail(registerForm, errors);
	}
}
