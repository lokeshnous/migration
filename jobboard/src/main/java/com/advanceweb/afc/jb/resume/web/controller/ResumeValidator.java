/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;
import com.advanceweb.afc.jb.lookup.service.LookupService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Component("resumeValidator")
public class ResumeValidator {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(ResumeValidator.class);

	/** The pattern. */
	private Pattern pattern;

	/** The matcher. */
	private Matcher matcher;

	/** The js work exp date compare. */
	private @Value("${jsWorkExpDateCompare}")
	String jsWorkExpDateCompare;

	/** The lookup service. */
	@Autowired
	private LookupService lookupService;

	/** The validate city state. */
	@Value("${validateCityState}")
	private String validateCityState;

	/**
	 * Validating resume builder
	 * 
	 * @param createResume
	 * @return
	 */
	public String validateResumeBuilder(CreateResume createResume) {

		String validationMessage = "";
		validationMessage = validateContactInfo(createResume);
		if (null == validationMessage) {
			validationMessage = validatePhoneNumbers(createResume);
		}
		if (!StringUtils.isEmpty(validationMessage)) {
			return validationMessage;
		}

		validationMessage = validateWorkExperienceOnSave(createResume
				.getListWorkExpForm());
		if (null == validationMessage) {
			validationMessage = validateReferences(createResume
					.getListRefForm());
		}
		boolean validateStateCityZip = false;
		if ((null != createResume.getContactInfoForm().getCity() && !createResume
				.getContactInfoForm().getCity().isEmpty())
				&& (null != createResume.getContactInfoForm().getState() && !createResume
						.getContactInfoForm().getState().isEmpty())
				&& (null != createResume.getContactInfoForm().getPostalCode() && !createResume
						.getContactInfoForm().getPostalCode().isEmpty())) {

			try {
				validateStateCityZip = lookupService.validateCityStateZip(
						createResume.getContactInfoForm().getCity(),
						createResume.getContactInfoForm().getState(),
						createResume.getContactInfoForm().getPostalCode());
			} catch (JobBoardServiceException e) {
				LOGGER.error("Errog while validating the resume of type Builder : "
						+ e.getMessage());
			}
			if (!validateStateCityZip) {
				validationMessage = validateCityState;
			}
		}
		return validationMessage;
	}

	/**
	 * Validating Contact Information
	 * 
	 * @param form
	 * @return
	 */
	private String validateContactInfo(CreateResume createResume) {
		ContactInfoForm form = new ContactInfoForm();
		if (null != createResume.getContactInfoForm()) {
			form = createResume.getContactInfoForm();
		}
		if (StringUtils.isBlank(form.getFirstName())
				|| StringUtils.isBlank(form.getLastName())
				|| StringUtils.isBlank(form.getAddressLine1())
				|| StringUtils.isBlank(form.getCity())
				|| (StringUtils.isBlank(form.getState()) || MMJBCommonConstants.ZERO
						.equals(form.getState()))
				|| (StringUtils.isBlank(form.getCountry()) || MMJBCommonConstants.ZERO
						.equals(form.getCountry()))
				|| StringUtils.isBlank(form.getPostalCode())) {

			return "Please fill the required fields";
		}

		if (!StringUtils.isEmpty(form.getPhoneNo())
				&& !validateMobileNumberPattern(form.getPhoneNo())) {
			return "Please enter a valid phone number in this format: (xxx) xxx-xxxx";
		}

		if (!StringUtils.isEmpty(form.getPostalCode())
				&& form.getPostalCode().length() > 5) {
			return "Please enter valid ZIP Code";
		}
		return null;
	}

	/**
	 * Validating Phone Numbers
	 * 
	 * @param createResume
	 * @return
	 */
	public String validatePhoneNumbers(CreateResume createResume) {

		if (null != createResume.getListPhoneDtlForm()) {
			for (PhoneDetailForm form : createResume.getListPhoneDtlForm()) {
				if (StringUtils.isEmpty(form.getPhoneType())
						&& StringUtils.isBlank(form.getPhoneNumber())) {
					return "Please fill the required fields";
				}
				if (!validateMobileNumberPattern(form.getPhoneNumber())) {
					return "Please enter a valid phone number in this format: (xxx) xxx-xxxx";
				}
			}
		}
		return null;
	}

