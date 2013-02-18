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
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="role_id")
	private Integer roleId;

	@Column(name="facility_id")
	private Integer facilityId;

	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getFacilityId() {
		return this.facilityId;
	}
	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

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
    
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.facilityId;
		
		return hash;
    }
}