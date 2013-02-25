/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.web.controller;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <code>EmpBrandTempForm</code> is a form bean to employer branding Templates.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class BrandingTemplateForm {

	/** The employer id. */
	private int employerId;
	
	/** The template id. */
	private String templateId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The logo path. */
	private String logoPath;
	
	/** The color. */
	private String color;
	
	/** The created date. */
	private String createdDate;
	
	/** The template name. */
	private String templateName;
	
	/** The company overview. */
	private String companyOverview;
	
	/** The main image path. */
	private String mainImagePath;
	
	/** The logo file data. */
	private CommonsMultipartFile logoFileData;
	
	/** The main image file data. */
	private CommonsMultipartFile mainImageFileData;

	// Multimedia section
	/** The list testimony. */
	private List<TestimonyForm> listTestimony;
	
	/** The list add images. */
	private List<AddImageForm> listAddImages;
	
	/** The list videos. */
	private List<VideoForm> listVideos;

	/** The is silver customer. */
	private Boolean isSilverCustomer = null;
	
	/** The browse path. */
	private String browsePath;
	
	/** The image size limit. */
	private String imageSizeLimit;
	
	/** The video size limit. */
	private String videoSizeLimit;
	
	/** The testimony container. */
	private String testimonyContainer;
	
	/** The package id. */
	private int packageId;
	
	/** The chosen logo. */
	private String chosenLogo;
	
	/** The chosen main image. */
	private String chosenMainImage;
	
	/** The edit mode. */
	private boolean editMode;
	
	/** The save success. */
	private String saveSuccess;
	
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
	 * Gets the template id.
	 *
	 * @return the template id
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * Sets the template id.
	 *
	 * @param templateId the new template id
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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
	 * Gets the list testimony.
	 *
	 * @return the list testimony
	 */
	public List<TestimonyForm> getListTestimony() {
		return listTestimony;
	}

	/**
	 * Sets the list testimony.
	 *
	 * @param listTestimony the new list testimony
	 */
	public void setListTestimony(List<TestimonyForm> listTestimony) {
		this.listTestimony = listTestimony;
	}

	/**
	 * Gets the list add images.
	 *
	 * @return the list add images
	 */
	public List<AddImageForm> getListAddImages() {
		return listAddImages;
	}

	/**
	 * Gets the browse path.
	 *
	 * @return the browse path
	 */
	public String getBrowsePath() {
		return browsePath;
	}

	/**
	 * Sets the browse path.
	 *
	 * @param browsePath the new browse path
	 */
	public void setBrowsePath(String browsePath) {
		this.browsePath = browsePath;
	}

	/**
	 * Sets the list add images.
	 *
	 * @param listAddImages the new list add images
	 */
	public void setListAddImages(List<AddImageForm> listAddImages) {
		this.listAddImages = listAddImages;
	}

	/**
	 * Gets the list videos.
	 *
	 * @return the list videos
	 */
	public List<VideoForm> getListVideos() {
		return listVideos;
	}

	/**
	 * Checks if is edits the mode.
	 *
	 * @return true, if is edits the mode
	 */
	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * Sets the edits the mode.
	 *
	 * @param editMode the new edits the mode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	/**
	 * Gets the chosen logo.
	 *
	 * @return the chosen logo
	 */
	public String getChosenLogo() {
		return chosenLogo;
	}

	/**
	 * Sets the chosen logo.
	 *
	 * @param chosenLogo the new chosen logo
	 */
	public void setChosenLogo(String chosenLogo) {
		this.chosenLogo = chosenLogo;
	}

	/**
	 * Gets the chosen main image.
	 *
	 * @return the chosen main image
	 */
	public String getChosenMainImage() {
		return chosenMainImage;
	}

	/**
	 * Sets the chosen main image.
	 *
	 * @param chosenMainImage the new chosen main image
	 */
	public void setChosenMainImage(String chosenMainImage) {
		this.chosenMainImage = chosenMainImage;
	}

	/**
	 * Gets the package id.
	 *
	 * @return the package id
	 */
	public int getPackageId() {
		return packageId;
	}

	/**
	 * Sets the package id.
	 *
	 * @param packageId the new package id
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	/**
	 * Gets the testimony container.
	 *
	 * @return the testimony container
	 */
	public String getTestimonyContainer() {
		return testimonyContainer;
	}

	/**
	 * Sets the testimony container.
	 *
	 * @param testimonyContainer the new testimony container
	 */
	public void setTestimonyContainer(String testimonyContainer) {
		this.testimonyContainer = testimonyContainer;
	}

	/**
	 * Gets the image size limit.
	 *
	 * @return the image size limit
	 */
	public String getImageSizeLimit() {
		return imageSizeLimit;
	}

	/**
	 * Sets the image size limit.
	 *
	 * @param imageSizeLimit the new image size limit
	 */
	public void setImageSizeLimit(String imageSizeLimit) {
		this.imageSizeLimit = imageSizeLimit;
	}

	/**
	 * Gets the video size limit.
	 *
	 * @return the video size limit
	 */
	public String getVideoSizeLimit() {
		return videoSizeLimit;
	}

	/**
	 * Sets the video size limit.
	 *
	 * @param videoSizeLimit the new video size limit
	 */
	public void setVideoSizeLimit(String videoSizeLimit) {
		this.videoSizeLimit = videoSizeLimit;
	}

	/**
	 * Sets the list videos.
	 *
	 * @param listVideos the new list videos
	 */
	public void setListVideos(List<VideoForm> listVideos) {
		this.listVideos = listVideos;
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
	 * Gets the save success.
	 *
	 * @return the save success
	 */
	public String getSaveSuccess() {
		return saveSuccess;
	}

	/**
	 * Sets the save success.
	 *
	 * @param saveSuccess the new save success
	 */
	public void setSaveSuccess(String saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

}