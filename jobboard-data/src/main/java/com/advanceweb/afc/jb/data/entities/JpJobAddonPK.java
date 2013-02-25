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
import javax.persistence.Embeddable;

/**
 * The primary key class for the jp_job_addon database table.
 * 
 */
@Embeddable
public class JpJobAddonPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	@Column(name="job_id")
	private int jobId;

	/** The addon id. */
	@Column(name="addon_id")
	private int addonId;

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return this.jobId;
	}
	
	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	/**
	 * Gets the addon id.
	 *
	 * @return the addon id
	 */
	public int getAddonId() {
		return this.addonId;
	}
	
	/**
	 * Sets the addon id.
	 *
	 * @param addonId the new addon id
	 */
	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JpJobAddonPK)) {
			return false;
		}
		JpJobAddonPK castOther = (JpJobAddonPK)other;
		return 
			(this.jobId == castOther.jobId)
			&& (this.addonId == castOther.addonId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.addonId;
		
		return hash;
    }
}