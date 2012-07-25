package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_user_role database table.
 * 
 */
@Embeddable
public class AdmUserRolePK implements Serializable {
	//default serial version id, required for serialize classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="role_id")
	private int roleId;

    public AdmUserRolePK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmUserRolePK)) {
			return false;
		}
		AdmUserRolePK castOther = (AdmUserRolePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.roleId == castOther.roleId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.roleId;
		
		return hash;
    }
}