package com.advanceweb.afc.jb.webapp.web.forms.registration;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerRegistrationForm {

	@NotEmpty
	private String addressLine1;
	@NotEmpty
	private String addressLine2;
	@NotEmpty
	private String city;

	@NotEmpty
	private String country;
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
	@NotEmpty
	private String firstName;
	private String gender;
	private String imCurrentlyIn;
	@NotEmpty
	private String lastName;
	private String middleName;
	@NotEmpty
	private String password;
	private String phone;
	
	private String currentPassword;
	
	@NotEmpty
	private String postalCode;
	@NotEmpty
	private String retypepassword;
	// private String emailId;//resume upload
	@NotEmpty
	private String state;
	private String veteranStatus;
	
	private String MobileNo;
	
	private CommonsMultipartFile fileData;
	private String fileName;

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getEthenticity() {
		return ethenticity;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}

	public String getImCurrentlyIn() {
		return imCurrentlyIn;
	}

	public String getLastName() {
		return lastName;
	}


	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getRetypepassword() {
		return retypepassword;
	}

	public String getState() {
		return state;
	}

	public String getVeteranStatus() {
		return veteranStatus;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setEthenticity(String ethenticity) {
		this.ethenticity = ethenticity;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setImCurrentlyIn(String imCurrentlyIn) {
		this.imCurrentlyIn = imCurrentlyIn;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	
}
