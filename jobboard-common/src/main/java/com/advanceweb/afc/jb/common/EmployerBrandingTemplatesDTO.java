package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * <code>EmployerBrandingTemplatesDTO</code>is a DTO class. The purpose of this class to
 * hold the required information of employer branding Templates.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 17 July 2012
 * 
 */
public class EmployerBrandingTemplatesDTO {
	
	private long jpBrandTempId;
	private long employerId;
	private String description;
	private String imagePath;
	private String logoPath;
	private String color;
	private Date createdDate;
	private Date updatedDate;
	
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


	public long getJpBrandTempId() {
		return jpBrandTempId;
	}


	public void setJpBrandTempId(long jpBrandTempId) {
		this.jpBrandTempId = jpBrandTempId;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}