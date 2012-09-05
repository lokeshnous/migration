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

	private int userID;
	private String url;
	private String searchName;
	private String emailFrequency;
	private Date createdDate;
	private String modifyDate;
	private Date deletedDate;
	private String notifyMe;
	private List<SaveSearchedJobsDTO> saveSearchedJobsDTOList;
	private String keywords;

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

	public List<SaveSearchedJobsDTO> getSaveSearchedJobsDTOList() {
		return saveSearchedJobsDTOList;
	}

	public void setSaveSearchedJobsDTOList(
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList) {
		this.saveSearchedJobsDTOList = saveSearchedJobsDTOList;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
