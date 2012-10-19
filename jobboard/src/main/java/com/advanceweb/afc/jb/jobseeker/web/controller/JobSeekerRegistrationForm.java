package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerRegistrationForm extends ContactInfoForm{

	private String emailId;
	private String confirmEmailId;
	private String newsLetterEmailId;
	@NotEmpty(message="Industry should not be blank.")
	private String myIndustry="Health Care";
	@NotEmpty(message="Profession should not be blank.")
	private String myProfession;
	@NotEmpty(message="Speciality should not be blank.")
	private String mySpeciality;
	@NotEmpty(message="Job Title should not be blank.")
	private String myJobTitle;
	private String ethenticity;
	private String gender;
	private String imCurrentlyIn;
	private String password;
	private String currentPassword;	
	private String retypepassword;
	private String veteranStatus;	
	private String mobileNo;
	private String employmentType;
	private MultipartFile uploadResume;
	private String[] currentsubs;
	private String userId;	
	private boolean bReadOnly;
	// used for social media sign up
	private boolean socialSignUp;
	private String serviceProviderName;
	private String socialProfileId;
	private List<JobSeekerProfileAttribForm> listProfAttribForms;
	private String otherProfession;
	private boolean clickBack;
	
	
	
	public String getOtherProfession() {
		return otherProfession;
	}

	public void setOtherProfession(String otherProfession) {
		this.otherProfession = otherProfession;
	}

	private CommonsMultipartFile fileData;
	private String fileName;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getConfirmEmailId() {
		return confirmEmailId;
	}

	public void setConfirmEmailId(String confirmEmailId) {
		this.confirmEmailId = confirmEmailId;
	}

	public String getNewsLetterEmailId() {
		return newsLetterEmailId;
	}

	public void setNewsLetterEmailId(String newsLetterEmailId) {
		this.newsLetterEmailId = newsLetterEmailId;
	}

	public String getMyIndustry() {
		return myIndustry;
	}

	public void setMyIndustry(String myIndustry) {
		this.myIndustry = myIndustry;
	}

	public String getMyProfession() {
		return myProfession;
	}

	public void setMyProfession(String myProfession) {
		this.myProfession = myProfession;
	}

	public String getMySpeciality() {
		return mySpeciality;
	}

	public void setMySpeciality(String mySpeciality) {
		this.mySpeciality = mySpeciality;
	}

	public String getEthenticity() {
		return ethenticity;
	}

	public void setEthenticity(String ethenticity) {
		this.ethenticity = ethenticity;
	}

	public String getImCurrentlyIn() {
		return imCurrentlyIn;
	}

	public void setImCurrentlyIn(String imCurrentlyIn) {
		this.imCurrentlyIn = imCurrentlyIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getRetypepassword() {
		return retypepassword;
	}

	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	public String getVeteranStatus() {
		return veteranStatus;
	}

	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}
	
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getMyJobTitle() {
		return myJobTitle;
	}

	public void setMyJobTitle(String myJobTitle) {
		this.myJobTitle = myJobTitle;
	}

	public MultipartFile getUploadResume() {
		return uploadResume;
	}

	public void setUploadResume(MultipartFile uploadResume) {
		this.uploadResume = uploadResume;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getCurrentsubs() {
		return currentsubs;
	}

	public void setCurrentsubs(String[] currentsubs) {
		this.currentsubs = currentsubs;
	}

	public List<JobSeekerProfileAttribForm> getListProfAttribForms() {
		return listProfAttribForms;
	}

	public void setListProfAttribForms(
			List<JobSeekerProfileAttribForm> listProfAttribForms) {
		this.listProfAttribForms = listProfAttribForms;
	}

	public boolean isbReadOnly() {
		return bReadOnly;
	}

	public void setbReadOnly(boolean bReadOnly) {
		this.bReadOnly = bReadOnly;
	}

	public boolean isClickBack() {
		return clickBack;
	}

	public void setClickBack(boolean clickBack) {
		this.clickBack = clickBack;
	}

	public boolean isSocialSignUp() {
		return socialSignUp;
	}

	public void setSocialSignUp(boolean socialSignUp) {
		this.socialSignUp = socialSignUp;
	}

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public String getSocialProfileId() {
		return socialProfileId;
	}

	public void setSocialProfileId(String socialProfileId) {
		this.socialProfileId = socialProfileId;
	}	
	
}
