package com.advanceweb.afc.jb.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the jp_save_search database table
 * 
 * @author bharatiu
 * @version 1.0
 * @since 10th July 2012
 */

@Entity
@Table(name="jp_save_search")
public class JpSaveSearch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="jp_save_search_id")
	private int jpSaveSearchId; 
	
	@Column(name="user_id")
	private String loginID;
	
	@Column(name="url")
	private  String url;
	
	@Column(name="url_name")
	private String urlName;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private Date modifyDate;
	
	@Column(name="email_frequency")
	private String emailFrequency;


	/**
	 * @return the jpSaveSearchId
	 */
	public int getJpSaveSearchId() {
		return jpSaveSearchId;
	}

	/**
	 * @param jpSaveSearchId the jpSaveSearchId to set
	 */
	public void setJpSaveSearchId(int jpSaveSearchId) {
		this.jpSaveSearchId = jpSaveSearchId;
	}

	/**
	 * @return the loginID
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * @param loginID the loginID to set
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the urlName
	 */
	public String getUrlName() {
		return urlName;
	}

	/**
	 * @param urlName the urlName to set
	 */
	public void setUrlName(String urlName) {
		this.urlName = urlName;
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

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

}
