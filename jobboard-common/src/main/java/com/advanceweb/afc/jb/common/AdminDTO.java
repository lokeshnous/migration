package com.advanceweb.afc.jb.common;

public class AdminDTO {
	private String userEmail;
	private String empOrAgencyEmail;
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
	 * @return the empOrAgencyEmail
	 */
	public String getEmpOrAgencyEmail() {
		return empOrAgencyEmail;
	}

	/**
	 * @param empOrAgencyEmail
	 *            the empOrAgencyEmail to set
	 */
	public void setEmpOrAgencyEmail(String empOrAgencyEmail) {
		this.empOrAgencyEmail = empOrAgencyEmail;
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
