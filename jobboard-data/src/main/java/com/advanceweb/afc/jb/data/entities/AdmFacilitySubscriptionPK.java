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
 * The primary key class for the adm_facility_subscription database table.
 * 
 */
@Embeddable
public class AdmFacilitySubscriptionPK implements Serializable {
	// default serial version id, required for serializable classes.
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facility id. */
	@Column(name = "facility_id")
	private int facilityId;

	/** The subscription id. */
	@Column(name = "subscription_id")
	private int subscriptionId;

	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return this.facilityId;
	}

	/** The publication id. */
	@Column(name = "publication_id")
	private int publicationId;

	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return publicationId;
	}

	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * Gets the subscription id.
	 *
	 * @return the subscription id
	 */
	public int getSubscriptionId() {
		return this.subscriptionId;
	}

	/**
	 * Sets the subscription id.
	 *
	 * @param subscriptionId the new subscription id
	 */
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AdmFacilitySubscriptionPK)) {
			return false;
		}
		AdmFacilitySubscriptionPK castOther = (AdmFacilitySubscriptionPK) other;
		return (this.facilityId == castOther.facilityId)
				&& (this.subscriptionId == castOther.subscriptionId)
				&& (this.publicationId == castOther.publicationId);

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * prime + this.facilityId;
		hash = hash * prime + this.subscriptionId;

		return hash;
	}
}