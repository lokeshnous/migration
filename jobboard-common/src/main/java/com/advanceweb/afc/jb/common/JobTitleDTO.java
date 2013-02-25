/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @Author : Pramodap
   @Version: 1.0
   @Created: Feb 07, 2013
   @Purpose: This class will act as a DTO for job title
 */
public class JobTitleDTO {
	
	/** The job title id. */
	private int jobTitleId;
	
	/** The jobtitle. */
	private String jobtitle;
	
	/**
	 * @return the jobTitleId
	 */
	public int getJobTitleId() {
		return jobTitleId;
	}
	/**
	 * @param jobTitleId the jobTitleId to set
	 */
	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	/**
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}
	/**
	 * @param jobtitle the jobtitle to set
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	
	
}
