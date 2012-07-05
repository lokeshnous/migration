package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_user_facility_system database table.
 * 
 */
@Embeddable
public class AdmUserFacilitySystemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="role_id")
	private int roleId;

	@Column(name="facility_system_id")
	private int facilitySystemId;

    public AdmUserFacilitySystemPK() {
    }
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
	public int getFacilitySystemId() {
		return this.facilitySystemId;
	}
	public void setFacilitySystemId(int facilitySystemId) {
		this.facilitySystemId = facilitySystemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUserFacilitySystemPK)) {
			return false;
		}
		AdmUserFacilitySystemPK castOther = (AdmUserFacilitySystemPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId)
			&& (this.facilitySystemId == castOther.facilitySystemId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.facilitySystemId;
		
		return hash;
    }
}