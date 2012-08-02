package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mer_user_application database table.
 * 
 */
@Embeddable
public class MerUserApplicationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="application_id")
	private int applicationId;

    public MerUserApplicationPK() {
    }
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MerUserApplicationPK)) {
			return false;
		}
		MerUserApplicationPK castOther = (MerUserApplicationPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.applicationId == castOther.applicationId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.applicationId;
		
		return hash;
    }
}