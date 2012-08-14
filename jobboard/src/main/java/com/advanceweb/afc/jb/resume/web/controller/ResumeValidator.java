package com.advanceweb.afc.jb.resume.web.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Component("resumeValidator")
public class ResumeValidator {
	
	private Pattern pattern;
	private Matcher matcher;
	
	/**
	 * Validating resume builder
	 * @param createResume
	 * @return
	 */
	public String validateResumeBuilder(CreateResume createResume){
		
		String validationMessage = ""; 
		validationMessage =	validateContactInfo(createResume.getContactInfoForm());
		if(!StringUtils.isEmpty(validationMessage))
			return validationMessage;		
		validationMessage =	validatePhoneNumbers(createResume);
		if(!StringUtils.isEmpty(validationMessage))
			return validationMessage;
		validationMessage = validateCertifications(createResume.getListCertForm());
		if(!StringUtils.isEmpty(validationMessage))
			return validationMessage;
		validationMessage = validateEducation(createResume.getListEduForm());
		if(!StringUtils.isEmpty(validationMessage))
			return validationMessage;
		validationMessage = validateWorkExperience(createResume.getListWorkExpForm());
		if(!StringUtils.isEmpty(validationMessage))
			return validationMessage;
		validationMessage = validateReferences(createResume.getListRefForm());
		
		return validationMessage;
	}
	
	/**
	 * Validating Contact Information
	 * @param form
	 * @return
	 */
	private String validateContactInfo(ContactInfoForm form){
		
		if(StringUtils.isEmpty(form.getFirstName()) ||
		   StringUtils.isEmpty(form.getLastName()) ||
		   StringUtils.isEmpty(form.getAddressLine1()) || 
		   StringUtils.isEmpty(form.getCity()) ||
		   (StringUtils.isEmpty(form.getState()) && 
				   MMJBCommonConstants.ZERO.equals(form.getState()))  ||
		   (StringUtils.isEmpty(form.getCountry()) 
		   			&& MMJBCommonConstants.ZERO.equals(form.getCountry()))||
		   StringUtils.isEmpty(form.getPostalCode())){
			
			return "Please fill the required fields";
		}
		return null;
	}
	
	/**
	 * Validating Phone Numbers
	 * @param createResume
	 * @return
	 */
	private String validatePhoneNumbers(CreateResume createResume){
		
		if(null != createResume.getListPhoneDtlForm()){
			for(PhoneDetailForm form : createResume.getListPhoneDtlForm()){				
				if(StringUtils.isEmpty(form.getPhoneType()) && StringUtils.isEmpty(form.getPhoneNumber())){
					if(!validateMobileNumberPattern(form.getPhoneNumber())){
						return "Phone number should contain only numbers";
					}
				}
			}
		}
		return null;	
	}
	
	/**
	 * Validating Phone Number Pattern
	 * @param mobile
	 * @return
	 */
	private boolean validateMobileNumberPattern(String mobile){
		pattern = Pattern.compile(MMJBCommonConstants.MOBILE_PATTERN);
		matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	
	/**
	 * Validating work experience
	 * @param workExpList
	 * @return
	 */
	private String validateWorkExperience(List<WorkExpForm> workExpList){
		
		if(null != workExpList){
			for(WorkExpForm form : workExpList){
				if(StringUtils.isEmpty(form.getJobTitle()) ||
				   StringUtils.isEmpty(form.getEmployerName()) ||
				   MMJBCommonConstants.ZERO.equals(form.getEmploymentType()) ||
				   StringUtils.isEmpty(form.getStartDate()) ||
				   (!form.isbPresent() && StringUtils.isEmpty(form.getEndDate())) ||
				   StringUtils.isEmpty(form.getYrsAtPostion()) ||
				   MMJBCommonConstants.ZERO.equals(form.getCurrentCareerLvl())){
					return "Please fill the required fields";
				}
				if((!StringUtils.isEmpty(form.getStartDate()) && !validateDatePattern(form.getStartDate()))||
					(!StringUtils.isEmpty(form.getEndDate()) && !validateDatePattern(form.getEndDate()))){
					return "Please enter valid date format";
				}
			}
		}
		return null;
	}
	
	/**
	 * Validating Education details
	 * @param eduList
	 * @return
	 */
	private String validateEducation(List<EducationForm> eduList){
		if(null != eduList){
			for(EducationForm form : eduList){
				if(StringUtils.isEmpty(form.getInstituteName()) ||
					MMJBCommonConstants.ZERO.equals(form.getDegreeLvl())){
					return "Please fill the required fields";
				}
				if((!StringUtils.isEmpty(form.getStartDate()) && !validateDatePattern(form.getStartDate()))||
						(!StringUtils.isEmpty(form.getEndDate()) && !validateDatePattern(form.getEndDate()))){
						return "Please enter valid date format";
				}
			}
			
		}
		return null;
	}
	
	
	/**
	 * Validate Certifications
	 * @param certsList
	 * @return
	 */
	private String validateCertifications(List<CertificationsForm> certsList){
		if(null != certsList){
			for(CertificationsForm form : certsList){
				if(StringUtils.isEmpty(form.getCertificationName())){
					return "Please fill the required fields";
				}
				if(!StringUtils.isEmpty(form.getDateOfReceipt()) && !validateDatePattern(form.getDateOfReceipt())){
						return "Please enter valid date format";
				}
			}			
		}
		return null;
	}
	
	/**
	 * Validate References
	 * @param certsList
	 * @return
	 */
	private String validateReferences(List<ReferenceForm> refList){
		if(null != refList){
			for(ReferenceForm form : refList){
				if(!StringUtils.isEmpty(form.getPhoneNo()) && !validateMobileNumberPattern(form.getPhoneNo())){
					return "Phone number should contain only numbers";
				}
				if(!StringUtils.isEmpty(form.getEmail()) && !validateEmailPattern(form.getEmail())){
					return "Please enter valid emai address";
				}
			}			
		}
		return null;
	}
	
	/**
	 * Validating Email Pattern
	 * @param emailId
	 * @return
	 */
	private boolean validateEmailPattern(String emailId){
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
	private boolean validateDatePattern(String strDate){
		pattern = Pattern.compile(MMJBCommonConstants.MMDDYYYY_PATTERN);
		matcher = pattern.matcher(strDate);
		return matcher.matches();
	}
}
