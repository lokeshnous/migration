/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act a DTO for the Dropdown of Job Posted field in Jobseekers Advance Search
 */
public class JobPostedDateDTO implements Serializable {

	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The job posted date id. */
	private String jobPostedDateId;
	
	/** The job posted date value. */
	private String jobPostedDateValue;
	/**
	 * @return the jobPostedDateId
	 */
	public String getJobPostedDateId() {
		return jobPostedDateId;
	}
	/**
	 * @param jobPostedDateId the jobPostedDateId to set
	 */
	public void setJobPostedDateId(String jobPostedDateId) {
		this.jobPostedDateId = jobPostedDateId;
	}
	/**
	 * @return the jobPostedDateValue
	 */
	public String getJobPostedDateValue() {
		return jobPostedDateValue;
	}
	/**
	 * @param jobPostedDateValue the jobPostedDateValue to set
	 */
	public void setJobPostedDateValue(String jobPostedDateValue) {
		this.jobPostedDateValue = jobPostedDateValue;
	}
	
	
	
}
