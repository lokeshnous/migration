package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the adm_facility_credit database table.
 * 
 */
@Entity
@Table(name="adm_facility_credit")
public class AdmFacilityCredit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="facility_id")
	private int facilityId;

	@Column(name="available_credits")
	private BigDecimal availableCredits;

	//bi-directional one-to-one association to AdmFacility
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility_id")
	private AdmFacility admFacility;

	public int getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public BigDecimal getAvailableCredits() {
		return this.availableCredits;
	}

	public void setAvailableCredits(BigDecimal availableCredits) {
		this.availableCredits = availableCredits;
	}

	public AdmFacility getAdmFacility() {
		return this.admFacility;
	}

	public void setAdmFacility(AdmFacility admFacility) {
		this.admFacility = admFacility;
	}
	
}