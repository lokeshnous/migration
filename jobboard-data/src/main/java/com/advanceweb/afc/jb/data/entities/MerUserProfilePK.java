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
 * The primary key class for the mer_user_profile database table.
 * 
 */
@Embeddable
public class MerUserProfilePK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/** The profile attrib id. */
	@Column(name="profile_attrib_id")
	private int profileAttribId;

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
	 * Gets the profile attrib id.
	 *
	 * @return the profile attrib id
	 */
	public int getProfileAttribId() {
		return this.profileAttribId;
	}
	
	/**
	 * Sets the profile attrib id.
	 *
	 * @param profileAttribId the new profile attrib id
	 */
	public void setProfileAttribId(int profileAttribId) {
		this.profileAttribId = profileAttribId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.profileAttribId;
		
		return hash;
    }
}