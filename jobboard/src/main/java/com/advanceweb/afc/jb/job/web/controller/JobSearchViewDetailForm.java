/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.web.controller;

import java.util.Date;

/**
 * <code> JobSearchViewDetailForm </code> is a form bean for searched Job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 11 July 2012
 */

public class JobSearchViewDetailForm {

	/** The job title. */
	private String jobTitle;
	
	/** The area of interest. */
	private String areaOfInterest;
	
	/** The position type. */
	private int positionType;
	
	/** The city. */
	private int city;
	
	/** The state. */
	private int state;
	
	/** The job desc. */
	private String jobDesc;

	// Added for save this job task
	/** The user id. */
	private int userID;
	
	/** The job id. */
	private int jobID;
	
	/** The created date. */
	private Date createdDate;
	
	/** The company name. */
	private String companyName;

	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the area of interest.
	 *
	 * @return the area of interest
	 */
	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	/**
	 * Sets the area of interest.
	 *
	 * @param areaOfInterest the new area of interest
	 */
	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

	/**
	 * Gets the position type.
	 *
	 * @return the position type
	 */
	public int getPositionType() {
		return positionType;
	}

	/**
	 * Sets the position type.
	 *
	 * @param positionType the new position type
	 */
	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public int getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(int city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Gets the job desc.
	 *
	 * @return the job desc
	 */
	public String getJobDesc() {
		return jobDesc;
	}

	/**
	 * Sets the job desc.
	 *
	 * @param jobDesc the new job desc
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
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

}
