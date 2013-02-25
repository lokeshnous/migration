/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the adm_order_address database table.
 *
 */
@Entity
@Table(name="adm_order_address")
public class AdmOrderAddress implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The order address id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_address_id")
	private Integer orderAddressId;
	
	/** The address type. */
	@Column(name="address_type")
	private String addressType;
	
	/** The first name. */
	@Column(name="first_name")
	private String firstName;
	
	/** The middle name. */
	@Column(name="middle_name")
	private String middleName;
	
	/** The last name. */
	@Column(name="last_name")
	private String lastName;
	
	/** The street. */
	@Column(name="street")
	private String street;
	
	/** The street2. */
	@Column(name="street2")
	private String street2;
	
	/** The city. */
	@Column(name="city")
	private String city;
	
	/** The postcode. */
	@Column(name="postcode")
	private String postcode;
	
	/** The state. */
	@Column(name="state")
	private String state;
	
	/** The country. */
	@Column(name="country")
	private String country;
	
	/** The phone. */
	@Column(name="phone")
	private String phone;
	
	/** The company. */
	@Column(name="company")
	private String company;
	
	/** The email. */
	@Column(name="email")
	private String email;
	
	/** The adm order header. */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private AdmOrderHeader admOrderHeader;

	/**
	 * @return the orderAddressId
	 */
	public Integer getOrderAddressId() {
		return orderAddressId;
	}

	/**
	 * @param orderAddressId the orderAddressId to set
	 */
	public void setOrderAddressId(Integer orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

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
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the street2
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * @param street2 the street2 to set
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
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
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return the admOrderHeader
	 */
	public AdmOrderHeader getAdmOrderHeader() {
		return admOrderHeader;
	}

	/**
	 * @param admOrderHeader the admOrderHeader to set
	 */
	public void setAdmOrderHeader(AdmOrderHeader admOrderHeader) {
		this.admOrderHeader = admOrderHeader;
	}

	
}
