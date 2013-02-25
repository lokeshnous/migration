/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <code>EmpBrandTempDTO</code>is a DTO class. The purpose of this class to hold
 * the required information of employer branding Templates.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class BrandingTemplateDTO {

	/** The jp brand temp id. */
	private int jpBrandTempId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The employer id. */
	private int employerId;
	
	/** The template name. */
	private String templateName;
	
	/** The main image path. */
	private String mainImagePath;
	
	/** The logo path. */
	private String logoPath;
	
	/** The color. */
	private String color;
	
	/** The created date. */
	private String createdDate;
	
	/** The company overview. */
	private String companyOverview;
	
	/** The logo file data. */
	private CommonsMultipartFile logoFileData;
	
	/** The main image file data. */
	private CommonsMultipartFile mainImageFileData;
	
	/** The count. */
	private int count;
	
//	Muli media section
	/** The list testimony. */
private List<TestimonyDTO> listTestimony;
	
	/** The list add images. */
	private List<AddImageDTO> listAddImages;
	
	/** The list videos. */
	private List<VideoDTO> listVideos;
	
	/** The is silver customer. */
	private Boolean isSilverCustomer=null;
	
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
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the jp brand temp id.
	 *
	 * @return the jp brand temp id
	 */
	public int getJpBrandTempId() {
		return jpBrandTempId;
	}

	/**
	 * Sets the jp brand temp id.
	 *
	 * @param jpBrandTempId the new jp brand temp id
	 */
	public void setJpBrandTempId(int jpBrandTempId) {
		this.jpBrandTempId = jpBrandTempId;
	}

	/**
	 * Gets the employer id.
	 *
	 * @return the employer id
	 */
	public int getEmployerId() {
		return employerId;
	}

	/**
	 * Sets the employer id.
	 *
	 * @param employerId the new employer id
	 */
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
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
	 * Gets the main image path.
	 *
	 * @return the main image path
	 */
	public String getMainImagePath() {
		return mainImagePath;
	}

	/**
	 * Sets the main image path.
	 *
	 * @param mainImagePath the new main image path
	 */
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
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
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * Gets the logo file data.
	 *
	 * @return the logo file data
	 */
	public CommonsMultipartFile getLogoFileData() {
		return logoFileData;
	}

	/**
	 * Sets the logo file data.
	 *
	 * @param logoFileData the new logo file data
	 */
	public void setLogoFileData(CommonsMultipartFile logoFileData) {
		this.logoFileData = logoFileData;
	}

	/**
	 * Gets the main image file data.
	 *
	 * @return the main image file data
	 */
	public CommonsMultipartFile getMainImageFileData() {
		return mainImageFileData;
	}

	/**
	 * Sets the main image file data.
	 *
	 * @param mainImageFileData the new main image file data
	 */
	public void setMainImageFileData(CommonsMultipartFile mainImageFileData) {
		this.mainImageFileData = mainImageFileData;
	}


	/**
	 * Gets the checks if is silver customer.
	 *
	 * @return the checks if is silver customer
	 */
	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	/**
	 * Sets the checks if is silver customer.
	 *
	 * @param isSilverCustomer the new checks if is silver customer
	 */
	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

	/**
	 * Gets the list testimony.
	 *
	 * @return the list testimony
	 */
	public List<TestimonyDTO> getListTestimony() {
		return listTestimony;
	}

	/**
	 * Sets the list testimony.
	 *
	 * @param listTestimony the new list testimony
	 */
	public void setListTestimony(List<TestimonyDTO> listTestimony) {
		this.listTestimony = listTestimony;
	}

	/**
	 * Gets the list add images.
	 *
	 * @return the list add images
	 */
	public List<AddImageDTO> getListAddImages() {
		return listAddImages;
	}

	/**
	 * Sets the list add images.
	 *
	 * @param listAddImages the new list add images
	 */
	public void setListAddImages(List<AddImageDTO> listAddImages) {
		this.listAddImages = listAddImages;
	}

	/**
	 * Gets the list videos.
	 *
	 * @return the list videos
	 */
	public List<VideoDTO> getListVideos() {
		return listVideos;
	}

	/**
	 * Sets the list videos.
	 *
	 * @param listVideos the new list videos
	 */
	public void setListVideos(List<VideoDTO> listVideos) {
		this.listVideos = listVideos;
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


}