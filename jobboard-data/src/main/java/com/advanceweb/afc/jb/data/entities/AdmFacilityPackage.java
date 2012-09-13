package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the adm_facility_package database table.
 * 
 */
@Entity
@Table(name = "adm_facility_package")
public class AdmFacilityPackage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_package_id")
	private int facilityPackageId;

	@Column(name = "facility_id")
	private int facilityId;

	@Column(name = "plan_id")
	private int planId;

	@Column(name = "package_id")
	private int packageId;

	@Column(name = "expire_dt")
	private Date expireDt;

	@Column(name = "active")
	private int active;

	@Column(name = "quantity")
	private int quantity;

	public int getFacilityPackageId() {
		return facilityPackageId;
	}

	public void setFacilityPackageId(int facilityPackageId) {
		this.facilityPackageId = facilityPackageId;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public Date getExpireDt() {
		return expireDt;
	}

	public void setExpireDt(Date expireDt) {
		this.expireDt = expireDt;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
