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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_subscription database table.
 * 
 */
@Entity
@Table(name = "adm_facility_subscription")
public class AdmFacilitySubscription implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The adm facility subscription pk. */
	@EmbeddedId
	private AdmFacilitySubscriptionPK admFacilitySubscriptionPK;

	/** The active. */
	@Column(name = "active")
	private int active;
	
	/** The create dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	/** The delete dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	// bi-directional many-to-one association to AdmSubscription
	/** The adm subscription. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id", insertable = false, updatable = false)
	private AdmSubscription admSubscription;

	// bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", insertable = false, updatable = false)
	private AdmFacility admFacility;

	/**
	 * Gets the adm facility subscription pk.
	 *
	 * @return the adm facility subscription pk
	 */
	public AdmFacilitySubscriptionPK getAdmFacilitySubscriptionPK() {
		return admFacilitySubscriptionPK;
	}

	/**
	 * Sets the adm facility subscription pk.
	 *
	 * @param admFacilitySubscriptionPK the new adm facility subscription pk
	 */
	public void setAdmFacilitySubscriptionPK(
			AdmFacilitySubscriptionPK admFacilitySubscriptionPK) {
		this.admFacilitySubscriptionPK = admFacilitySubscriptionPK;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
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
	 * Gets the adm subscription.
	 *
	 * @return the adm subscription
	 */
	public AdmSubscription getAdmSubscription() {
		return this.admSubscription;
	}

	/**
	 * Sets the adm subscription.
	 *
	 * @param admSubscription the new adm subscription
	 */
	public void setAdmSubscription(AdmSubscription admSubscription) {
		this.admSubscription = admSubscription;
	}

	/**
	 * Gets the adm facility.
	 *
	 * @return the adm facility
	 */
	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	/**
	 * Sets the adm facility.
	 *
	 * @param admFacility the new adm facility
	 */
	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
}