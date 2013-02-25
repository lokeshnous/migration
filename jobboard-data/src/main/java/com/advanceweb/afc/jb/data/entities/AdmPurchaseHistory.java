/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the adm_purchase_history database table.
 * 
 */
@Entity
@Table(name="adm_purchase_history")
public class AdmPurchaseHistory implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The purchase history id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="purchase_history_id")
	private int purchaseHistoryId;

    /** The expire dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="expire_dt")
	private Date expireDt;

    /** The purchase dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="purchase_dt")
	private Date purchaseDt;

	/** The purchased credits. */
	@Column(name="purchased_credits")
	private BigDecimal purchasedCredits;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to AdmFacility
	/** The adm facility. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	/**
	 * Gets the purchase history id.
	 *
	 * @return the purchase history id
	 */
	public int getPurchaseHistoryId() {
		return this.purchaseHistoryId;
	}

	/**
	 * Sets the purchase history id.
	 *
	 * @param purchaseHistoryId the new purchase history id
	 */
	public void setPurchaseHistoryId(int purchaseHistoryId) {
		this.purchaseHistoryId = purchaseHistoryId;
	}

	/**
	 * Gets the expire dt.
	 *
	 * @return the expire dt
	 */
	public Date getExpireDt() {
		return this.expireDt;
	}

	/**
	 * Sets the expire dt.
	 *
	 * @param expireDt the new expire dt
	 */
	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	/**
	 * Gets the purchase dt.
	 *
	 * @return the purchase dt
	 */
	public Date getPurchaseDt() {
		return this.purchaseDt;
	}

	/**
	 * Sets the purchase dt.
	 *
	 * @param purchaseDt the new purchase dt
	 */
	public void setPurchaseDt(Date purchaseDt) {
		this.purchaseDt = purchaseDt;
	}

	/**
	 * Gets the purchased credits.
	 *
	 * @return the purchased credits
	 */
	public BigDecimal getPurchasedCredits() {
		return this.purchasedCredits;
	}

	/**
	 * Sets the purchased credits.
	 *
	 * @param purchasedCredits the new purchased credits
	 */
	public void setPurchasedCredits(BigDecimal purchasedCredits) {
		this.purchasedCredits = purchasedCredits;
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