package com.advanceweb.afc.jb.common;

import java.util.Date;
import java.util.List;

/**
 * <code> SearchedJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information for searched Job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 11 July 2012
 */

public class SearchedJobDTO {

	private String jobTitle;
	private String jobDesc;
	private int jobID;
	private String employerEmailAddress;
	private String positionType;
	private String logo;
	private String imagePath;
	private Date createdDate;
	private String companyOverview;
	private int userID;	
	// Admin facility 
	private String companyName;
	private String companyNameDisp;
	private int facilityId;
	// Location
	private String city;
	private String stateFullName;
	private String country;
	private String areaOfInterest;
	private boolean featureEmployer;
	private int templateId;
	private String color;
	private Boolean isSilverCustomer;
	private int packageId;
	private List<TestimonyDTO> listTestimony;
	private List<AddImageDTO> listAddImages;
	private List<VideoDTO> listVideos;
	
	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	/**
	 * @return the employerEmailAddress
	 */
	public String getEmployerEmailAddress() {
		return employerEmailAddress;
	}

	/**
	 * @param employerEmailAddress the employerEmailAddress to set
	 */
	public void setEmployerEmailAddress(String employerEmailAddress) {
		this.employerEmailAddress = employerEmailAddress;
	}

	

	// Added for save this job task
	

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getCompanyOverview() {
		return companyOverview;
	}

	public void setCompanyOverview(String companyOverview) {
		this.companyOverview = companyOverview;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the jobID
	 */
	public int getJobID() {
		return jobID;
	}

	/**
	 * @param jobID the jobID to set
	 */
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getStateFullName() {
		return stateFullName;
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

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public Boolean getIsSilverCustomer() {
		return isSilverCustomer;
	}

	public void setIsSilverCustomer(Boolean isSilverCustomer) {
		this.isSilverCustomer = isSilverCustomer;
	}

	public void setStateFullName(String stateFullName) {
		this.stateFullName = stateFullName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompanyNameDisp() {
		return companyNameDisp;
	}

	public void setCompanyNameDisp(String companyNameDisp) {
		this.companyNameDisp = companyNameDisp;
	}

	public boolean isFeatureEmployer() {
		return featureEmployer;
	}

	public void setFeatureEmployer(boolean featureEmployer) {
		this.featureEmployer = featureEmployer;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
}
