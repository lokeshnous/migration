package com.advanceweb.afc.jb.agency.web.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author muralikc
 *
 */
public class AgencyRegistrationForm {

	private int userId;

	@NotEmpty
	private String emailId;

	@NotEmpty
	private String confirmEmailId;
	
	@NotEmpty(message = "Position Tilte should not be blank.")
	private String positionTitle;

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty(message = "Company should not be blank.")
	private String company;

	@NotEmpty(message = "Street should not be blank.")
	private String street;

	@NotEmpty(message = "First Name should not be blank.")
	private String firstName;

	private String middleName;

	@NotEmpty(message = "Last Name should not be blank.")
	private String lastName;

	@NotEmpty(message = "City should not be blank.")
	private String city;

	@NotEmpty(message = "State should not be blank.")
	private String state;

	@NotEmpty(message = "Zip Code should not be blank.")
	private String zipCode;

	@NotEmpty(message = "Primary Phone should not be blank.")
	private String primaryPhone;

	private String secondryPhone;

	@NotEmpty(message = "Country should not be blank.")
	private String country;
	
	private boolean bReadOnly;
	// for social media sign up
    private boolean socialSignUp;
	private String serviceProviderName;
	private String socialProfileId;
	private boolean oldUSer;

	public boolean isOldUSer() {
	return oldUSer;
}

public void setOldUSer(boolean oldUSer) {
	this.oldUSer = oldUSer;
}

	private List<AgencyProfileAttribForm> listProfAttribForms;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the primaryPhone
	 */
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primaryPhone
	 *            the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @return the secondryPhone
	 */
	public String getSecondryPhone() {
		return secondryPhone;
	}

	/**
	 * @param secondryPhone
	 *            the secondryPhone to set
	 */
	public void setSecondryPhone(String secondryPhone) {
		this.secondryPhone = secondryPhone;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the listProfAttribForms
	 */
	public List<AgencyProfileAttribForm> getListProfAttribForms() {
		return listProfAttribForms;
	}

	/**
	 * @param listProfAttribForms
	 *            the listProfAttribForms to set
	 */
	public void setListProfAttribForms(
			List<AgencyProfileAttribForm> listProfAttribForms) {
		this.listProfAttribForms = listProfAttribForms;
	}

	/**
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return
	 */
	public String getConfirmEmailId() {
		return confirmEmailId;
	}

	/**
	 * @param confirmEmailId
	 */
	public void setConfirmEmailId(String confirmEmailId) {
		this.confirmEmailId = confirmEmailId;
	}

	/**
	 * @return
	 */
	public String getPositionTitle() {
		return positionTitle;
	}

	/**
	 * @param positionTitle
	 */
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	public boolean isbReadOnly() {
		return bReadOnly;
	}

	public void setbReadOnly(boolean bReadOnly) {
		this.bReadOnly = bReadOnly;
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
