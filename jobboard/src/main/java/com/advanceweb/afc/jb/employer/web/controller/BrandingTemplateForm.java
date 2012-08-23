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
	private String mainImage;
	private CommonsMultipartFile logoFileData;
	private CommonsMultipartFile mainImageFileData;
	private String logoFileName;
	private String logoFilePath;	
	private String mainImageFileName;
	private String mainImageFilePath;
	
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


	public String getMainImage() {
		return mainImage;
	}


	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
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


	public String getLogoFileName() {
		return logoFileName;
	}


	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}


	public String getLogoFilePath() {
		return logoFilePath;
	}


	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}


	public String getMainImageFileName() {
		return mainImageFileName;
	}


	public void setMainImageFileName(String mainImageFileName) {
		this.mainImageFileName = mainImageFileName;
	}


	public String getMainImageFilePath() {
		return mainImageFilePath;
	}


	public void setMainImageFilePath(String mainImageFilePath) {
		this.mainImageFilePath = mainImageFilePath;
	}


	

}