package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the adm_user_subscription database table.
 * 
 */
@Embeddable
public class AdmUserSubscriptionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private int userId;

	@Column(name="subscription_id")
	private int subscriptionId;

	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
		if (!(other instanceof AdmUserSubscriptionPK)) {
			return false;
		}
		AdmUserSubscriptionPK castOther = (AdmUserSubscriptionPK)other;
		return 
			(this.userId == castOther.userId)
			&& (this.subscriptionId == castOther.subscriptionId);

    }
    
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId;
		hash = hash * prime + this.subscriptionId;
		
		return hash;
    }
}