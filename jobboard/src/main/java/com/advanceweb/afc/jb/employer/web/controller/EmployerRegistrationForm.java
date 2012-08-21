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
	private int userId;

	@NotEmpty
	private String emailId;
	@NotEmpty
	private String confirmEmailId;
	@NotEmpty
	private String positionTitle;
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty
	private String company;
	@NotEmpty(message = "First Name should not be empty")
	private String firstName;
	@NotEmpty(message = "Last Name should not be empty")
	private String lastName;
	@NotEmpty(message = "City should not be empty")
	private String city;
	@NotEmpty(message = "Country should not be empty")
	private String country;
	@NotEmpty(message = "State should not be empty")
	private String state;
	@NotEmpty(message = "Zip Code should not be empty")
	private String postalCode;
	@NotEmpty(message = "Address should not be empty")
	private String address;
	private String middleName;
	private String primaryPhone;
	private String secondryPhone;

	private List<EmployerProfileAttribForm> listProfAttribForms;

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

}
