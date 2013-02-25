/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:27 PM
 */
public class CompanyProfileDTO implements Serializable {

	/**
	 * Default Serial version
	 */
	private static final long serialVersionUID = 1L;

	/** The facilityid. */
	private String facilityid;

	/**
	 * Gets the facilityid.
	 *
	 * @return the facilityid
	 */
	public String getFacilityid() {
		return facilityid;
	}

	/**
	 * Sets the facilityid.
	 *
	 * @param facilityid the new facilityid
	 */
	public void setFacilityid(String facilityid) {
		this.facilityid = facilityid;
	}

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
	
	/** The positional media. */
	private String positionalMedia;
	
	/** The primary color. */
	private String primaryColor;
	
	/** The featured start date. */
	private Date featuredStartDate;
	
	/** The featured end date. */
	private Date featuredEndDate;

	/**
	 * @return the primaryColor
	 */
	public String getPrimaryColor() {
		return primaryColor;
	}

	/**
	 * @param primaryColor
	 *            the primaryColor to set
	 */
	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
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
	 * @return the positionalMedia
	 */
	public String getPositionalMedia() {
		return positionalMedia;
	}

	/**
	 * @param positionalMedia
	 *            the positionalMedia to set
	 */
	public void setPositionalMedia(String positionalMedia) {
		this.positionalMedia = positionalMedia;
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
	 * @return the featuredStartDate
	 */
	public Date getFeaturedStartDate() {
		return featuredStartDate;
	}

	/**
	 * @param featuredStartDate the featuredStartDate to set
	 */
	public void setFeaturedStartDate(Date featuredStartDate) {
		this.featuredStartDate = featuredStartDate;
	}

	/**
	 * @return the featuredEndDate
	 */
	public Date getFeaturedEndDate() {
		return featuredEndDate;
	}

	/**
	 * @param featuredEndDate the featuredEndDate to set
	 */
	public void setFeaturedEndDate(Date featuredEndDate) {
		this.featuredEndDate = featuredEndDate;
	}

}