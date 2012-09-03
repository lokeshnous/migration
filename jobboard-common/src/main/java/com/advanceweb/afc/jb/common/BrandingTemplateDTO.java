package com.advanceweb.afc.jb.common;

import java.util.Date;
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

	private int jpBrandTempId;
	private int facilityId;
	private int employerId;
	private String templateName;
	private String mainImagePath;
	private String logoPath;
	private String color;
	private Date createdDate;
	private String companyOverview;
	private CommonsMultipartFile logoFileData;
	private CommonsMultipartFile mainImageFileData;
	
//	Muli media section
//	private String addImagePath;
//	private String videoPath;
//	private String testimony;
//	private CommonsMultipartFile addImageFileData;
//	private CommonsMultipartFile videoFileData;
	private List<String> listAddImagePath;
	private List<String> listVideoPath;
	private List<TestimonyDTO> listTestimony;
	private List<AddImageDTO> listAddImages;
	private List<VideoDTO> listVideos;
	
	private Boolean isSilverCustomer=null;
	
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
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


	public List<String> getListAddImagePath() {
		return listAddImagePath;
	}

	public void setListAddImagePath(List<String> listAddImagePath) {
		this.listAddImagePath = listAddImagePath;
	}

	public List<String> getListVideoPath() {
		return listVideoPath;
	}

	public void setListVideoPath(List<String> listVideoPath) {
		this.listVideoPath = listVideoPath;
	}

	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

	public List<TestimonyDTO> getListTestimony() {
		return listTestimony;
	}

	public void setListTestimony(List<TestimonyDTO> listTestimony) {
		this.listTestimony = listTestimony;
	}

	public List<AddImageDTO> getListAddImages() {
		return listAddImages;
	}

	public void setListAddImages(List<AddImageDTO> listAddImages) {
		this.listAddImages = listAddImages;
	}

	public List<VideoDTO> getListVideos() {
		return listVideos;
	}

	public void setListVideos(List<VideoDTO> listVideos) {
		this.listVideos = listVideos;
	}


}