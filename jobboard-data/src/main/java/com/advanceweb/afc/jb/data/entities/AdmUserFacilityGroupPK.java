package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_user_facility_group database table.
 * 
 */
@Embeddable
public class AdmUserFacilityGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="role_id")
	private int roleId;

	@Column(name="facility_group_id")
	private int facilityGroupId;

	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getFacilityGroupId() {
		return this.facilityGroupId;
	}
	public void setFacilityGroupId(int facilityGroupId) {
		this.facilityGroupId = facilityGroupId;
	}

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
    
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.facilityGroupId;
		
		return hash;
    }
}