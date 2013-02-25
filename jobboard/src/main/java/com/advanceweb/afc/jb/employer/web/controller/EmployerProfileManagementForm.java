/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class EmployerProfileManagementForm {
	
	/** The facility id. */
	private int facilityId;
	
	/** The company name. */
	private String companyName;
	
	/** The company overview. */
	private String companyOverview;
	
	/** The company website. */
	private String companyWebsite;
	
	/** The company email. */
	private String companyEmail;
	
	/** The company news. */
	private String companyNews;
	
	/** The position title. */
	private String positionTitle;
	
	/** The logo path. */
	private String logoPath;
	
	/** The logo url. */
	private CommonsMultipartFile logoUrl;
	
	/** The primary color. */
	private String primaryColor;
	
	/** The positional media. */
	private CommonsMultipartFile positionalMedia;
	
	/** The positional media url. */
	private String positionalMediaUrl;

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
	 * Gets the primary color.
	 *
	 * @return the primary color
	 */
	public String getPrimaryColor() {
		return primaryColor;
	}
	
	/**
	 * Sets the primary color.
	 *
	 * @param primaryColor the new primary color
	 */
	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
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
	 * Gets the company website.
	 *
	 * @return the company website
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	
	/**
	 * Sets the company website.
	 *
	 * @param companyWebsite the new company website
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	
	/**
	 * Gets the company email.
	 *
	 * @return the company email
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}
	
	/**
	 * Sets the company email.
	 *
	 * @param companyEmail the new company email
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
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
	 * Gets the position title.
	 *
	 * @return the position title
	 */
	public String getPositionTitle() {
		return positionTitle;
	}
	
	/**
	 * Sets the position title.
	 *
	 * @param positionTitle the new position title
	 */
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	/**
	 * @return the positionalMedia
	 */
	public CommonsMultipartFile getPositionalMedia() {
		return positionalMedia;
	}
	/**
	 * @param positionalMedia the positionalMedia to set
	 */
	public void setPositionalMedia(CommonsMultipartFile positionalMedia) {
		this.positionalMedia = positionalMedia;
	}
	/**
	 * @return the logoUrl
	 */
	public CommonsMultipartFile getLogoUrl() {
		return logoUrl;
	}
	/**
	 * @param logoUrl the logoUrl to set
	 */
	public void setLogoUrl(CommonsMultipartFile logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * @return the positionalMediaUrl
	 */
	public String getPositionalMediaUrl() {
		return positionalMediaUrl;
	}
	/**
	 * @param positionalMediaUrl the positionalMediaUrl to set
	 */
	public void setPositionalMediaUrl(String positionalMediaUrl) {
		this.positionalMediaUrl = positionalMediaUrl;
	}

}
