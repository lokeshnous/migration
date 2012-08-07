package com.advanceweb.afc.jb.login.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class ChangePasswordForm {
	

	private String emailId;
	private String currentPassword;
	private String password;
	private String retypepassword;
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetypepassword() {
		return retypepassword;
	}
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	
	
		
}
