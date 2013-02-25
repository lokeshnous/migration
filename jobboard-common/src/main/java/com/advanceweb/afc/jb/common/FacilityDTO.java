/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * This class is the DTO class for AdmFacility.
 * @author Reetesh R N
 * @version 1.0
 * @since 08/09/2012
 */


public class FacilityDTO {
	
	/** The facility id. */
	private Integer facilityId;

	/** The account number. */
	private String accountNumber;

	/** The admin user id. */
	private Integer adminUserId;

	/** The city. */
	private String city;

	/** The color palette. */
	private String colorPalette;

	/** The company news. */
	private String companyNews;

	/** The company overview. */
	private String companyOverview;

	/** The country. */
	private String country;

	/** The create dt. */
	private Date createDt;

	/** The create user id. */
	private Integer createUserId;

	/** The delete dt. */
	private Date deleteDt;

	/** The delete user id. */
	private Integer deleteUserId;

	/** The email. */
	private String email;

	/** The email display. */
	private String emailDisplay;

	/** The facility type. */
	private String facilityType;

	/** The logo path. */
	private String logoPath;

	/** The name. */
	private String name;

	/** The name display. */
	private String nameDisplay;

	/** The postcode. */
	private String postcode;

	/** The promo media path. */
	private String promoMediaPath;

	/** The state. */
	private String state;

	/** The street. */
	private String street;

	/** The url. */
	private String url;

	/** The url display. */
	private String urlDisplay;

	/** The facility parent id. */
	private int  facilityParentId;

	/** The ns customer id. */
	private int nsCustomerID;
	
	/** The role id. */
	private int roleId;
	
	/** The user id. */
	private int userId;
	
	/** The phone. */
	private String phone;

	/** The template name. */
	private String templateName;
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public Integer getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the admin user id.
	 *
	 * @return the admin user id
	 */
	public Integer getAdminUserId() {
		return adminUserId;
	}

	/**
	 * Sets the admin user id.
	 *
	 * @param adminUserId the new admin user id
	 */
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
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
	 * Gets the color palette.
	 *
	 * @return the color palette
	 */
	public String getColorPalette() {
		return colorPalette;
	}

	/**
	 * Sets the color palette.
	 *
	 * @param colorPalette the new color palette
	 */
	public void setColorPalette(String colorPalette) {
		this.colorPalette = colorPalette;
	}

	/**
	 * Gets the company news.
	 *
	 * @return the company news
	 */
	public String getCompanyNews() {
		return companyNews;
	}

	/**
	 * Sets the company news.
	 *
	 * @param companyNews the new company news
	 */
	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
	}

	/**
	 * Gets the company overview.
	 *
	 * @return the company overview
	 */
	public String getCompanyOverview() {
		return companyOverview;
	}

	/**
	 * Sets the company overview.
	 *
	 * @param companyOverview the new company overview
	 */
	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
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
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return createDt;
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
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the delete user id.
	 *
	 * @return the delete user id
	 */
	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
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
	 * Gets the template name.
	 *
	 * @return the template name
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Sets the template name.
	 *
	 * @param templateName the new template name
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
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
	 * Gets the email display.
	 *
	 * @return the email display
	 */
	public String getEmailDisplay() {
		return emailDisplay;
	}

	/**
	 * Sets the email display.
	 *
	 * @param emailDisplay the new email display
	 */
	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	/**
	 * Gets the facility type.
	 *
	 * @return the facility type
	 */
	public String getFacilityType() {
		return facilityType;
	}

	/**
	 * Sets the facility type.
	 *
	 * @param facilityType the new facility type
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	/**
	 * Gets the logo path.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * Sets the logo path.
	 *
	 * @param logoPath the new logo path
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name display.
	 *
	 * @return the name display
	 */
	public String getNameDisplay() {
		return nameDisplay;
	}

	/**
	 * Sets the name display.
	 *
	 * @param nameDisplay the new name display
	 */
	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
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
	 * Gets the promo media path.
	 *
	 * @return the promo media path
	 */
	public String getPromoMediaPath() {
		return promoMediaPath;
	}

	/**
	 * Sets the promo media path.
	 *
	 * @param promoMediaPath the new promo media path
	 */
	public void setPromoMediaPath(String promoMediaPath) {
		this.promoMediaPath = promoMediaPath;
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
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
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
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the url display.
	 *
	 * @return the url display
	 */
	public String getUrlDisplay() {
		return urlDisplay;
	}

	/**
	 * Sets the url display.
	 *
	 * @param urlDisplay the new url display
	 */
	public void setUrlDisplay(String urlDisplay) {
		this.urlDisplay = urlDisplay;
	}

	/**
	 * Gets the facility parent id.
	 *
	 * @return the facility parent id
	 */
	public int getFacilityParentId() {
		return facilityParentId;
	}

	/**
	 * Sets the facility parent id.
	 *
	 * @param facilityParentId the new facility parent id
	 */
	public void setFacilityParentId(int facilityParentId) {
		this.facilityParentId = facilityParentId;
	}

	/**
	 * Gets the ns customer id.
	 *
	 * @return the ns customer id
	 */
	public int getNsCustomerID() {
		return nsCustomerID;
	}

	/**
	 * Sets the ns customer id.
	 *
	 * @param nsCustomerID the new ns customer id
	 */
	public void setNsCustomerID(int nsCustomerID) {
		this.nsCustomerID = nsCustomerID;
	}

	
	
	

}
