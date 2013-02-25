/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.Date;

/**
 * <code>ApplyJobForm</code> is a form bean to apply job
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 12 July 2012
 * 
 */

public class ApplyJobForm {

	/** The user id. */
	private String userId;
	
	/** The useremail. */
	private String useremail;
	
	/** The created date. */
	private Date createdDate;
	
	/** The job id. */
	private int jobID;

	
	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobID() {
		return jobID;
	}
	
	/**
	 * Sets the job id.
	 *
	 * @param jobID the new job id
	 */
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the useremail.
	 *
	 * @return the useremail
	 */
	public String getUseremail() {
		return useremail;
	}
	
	/**
	 * Sets the useremail.
	 *
	 * @param useremail the new useremail
	 */
	public void setUseremail(String useremail) {
		this.useremail = useremail;
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

}
