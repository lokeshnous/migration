/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the jp_type_addon_xref database table.
 * 
 */
@Entity
@Table(name = "jp_type_addon_xref")
public class JpTypeAddonXref implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type addon id. */
	@Id
	@Column(name = "type_addon_id")
	private int typeAddonId;

	/** The job type id. */
	@Column(name = "job_type_id")
	private int jobTypeId;

	/** The addon id. */
	@Column(name = "addon_id")
	private int addonId;

	/** The combo id. */
	@Column(name = "combo_id")
	private int comboId;

	/**
	 * Gets the type addon id.
	 *
	 * @return the type addon id
	 */
	public int getTypeAddonId() {
		return typeAddonId;
	}

	/**
	 * Sets the type addon id.
	 *
	 * @param typeAddonId the new type addon id
	 */
	public void setTypeAddonId(int typeAddonId) {
		this.typeAddonId = typeAddonId;
	}

	/**
	 * Gets the job type id.
	 *
	 * @return the job type id
	 */
	public int getJobTypeId() {
		return jobTypeId;
	}

	/**
	 * Sets the job type id.
	 *
	 * @param jobTypeId the new job type id
	 */
	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	/**
	 * Gets the addon id.
	 *
	 * @return the addon id
	 */
	public int getAddonId() {
		return addonId;
	}

	/**
	 * Sets the addon id.
	 *
	 * @param addonId the new addon id
	 */
	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

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

}