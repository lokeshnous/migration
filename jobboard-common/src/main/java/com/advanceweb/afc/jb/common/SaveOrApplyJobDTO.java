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
 * <code> SaveOrApplyJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information to apply job.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 13 July 2012
 * 
 * 
 */
public class SaveOrApplyJobDTO {

	/** The user id. */
	private int userId;
	
	/** The job id. */
	private int jobId;
	
	/** The create date. */
	private Date createDate;
	
	/** The applied date. */
	private Date appliedDate;
	
	/** The is applied. */
	private byte isApplied;
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return jobId;
	}
	
	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}
	
	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * Gets the applied date.
	 *
	 * @return the applied date
	 */
	public Date getAppliedDate() {
		return appliedDate;
	}
	
	/**
	 * Sets the applied date.
	 *
	 * @param appliedDate the new applied date
	 */
	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	/**
	 * Gets the checks if is applied.
	 *
	 * @return the checks if is applied
	 */
	public byte getIsApplied() {
		return isApplied;
	}
	
	/**
	 * Sets the checks if is applied.
	 *
	 * @param isApplied the new checks if is applied
	 */
	public void setIsApplied(byte isApplied) {
		this.isApplied = isApplied;
	}
	

}
