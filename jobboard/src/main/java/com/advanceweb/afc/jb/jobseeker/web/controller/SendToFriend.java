/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;


public class SendToFriend {
    
    /** The email. */
    private String email;
	
	/** The message. */
	private String message;
	
	/** The joburl. */
	private String joburl;
	
	/** The job id. */
	private int jobId;
	
	/** The resume id. */
	private int resumeId;
	
	private String name;
	
	/**
	 * Gets the joburl.
	 *
	 * @return the joburl
	 */
	public String getJoburl() {
		return joburl;
	}
	
	/**
	 * Sets the joburl.
	 *
	 * @param joburl the new joburl
	 */
	public void setJoburl(String joburl) {
		this.joburl = joburl;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the resumeId
	 */
	public int getResumeId() {
		return resumeId;
	}
	/**
	 * @param resumeId the resumeId to set
	 */
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
