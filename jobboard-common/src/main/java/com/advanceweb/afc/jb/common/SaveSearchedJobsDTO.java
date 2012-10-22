package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public class SaveSearchedJobsDTO {

	private int saveSearchID;
	private int userID;
	private String url;
	private String searchName;
	private String emailFrequency;
	private Date createdDate;
	private String modifyDate;
	private Date deletedDate;
	private String keywords;
	/**
	 * @return the keyword
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keyword the keyword to set
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
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
