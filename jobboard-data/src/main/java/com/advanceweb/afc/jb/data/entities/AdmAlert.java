/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The alert id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_id")
	private int alertId;

	/** The alert type. */
	@Column(name = "alert_type")
	private String alertType;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	// bi-directional many-to-one association to AdmUserAlert
	/** The adm user alerts. */
	@OneToMany(mappedBy = "admAlert", cascade = CascadeType.ALL)
	private List<AdmUserAlert> admUserAlerts;

	// bi-directional many-to-one association to AdmUserAlert
	/** The adm facility alert. */
	@OneToMany(mappedBy = "admAlert", cascade = CascadeType.ALL)
	private List<AdmFacilityAlert> admFacilityAlert;

	/**
	 * Gets the alert id.
	 *
	 * @return the alert id
	 */
	public int getAlertId() {
		return this.alertId;
	}

	/**
	 * Sets the alert id.
	 *
	 * @param alertId the new alert id
	 */
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the adm user alerts.
	 *
	 * @return the adm user alerts
	 */
	public List<AdmUserAlert> getAdmUserAlerts() {
		return this.admUserAlerts;
	}

	/**
	 * Sets the adm user alerts.
	 *
	 * @param admUserAlerts the new adm user alerts
	 */
	public void setAdmUserAlerts(List<AdmUserAlert> admUserAlerts) {
		this.admUserAlerts = admUserAlerts;
	}

	/**
	 * Gets the adm facility alert.
	 *
	 * @return the adm facility alert
	 */
	public List<AdmFacilityAlert> getAdmFacilityAlert() {
		return admFacilityAlert;
	}

	/**
	 * Sets the adm facility alert.
	 *
	 * @param admFacilityAlert the new adm facility alert
	 */
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