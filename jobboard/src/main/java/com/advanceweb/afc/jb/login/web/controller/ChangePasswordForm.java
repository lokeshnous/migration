package com.advanceweb.afc.jb.login.web.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class ChangePasswordForm {
	
	@NotEmpty
	private String emailId;
	@NotEmpty(message="Current Password should not be empty")
	private String currentPassword;
	@NotEmpty(message="New Password should not be empty")
	private String password;
	@NotEmpty(message="Confirm Password should not be empty")
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
