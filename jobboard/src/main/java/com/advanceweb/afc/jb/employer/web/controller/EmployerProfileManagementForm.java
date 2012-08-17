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
	private String companyName;
	private String companyOverview;
	private String companyWebsite;
	private String companyEmail;
	private String companyNews;
	private String positionTitle;
	private String logoPath;
	private CommonsMultipartFile logoUrl;
	private String primaryColor;
	private CommonsMultipartFile positionalMedia;
	private String positionalMediaUrl;

	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	public String getPrimaryColor() {
		return primaryColor;
	}
	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
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
