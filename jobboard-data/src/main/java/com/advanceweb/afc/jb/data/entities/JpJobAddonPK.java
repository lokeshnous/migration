package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the jp_job_addon database table.
 * 
 */
@Embeddable
public class JpJobAddonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="job_id")
	private int jobId;

	@Column(name="addon_id")
	private int addonId;

    public JpJobAddonPK() {
    }
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getAddonId() {
		return this.addonId;
	}
	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JpJobAddonPK)) {
			return false;
		}
		JpJobAddonPK castOther = (JpJobAddonPK)other;
		return 
			(this.jobId == castOther.jobId)
			&& (this.addonId == castOther.addonId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.addonId;
		
		return hash;
    }
}