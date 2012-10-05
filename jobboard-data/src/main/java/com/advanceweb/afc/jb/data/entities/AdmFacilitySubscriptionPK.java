package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the adm_facility_subscription database table.
 * 
 */
@Embeddable
public class AdmFacilitySubscriptionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="facility_id")
	private int facilityId;

	@Column(name="subscription_id")
	private int subscriptionId;

	public int getFacilityId() {
		return this.facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getSubscriptionId() {
		return this.subscriptionId;
	}
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFacilitySubscriptionPK)) {
			return false;
		}
		AdmFacilitySubscriptionPK castOther = (AdmFacilitySubscriptionPK)other;
		return 
			(this.facilityId == castOther.facilityId)
			&& (this.subscriptionId == castOther.subscriptionId);

    }
    
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facilityId;
		hash = hash * prime + this.subscriptionId;
		
		return hash;
    }
}