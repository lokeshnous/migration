package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

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

	private long employerId;
	private String logoPath;
	private String color;
	private Date createdDate;
	private String templateName;
	private String companyOverview;
	private String mainImagePath;
	private CommonsMultipartFile logoFileData;
	private CommonsMultipartFile mainImageFileData;
//	Muli media section
	private String addImagePath;
	private String videoPath;
	private String testimony;
	private CommonsMultipartFile addImageFileData;
	private CommonsMultipartFile videoFileData;
	
	private Boolean isSilverCustomer=null;
	
	public long getEmployerId() {
		return employerId;
	}

	public void setEmployerId(long employerId) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
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

	public String getAddImagePath() {
		return addImagePath;
	}

	public void setAddImagePath(String addImagePath) {
		this.addImagePath = addImagePath;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getTestimony() {
		return testimony;
	}

	public void setTestimony(String testimony) {
		this.testimony = testimony;
	}

	public CommonsMultipartFile getAddImageFileData() {
		return addImageFileData;
	}

	public void setAddImageFileData(CommonsMultipartFile addImageFileData) {
		this.addImageFileData = addImageFileData;
	}

	public CommonsMultipartFile getVideoFileData() {
		return videoFileData;
	}

	public void setVideoFileData(CommonsMultipartFile videoFileData) {
		this.videoFileData = videoFileData;
	}

	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

}