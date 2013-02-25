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
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public class SaveSearchedJobsDTO {

	/** The save search id. */
	private int saveSearchID;
	
	/** The user id. */
	private int userID;
	
	/** The url. */
	private String url;
	
	/** The search name. */
	private String searchName;
	
	/** The email frequency. */
	private String emailFrequency;
	
	/** The created date. */
	private Date createdDate;
	
	/** The modify date. */
	private String modifyDate;
	
	/** The deleted date. */
	private Date deletedDate;

	/** The recent url. */
	private String recentURL;

	/** The keywords. */
	private String keywords;

	/**
	 * @return the keyword
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Instantiates a new save searched jobs dto.
	 */
	public SaveSearchedJobsDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new save searched jobs dto.
	 *
	 * @param saveSearchID the save search id
	 * @param userID the user id
	 * @param url the url
	 * @param searchName the search name
	 * @param emailFrequency the email frequency
	 * @param createdDate the created date
	 * @param modifyDate the modify date
	 * @param deletedDate the deleted date
	 * @param recentURL the recent url
	 */
	public SaveSearchedJobsDTO(int saveSearchID, int userID, String url,
			String searchName, String emailFrequency, Date createdDate,
			String modifyDate, Date deletedDate, String recentURL) {
		super();
		this.saveSearchID = saveSearchID;
		this.userID = userID;
		this.url = url;
		this.searchName = searchName;
		this.emailFrequency = emailFrequency;
		this.createdDate = createdDate;
		this.modifyDate = modifyDate;
		this.deletedDate = deletedDate;
		this.recentURL = recentURL;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the saveSearchID
	 */
	public int getSaveSearchID() {
		return saveSearchID;
	}

	/**
	 * @param saveSearchID
	 *            the saveSearchID to set
	 */
	public void setSaveSearchID(int saveSearchID) {
		this.saveSearchID = saveSearchID;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName
	 *            the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the emailFrequency
	 */
	public String getEmailFrequency() {
		return emailFrequency;
	}

	/**
	 * @param emailFrequency
	 *            the emailFrequency to set
	 */
	public void setEmailFrequency(String emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

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
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}

	/**
	 * @param deletedDate
	 *            the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	/**
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Gets the recent url.
	 *
	 * @return the recent url
	 */
	public String getRecentURL() {
		return recentURL;
	}

	/**
	 * Sets the recent url.
	 *
	 * @param recentURL the new recent url
	 */
	public void setRecentURL(String recentURL) {
		this.recentURL = recentURL;
	}

}
