package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_facility_subscription database table.
 * 
 */
@Entity
@Table(name = "adm_facility_subscription")
public class AdmFacilitySubscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdmFacilitySubscriptionPK admFacilitySubscriptionPK;

	@Column(name = "active")
	private int active;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	// bi-directional many-to-one association to AdmSubscription
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id", insertable = false, updatable = false)
	private AdmSubscription admSubscription;

	// bi-directional many-to-one association to AdmFacility
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", insertable = false, updatable = false)
	private AdmFacility admFacility;

	public AdmFacilitySubscriptionPK getAdmFacilitySubscriptionPK() {
		return admFacilitySubscriptionPK;
	}

	public void setAdmFacilitySubscriptionPK(
			AdmFacilitySubscriptionPK admFacilitySubscriptionPK) {
		this.admFacilitySubscriptionPK = admFacilitySubscriptionPK;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getDeleteDt() {
		return this.deleteDt;
	}

	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	public AdmSubscription getAdmSubscription() {
		return this.admSubscription;
	}

	public void setAdmSubscription(AdmSubscription admSubscription) {
		this.admSubscription = admSubscription;
	}

	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
}