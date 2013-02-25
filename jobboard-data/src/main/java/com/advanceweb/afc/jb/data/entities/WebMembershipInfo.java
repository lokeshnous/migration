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
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the WebMembershipInfo database table.
 * 
 */
@Entity
@Table(name="WebMembershipInfo")
public class WebMembershipInfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The web membership info id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WebMembershipInfoID")
	private Integer webMembershipInfoID;

	/** The believed sub number. */
	@Column(name="BelievedSubNumber")
	private String believedSubNumber;

	/** The country id. */
	@Column(name="CountryId")
	private Integer countryId;

	/** The create date. */
	@Column(name="CreateDate")
	private Date createDate;

	/** The create web user id. */
	@Column(name="CreateWebUserId")
	private Integer createWebUserId;

	/** The delete date. */
	@Column(name="DeleteDate")
	private Date deleteDate;

	/** The delete web user id. */
	@Column(name="DeleteWebUserId")
	private Integer deleteWebUserId;

	/** The first name. */
	@Column(name="FirstName")
	private String firstName;

	/** The last name. */
	@Column(name="LastName")
	private String lastName;

	/** The modify date. */
	@Column(name="ModifyDate")
	private Date modifyDate;

	/** The modify web user id. */
	@Column(name="ModifyWebUserId")
	private Integer modifyWebUserId;

	/** The zip code. */
	@Column(name="ZipCode")
	private String zipCode;

	/** The web membership. */
	@OneToOne(fetch=FetchType.EAGER, mappedBy="webMembershipInfo")
	private WebMembership webMembership;

	/**
	 * Gets the web membership info id.
	 *
	 * @return the web membership info id
	 */
	public Integer getWebMembershipInfoID() {
		return webMembershipInfoID;
	}

	/**
	 * Sets the web membership info id.
	 *
	 * @param webMembershipInfoID the new web membership info id
	 */
	public void setWebMembershipInfoID(Integer webMembershipInfoID) {
		this.webMembershipInfoID = webMembershipInfoID;
	}

	/**
	 * Gets the believed sub number.
	 *
	 * @return the believed sub number
	 */
	public String getBelievedSubNumber() {
		return believedSubNumber;
	}

	/**
	 * Sets the believed sub number.
	 *
	 * @param believedSubNumber the new believed sub number
	 */
	public void setBelievedSubNumber(String believedSubNumber) {
		this.believedSubNumber = believedSubNumber;
	}

	/**
	 * Gets the country id.
	 *
	 * @return the country id
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * Sets the country id.
	 *
	 * @param countryId the new country id
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the creates the web user id.
	 *
	 * @return the creates the web user id
	 */
	public Integer getCreateWebUserId() {
		return createWebUserId;
	}

	/**
	 * Sets the creates the web user id.
	 *
	 * @param createWebUserId the new creates the web user id
	 */
	public void setCreateWebUserId(Integer createWebUserId) {
		this.createWebUserId = createWebUserId;
	}

	/**
	 * Gets the delete date.
	 *
	 * @return the delete date
	 */
	public Date getDeleteDate() {
		return deleteDate;
	}

	/**
	 * Sets the delete date.
	 *
	 * @param deleteDate the new delete date
	 */
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * Gets the delete web user id.
	 *
	 * @return the delete web user id
	 */
	public Integer getDeleteWebUserId() {
		return deleteWebUserId;
	}

	/**
	 * Sets the delete web user id.
	 *
	 * @param deleteWebUserId the new delete web user id
	 */
	public void setDeleteWebUserId(Integer deleteWebUserId) {
		this.deleteWebUserId = deleteWebUserId;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
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
	 * Gets the modify date.
	 *
	 * @return the modify date
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * Sets the modify date.
	 *
	 * @param modifyDate the new modify date
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Gets the modify web user id.
	 *
	 * @return the modify web user id
	 */
	public Integer getModifyWebUserId() {
		return modifyWebUserId;
	}

	/**
	 * Sets the modify web user id.
	 *
	 * @param modifyWebUserId the new modify web user id
	 */
	public void setModifyWebUserId(Integer modifyWebUserId) {
		this.modifyWebUserId = modifyWebUserId;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the web membership.
	 *
	 * @return the web membership
	 */
	public WebMembership getWebMembership() {
		return webMembership;
	}

	/**
	 * Sets the web membership.
	 *
	 * @param webMembership the new web membership
	 */
	public void setWebMembership(WebMembership webMembership) {
		this.webMembership = webMembership;
	}
	
}