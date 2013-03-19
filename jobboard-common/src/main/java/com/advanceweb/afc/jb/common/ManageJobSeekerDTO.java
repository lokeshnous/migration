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
 * @Author : Devi Prasad
   @Version: 1.0
   @Created: Oct 15, 2012
   @Purpose: This class will act as a DTO for Manage Job seeker
 */
public class ManageJobSeekerDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The resume id. */
	private int resumeId;
	
	/** The resume name. */
	private String resumeName;
	
	/** The rating. */
	private int rating;
	
	/** The application status. */
	private int applicationStatus;
	
	/** The saved date. */
	private String savedDate;
	
	/** The folder id. */
	private int folderId;
	
	/** The folder name. */
	private String folderName;
	
	/** The folder resume id. */
	private int folderResumeId;
	/** The resume viewed. */
	private boolean resumeViewed;
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
	 * @return the resumeName
	 */
	public String getResumeName() {
		return resumeName;
	}
	/**
	 * @param resumeName the resumeName to set
	 */
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the applicationStatus
	 */
	public int getApplicationStatus() {
		return applicationStatus;
	}
	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(int applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	/**
	 * @return the savedDate
	 */
	public String getSavedDate() {
		return savedDate;
	}
	/**
	 * @param savedDate the savedDate to set
	 */
	public void setSavedDate(String savedDate) {
		this.savedDate = savedDate;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}
	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}
	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	/**
	 * @return the folderResumeId
	 */
	public int getFolderResumeId() {
		return folderResumeId;
	}
	/**
	 * @param folderResumeId the folderResumeId to set
	 */
	public void setFolderResumeId(int folderResumeId) {
		this.folderResumeId = folderResumeId;
	}
	/**
	 * @return the resumeViewed
	 */
	public boolean isResumeViewed() {
		return resumeViewed;
	}
	/**
	 * @param resumeViewed the resumeViewed to set
	 */
	public void setResumeViewed(boolean resumeViewed) {
		this.resumeViewed = resumeViewed;
	}
	
   

		
}
