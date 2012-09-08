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

	private Pattern pattern;
	private Matcher matcher;

	public boolean supports(Class<?> form) {
		return AdminLoginForm.class.isAssignableFrom(form);
	}

	/**
	 * Validating the emailId
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void validateEmail(AdminLoginForm registerForm, Errors errors) {
		if (StringUtils.isEmpty(registerForm.getJobSeekerOrEmpOrAgeEmail())) {
			errors.rejectValue("jobSeekerOrEmpOrAgeEmail", "NotEmpty",
					"Email id should not be blank");
		}
		 if(!StringUtils.isEmpty(registerForm.getJobSeekerOrEmpOrAgeEmail())){
			 if(!validateEmailPattern(registerForm.getJobSeekerOrEmpOrAgeEmail())){
				 errors.rejectValue("jobSeekerOrEmpOrAgeEmail", "NotEmpty", "Invalid Email Id"); 
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
