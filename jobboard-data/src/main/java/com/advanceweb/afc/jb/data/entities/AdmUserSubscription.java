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
 * The persistent class for the adm_user_subscription database table.
 * 
 */
@Entity
@Table(name="adm_user_subscription")
public class AdmUserSubscription implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subscription pk. */
	@EmbeddedId
	private AdmUserSubscriptionPK subscriptionPK;

	/** The active. */
	private int active;

	/** The create dt. */
	@Column(name="create_dt")
    private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    
	/** The info. */
	private String info;

	//bi-directional many-to-one association to AdmSubscription
	/** The adm subscription. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subscription_id", insertable=false, updatable=false)
	private AdmSubscription admSubscription;

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
	 * Gets the info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return this.info;
	}

	/**
	 * Sets the info.
	 *
	 * @param info the new info
	 */
	public void setInfo(String info) {
		this.info = info;
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
		return deleteDt;
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
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return createDt;
	}

	/**
	 * @return the subscriptionPK
	 */
	public AdmUserSubscriptionPK getSubscriptionPK() {
		return subscriptionPK;
	}

	/**
	 * @param subscriptionPK the subscriptionPK to set
	 */
	public void setSubscriptionPK(AdmUserSubscriptionPK subscriptionPK) {
		this.subscriptionPK = subscriptionPK;
	}

	
	
}