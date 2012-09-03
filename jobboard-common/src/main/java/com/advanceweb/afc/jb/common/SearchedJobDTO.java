package com.advanceweb.afc.jb.common;

import java.util.Date;

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
	// Location
	private String city;
	private String stateFullName;
	private String country;
	private String areaOfInterest;
	private boolean featureEmployer;
	


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

	
}
