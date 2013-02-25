/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
public class UserSubscriptionsDTO {
	
	/** The user id. */
	private int userId;
	
	/** The subscription id. */
	private int subscriptionId;
	
	/** The active. */
	private int active;
	
	/** The publication id. */
	private int publicationId;
	
	/** The publication name. */
	private String publicationName;
	
	/** The facility id. */
	private int facilityId;
	
	/** The role id. */
	private int roleId;
	
	/**
	 * Gets the subscription id.
	 *
	 * @return the subscription id
	 */
	public int getSubscriptionId() {
		return subscriptionId;
	}
	
	/**
	 * Sets the subscription id.
	 *
	 * @param subscriptionId the new subscription id
	 */
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return publicationId;
	}
	
	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	
	/**
	 * Gets the publication name.
	 *
	 * @return the publication name
	 */
	public String getPublicationName() {
		return publicationName;
	}
	
	/**
	 * Sets the publication name.
	 *
	 * @param publicationName the new publication name
	 */
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}
	
	/**
	 * Gets the facility id.
	 *
	 * @return the facility id
	 */
	public int getFacilityId() {
		return facilityId;
	}
	
	/**
	 * Sets the facility id.
	 *
	 * @param facilityId the new facility id
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	
	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public int getRoleId() {
		return roleId;
	}
	
	/**
	 * Sets the role id.
	 *
	 * @param roleId the new role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
