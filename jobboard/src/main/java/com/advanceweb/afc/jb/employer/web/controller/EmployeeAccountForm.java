/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import com.advanceweb.afc.jb.pgi.web.controller.BillingAddressForm;

/**
 * 
 * @author kartikm 
 * Email kartikm@nousinfo.com Date 20.08.2012
 * 
 */
public class EmployeeAccountForm {

	// Account Address information
	/** The billing address form. */
	public BillingAddressForm billingAddressForm;
	
	/** The first name. */
	private String firstName;
	
	/** The middle name. */
	private String middleName;
	
	/** The last name. */
	private String lastName;
	
	/** The email. */
	private String email;
	
	/** The original email. */
	private String originalEmail;
	
	/** The company. */
	private String company;
	
	/** The street address. */
	private String streetAddress;
	
	/** The city or town. */
	private String cityOrTown;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The zip code. */
	private String zipCode;
	
	/** The phone. */
	private String phone;
	
	/** The admin login. */
	private boolean adminLogin;
	
	/** The read only. */
	private boolean readOnly;
	
	/** The use my account addr. */
	private boolean useMyAccountAddr;
	
	
	/**
	 * Checks if is admin login.
	 *
	 * @return true, if is admin login
	 */
	public boolean isAdminLogin() {
		return adminLogin;
	}
	
	/**
	 * Sets the admin login.
	 *
	 * @param adminLogin the new admin login
	 */
	public void setAdminLogin(boolean adminLogin) {
		this.adminLogin = adminLogin;
	}

	/**
	 * @return the billingAddressForm
	 */
	public BillingAddressForm getBillingAddressForm() {
		return billingAddressForm;
	}

	/**
	 * @param billingAddressForm
	 *            the billingAddressForm to set
	 */
	public void setBillingAddressForm(BillingAddressForm billingAddressForm) {
		this.billingAddressForm = billingAddressForm;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress
	 *            the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the cityOrTown
	 */
	public String getCityOrTown() {
		return cityOrTown;
	}

	/**
	 * @param cityOrTown
	 *            the cityOrTown to set
	 */
	public void setCityOrTown(String cityOrTown) {
		this.cityOrTown = cityOrTown;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	/**
	 * Checks if is use my account addr.
	 *
	 * @return true, if is use my account addr
	 */
	public boolean isUseMyAccountAddr() {
		return useMyAccountAddr;
	}
	
	/**
	 * Sets the use my account addr.
	 *
	 * @param useMyAccountAddr the new use my account addr
	 */
	public void setUseMyAccountAddr(boolean useMyAccountAddr) {
		this.useMyAccountAddr = useMyAccountAddr;
	}

	public String getOriginalEmail() {
		return originalEmail;
	}

	public void setOriginalEmail(String originalEmail) {
		this.originalEmail = originalEmail;
	}


}
