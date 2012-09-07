package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the adm_user_alert database table.
 * 
 */
@Entity
@Table(name = "adm_facility_alert")
public class AdmFacilityAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "facility_alert_id")
	private int facilityAlertId;

	@Column(name = "alert_value")
	private String alertValue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "facility_id")
	private int facilityId;

	// bi-directional many-to-one association to AdmAlert
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id")
	private AdmAlert admAlert;

	public String getAlertValue() {
		return this.alertValue;
	}

	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public AdmAlert getAdmAlert() {
		return this.admAlert;
	}

	public void setAdmAlert(AdmAlert admAlert) {
		this.admAlert = admAlert;
	}

	public int getFacilityAlertId() {
		return facilityAlertId;
	}

	public void setFacilityAlertId(int facilityAlertId) {
		this.facilityAlertId = facilityAlertId;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

}