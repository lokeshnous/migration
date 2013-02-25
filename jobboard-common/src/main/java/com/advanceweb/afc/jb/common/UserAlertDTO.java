/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;


/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept 2012
 * 
 */
public class UserAlertDTO {

	/** The alert id. */
	private int alertId;
	
	/** The alert type. */
	private String alertType;
	
	/** The job owner. */
	private String jobOwner;
	
	/** The set date. */
	private String setDate;
	
	/** The user id. */
	private int userId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The facility alert id. */
	private int facilityAlertId;

	/**
	 * @return the alertTYpe
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertTYpe
	 *            the alertTYpe to set
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return the jobOwner
	 */
	public String getJobOwner() {
		return jobOwner;
	}

	/**
	 * @param jobOwner
	 *            the jobOwner to set
	 */
	public void setJobOwner(String jobOwner) {
		this.jobOwner = jobOwner;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the setDate
	 */
	public String getSetDate() {
		return setDate;
	}

	/**
	 * @param setDate
	 *            the setDate to set
	 */
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	/**
	 * @return the alertId
	 */
	public int getAlertId() {
		return alertId;
	}

	/**
	 * @param alertId
	 *            the alertId to set
	 */
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the facilityAlertId
	 */
	public int getFacilityAlertId() {
		return facilityAlertId;
	}

	/**
	 * @param facilityAlertId the facilityAlertId to set
	 */
	public void setFacilityAlertId(int facilityAlertId) {
		this.facilityAlertId = facilityAlertId;
	}

}
