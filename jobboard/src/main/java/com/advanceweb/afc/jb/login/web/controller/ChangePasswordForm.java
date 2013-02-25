/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.login.web.controller;


/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class ChangePasswordForm {
	

	/** The email id. */
	private String emailId;
	
	/** The current password. */
	private String currentPassword;
	
	/** The password. */
	private String password;
	
	/** The retypepassword. */
	private String retypepassword;
	
	
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
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	/**
	 * Sets the current password.
	 *
	 * @param currentPassword the new current password
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
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
	 * Gets the retypepassword.
	 *
	 * @return the retypepassword
	 */
	public String getRetypepassword() {
		return retypepassword;
	}
	
	/**
	 * Sets the retypepassword.
	 *
	 * @param retypepassword the new retypepassword
	 */
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	
	
		
}
