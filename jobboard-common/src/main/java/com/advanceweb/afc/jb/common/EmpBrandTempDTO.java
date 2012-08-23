package com.advanceweb.afc.jb.common;

import java.util.Date;

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
public class EmpBrandTempDTO {

	private int jpBrandTempId;
	private int employerId;
	private String templateName;
//	private String description;
	private String imagePath;
	private String logoPath;
	private String color;
	private Date createdDate;
//	private String updatedDate;
	private String companyOverview;
	private int facilityId;
	private CommonsMultipartFile logoFileData;
	private CommonsMultipartFile mainImageFileData;
	private String fileServer;
	private String logoFileName;
	private String logoFilePath;	
	private String mainImageFileName;
	private String mainImageFilePath;	

	
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

//	public String getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(String updatedDate) {
//		this.updatedDate = updatedDate;
//	}

//	public String getDescription() {
//		return templateName;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

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

	public int getJpBrandTempId() {
		return jpBrandTempId;
	}

	public void setJpBrandTempId(int jpBrandTempId) {
		this.jpBrandTempId = jpBrandTempId;
	}

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
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

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
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

	public String getFileServer() {
		return fileServer;
	}

	public void setFileServer(String fileServer) {
		this.fileServer = fileServer;
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