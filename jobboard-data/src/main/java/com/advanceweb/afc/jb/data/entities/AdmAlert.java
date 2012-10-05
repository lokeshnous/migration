package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the adm_alert database table.
 * 
 */
@Entity
@Table(name = "adm_alert")
public class AdmAlert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_id")
	private int alertId;

	@Column(name = "alert_type")
	private String alertType;

	private String description;

	private String name;

	// bi-directional many-to-one association to AdmUserAlert
	@OneToMany(mappedBy = "admAlert", cascade = CascadeType.ALL)
	private List<AdmUserAlert> admUserAlerts;

	// bi-directional many-to-one association to AdmUserAlert
	@OneToMany(mappedBy = "admAlert", cascade = CascadeType.ALL)
	private List<AdmFacilityAlert> admFacilityAlert;

	public int getAlertId() {
		return this.alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AdmUserAlert> getAdmUserAlerts() {
		return this.admUserAlerts;
	}

	public void setAdmUserAlerts(List<AdmUserAlert> admUserAlerts) {
		this.admUserAlerts = admUserAlerts;
	}

	public List<AdmFacilityAlert> getAdmFacilityAlert() {
		return admFacilityAlert;
	}

	public void setAdmFacilityAlert(List<AdmFacilityAlert> admFacilityAlert) {
		this.admFacilityAlert = admFacilityAlert;
	}

	/**
	 * @return the alertType
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType the alertType to set
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

}