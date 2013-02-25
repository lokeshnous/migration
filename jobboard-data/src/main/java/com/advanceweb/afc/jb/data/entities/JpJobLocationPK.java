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
 * The primary key class for the jp_job_location database table.
 * 
 */
@Embeddable
public class JpJobLocationPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	@Column(name="job_id")
	private int jobId;

	/** The location id. */
	@Column(name="location_id")
	private int locationId;

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
	 * Gets the location id.
	 *
	 * @return the location id
	 */
	public int getLocationId() {
		return this.locationId;
	}
	
	/**
	 * Sets the location id.
	 *
	 * @param locationId the new location id
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JpJobLocationPK)) {
			return false;
		}
		JpJobLocationPK castOther = (JpJobLocationPK)other;
		return 
			(this.jobId == castOther.jobId)
			&& (this.locationId == castOther.locationId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.locationId;
		
		return hash;
    }
}