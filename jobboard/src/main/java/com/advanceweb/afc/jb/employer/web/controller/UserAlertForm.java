package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

/**
 * 
 * @author Bharati Umarani
 * @version 1.0
 * @since 4th sept, 2012
 */

public class UserAlertForm {

	private int alertId;
	private String alertTYpe;
	private String jobOwner;
	private Date setDate;
	private int userId;
	private int facilityId;
	private String[] selectedAlerts;
	private String selJobOwner;
	private int selJbOwnerId;
	private int facilityAlertId;

	/**
	 * @return the alertTYpe
	 */
	public String getAlertTYpe() {
		return alertTYpe;
	}

	/**
	 * @param alertTYpe
	 *            the alertTYpe to set
	 */
	public void setAlertTYpe(String alertTYpe) {
		this.alertTYpe = alertTYpe;
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
	 * @return the setDate
	 */
	public Date getSetDate() {
		return setDate;
	}

	/**
	 * @param setDate
	 *            the setDate to set
	 */
	public void setSetDate(Date setDate) {
		this.setDate = setDate;
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
	 * @return the selectedAlerts
	 */
	public String[] getSelectedAlerts() {
		return selectedAlerts;
	}

	/**
	 * @param selectedAlerts the selectedAlerts to set
	 */
	public void setSelectedAlerts(String[] selectedAlerts) {
		this.selectedAlerts = selectedAlerts;
	}

	/**
	 * @return the alertId
	 */
	public int getAlertId() {
		return alertId;
	}

	/**
	 * @param alertId the alertId to set
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
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the selJobOwner
	 */
	public String getSelJobOwner() {
		return selJobOwner;
	}

	/**
	 * @param selJobOwner the selJobOwner to set
	 */
	public void setSelJobOwner(String selJobOwner) {
		this.selJobOwner = selJobOwner;
	}

	/**
	 * @return the selJbOwnerId
	 */
	public int getSelJbOwnerId() {
		return selJbOwnerId;
	}

	/**
	 * @param selJbOwnerId the selJbOwnerId to set
	 */
	public void setSelJbOwnerId(int selJbOwnerId) {
		this.selJbOwnerId = selJbOwnerId;
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
