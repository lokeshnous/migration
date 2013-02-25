/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerRegistrationForm extends ContactInfoForm {

	/** The email id. */
	private String emailId;
	
	/** The confirm email id. */
	private String confirmEmailId;
	
	/** The news letter email id. */
	private String newsLetterEmailId;
	
	/** The my industry. */
	@NotEmpty(message = "Industry should not be blank.")
	private String myIndustry = "Healthcare";
	
	/** The my profession. */
	@NotEmpty(message = "Profession should not be blank.")
	private String myProfession;
	
	/** The my speciality. */
	@NotEmpty(message = "Speciality should not be blank.")
	private String mySpeciality;
	
	/** The my job title. */
	@NotEmpty(message = "Job Title should not be blank.")
	private String myJobTitle;
	
	/** The ethenticity. */
	private String ethenticity;
	
	/** The gender. */
	private String gender;
	
	/** The im currently in. */
	private String imCurrentlyIn;
	
	/** The password. */
	private String password;
	
	/** The current password. */
	private String currentPassword;
	
	/** The retypepassword. */
	private String retypepassword;
	
	/** The veteran status. */
	private String veteranStatus;
	
	/** The mobile no. */
	private String mobileNo;
	
	/** The employment type. */
	private String employmentType;
	
	/** The upload resume. */
	private MultipartFile uploadResume;
	
	/** The currentsubs. */
	private String[] currentsubs;
	
	/** The user id. */
	private String userId;
	
	/** The b read only. */
	private boolean bReadOnly;
	
	/** The old user. */
	private boolean oldUser;
	
	/** The adv pass user. */
	private boolean advPassUser;
	// used for social media sign up
	/** The social sign up. */
	private boolean socialSignUp;
	
	/** The service provider name. */
	private String serviceProviderName;
	
	/** The social profile id. */
	private String socialProfileId;
	
	/** The list prof attrib forms. */
	private List<JobSeekerProfileAttribForm> listProfAttribForms;
	
	/** The other profession. */
	private String otherProfession;
	
	/** The click back. */
	private boolean clickBack;

	/** The print sub. */
	private String[] printSub;
	
	/** The dig sub. */
	private String[] digSub;
	
	/** The news sub. */
	private String[] newsSub;
	
	/** The email sub. */
	private String[] emailSub;
	
	/** The print checkbox. */
	private boolean printCheckbox;
	
	/** The dig checkbox. */
	private boolean digCheckbox;
	
	/** The enews checkbox. */
	private boolean enewsCheckbox;
	
	/** The mail checkbox. */
	private boolean mailCheckbox;

	
	/**
	 * Checks if is adv pass user.
	 *
	 * @return true, if is adv pass user
	 */
	public boolean isAdvPassUser() {
		return advPassUser;
	}

	/**
	 * Sets the adv pass user.
	 *
	 * @param advPassUser the new adv pass user
	 */
	public void setAdvPassUser(boolean advPassUser) {
		this.advPassUser = advPassUser;
	}

	/**
	 * Checks if is old user.
	 *
	 * @return true, if is old user
	 */
	public boolean isOldUser() {
		return oldUser;
	}

	/**
	 * Sets the old user.
	 *
	 * @param oldUser the new old user
	 */
	public void setOldUser(boolean oldUser) {
		this.oldUser = oldUser;
	}

	/**
	 * Gets the other profession.
	 *
	 * @return the other profession
	 */
	public String getOtherProfession() {
		return otherProfession;
	}

	/**
	 * Sets the other profession.
	 *
	 * @param otherProfession the new other profession
	 */
	public void setOtherProfession(String otherProfession) {
		this.otherProfession = otherProfession;
	}

	/** The file data. */
	private CommonsMultipartFile fileData;
	
	/** The file name. */
	private String fileName;

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the confirm email id.
	 *
	 * @return the confirm email id
	 */
	public String getConfirmEmailId() {
		return confirmEmailId;
	}

	/**
	 * Sets the confirm email id.
	 *
	 * @param confirmEmailId the new confirm email id
	 */
	public void setConfirmEmailId(String confirmEmailId) {
		this.confirmEmailId = confirmEmailId;
	}

	/**
	 * Gets the news letter email id.
	 *
	 * @return the news letter email id
	 */
	public String getNewsLetterEmailId() {
		return newsLetterEmailId;
	}

	/**
	 * Sets the news letter email id.
	 *
	 * @param newsLetterEmailId the new news letter email id
	 */
	public void setNewsLetterEmailId(String newsLetterEmailId) {
		this.newsLetterEmailId = newsLetterEmailId;
	}

	/**
	 * Gets the my industry.
	 *
	 * @return the my industry
	 */
	public String getMyIndustry() {
		return myIndustry;
	}

	/**
	 * Sets the my industry.
	 *
	 * @param myIndustry the new my industry
	 */
	public void setMyIndustry(String myIndustry) {
		this.myIndustry = myIndustry;
	}

	/**
	 * Gets the my profession.
	 *
	 * @return the my profession
	 */
	public String getMyProfession() {
		return myProfession;
	}

	/**
	 * Sets the my profession.
	 *
	 * @param myProfession the new my profession
	 */
	public void setMyProfession(String myProfession) {
		this.myProfession = myProfession;
	}

	/**
	 * Gets the my speciality.
	 *
	 * @return the my speciality
	 */
	public String getMySpeciality() {
		return mySpeciality;
	}

	/**
	 * Sets the my speciality.
	 *
	 * @param mySpeciality the new my speciality
	 */
	public void setMySpeciality(String mySpeciality) {
		this.mySpeciality = mySpeciality;
	}

	/**
	 * Gets the ethenticity.
	 *
	 * @return the ethenticity
	 */
	public String getEthenticity() {
		return ethenticity;
	}

	/**
	 * Sets the ethenticity.
	 *
	 * @param ethenticity the new ethenticity
	 */
	public void setEthenticity(String ethenticity) {
		this.ethenticity = ethenticity;
	}

	/**
	 * Gets the im currently in.
	 *
	 * @return the im currently in
	 */
	public String getImCurrentlyIn() {
		return imCurrentlyIn;
	}

	/**
	 * Sets the im currently in.
	 *
	 * @param imCurrentlyIn the new im currently in
	 */
	public void setImCurrentlyIn(String imCurrentlyIn) {
		this.imCurrentlyIn = imCurrentlyIn;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * Sets the current password.
	 *
	 * @param currentPassword the new current password
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * Gets the retypepassword.
	 *
	 * @return the retypepassword
	 */
	public String getRetypepassword() {
		return retypepassword;
	}

	/**
	 * Sets the retypepassword.
	 *
	 * @param retypepassword the new retypepassword
	 */
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	/**
	 * Gets the veteran status.
	 *
	 * @return the veteran status
	 */
	public String getVeteranStatus() {
		return veteranStatus;
	}

	/**
	 * Sets the veteran status.
	 *
	 * @param veteranStatus the new veteran status
	 */
	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	/**
	 * Gets the file data.
	 *
	 * @return the file data
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	/**
	 * Sets the file data.
	 *
	 * @param fileData the new file data
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm#getMobileNo()
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm#setMobileNo(java.lang.String)
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * Gets the my job title.
	 *
	 * @return the my job title
	 */
	public String getMyJobTitle() {
		return myJobTitle;
	}

	/**
	 * Sets the my job title.
	 *
	 * @param myJobTitle the new my job title
	 */
	public void setMyJobTitle(String myJobTitle) {
		this.myJobTitle = myJobTitle;
	}

	/**
	 * Gets the upload resume.
	 *
	 * @return the upload resume
	 */
	public MultipartFile getUploadResume() {
		return uploadResume;
	}

	/**
	 * Sets the upload resume.
	 *
	 * @param uploadResume the new upload resume
	 */
	public void setUploadResume(MultipartFile uploadResume) {
		this.uploadResume = uploadResume;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the currentsubs.
	 *
	 * @return the currentsubs
	 */
	public String[] getCurrentsubs() {
		return currentsubs;
	}

	/**
	 * Sets the currentsubs.
	 *
	 * @param currentsubs the new currentsubs
	 */
	public void setCurrentsubs(String[] currentsubs) {
		this.currentsubs = currentsubs;
	}

	/**
	 * Gets the list prof attrib forms.
	 *
	 * @return the list prof attrib forms
	 */
	public List<JobSeekerProfileAttribForm> getListProfAttribForms() {
		return listProfAttribForms;
	}

	/**
	 * Sets the list prof attrib forms.
	 *
	 * @param listProfAttribForms the new list prof attrib forms
	 */
	public void setListProfAttribForms(
			List<JobSeekerProfileAttribForm> listProfAttribForms) {
		this.listProfAttribForms = listProfAttribForms;
	}

	/**
	 * Checks if is b read only.
	 *
	 * @return true, if is b read only
	 */
	public boolean isbReadOnly() {
		return bReadOnly;
	}

	/**
	 * Sets the b read only.
	 *
	 * @param bReadOnly the new b read only
	 */
	public void setbReadOnly(boolean bReadOnly) {
		this.bReadOnly = bReadOnly;
	}

	/**
	 * Checks if is click back.
	 *
	 * @return true, if is click back
	 */
	public boolean isClickBack() {
		return clickBack;
	}

	/**
	 * Sets the click back.
	 *
	 * @param clickBack the new click back
	 */
	public void setClickBack(boolean clickBack) {
		this.clickBack = clickBack;
	}

	/**
	 * Checks if is social sign up.
	 *
	 * @return true, if is social sign up
	 */
	public boolean isSocialSignUp() {
		return socialSignUp;
	}

	/**
	 * Sets the social sign up.
	 *
	 * @param socialSignUp the new social sign up
	 */
	public void setSocialSignUp(boolean socialSignUp) {
		this.socialSignUp = socialSignUp;
	}

	/**
	 * Gets the service provider name.
	 *
	 * @return the service provider name
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * Sets the service provider name.
	 *
	 * @param serviceProviderName the new service provider name
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * Gets the social profile id.
	 *
	 * @return the social profile id
	 */
	public String getSocialProfileId() {
		return socialProfileId;
	}

	/**
	 * Sets the social profile id.
	 *
	 * @param socialProfileId the new social profile id
	 */
	public void setSocialProfileId(String socialProfileId) {
		this.socialProfileId = socialProfileId;
	}

	/**
	 * Gets the prints the sub.
	 *
	 * @return the prints the sub
	 */
	public String[] getPrintSub() {
		return printSub;
	}

	/**
	 * Sets the prints the sub.
	 *
	 * @param printSub the new prints the sub
	 */
	public void setPrintSub(String[] printSub) {
		this.printSub = printSub;
	}

	/**
	 * Gets the dig sub.
	 *
	 * @return the dig sub
	 */
	public String[] getDigSub() {
		return digSub;
	}

	/**
	 * Sets the dig sub.
	 *
	 * @param digSub the new dig sub
	 */
	public void setDigSub(String[] digSub) {
		this.digSub = digSub;
	}

	/**
	 * Gets the news sub.
	 *
	 * @return the news sub
	 */
	public String[] getNewsSub() {
		return newsSub;
	}

	/**
	 * Sets the news sub.
	 *
	 * @param newsSub the new news sub
	 */
	public void setNewsSub(String[] newsSub) {
		this.newsSub = newsSub;
	}

	/**
	 * Checks if is prints the checkbox.
	 *
	 * @return true, if is prints the checkbox
	 */
	public boolean isPrintCheckbox() {
		return printCheckbox;
	}

	/**
	 * Sets the prints the checkbox.
	 *
	 * @param printCheckbox the new prints the checkbox
	 */
	public void setPrintCheckbox(boolean printCheckbox) {
		this.printCheckbox = printCheckbox;
	}

	/**
	 * Checks if is dig checkbox.
	 *
	 * @return true, if is dig checkbox
	 */
	public boolean isDigCheckbox() {
		return digCheckbox;
	}

	/**
	 * Sets the dig checkbox.
	 *
	 * @param digCheckbox the new dig checkbox
	 */
	public void setDigCheckbox(boolean digCheckbox) {
		this.digCheckbox = digCheckbox;
	}

	/**
	 * Checks if is enews checkbox.
	 *
	 * @return true, if is enews checkbox
	 */
	public boolean isEnewsCheckbox() {
		return enewsCheckbox;
	}

	/**
	 * Sets the enews checkbox.
	 *
	 * @param enewsCheckbox the new enews checkbox
	 */
	public void setEnewsCheckbox(boolean enewsCheckbox) {
		this.enewsCheckbox = enewsCheckbox;
	}

	/**
	 * Checks if is mail checkbox.
	 *
	 * @return true, if is mail checkbox
	 */
	public boolean isMailCheckbox() {
		return mailCheckbox;
	}

	/**
	 * Sets the mail checkbox.
	 *
	 * @param mailCheckbox the new mail checkbox
	 */
	public void setMailCheckbox(boolean mailCheckbox) {
		this.mailCheckbox = mailCheckbox;
	}

	/**
	 * Gets the email sub.
	 *
	 * @return the email sub
	 */
	public String[] getEmailSub() {
		return emailSub;
	}

	/**
	 * Sets the email sub.
	 *
	 * @param emailSub the new email sub
	 */
	public void setEmailSub(String[] emailSub) {
		this.emailSub = emailSub;
	}

}
