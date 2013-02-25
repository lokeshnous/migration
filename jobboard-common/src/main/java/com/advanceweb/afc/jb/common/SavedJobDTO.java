/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * <code> SavedJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information for Saved Job job.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 2 July 2012
 * 
 * 
 */

public class SavedJobDTO {

	/** The job title. */
	private String jobTitle;
	
	/** The created date. */
	private Date createdDate;
	
	/** The company name. */
	private String companyName;
	
	/** The job age. */
	private int jobAge;

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
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the job age.
	 *
	 * @return the job age
	 */
	public int getJobAge() {
		return jobAge;
	}

	/**
	 * Sets the job age.
	 *
	 * @param jobAge the new job age
	 */
	public void setJobAge(int jobAge) {
		this.jobAge = jobAge;
	}

}
