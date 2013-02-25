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
 * The primary key class for the adm_facility_jp_audit database table.
 * 
 */
@Embeddable
public class AdmFacilityJpAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility id. */
	@Column(name="facility_id")
	private int facilityId;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/** The job id. */
	@Column(name="job_id")
	private int jobId;

	/** The inventory detail id. */
	@Column(name="inventory_detail_id")
	private int inventoryDetailId;

//	Commented unused constructor
//    public AdmFacilityJpAuditPK() {
//    }
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
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
	 * Gets the inventory detail id.
	 *
	 * @return the inventory detail id
	 */
	public int getInventoryDetailId() {
		return this.inventoryDetailId;
	}
	
	/**
	 * Sets the inventory detail id.
	 *
	 * @param inventoryDetailId the new inventory detail id
	 */
	public void setInventoryDetailId(int inventoryDetailId) {
		this.inventoryDetailId = inventoryDetailId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFacilityJpAuditPK)) {
			return false;
		}
		AdmFacilityJpAuditPK castOther = (AdmFacilityJpAuditPK)other;
		return 
			(this.facilityId == castOther.facilityId)
			&& (this.userId == castOther.userId)
			&& (this.jobId == castOther.jobId)
			&& (this.inventoryDetailId == castOther.inventoryDetailId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facilityId;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.inventoryDetailId;
		
		return hash;
    }
}