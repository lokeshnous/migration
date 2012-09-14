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
	 * @param form
	 * @return
	 * @return
	 */
	public void validateEmail(AdminLoginForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getEmpOrAgencyEmail())
				|| StringUtils.isEmpty(form.getPassword())
				|| StringUtils.isEmpty(form.getUserEmail())) {
			errors.rejectValue("empOrAgencyEmail", "NotEmpty",
					"Please fill all the fields");
		}
		if (!StringUtils.isEmpty(form.getEmpOrAgencyEmail())) {
			if (!validateEmailPattern(form.getEmpOrAgencyEmail())) {
				errors.rejectValue("empOrAgencyEmail", "NotEmpty",
						"Invalid Employer/Agency Email address");
			}
		}
		if (!StringUtils.isEmpty(form.getUserEmail())) {
			if (!validateEmailPattern(form.getUserEmail())) {
				errors.rejectValue("userEmail", "NotEmpty",
						"Invalid User Email address");
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
