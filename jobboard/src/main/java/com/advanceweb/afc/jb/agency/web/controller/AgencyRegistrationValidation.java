package com.advanceweb.afc.jb.agency.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

@Component("agencyRegistrationValidation")
public class AgencyRegistrationValidation {
	


	private Pattern pattern;
	private Matcher matcher;
    private final String NOT_EMPTY= "NotEmpty";
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
			 errors.rejectValue("emailId", NOT_EMPTY, "Email id should not be blank");
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 errors.rejectValue("confirmEmailId", NOT_EMPTY, "Confirm Email id should not be blank");
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getEmailId()) 
				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 
			 if(!validateEmailPattern(registerForm.getEmailId())){
				 errors.rejectValue("emailId", NOT_EMPTY, "Invalid Email Id"); 
			 }
			 
			 if(!validateEmailPattern(registerForm.getConfirmEmailId())){
				 errors.rejectValue("confirmEmailId", NOT_EMPTY, "Invalid Email Id"); 
			 }
			 
			 if(!registerForm.getEmailId().equals(registerForm.getConfirmEmailId())){
				errors.rejectValue("confirmEmailId", NOT_EMPTY, "E-Mail addresses doesn’t match");
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
					"Password Should not be empty");
		}

		if (StringUtils.isEmpty(confirmPassword)) {
			errors.rejectValue("confirmPassword", NOT_EMPTY,
					"Confirm Password Should not be empty");
		}

		if (!StringUtils.isEmpty(password)
				&& !StringUtils.isEmpty(confirmPassword)) {

			if (!validatePasswordPattern(password)) {
				errors.rejectValue("password", NOT_EMPTY,
						"Password should contain 8-20 characters, including at least 1 number");
			}

			if (!validatePasswordPattern(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOT_EMPTY,
						"Password should contain  8-20 characters, including at least 1 number");
			}

			if (!password.equals(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOT_EMPTY,
						"Passwords are not equal");
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
