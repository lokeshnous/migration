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
	private static final long serialVersionUID = 1L;

	// This column contains the job_id
	@Column(name = "key_id")
	private int keyId;

	@Temporal(TemporalType.DATE)
	@Column(name = "clickthrough_dt")
	private Date clickthroughDt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clickthrough_type_id", referencedColumnName = "clickthrough_type_id")
	private VstClickthroughType vstClickthroughType;

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public Date getClickthroughDt() {
		return clickthroughDt;
	}

	public void setClickthroughDt(Date clickthroughDt) {
		this.clickthroughDt = clickthroughDt;
	}

	public VstClickthroughType getVstClickthroughType() {
		return vstClickthroughType;
	}

	public void setVstClickthroughType(VstClickthroughType vstClickthroughType) {
		this.vstClickthroughType = vstClickthroughType;
	}

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