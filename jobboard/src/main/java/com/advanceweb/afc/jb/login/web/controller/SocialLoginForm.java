/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;


public class SocialLoginForm {
	
	/** The email id. */
	private String emailId;	
	
	/** The password. */
	private String password;
	
	/** The profile id. */
	private String profileId;
	
	/** The page value. */
	private String pageValue;
	
	/** The service provider id. */
	private String serviceProviderId;
	
	/** The error. */
	private boolean error ;
	
	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the profile id.
	 *
	 * @return the profile id
	 */
	public String getProfileId() {
		return profileId;
	}
	
	/**
	 * Sets the profile id.
	 *
	 * @param profileId the new profile id
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	/**
	 * Gets the page value.
	 *
	 * @return the page value
	 */
	public String getPageValue() {
		return pageValue;
	}
	
	/**
	 * Sets the page value.
	 *
	 * @param pageValue the new page value
	 */
	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}
	
	/**
	 * Gets the service provider id.
	 *
	 * @return the service provider id
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	
	/**
	 * Sets the service provider id.
	 *
	 * @param serviceProviderId the new service provider id
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	
	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return error;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(boolean error) {
		this.error = error;
	}
	
	
}
