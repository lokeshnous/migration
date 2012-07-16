package com.advanceweb.afc.jb.employer.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;





/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class EmployerRegistrationForm extends ContactInfoForm{
	private int userId;
	
	@NotEmpty
	private String emailId;
	@NotEmpty
	private String confirmEmailId;
	@NotEmpty
	private String positionTitle;
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty
	private String company;
	@NotEmpty
	private String street;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getConfirmEmailId() {
		return confirmEmailId;
	}
	public void setConfirmEmailId(String confirmEmailId) {
		this.confirmEmailId = confirmEmailId;
	}
	public String getPositionTitle() {
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
			
}
