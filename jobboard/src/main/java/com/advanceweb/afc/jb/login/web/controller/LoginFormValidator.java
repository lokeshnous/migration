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

	private Pattern pattern;
	private Matcher matcher;

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
			errors.rejectValue("emailAddress", "NotEmpty",
					"Email Id Should not be empty");
		}

		if (!StringUtils.isEmpty(loginForm.getEmailAddress())) {

			if (!validateEmailPattern(loginForm.getEmailAddress())) {
				errors.rejectValue("emailAddress", "NotEmpty", "Invalid Email Id");
			}
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
			errors.rejectValue("password", "NotEmpty",
					"Password Should not be empty");
		}

		if (!StringUtils.isEmpty(loginForm.getPassword())) {

			if (!validatePasswordPattern(loginForm.getPassword())) {
				errors.rejectValue("password", "NotEmpty",
						"(Password should contain 8-20 characters, including at least 1 number)");
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
		if (form != null && loginFormDTOForUser != null) {
			if (form.getRoleId() == loginFormDTOForUser.getRoleId()) {
				if ((form.getEmailAddress().equals(loginFormDTOForUser
						.getEmailAddress()))
						&& (form.getPassword().equals(loginFormDTOForUser
								.getPassword()))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Validation for forgot password
	 * 
	 * @param form
	 * @param userDetailsLoginFormDTO
	 * @return
	 */
	public boolean validateEmailValues(String email,
			LoginDTO userDetailsLoginFormDTO) {

		if (email != null && userDetailsLoginFormDTO != null) {
			if (email.equals(
					userDetailsLoginFormDTO.getEmailAddress())) {
				return true;
			}
		}
		return false;

	}
}
