package com.advanceweb.afc.jb.agency.web.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.advanceweb.afc.jb.employer.web.controller.EmployerProfileAttribForm;

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
	
	@NotEmpty(message = "Position Tilte should not be empty")
	private String positionTitle;

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty(message = "Company should not be empty")
	private String company;

	@NotEmpty(message = "Street should not be empty")
	private String street;

	@NotEmpty(message = "First Name should not be empty")
	private String firstName;

	private String middleName;

	@NotEmpty(message = "Last Name should not be empty")
	private String lastName;

	@NotEmpty(message = "City should not be empty")
	private String city;

	@NotEmpty(message = "State should not be empty")
	private String state;

	@NotEmpty(message = "Zip Code should not be empty")
	private String zipCode;

	@NotEmpty(message = "Primary Phone should not be empty")
	private String primaryPhone;

	private String secondryPhone;

	@NotEmpty(message = "Country should not be empty")
	private String country;

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


}
