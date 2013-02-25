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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the adm_facility_credit database table.
 * 
 */
@Entity
@Table(name="adm_facility_credit")
public class AdmFacilityCredit implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_id")
	private int facilityId;

	/** The available credits. */
	@Column(name="available_credits")
	private BigDecimal availableCredits;

	//bi-directional one-to-one association to AdmFacility
	/** The adm facility. */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return this.facilityId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * Gets the available credits.
	 *
	 * @return the available credits
	 */
	public BigDecimal getAvailableCredits() {
		return this.availableCredits;
	}

	/**
	 * Sets the available credits.
	 *
	 * @param availableCredits the new available credits
	 */
	public void setAvailableCredits(BigDecimal availableCredits) {
		this.availableCredits = availableCredits;
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