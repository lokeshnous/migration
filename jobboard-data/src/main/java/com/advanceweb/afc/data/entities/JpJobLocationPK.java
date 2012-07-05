package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the jp_job_location database table.
 * 
 */
@Embeddable
public class JpJobLocationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="job_id")
	private int jobId;

	@Column(name="location_id")
	private int locationId;

    public JpJobLocationPK() {
    }
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getLocationId() {
		return this.locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JpJobLocationPK)) {
			return false;
		}
		JpJobLocationPK castOther = (JpJobLocationPK)other;
		return 
			(this.jobId == castOther.jobId)
			&& (this.locationId == castOther.locationId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.locationId;
		
		return hash;
    }
}