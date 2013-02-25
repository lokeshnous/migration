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
 * The primary key class for the mer_user_application database table.
 * 
 */
@Embeddable
public class MerUserApplicationPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/** The application id. */
	@Column(name="application_id")
	private int applicationId;

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
	 * Gets the application id.
	 *
	 * @return the application id
	 */
	public int getApplicationId() {
		return this.applicationId;
	}
	
	/**
	 * Sets the application id.
	 *
	 * @param applicationId the new application id
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.applicationId;
		
		return hash;
    }
}