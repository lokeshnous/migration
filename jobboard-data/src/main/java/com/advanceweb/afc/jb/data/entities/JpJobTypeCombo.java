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
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * The persistent class for the jp_jobtype_combo database table.
 * 
 */
@Entity
@Table(name="jp_jobtype_combo")
public class JpJobTypeCombo {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The combo id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OrderBy
	@Column(name="combo_id")
	private int comboId;
	
	/** The job type. */
	@Column(name="job_type")
	private String jobType;
	
	/** The addons. */
	@Column(name="addons")
	private String addons;
	
	/** The net suite id. */
	@Column(name="netsuite_id")
	private int netSuiteId;
	
	/** The base price. */
	@Column(name="base_price")
	private float basePrice;
	
	/**
	 * Gets the combo id.
	 *
	 * @return the combo id
	 */
	public int getComboId() {
		return comboId;
	}

	/**
	 * Sets the combo id.
	 *
	 * @param comboId the new combo id
	 */
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	/**
	 * Gets the job type.
	 *
	 * @return the job type
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * Sets the job type.
	 *
	 * @param jobType the new job type
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * Gets the addons.
	 *
	 * @return the addons
	 */
	public String getAddons() {
		return addons;
	}

	/**
	 * Sets the addons.
	 *
	 * @param addons the new addons
	 */
	public void setAddons(String addons) {
		this.addons = addons;
	}
	
	/**
	 * Gets the net suite id.
	 *
	 * @return the net suite id
	 */
	public int getNetSuiteId() {
		return netSuiteId;
	}

	/**
	 * Sets the net suite id.
	 *
	 * @param netSuiteId the new net suite id
	 */
	public void setNetSuiteId(int netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	/**
	 * Gets the base price.
	 *
	 * @return the base price
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/**
	 * Sets the base price.
	 *
	 * @param basePrice the new base price
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
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
