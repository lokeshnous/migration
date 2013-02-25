/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.Date;
import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public class SaveSearchForm {

	/** The save search id. */
	private int saveSearchId;
	
	/** The url. */
	private String url;
	
	/** The search name. */
	private String searchName;
	
	/** The email frequency. */
	private String emailFrequency;
	
	/** The save searched jobs dto list. */
	private List<SaveSearchedJobsDTO> saveSearchedJobsDTOList;
	
	/** The created date. */
	private Date createdDate;
	
	/** The modify date. */
	private String modifyDate;
	
	/** The deleted date. */
	private Date deletedDate;
	
	/** The notify me. */
	private String notifyMe;
	
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
	 * @return the notifyMe
	 */
	public String getNotifyMe() {
		return notifyMe;
	}

	/**
	 * @param notifyMe
	 *            the notifyMe to set
	 */
	public void setNotifyMe(String notifyMe) {
		this.notifyMe = notifyMe;
	}

	/**
	 * Gets the save search id.
	 *
	 * @return the save search id
	 */
	public int getSaveSearchId() {
		return saveSearchId;
	}

	/**
	 * Sets the save search id.
	 *
	 * @param saveSearchId the new save search id
	 */
	public void setSaveSearchId(int saveSearchId) {
		this.saveSearchId = saveSearchId;
	}

	/**
	 * Gets the save searched jobs dto list.
	 *
	 * @return the save searched jobs dto list
	 */
	public List<SaveSearchedJobsDTO> getSaveSearchedJobsDTOList() {
		return saveSearchedJobsDTOList;
	}

	/**
	 * Sets the save searched jobs dto list.
	 *
	 * @param saveSearchedJobsDTOList the new save searched jobs dto list
	 */
	public void setSaveSearchedJobsDTOList(
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList) {
		this.saveSearchedJobsDTOList = saveSearchedJobsDTOList;
	}

}
