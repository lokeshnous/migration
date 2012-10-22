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

	private int employerId;
	private String templateId;
	private int facilityId;
	private String logoPath;
	private String color;
	private String createdDate;
	private String templateName;
	private String companyOverview;
	private String mainImagePath;
	private CommonsMultipartFile logoFileData;
	private CommonsMultipartFile mainImageFileData;

	// Multimedia section
	private List<TestimonyForm> listTestimony;
	private List<AddImageForm> listAddImages;
	private List<VideoForm> listVideos;

	private Boolean isSilverCustomer = null;
	private String browsePath;
	private String imageSizeLimit;
	private String videoSizeLimit;
	private String testimonyContainer;
	private int packageId;
	private String chosenLogo;
	private String chosenMainImage;
	private boolean editMode;
	
	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public CommonsMultipartFile getLogoFileData() {
		return logoFileData;
	}

	public void setLogoFileData(CommonsMultipartFile logoFileData) {
		this.logoFileData = logoFileData;
	}

	public CommonsMultipartFile getMainImageFileData() {
		return mainImageFileData;
	}

	public void setMainImageFileData(CommonsMultipartFile mainImageFileData) {
		this.mainImageFileData = mainImageFileData;
	}

	
	public List<TestimonyForm> getListTestimony() {
		return listTestimony;
	}

	public void setListTestimony(List<TestimonyForm> listTestimony) {
		this.listTestimony = listTestimony;
	}

	public List<AddImageForm> getListAddImages() {
		return listAddImages;
	}

	public String getBrowsePath() {
		return browsePath;
	}

	public void setBrowsePath(String browsePath) {
		this.browsePath = browsePath;
	}

	public void setListAddImages(List<AddImageForm> listAddImages) {
		this.listAddImages = listAddImages;
	}

	public List<VideoForm> getListVideos() {
		return listVideos;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public String getChosenLogo() {
		return chosenLogo;
	}

	public void setChosenLogo(String chosenLogo) {
		this.chosenLogo = chosenLogo;
	}

	public String getChosenMainImage() {
		return chosenMainImage;
	}

	public void setChosenMainImage(String chosenMainImage) {
		this.chosenMainImage = chosenMainImage;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getTestimonyContainer() {
		return testimonyContainer;
	}

	public void setTestimonyContainer(String testimonyContainer) {
		this.testimonyContainer = testimonyContainer;
	}

	public String getImageSizeLimit() {
		return imageSizeLimit;
	}

	public void setImageSizeLimit(String imageSizeLimit) {
		this.imageSizeLimit = imageSizeLimit;
	}

	public String getVideoSizeLimit() {
		return videoSizeLimit;
	}

	public void setVideoSizeLimit(String videoSizeLimit) {
		this.videoSizeLimit = videoSizeLimit;
	}

	public void setListVideos(List<VideoForm> listVideos) {
		this.listVideos = listVideos;
	}

	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

}