/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
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
 * The persistent class for the adm_save_search database table.
 * 
 */
@Entity
@Table(name="adm_save_search")
public class SaveSearchResults implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The save search id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_search_id")
	private int saveSearchId;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The email frequency. */
	@Column(name="email_frequency")
	private String emailFrequency;

    /** The modify dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="modify_dt")
	private Date modifyDt;

	/** The search name. */
	@Column(name="search_name")
	private String searchName;

	/** The url. */
	private String url;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/**
	 * Gets the save search id.
	 *
	 * @return the save search id
	 */
	public int getSaveSearchId() {
		return this.saveSearchId;
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
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the email frequency.
	 *
	 * @return the email frequency
	 */
	public String getEmailFrequency() {
		return this.emailFrequency;
	}

	/**
	 * Sets the email frequency.
	 *
	 * @param emailFrequency the new email frequency
	 */
	public void setEmailFrequency(String emailFrequency) {
		this.emailFrequency = emailFrequency;
	}

	/**
	 * Gets the modify dt.
	 *
	 * @return the modify dt
	 */
	public Date getModifyDt() {
		return this.modifyDt;
	}

	/**
	 * Sets the modify dt.
	 *
	 * @param modifyDt the new modify dt
	 */
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	/**
	 * Gets the search name.
	 *
	 * @return the search name
	 */
	public String getSearchName() {
		return this.searchName;
	}

	/**
	 * Sets the search name.
	 *
	 * @param searchName the new search name
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}