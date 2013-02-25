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
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the vst_clickthrough_new database table.
 * 
 */
@Embeddable
public class VstClickthroughNewPK implements Serializable {
	// Default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// This column contains the job_id
	/** The key id. */
	@Column(name = "key_id")
	private int keyId;

	/** The clickthrough dt. */
	@Temporal(TemporalType.DATE)
	@Column(name = "clickthrough_dt")
	private Date clickthroughDt;

	/** The vst clickthrough type. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clickthrough_type_id", referencedColumnName = "clickthrough_type_id")
	private VstClickthroughType vstClickthroughType;

	/**
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	public int getKeyId() {
		return keyId;
	}

	/**
	 * Sets the key id.
	 *
	 * @param keyId the new key id
	 */
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	/**
	 * Gets the clickthrough dt.
	 *
	 * @return the clickthrough dt
	 */
	public Date getClickthroughDt() {
		return clickthroughDt;
	}

	/**
	 * Sets the clickthrough dt.
	 *
	 * @param clickthroughDt the new clickthrough dt
	 */
	public void setClickthroughDt(Date clickthroughDt) {
		this.clickthroughDt = clickthroughDt;
	}

	/**
	 * Gets the vst clickthrough type.
	 *
	 * @return the vst clickthrough type
	 */
	public VstClickthroughType getVstClickthroughType() {
		return vstClickthroughType;
	}

	/**
	 * Sets the vst clickthrough type.
	 *
	 * @param vstClickthroughType the new vst clickthrough type
	 */
	public void setVstClickthroughType(VstClickthroughType vstClickthroughType) {
		this.vstClickthroughType = vstClickthroughType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clickthroughDt == null) ? 0 : clickthroughDt.hashCode());
		result = prime * result + keyId;
		result = prime
				* result
				+ ((vstClickthroughType == null) ? 0 : vstClickthroughType
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VstClickthroughNewPK other = (VstClickthroughNewPK) obj;
		if (clickthroughDt == null) {
			if (other.clickthroughDt != null)
				return false;
		} else if (!clickthroughDt.equals(other.clickthroughDt))
			return false;
		if (keyId != other.keyId)
			return false;
		if (vstClickthroughType == null) {
			if (other.vstClickthroughType != null)
				return false;
		} else if (!vstClickthroughType.equals(other.vstClickthroughType))
			return false;
		return true;
	}

}