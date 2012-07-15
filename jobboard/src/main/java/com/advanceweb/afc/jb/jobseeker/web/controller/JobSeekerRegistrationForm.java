package com.advanceweb.afc.jb.jobseeker.web.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerRegistrationForm {

//	@NotEmpty
//	private String addressLine1;
//	@NotEmpty
//	private String addressLine2;
//	@NotEmpty
//	private String city;
//
//	@NotEmpty
//	private String country;
	@NotEmpty
	private String emailId;
	@NotEmpty
	private String confirmEmailId;
	private String newsLetterEmailId;
	@NotEmpty
	private String myIndustry;
	@NotEmpty
	private String myProfession;
	@NotEmpty
	private String mySpeciality;
	private String ethenticity;
	// private String emailId;//security check
//	@NotEmpty
//	private String firstName;
	private String gender;
	private String imCurrentlyIn;
//	@NotEmpty
//	private String lastName;
//	private String middleName;
	@NotEmpty
	private String password;
	private String phone;
	
	private String currentPassword;
	
//	@NotEmpty
//	private String postalCode;
	@NotEmpty
	private String retypepassword;
	// private String emailId;//resume upload
//	@NotEmpty
//	private String state;
	private String veteranStatus;
	
	private String MobileNo;
	
	private CommonsMultipartFile fileData;
	private String fileName;
	
	private ContactInfoForm contactForm;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
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

	public ContactInfoForm getContactForm() {
		return contactForm;
	}

	public void setContactForm(ContactInfoForm contactForm) {
		this.contactForm = contactForm;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
