package com.advanceweb.afc.jb.employer.web.controller;

import java.util.Date;

/**
 * 
 * @author bharatiu
 * @version 1.0
 * @since 4th sept, 2012
 */

public class UserAlertForm {

	private String alertTYpe;
	private String jobOwner;
	private Date setDate;

	/**
	 * @return the alertTYpe
	 */
	public String getAlertTYpe() {
		return alertTYpe;
	}

	/**
	 * @param alertTYpe
	 *            the alertTYpe to set
	 */
	public void setAlertTYpe(String alertTYpe) {
		this.alertTYpe = alertTYpe;
	}

	/**
	 * @return the jobOwner
	 */
	public String getJobOwner() {
		return jobOwner;
	}

	/**
	 * @param jobOwner
	 *            the jobOwner to set
	 */
	public void setJobOwner(String jobOwner) {
		this.jobOwner = jobOwner;
	}

	/**
	 * @return the setDate
	 */
	public Date getSetDate() {
		return setDate;
	}

	/**
	 * @param setDate
	 *            the setDate to set
	 */
	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}
}
