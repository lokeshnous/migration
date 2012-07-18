package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

/**
 * <code>EmployerBrandingTemplatesForm</code> is a form bean to employer branding Templates.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class EmployerBrandingTemplatesForm {

	private long employerId;
	private String description;
	private String imageTemplatePath;
	private String logoPath;
	private int color;
	private Date createdDate;
	private Date updatedDate;
	

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


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}