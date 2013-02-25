/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminLoginForm {

	/*@NotEmpty
	private String userEmail;*/

	/** The emp or agency email. */
	@NotEmpty
	private String empOrAgencyEmail;

	/** The admin user id. */
	private int adminUserId;
	
	
	/*@NotEmpty
	private String password;*/

	/**
	 * @return the userEmail
	 */
	/*public String getUserEmail() {
		return userEmail;
	}
*/
	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	/*public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}*/

	public int getAdminUserId() {
		return adminUserId;
	}

	/**
	 * Sets the admin user id.
	 *
	 * @param adminUserId the new admin user id
	 */
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	/**
	 * @return the empOrAgencyEmail
	 */
	public String getEmpOrAgencyEmail() {
		return empOrAgencyEmail;
	}

	/**
	 * @param empOrAgencyEmail the empOrAgencyEmail to set
	 */
	public void setEmpOrAgencyEmail(String empOrAgencyEmail) {
		this.empOrAgencyEmail = empOrAgencyEmail;
	}

	/**
	 * @return the password
	 *//*
	public String getPassword() {
		return password;
	}

	*//**
	 * @param password
	 *            the password to set
	 *//*
	public void setPassword(String password) {
		this.password = password;
	}*/

}
