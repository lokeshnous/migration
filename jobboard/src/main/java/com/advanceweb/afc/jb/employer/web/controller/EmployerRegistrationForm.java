/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class EmployerRegistrationForm {
	
	/** The user id. */
	private int userId;

	/** The email id. */
	@NotEmpty
	private String emailId;

	/** The confirm email id. */
	@NotEmpty
	private String confirmEmailId;

	/** The position title. */
	@NotEmpty(message = "Position Tilte should not be blank.")
	private String positionTitle;

	/** The password. */
	@NotEmpty
	private String password;

	/** The confirm password. */
	@NotEmpty
	private String confirmPassword;

	/** The company. */
	@NotEmpty(message = "Company should not be blank.")
	private String company;

	/** The street. */
	@NotEmpty(message = "Street should not be blank.")
	private String street;

	/** The first name. */
	@NotEmpty(message = "First Name should not be blank.")
	private String firstName;

	/** The middle name. */
	private String middleName;

	/** The last name. */
	@NotEmpty(message = "Last Name should not be blank.")
	private String lastName;

	/** The city. */
	@NotEmpty(message = "City should not be blank.")
	private String city;

	/** The state. */
	@NotEmpty(message = "State should not be blank.")
	private String state;

	/** The zip code. */
	@NotEmpty(message = "ZIP Code should not be blank.")
	private String zipCode;

	/** The primary phone. */
	@NotEmpty(message = "Primary Phone should not be blank.")
	private String primaryPhone;

	/** The secondry phone. */
	private String secondryPhone;

	/** The country. */
	@NotEmpty(message = "Country should not be blank.")
	private String country;

	/** The facility id. */
	private int facilityId;

	/** The list prof attrib forms. */
	private List<EmployerProfileAttribForm> listProfAttribForms;

	/** The b read only. */
	private boolean bReadOnly;
	// used for social media sign up
	/** The social sign up. */
	private boolean socialSignUp;
	
	/** The service provider name. */
	private String serviceProviderName;
	
	/** The social profile id. */
	private String socialProfileId;
	
	/** The old user. */
	private boolean oldUser;
	
	/** The adv pass user. */
	private boolean advPassUser;
	
	/** The helth system. */
	private boolean helthSystem;
	
	
	
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
	 * Checks if is helth system.
	 *
	 * @return true, if is helth system
	 */
	public boolean isHelthSystem() {
		return helthSystem;
	}

	/**
	 * Sets the helth system.
	 *
	 * @param helthSystem the new helth system
	 */
	public void setHelthSystem(boolean helthSystem) {
		this.helthSystem = helthSystem;
	}

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
	public List<EmployerProfileAttribForm> getListProfAttribForms() {
		return listProfAttribForms;
	}

	/**
	 * @param listProfAttribForms
	 *            the listProfAttribForms to set
	 */
	public void setListProfAttribForms(
			List<EmployerProfileAttribForm> listProfAttribForms) {
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
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
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
	
}
