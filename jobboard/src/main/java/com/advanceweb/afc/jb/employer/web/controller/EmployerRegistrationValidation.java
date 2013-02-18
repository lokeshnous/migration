package com.advanceweb.afc.jb.employer.web.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * @author muralikc
 *
 */
@Component("employerRegistrationValidation")
public class EmployerRegistrationValidation {
	
	@Value("${emp.email.blank}")
	private String emailBlank;
	
	@Value("${emp.conformEmail.blank}")
	private String conformEmailBlank;
	
	@Value("${emp.invalid.email}")
	private String invalidEmail;
	
	@Value("${emp.email.match}")
	private String emailMatch;
	
	@Value("${emp.password.empty}")
	private String pwdEmpty;
	
	@Value("${emp.conform.pass.empty}")
	private String conformPassEmpty;
	
	@Value("${emp.pwd.hint}")
	private String pwdHint;
	
	@Value("${emp.pwd.not.equal}")
	private String pwdNotEqual;
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String NOTEMPTY = "NotEmpty";
	private static final String EMAILID = "emailId";

	public boolean supports(Class<?> form) {
		return EmployerRegistrationForm.class.isAssignableFrom(form);
	}
	public boolean support(Class<?> form) {
		return EmployeeAccountForm.class.isAssignableFrom(form);
	}

	/**
	 * Validating the emailId
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void validateEmail(EmployerRegistrationForm registerForm,
			Errors errors) {
		
		 if(StringUtils.isEmpty(registerForm.getEmailId())){
			 errors.rejectValue(EMAILID, NOTEMPTY, emailBlank);
		 }
		 
		 if(StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 errors.rejectValue("confirmEmailId", NOTEMPTY, conformEmailBlank);
		 }
		 
		 if(!StringUtils.isEmpty(registerForm.getEmailId()) 
				 && !StringUtils.isEmpty(registerForm.getConfirmEmailId())){
			 
			 if(!validateEmailPattern(registerForm.getEmailId())){
				 errors.rejectValue(EMAILID, NOTEMPTY, ""); 
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
			errors.rejectValue("password", NOTEMPTY,
					pwdEmpty);
		}

		if (StringUtils.isEmpty(confirmPassword)) {
			errors.rejectValue("confirmPassword", NOTEMPTY,
					conformPassEmpty);
		}

		if (!StringUtils.isEmpty(password)
				&& !StringUtils.isEmpty(confirmPassword)) {

			if (!validatePasswordPattern(password)) {
				errors.rejectValue("password", NOTEMPTY,
						pwdHint);
			}

			if (!validatePasswordPattern(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOTEMPTY,
						pwdHint);
			}

			if (!password.equals(confirmPassword)) {
				errors.rejectValue("confirmPassword", NOTEMPTY,
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

		EmployerRegistrationForm registerForm = (EmployerRegistrationForm) target;

		validateEmail(registerForm, errors);
		validatePassoword(registerForm.getPassword(),
				registerForm.getConfirmPassword(), errors);
	}
	
	
	/**
	 * Validating the emailId
	 * 
	 * @param registerForm
	 * @return
	 * @return
	 */
	public void checkEmail(EmployeeAccountForm registerForm,
			Errors errors) {
		
		 if(StringUtils.isEmpty(registerForm.getEmail())){
			 errors.rejectValue(EMAILID, NOTEMPTY, emailBlank);
		 }
			 
		 if(!StringUtils.isEmpty(registerForm.getEmail())){
			 if(!validateEmailPattern(registerForm.getEmail())){
				 errors.rejectValue(EMAILID, NOTEMPTY, invalidEmail); 
			 }
			 
		 }
	}
	
	/**
	 * Validating form
	 * 
	 * @param target
	 * @param errors
	 * @return 
	 */
	public boolean accountValidate(Object target, Errors errors) {
		EmployeeAccountForm registerForm = (EmployeeAccountForm) target;
		return StringUtils.isBlank(registerForm.getEmail());
	}
}
