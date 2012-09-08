package com.advanceweb.afc.jb.admin.web.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminLoginForm {

	@NotEmpty
	private String userEmail;

	@NotEmpty
	private String jobSeekerOrEmpOrAgeEmail;

	@NotEmpty
	private String password;

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the jobSeekerOrEmpOrAgeEmail
	 */
	public String getJobSeekerOrEmpOrAgeEmail() {
		return jobSeekerOrEmpOrAgeEmail;
	}

	/**
	 * @param jobSeekerOrEmpOrAgeEmail
	 *            the jobSeekerOrEmpOrAgeEmail to set
	 */
	public void setJobSeekerOrEmpOrAgeEmail(String jobSeekerOrEmpOrAgeEmail) {
		this.jobSeekerOrEmpOrAgeEmail = jobSeekerOrEmpOrAgeEmail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
