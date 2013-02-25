/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.LoginDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerRegistrationForm;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

@Repository("loginValidator")
public class LoginFormValidator {

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
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void validateEmail(LoginForm loginForm, Errors errors) {

		if (StringUtils.isEmpty(loginForm.getEmailAddress())) {
			errors.rejectValue("emailAddress", NOTEMPTY,
					"Email address Should not be blank.");
		}

		if (!StringUtils.isEmpty(loginForm.getEmailAddress())
				&& !validateEmailPattern(loginForm.getEmailAddress())) {

			errors.rejectValue("emailAddress", NOTEMPTY,
					"Please enter valid Email address");

		}
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	private boolean validateEmailPattern(String emailAddress) {
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	private boolean validatePasswordPattern(String password) {
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
	public void validatePassoword(LoginForm loginForm, Errors errors) {

		if (StringUtils.isEmpty(loginForm.getPassword())) {
			errors.rejectValue("password", NOTEMPTY,
					"Password Should not be blank.");
		}

		if (!StringUtils.isEmpty(loginForm.getPassword())
				&& !validatePasswordPattern(loginForm.getPassword())) {

			errors.rejectValue("password", NOTEMPTY,
					"(Invalid password)");

		}
	}

	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {

		LoginForm loginForm = (LoginForm) target;

		validateEmail(loginForm, errors);
		validatePassoword(loginForm, errors);
	}

	/**
	 * Validation for login form
	 * 
	 * @param form
	 * @param loginFormDTOForUser
	 * @return
	 */
	public boolean validateLoginValues(LoginForm form,
			LoginDTO loginFormDTOForUser) {

		// we need to first check for the role id of logged in user.
		// if value for role id stored in adm_user_role table is same as
		// property file value then validate for the email and password of user
		if (form != null
				&& loginFormDTOForUser != null
				&& form.getRoleId() == loginFormDTOForUser.getRoleId()
				&& (form.getEmailAddress().equals(loginFormDTOForUser
						.getEmailAddress()))
				&& (form.getPassword()
						.equals(loginFormDTOForUser.getPassword()))) {

			return true;

		}
		return false;
	}

	/**
	 * Validation for forgot password based on the email, page value and user
	 * role id
	 * 
	 * @param form
	 * @param userDetailsDTO
	 * @return
	 */
	public boolean validateEmailValues(String email, LoginDTO userDetailsDTO,
			String page) {
		if (page.equals(MMJBCommonConstants.JOB_SEEKER)
				&& email != null
				&& userDetailsDTO != null
				&& email.equals(userDetailsDTO.getEmailAddress())
				&& userDetailsDTO.getRoleId() == MMJBCommonConstants.JOBSEEKER_ROLE_ID) {
			return true;
		} else if (page.equals(MMJBCommonConstants.EMPLOYER)
				&& email != null
				&& userDetailsDTO != null
				&& email.equals(userDetailsDTO.getEmailAddress())
				&& userDetailsDTO.getFacilityType()!=null
				&& ((userDetailsDTO.getFacilityType()
						.equals(MMJBCommonConstants.FACILITY_GROUP)) || (userDetailsDTO
						.getFacilityType().equals(MMJBCommonConstants.FACILITY)))) {
			return true;
		} else if (page.equals(MMJBCommonConstants.AGENCY)
				&& email != null
				&& userDetailsDTO != null
				&& email.equals(userDetailsDTO.getEmailAddress())
				&& userDetailsDTO.getFacilityType()!=null
				&& (userDetailsDTO.getFacilityType()
						.equals(MMJBCommonConstants.FACILITY_SYSTEM))) {
			return true;
		}
		return false;
	}
}
