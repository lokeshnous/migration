package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the vst_session_event_info database table.
 * 
 */
@Embeddable
public class VstSessionEventInfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="session_event_id")
	private int sessionEventId;

	@Column(name="info_type_id")
	private int infoTypeId;

    public VstSessionEventInfoPK() {
    }
	public int getSessionEventId() {
		return this.sessionEventId;
	}
	public void setSessionEventId(int sessionEventId) {
		this.sessionEventId = sessionEventId;
	}
	public int getInfoTypeId() {
		return this.infoTypeId;
	}
	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

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
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sessionEventId;
		hash = hash * prime + this.infoTypeId;
		
		return hash;
    }
}