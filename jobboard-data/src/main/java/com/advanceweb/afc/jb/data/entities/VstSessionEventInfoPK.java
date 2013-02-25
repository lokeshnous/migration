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
 * The primary key class for the vst_session_event_info database table.
 * 
 */
@Embeddable
public class VstSessionEventInfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session event id. */
	@Column(name="session_event_id")
	private int sessionEventId;

	/** The info type id. */
	@Column(name="info_type_id")
	private int infoTypeId;

	/**
	 * Gets the session event id.
	 *
	 * @return the session event id
	 */
	public int getSessionEventId() {
		return this.sessionEventId;
	}
	
	/**
	 * Sets the session event id.
	 *
	 * @param sessionEventId the new session event id
	 */
	public void setSessionEventId(int sessionEventId) {
		this.sessionEventId = sessionEventId;
	}
	
	/**
	 * Gets the info type id.
	 *
	 * @return the info type id
	 */
	public int getInfoTypeId() {
		return this.infoTypeId;
	}
	
	/**
	 * Sets the info type id.
	 *
	 * @param infoTypeId the new info type id
	 */
	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VstSessionEventInfoPK)) {
			return false;
		}
		VstSessionEventInfoPK castOther = (VstSessionEventInfoPK)other;
		return 
			(this.sessionEventId == castOther.sessionEventId)
			&& (this.infoTypeId == castOther.infoTypeId);

    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sessionEventId;
		hash = hash * prime + this.infoTypeId;
		
		return hash;
    }
}