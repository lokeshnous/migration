package com.advanceweb.afc.jb.data.entities;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the adm_facility_jp_audit database table.
 * 
 */
@Embeddable
public class AdmFacilityJpAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="facility_id")
	private int facilityId;

	@Column(name="user_id")
	private int userId;

	@Column(name="job_id")
	private int jobId;

	@Column(name="inventory_detail_id")
	private int inventoryDetailId;

//	Commented unused constructor
//    public AdmFacilityJpAuditPK() {
//    }
	public int getFacilityId() {
		return this.facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return this.jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getInventoryDetailId() {
		return this.inventoryDetailId;
	}
	public void setInventoryDetailId(int inventoryDetailId) {
		this.inventoryDetailId = inventoryDetailId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFacilityJpAuditPK)) {
			return false;
		}
		AdmFacilityJpAuditPK castOther = (AdmFacilityJpAuditPK)other;
		return 
			(this.facilityId == castOther.facilityId)
			&& (this.userId == castOther.userId)
			&& (this.jobId == castOther.jobId)
			&& (this.inventoryDetailId == castOther.inventoryDetailId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facilityId;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.jobId;
		hash = hash * prime + this.inventoryDetailId;
		
		return hash;
    }
}