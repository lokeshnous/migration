package com.advanceweb.afc.jb.common;


/**
 * <code> AppliedJobDTO </code> is a DTO class. The purpose of this class to
 * hold the required information for applied job.
 * 
 * 
 * @author sharad kumar
 * @version 1.0
 * @since 10 July 2012
 * 
 * 
 */
public class JobSeekerSubscriptionsDTO {
	
	private int alertId;
	private int userId;
	private String lookUpId;
	private String alertValue;
	private String createdDate;
	
	
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLookUpId() {
		return lookUpId;
	}
	public void setLookUpId(String lookUpId) {
		this.lookUpId = lookUpId;
	}
	public String getAlertValue() {
		return alertValue;
	}
	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
