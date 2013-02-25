/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * 
 * @author kartikm
 *
 */
public class AdmFacilityContactDTO implements Serializable {
	/**
	 * serial version id is long.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * count count.
	 */
	private int count;
	/**
	 * name name.
	 */
	private String firstName;
	/**
	 * laststName laststName
	 */
	private String lastName;
	/**
	 * companyName companyName.
	 */
	private String companyName;
	/**
	 * street street.
	 */
	private String street;
	/**
	 * city city.
	 */
	private String city;
	/**
	 * zipCode zipCode.
	 */
	private String zipCode;	
	/**
	 * email email.
	 */
	private String email;
	/**
	 * country country.
	 */
	private String phone;
	/**
	 * state state
	 */
	private String state;
	/**
	 * country country
	 */
	private String country;
	/**
	 * contactType is BILLING or PRIMARY
	 */
	private String contactType;
	/**
	 * facilityId facilityId 111,112,etc.
	 */
	private int facilityId;
	/**
	 * facility contact id 1,2 ,3....
	 */	
	private int facilityContactId;
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
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
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}
	/**
	 * @param contactType the contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}
	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	/**
	 * @return the facilityContactId
	 */
	public int getFacilityContactId() {
		return facilityContactId;
	}
	/**
	 * @param facilityContactId the facilityContactId to set
	 */
	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}
}
