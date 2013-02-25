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
 * The persistent class for the mer_user_alerts database table.
 * 
 */
@Entity
@Table(name="mer_user_alerts")
public class MerUserAlerts implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The alert id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_id")
	private int alertId;

    /** The created date. */
    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="created_date")
	private Date createdDate;
    
    /** The alert value. */
    @Column(name="alert_value")
	private String alertValue;
    
    /** The user id. */
    @Column(name="user_id")
	private int userId;
    
    /** The lookupid. */
    @Column(name="lookup_id")
	private int lookupid;
    

	/**
	 * Gets the alert id.
	 *
	 * @return the alert id
	 */
	public int getAlertId() {
		return alertId;
	}

	/**
	 * Sets the alert id.
	 *
	 * @param alertId the new alert id
	 */
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param created_date the new created date
	 */
	public void setCreatedDate(Date created_date) {
		this.createdDate = created_date;
	}

	/**
	 * Gets the alertvalue.
	 *
	 * @return the alertvalue
	 */
	public String getAlertvalue() {
		return alertValue;
	}

	/**
	 * Sets the alert value.
	 *
	 * @param alertvalue the new alert value
	 */
	public void setAlertValue(String alertvalue) {
		this.alertValue = alertvalue;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public int getUserid() {
		return userId;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(int userid) {
		this.userId = userid;
	}

	/**
	 * Gets the lookupid.
	 *
	 * @return the lookupid
	 */
	public int getLookupid() {
		return lookupid;
	}

	/**
	 * Sets the lookupid.
	 *
	 * @param lookupid the new lookupid
	 */
	public void setLookupid(int lookupid) {
		this.lookupid = lookupid;
	}
    
    
}