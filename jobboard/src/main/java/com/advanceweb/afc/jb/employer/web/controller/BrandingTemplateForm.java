package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

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
//	private String description;
	private String imageTemplatePath;
	private String logoPath;
	private String color;
	private Date createdDate;
	private Date updatedDate;
	private String templateName;
	private String companyOverview;
	private String mainImage;
	
	

	public long getEmployerId() {
		return employerId;
	}


	public void setEmployerId(long employerId) {
		this.employerId = employerId;
	}

	public String getImageTemplatePath() {
		return imageTemplatePath;
	}


	public void setImageTemplatePath(String imageTemplatePath) {
		this.imageTemplatePath = imageTemplatePath;
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


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


//	public String getDescription() {
//		return description;
//	}
//
//
//	public void setDescription(String description) {
//		this.description = description;
//	}


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

}