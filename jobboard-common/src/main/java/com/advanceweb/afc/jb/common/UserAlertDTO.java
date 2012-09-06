package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept 2012
 * 
 */
public class UserAlertDTO {

	private int alertId;
	private String alertType;
	private String jobOwner;
	private String setDate;
	private int userId;
	private int facilityId;

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

}
