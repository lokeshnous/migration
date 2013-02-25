/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.netsuite;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the DTO class for Customer(which means Employer and Agency in JB).
 * @author Reetesh RN 
 * @Version 1.0
 * @Since 3rd Aug 2012
 */

public class NSCustomer {

	//refers to internalid in netSuite
	/** The internal id. */
	private int internalID;
	
	/** The phone. */
	private String phone;
	
	/** The record type. */
	private String recordType;

	/** The company name. */
	private String companyName;
	
	/** The cc number. */
	private String ccNumber;
	
	/** The cc expire date. */
	private String ccExpireDate;
	
	/** The cc name. */
	private String ccName;
	
	/** The cc zip code. */
	private String ccZipCode;
	
	/** The cc street. */
	private String ccStreet;
	
	/** The first name. */
	private String firstName;
	
	/** The middle name. */
	private String middleName;
	
	/** The lastname. */
	private String lastname;
	
	/** The email. */
	private String email;
	
	/** The zip. */
	private String zip;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The alt phone. */
	private String altPhone;
	
	/** The entity. */
	private String entity;
	
	/** The payment method. */
	private String paymentMethod;
	
	/** The card type. */
	private String cardType;
	
	/** The is person. */
	private String isPerson;
	
	/** The addr1. */
	private String addr1;
	
	/** The city. */
	private String city;
	
	/** The cust entity featured employee. */
	private boolean custEntityFeaturedEmployee;
	
	/** The item. */
	private List<NSItem> item = new ArrayList<NSItem>();
	
	/** The discount item. */
	private String discountItem;
	
	/**
	 * Checks if is cust entity featured employee.
	 *
	 * @return true, if is cust entity featured employee
	 */
	public boolean isCustEntityFeaturedEmployee() {
		return custEntityFeaturedEmployee;
	}

	/**
	 * Sets the cust entity featured employee.
	 *
	 * @param custEntityFeaturedEmployee the new cust entity featured employee
	 */
	public void setCustEntityFeaturedEmployee(boolean custEntityFeaturedEmployee) {
		this.custEntityFeaturedEmployee = custEntityFeaturedEmployee;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the addr1.
	 *
	 * @return the addr1
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * Sets the addr1.
	 *
	 * @param addr1 the new addr1
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	/**
	 * Gets the checks if is person.
	 *
	 * @return the checks if is person
	 */
	public String getIsPerson() {
		return isPerson;
	}

	/**
	 * Sets the checks if is person.
	 *
	 * @param isPerson the new checks if is person
	 */
	public void setIsPerson(String isPerson) {
		this.isPerson = isPerson;
	}
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * Sets the card type.
	 *
	 * @param cardType the new card type
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod the new payment method
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * Sets the entity.
	 *
	 * @param entity the new entity
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the zip.
	 *
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the zip.
	 *
	 * @param zip the new zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the alt phone.
	 *
	 * @return the alt phone
	 */
	public String getAltPhone() {
		return altPhone;
	}

	/**
	 * Sets the alt phone.
	 *
	 * @param altPhone the new alt phone
	 */
	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	/**
	 * Gets the internal id.
	 *
	 * @return the internal id
	 */
	public int getInternalID() {
		return internalID;
	}

	/**
	 * Sets the internal id.
	 *
	 * @param internalID the new internal id
	 */
	public void setInternalID(int internalID) {
		this.internalID = internalID;
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public List<NSItem> getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(List<NSItem> item) {
		this.item = item;
	}
	
	/**
	 * Gets the cc number.
	 *
	 * @return the cc number
	 */
	public String getCcNumber() {
		return ccNumber;
	}

	/**
	 * Sets the cc number.
	 *
	 * @param ccNumber the new cc number
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	/**
	 * Gets the cc expire date.
	 *
	 * @return the cc expire date
	 */
	public String getCcExpireDate() {
		return ccExpireDate;
	}

	/**
	 * Sets the cc expire date.
	 *
	 * @param ccExpireDate the new cc expire date
	 */
	public void setCcExpireDate(String ccExpireDate) {
		this.ccExpireDate = ccExpireDate;
	}

	/**
	 * Gets the cc name.
	 *
	 * @return the cc name
	 */
	public String getCcName() {
		return ccName;
	}

	/**
	 * Sets the cc name.
	 *
	 * @param ccName the new cc name
	 */
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	/**
	 * Gets the cc zip code.
	 *
	 * @return the cc zip code
	 */
	public String getCcZipCode() {
		return ccZipCode;
	}

	/**
	 * Sets the cc zip code.
	 *
	 * @param ccZipCode the new cc zip code
	 */
	public void setCcZipCode(String ccZipCode) {
		this.ccZipCode = ccZipCode;
	}

	/**
	 * Gets the cc street.
	 *
	 * @return the cc street
	 */
	public String getCcStreet() {
		return ccStreet;
	}

	/**
	 * Sets the cc street.
	 *
	 * @param ccStreet the new cc street
	 */
	public void setCcStreet(String ccStreet) {
		this.ccStreet = ccStreet;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the record type.
	 *
	 * @return the record type
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * Sets the record type.
	 *
	 * @param recordType the new record type
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the discounItem
	 */
	public String getDiscountItem() {
		return discountItem;
	}

	/**
	 * @param discounItem the discounItem to set
	 */
	public void setDiscountItem(String discounItem) {
		this.discountItem = discounItem;
	}

}
