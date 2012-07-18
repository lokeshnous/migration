package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

public class SaveSearchedJobsDTO {
	
	private int jpSaveSearchId;
	private String loginID;
	private String url;
	private String urlName;
	private Date createdDate;
	private Date modifyDate;
	private String emailFrequency;

	
	public int getJpSaveSearchId() {
		return jpSaveSearchId;
	}

	public void setJpSaveSearchId(int jpSaveSearchId) {
		this.jpSaveSearchId = jpSaveSearchId;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrlName(){
		return urlName;
	}
	
	public void setUrlName(String urlName){
		this.urlName = urlName;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	/**
	 * @return the emailFrequency
	 */
	public String getEmailFrequency() {
		return emailFrequency;
	}

	/**
	 * @param emailFrequency the emailFrequency to set
	 */
	public void setEmailFrequency(String emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

	@Override
	public String toString() {
		return "SaveSearchedJobsDTO [loginID=" + loginID + ", url=" + url
				+ ", urlName=" + urlName + ", createdDate=" + createdDate
				+ ", ModifyDate=" + modifyDate + ", emailFrequency="
				+ emailFrequency + "]";
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
