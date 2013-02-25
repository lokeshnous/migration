/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_contact database table.
 * 
 */
@Entity
@Table(name = "adm_facility_contact")
public class AdmFacilityContact implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility contact id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_contact_id")
	private int facilityContactId;

	/** The active. */
	private int active;

	/** The city. */
	@Column(name = "city")
	private String city;

	/** The company. */
	@Column(name = "company")
	private String company;

	/** The contact type. */
	@Column(name = "contact_type")
	private String contactType;

	/** The country. */
	@Column(name = "country")
	private String country;

	/** The create dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	/** The delete dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	/** The email. */
	private String email;

	/** The first name. */
	@Column(name = "first_name")
	private String firstName;

	/** The job title. */
	@Column(name = "job_title")
	private String jobTitle;

	/** The last name. */
	@Column(name = "last_name")
	private String lastName;

	/** The middle name. */
	@Column(name = "middle_name")
	private String middleName;

	/** The phone. */
	@Column(name = "phone")
	private String phone;

	/** The phone2. */
	private String phone2;

	/** The postcode. */
	@Column(name = "postcode")
	private String postcode;

	/** The state. */
	@Column(name = "state")
	private String state;

	/** The street. */
	private String street;

	/** The street2. */
	private String street2;

	// bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "facility_id")
	private AdmFacility admFacility;

	/**
	 * Gets the facility contact id.
	 *
	 * @return the facility contact id
	 */
	public int getFacilityContactId() {
		return this.facilityContactId;
	}

	/**
	 * Sets the facility contact id.
	 *
	 * @param facilityContactId the new facility contact id
	 */
	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
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
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return this.company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Gets the contact type.
	 *
	 * @return the contact type
	 */
	public String getContactType() {
		return this.contactType;
	}

	/**
	 * Sets the contact type.
	 *
	 * @param contactType the new contact type
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
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
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(final Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
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
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
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
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return this.middleName;
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
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return this.phone;
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
	 * Gets the phone2.
	 *
	 * @return the phone2
	 */
	public String getPhone2() {
		return this.phone2;
	}

	/**
	 * Sets the phone2.
	 *
	 * @param phone2 the new phone2
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return this.state;
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
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return this.street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the street2.
	 *
	 * @return the street2
	 */
	public String getStreet2() {
		return this.street2;
	}

	/**
	 * Sets the street2.
	 *
	 * @param street2 the new street2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * Gets the adm facility.
	 *
	 * @return the adm facility
	 */
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}

}