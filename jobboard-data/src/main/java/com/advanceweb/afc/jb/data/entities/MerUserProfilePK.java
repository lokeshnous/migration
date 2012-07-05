package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mer_user_profile database table.
 * 
 */
@Embeddable
public class MerUserProfilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="profile_attrib_id")
	private int profileAttribId;

    public MerUserProfilePK() {
    }
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProfileAttribId() {
		return this.profileAttribId;
	}
	public void setProfileAttribId(int profileAttribId) {
		this.profileAttribId = profileAttribId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MerUserProfilePK)) {
			return false;
		}
		MerUserProfilePK castOther = (MerUserProfilePK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.profileAttribId == castOther.profileAttribId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.profileAttribId;
		
		return hash;
    }
}