	/**
	 * Validating Phone Number Pattern
	 * 
	 * @param mobile
	 * @return
	 */
	private boolean validateMobileNumberPattern(String mobile) {
		pattern = Pattern.compile(MMJBCommonConstants.MOBILE_PATTERN);
		matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	/**
	 * Validating Phone Number Pattern
	 * 
	 * @param mobile
	 * @return
	 */
	private boolean validateNumericsPattern(String value) {
		pattern = Pattern.compile(MMJBCommonConstants.NUMERICS_PATTERN);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * Validating work experience on click of save
	 * 
	 * @param workExpList
	 * @return
	 */
	public String validateWorkExperienceOnSave(List<WorkExpForm> workExpList) {

		if (null != workExpList) {
			for (WorkExpForm form : workExpList) {

				Date currentDate = new Date();
				if (!StringUtils.isEmpty(form.getStartDate())) {
					Date startDate = DateUtils.convertStringToSQLDate(form
							.getStartDate());
					Calendar curDt = Calendar.getInstance();
					Calendar startDt = Calendar.getInstance();
					curDt.setTime(currentDate);
					startDt.setTime(startDate);
					if (startDt.after(curDt)) {
						return "Entered Date should not be greater than current Date";
					}
				}

				if (!StringUtils.isEmpty(form.getEndDate())) {
					Date endDate = DateUtils.convertStringToSQLDate(form
							.getEndDate());
					Calendar curDt = Calendar.getInstance();
					Calendar endDt = Calendar.getInstance();
					curDt.setTime(currentDate);
					endDt.setTime(endDate);
					if (endDt.after(curDt)) {
						return "Entered Date should not be greater than current Date";
					}
				}

				if (!StringUtils.isEmpty(form.getStartDate())
						&& !StringUtils.isEmpty(form.getEndDate())) {
					Date startDate = DateUtils.convertStringToSQLDate(form
							.getStartDate());
					Date endDate = DateUtils.convertStringToSQLDate(form
							.getEndDate());

					Calendar curDt = Calendar.getInstance();
					Calendar startDt = Calendar.getInstance();
					Calendar endDt = Calendar.getInstance();
					curDt.setTime(currentDate);
					endDt.setTime(endDate);
					startDt.setTime(startDate);
					int yearDiff = endDt.get(Calendar.YEAR)
							- startDt.get(Calendar.YEAR);
					if (startDt.after(endDt)) {
						return "Start date should be less than end date";
					}
					if (yearDiff < 0) {
						return "Start date should be less than end date";
					}
				}

				if (!StringUtils.isEmpty(form.getYrsAtPostion())) {
					if ((!StringUtils.isEmpty(form.getYrsAtPostion()) && !validateNumericsPattern(form
							.getYrsAtPostion()))) {
						return "Years at Position should contain only numeric values";
					}
					if (!StringUtils.isEmpty(form.getStartDate())) {
						Date startDate = DateUtils.convertStringToSQLDate(form
								.getStartDate());
						Calendar curDt = Calendar.getInstance();
						Calendar startDt = Calendar.getInstance();
						curDt.setTime(currentDate);
						startDt.setTime(startDate);
						if (startDt.after(curDt)) {
							return "Entered Date should not be greater than current Date";
						}
					}

					if (!StringUtils.isEmpty(form.getEndDate())) {
						Date endDate = DateUtils.convertStringToSQLDate(form
								.getEndDate());
						Calendar curDt = Calendar.getInstance();
						Calendar endDt = Calendar.getInstance();
						curDt.setTime(currentDate);
						endDt.setTime(endDate);
						if (endDt.after(curDt)) {
							return "Entered Date should not be greater than current Date";
						}
					}

					if (!StringUtils.isEmpty(form.getStartDate())
							&& !StringUtils.isEmpty(form.getEndDate())) {
						Date startDate = DateUtils.convertStringToSQLDate(form
								.getStartDate());
						Date endDate = DateUtils.convertStringToSQLDate(form
								.getEndDate());

						Calendar curDt = Calendar.getInstance();
						Calendar startDt = Calendar.getInstance();
						Calendar endDt = Calendar.getInstance();
						curDt.setTime(currentDate);
						endDt.setTime(endDate);
						startDt.setTime(startDate);
						int yearDiff = endDt.get(Calendar.YEAR)
								- startDt.get(Calendar.YEAR);
						if (startDt.after(endDt)) {
							return "Start date should be less than end date";
						}
						if (yearDiff < 0) {
							return "Start date should be less than end date";
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Validating work experience
	 * 
	 * @param workExpList
	 * @return
	 */
	public String validateWorkExperience(List<WorkExpForm> workExpList) {

		if (null != workExpList) {
			for (WorkExpForm form : workExpList) {
				if (!StringUtils.isEmpty(form.getYrsAtPostion())) {
					if ((!StringUtils.isEmpty(form.getYrsAtPostion()) && !validateNumericsPattern(form
							.getYrsAtPostion()))) {
						return "Years at Position should contain only numeric values";
					}
					if (!StringUtils.isEmpty(form.getStartDate())
							&& !StringUtils.isEmpty(form.getEndDate())) {
						Date startDate = DateUtils.convertStringToSQLDate(form
								.getStartDate());
						Date endDate = DateUtils.convertStringToSQLDate(form
								.getEndDate());
						Calendar startDt = Calendar.getInstance();
						Calendar endDt = Calendar.getInstance();

						endDt.setTime(endDate);
						startDt.setTime(startDate);
						int yearDiff = endDt.get(Calendar.YEAR)
								- startDt.get(Calendar.YEAR);
						if (yearDiff < 0) {
							return "Start date should be less than end date";
						}
						int yearAtPosition = Integer.valueOf(form
								.getYrsAtPostion());
						if (yearAtPosition > yearDiff) {
							return "Years at position should be less than the specified start and end date difference";
						}

					}
				}
				if (StringUtils.isEmpty(form.getJobTitle())
						|| StringUtils.isEmpty(form.getEmployerName())
						|| MMJBCommonConstants.ZERO.equals(form
								.getEmploymentType())
						|| StringUtils.isEmpty(form.getStartDate())
						|| (!form.isbPresent() && StringUtils.isEmpty(form
								.getEndDate()))
						|| StringUtils.isEmpty(form.getYrsAtPostion())
						|| MMJBCommonConstants.ZERO.equals(form
								.getCurrentCareerLvl())) {
					return "Please fill the required fields";
				}
			}

		}
		return null;
	}

	/**
	 * This method validates the date in Work Experience section
	 * 
	 * @param form
	 */
	private String validateWorkExpDt(WorkExpForm form) {
		String errorMsg = MMJBCommonConstants.EMPTY;
		if ((!StringUtils.isEmpty(form.getStartDate()) && !validateDatePattern(form
				.getStartDate()))
				|| (!StringUtils.isEmpty(form.getEndDate()) && !validateDatePattern(form
						.getEndDate()))) {
			errorMsg = "Please enter valid date format";
			return errorMsg;
		}
		if (compareDates(form.getStartDate(), form.getEndDate())) {
			errorMsg = jsWorkExpDateCompare;
		}
		return errorMsg;
	}

	/**
	 * Validating Education details
	 * 
	 * @param eduList
	 * @return
	 */
	public String validateEducation(List<EducationForm> eduList) {
		if (null != eduList) {
			for (EducationForm form : eduList) {
				if (StringUtils.isEmpty(form.getInstituteName())
						|| MMJBCommonConstants.ZERO.equals(form.getDegreeLvl())) {
					return "Please fill the required fields";
				}

			}

		}
		return null;
	}

	/**
	 * Validate Certifications
	 * 
	 * @param certsList
	 * @return
	 */
	public String validateCertifications(List<CertificationsForm> certsList) {
		if (null != certsList) {
			for (CertificationsForm form : certsList) {
				if (StringUtils.isEmpty(form.getCertificationName())) {
					return "Please fill the required fields";
				}

			}
		}
		return null;
	}

	/**
	 * Validate References
	 * 
	 * @param certsList
	 * @return
	 */
	private String validateReferences(List<ReferenceForm> refList) {
		if (null != refList) {
			for (ReferenceForm form : refList) {
				if (!StringUtils.isEmpty(form.getPhoneNo())
						&& !validateMobileNumberPattern(form.getPhoneNo())) {
					return "Please enter a valid phone number in this format: (xxx) xxx-xxxx";
				}
				if (!StringUtils.isEmpty(form.getEmail())
						&& !validateEmailPattern(form.getEmail())) {
					return "Please enter valid Email address";
				}
			}
		}
		return null;
	}

	/**
	 * Validating Email Pattern
	 * 
	 * @param emailId
	 * @return
	 */
	private boolean validateEmailPattern(String emailId) {
		pattern = Pattern.compile(MMJBCommonConstants.EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

	/**
	 * Validating Date Pattern
	 * 
	 * @param strDate
	 * @return
	 */
	private boolean validateDatePattern(String strDate) {
		pattern = Pattern.compile(MMJBCommonConstants.MMDDYYYY_PATTERN);
		matcher = pattern.matcher(strDate);
		return matcher.matches();
	}

	/**
	 * This method compares if start Date is greater than End date
	 * 
	 * @param startDate
	 * @param endDate
	 * @return booelan
	 */
	private boolean compareDates(String startDate, String endDate) {

		Date convStartDt = DateUtils.convertToDate(startDate);
		Date convEndDt = DateUtils.convertToDate(endDate);
		if (null != convStartDt && null != convEndDt) {
			return convStartDt.after(convEndDt);
		} else {
			return false;
		}
	}
}
