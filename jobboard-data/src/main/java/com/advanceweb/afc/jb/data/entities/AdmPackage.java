/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adm_package")
public class AdmPackage {

	/** The package id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "package_id")
	private int packageId;

	/** The package type. */
	@Column(name = "package_type")
	private String packageType;
	
	/** The package name. */
	@Column(name = "package_name")
	private String packageName;
	
	/** The netsuite id. */
	@Column(name = "netsuite_id")
	private int netsuiteId;
	
	/** The duration. */
	@Column(name = "Duration")
	private int duration;
	
	/** The active. */
	@Column(name = "active")
	private int active;
	
	/** The discount. */
	@Column(name = "discount")
	private float discount;
	
	/** The price amt. */
	@Column(name = "price_amt")
	private float priceAmt;

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
	 * Gets the package type.
	 *
	 * @return the package type
	 */
	public String getPackageType() {
		return packageType;
	}

	/**
	 * Sets the package type.
	 *
	 * @param packageType the new package type
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Gets the netsuite id.
	 *
	 * @return the netsuite id
	 */
	public int getNetsuiteId() {
		return netsuiteId;
	}

	/**
	 * Sets the netsuite id.
	 *
	 * @param netsuiteId the new netsuite id
	 */
	public void setNetsuiteId(int netsuiteId) {
		this.netsuiteId = netsuiteId;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return active;
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
	 * Gets the price amt.
	 *
	 * @return the price amt
	 */
	public float getPriceAmt() {
		return priceAmt;
	}

	/**
	 * Sets the price amt.
	 *
	 * @param priceAmt the new price amt
	 */
	public void setPriceAmt(float priceAmt) {
		this.priceAmt = priceAmt;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
}
