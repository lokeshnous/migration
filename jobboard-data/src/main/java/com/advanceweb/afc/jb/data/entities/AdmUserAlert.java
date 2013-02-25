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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_user_alert database table.
 * 
 */
@Entity
@Table(name="adm_user_alert")
public class AdmUserAlert implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user alert id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_alert_id")
	private int userAlertId;

	/** The alert value. */
	@Column(name="alert_value")
	private String alertValue;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to AdmAlert
	/** The adm alert. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alert_id")
	private AdmAlert admAlert;

	/**
	 * Gets the user alert id.
	 *
	 * @return the user alert id
	 */
	public int getUserAlertId() {
		return this.userAlertId;
	}

	/**
	 * Sets the user alert id.
	 *
	 * @param userAlertId the new user alert id
	 */
	public void setUserAlertId(int userAlertId) {
		this.userAlertId = userAlertId;
	}

	/**
	 * Gets the alert value.
	 *
	 * @return the alert value
	 */
	public String getAlertValue() {
		return this.alertValue;
	}

	/**
	 * Sets the alert value.
	 *
	 * @param alertValue the new alert value
	 */
	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
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

	/**
	 * Gets the adm alert.
	 *
	 * @return the adm alert
	 */
	public AdmAlert getAdmAlert() {
		return this.admAlert;
	}

	/**
	 * Sets the adm alert.
	 *
	 * @param admAlert the new adm alert
	 */
	public void setAdmAlert(AdmAlert admAlert) {
		this.admAlert = admAlert;
	}
	
}