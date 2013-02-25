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
 * The primary key class for the adm_user_facility_group database table.
 * 
 */
@Embeddable
public class AdmUserFacilityGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/** The role id. */
	@Column(name="role_id")
	private int roleId;

	/** The facility group id. */
	@Column(name="facility_group_id")
	private int facilityGroupId;

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
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return this.roleId;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * Gets the facility group id.
	 *
	 * @return the facility group id
	 */
	public int getFacilityGroupId() {
		return this.facilityGroupId;
	}
	
	/**
	 * Sets the facility group id.
	 *
	 * @param facilityGroupId the new facility group id
	 */
	public void setFacilityGroupId(int facilityGroupId) {
		this.facilityGroupId = facilityGroupId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUserFacilityGroupPK)) {
			return false;
		}
		AdmUserFacilityGroupPK castOther = (AdmUserFacilityGroupPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId)
			&& (this.facilityGroupId == castOther.facilityGroupId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.facilityGroupId;
		
		return hash;
    }
}