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
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobAlertsDTO {
	
	/** The alert id. */
	private String alertId;
	
	/** The alert name. */
	private String alertName;
	
	
	/**
	 * Gets the alert id.
	 *
	 * @return the alert id
	 */
	public String getAlertId() {
		return alertId;
	}
	
	/**
	 * Sets the alert id.
	 *
	 * @param alertId the new alert id
	 */
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	
	/**
	 * Gets the alert name.
	 *
	 * @return the alert name
	 */
	public String getAlertName() {
		return alertName;
	}
	
	/**
	 * Sets the alert name.
	 *
	 * @param alertName the new alert name
	 */
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}		
}
