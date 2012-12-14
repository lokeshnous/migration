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

	private String facilityid;

	public String getFacilityid() {
		return facilityid;
	}

	public void setFacilityid(String facilityid) {
		this.facilityid = facilityid;
	}

	private String companyName;
	private String companyOverview;
	private String companyWebsite;
	private String companyEmail;
	private String companyNews;
	private String positionTitle;
	private String logoPath;
	private String positionalMedia;
	private String primaryColor;
	private Date featuredStartDate;
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

	public String getLogoPath() {
		return logoPath;
	}

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyNews() {
		return companyNews;
	}

	public void setCompanyNews(String companyNews) {
		this.companyNews = companyNews;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

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