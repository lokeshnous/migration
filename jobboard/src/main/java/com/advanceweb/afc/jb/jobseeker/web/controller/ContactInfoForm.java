package com.advanceweb.afc.jb.jobseeker.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */

public class ContactInfoForm {
	
	@NotEmpty(message="First Name should not be blank.")
	private String firstName;	
	@NotEmpty(message="Last Name should not be blank.")
	private String lastName;
	@NotEmpty(message="City should not be blank.")
	private String city;	
	@NotEmpty(message="Country should not be blank.")
	private String country;	
	@NotEmpty(message="State should not be blank.")
	private String state;	
	@NotEmpty(message="Zip Code should not be blank.")
	private String postalCode;
	@NotEmpty(message="Address should not be blank.")
	private String addressLine1;
	private String addressLine2;	
	private String middleName;
	private String phoneNo;	
	private String mobileNo;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
			
}
