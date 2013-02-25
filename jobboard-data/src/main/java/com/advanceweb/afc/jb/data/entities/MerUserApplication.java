/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the mer_user_application database table.
 * 
 */
@Entity
@Table(name="mer_user_application")
public class MerUserApplication implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The application pk. */
	@EmbeddedId
	private MerUserApplicationPK applicationPK;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    /** The last visit dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_visit_dt")
	private Date lastVisitDt;

	//bi-directional many-to-one association to MerUser
	/** The mer user. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private MerUser merUser;

	//bi-directional many-to-one association to MerApplication
	/** The mer application. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id", insertable=false, updatable=false)
	private MerApplication merApplication;

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
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
	 * Gets the last visit dt.
	 *
	 * @return the last visit dt
	 */
	public Date getLastVisitDt() {
		return this.lastVisitDt;
	}

	/**
	 * Sets the last visit dt.
	 *
	 * @param lastVisitDt the new last visit dt
	 */
	public void setLastVisitDt(Date lastVisitDt) {
		this.lastVisitDt = lastVisitDt;
	}

	/**
	 * Gets the mer user.
	 *
	 * @return the mer user
	 */
	public MerUser getMerUser() {
		return this.merUser;
	}

	/**
	 * Sets the mer user.
	 *
	 * @param merUser the new mer user
	 */
	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
	/**
	 * Gets the mer application.
	 *
	 * @return the mer application
	 */
	public MerApplication getMerApplication() {
		return this.merApplication;
	}

	/**
	 * Sets the mer application.
	 *
	 * @param merApplication the new mer application
	 */
	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}

	/**
	 * @return the applicationPK
	 */
	public MerUserApplicationPK getApplicationPK() {
		return applicationPK;
	}

	/**
	 * @param applicationPK the applicationPK to set
	 */
	public void setApplicationPK(MerUserApplicationPK applicationPK) {
		this.applicationPK = applicationPK;
	}
	
}