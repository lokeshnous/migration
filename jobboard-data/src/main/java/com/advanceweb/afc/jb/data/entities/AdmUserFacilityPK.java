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
 * The primary key class for the adm_user_facility database table.
 * 
 */
@Embeddable
public class AdmUserFacilityPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Column(name="user_id")
	private Integer userId;

	/** The role id. */
	@Column(name="role_id")
	private Integer roleId;

	/** The facility id. */
	@Column(name="facility_id")
	private Integer facilityId;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public Integer getRoleId() {
		return this.roleId;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public Integer getFacilityId() {
		return this.facilityId;
	}
	
	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUserFacilityPK)) {
			return false;
		}
		AdmUserFacilityPK castOther = (AdmUserFacilityPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId)
			&& (this.facilityId == castOther.facilityId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.facilityId;
		
		return hash;
    }
}