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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_location database table.
 * 
 */
@Entity
@Table(name="jp_job_location")
public class JpJobLocation implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The location pk. */
	@EmbeddedId
	private JpJobLocationPK locationPK;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The hide city. */
	@Column(name="hide_city")
	private Integer hideCity;

	/** The hide country. */
	@Column(name="hide_country")
	private Integer hideCountry;

	/** The hide postcode. */
	@Column(name="hide_postcode")
	private Integer hidePostcode;

	/** The hide state. */
	@Column(name="hide_state")
	private Integer hideState;

	//bi-directional many-to-one association to JpJob
	/** The jp job. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false)
	private JpJob jpJob;

	//bi-directional many-to-one association to JpLocation
	/** The jp location. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable=false, updatable=false)
	private JpLocation jpLocation;

	/**
	 * Gets the location pk.
	 *
	 * @return the location pk
	 */
	public JpJobLocationPK getLocationPK() {
		return locationPK;
	}

	/**
	 * Sets the location pk.
	 *
	 * @param locationPK the new location pk
	 */
	public void setLocationPK(JpJobLocationPK locationPK) {
		this.locationPK = locationPK;
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
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the hide city.
	 *
	 * @return the hide city
	 */
	public Integer getHideCity() {
		return hideCity;
	}

	/**
	 * Sets the hide city.
	 *
	 * @param hideCity the new hide city
	 */
	public void setHideCity(Integer hideCity) {
		this.hideCity = hideCity;
	}

	/**
	 * Gets the hide country.
	 *
	 * @return the hide country
	 */
	public Integer getHideCountry() {
		return hideCountry;
	}

	/**
	 * Sets the hide country.
	 *
	 * @param hideCountry the new hide country
	 */
	public void setHideCountry(Integer hideCountry) {
		this.hideCountry = hideCountry;
	}

	/**
	 * Gets the hide postcode.
	 *
	 * @return the hide postcode
	 */
	public Integer getHidePostcode() {
		return hidePostcode;
	}

	/**
	 * Sets the hide postcode.
	 *
	 * @param hidePostcode the new hide postcode
	 */
	public void setHidePostcode(Integer hidePostcode) {
		this.hidePostcode = hidePostcode;
	}

	/**
	 * Gets the hide state.
	 *
	 * @return the hide state
	 */
	public Integer getHideState() {
		return hideState;
	}

	/**
	 * Sets the hide state.
	 *
	 * @param hideState the new hide state
	 */
	public void setHideState(Integer hideState) {
		this.hideState = hideState;
	}

	/**
	 * Gets the jp job.
	 *
	 * @return the jp job
	 */
	public JpJob getJpJob() {
		return jpJob;
	}

	/**
	 * Sets the jp job.
	 *
	 * @param jpJob the new jp job
	 */
	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}

	/**
	 * Gets the jp location.
	 *
	 * @return the jp location
	 */
	public JpLocation getJpLocation() {
		return jpLocation;
	}

	/**
	 * Sets the jp location.
	 *
	 * @param jpLocation the new jp location
	 */
	public void setJpLocation(JpLocation jpLocation) {
		this.jpLocation = jpLocation;
	}

	
	
}