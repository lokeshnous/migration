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

/**
 * The persistent class for the adm_facility_package database table.
 * 
 */
@Entity
@Table(name = "adm_facility_package")
public class AdmFacilityPackage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility package id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_package_id")
	private int facilityPackageId;

	/** The facility id. */
	@Column(name = "facility_id")
	private int facilityId;

	/** The package id. */
	@Column(name = "package_id")
	private int packageId;

	/** The start date. */
	@Column(name = "start_dt")
	private Date startDate;

	/** The end date. */
	@Column(name = "end_dt")
	private Date endDate;

	/**
	 * Gets the facility package id.
	 *
	 * @return the facility package id
	 */
	public int getFacilityPackageId() {
		return facilityPackageId;
	}

	/**
	 * Sets the facility package id.
	 *
	 * @param facilityPackageId the new facility package id
	 */
	public void setFacilityPackageId(int facilityPackageId) {
		this.facilityPackageId = facilityPackageId;
	}

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
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
	 * Gets the package id.
	 *
	 * @return the package id
	 */
	public int getPackageId() {
		return packageId;
	}

	/**
	 * Sets the package id.
	 *
	 * @param packageId the new package id
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
