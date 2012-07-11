package com.advanceweb.afc.jb.common;

import java.util.Date;

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
public class JobSeekerSubscriptionsDTO {

	private String subscriptions;
	private String magazines;
	private String jobAlerts;

	public String getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(String subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getMagazines() {
		return magazines;
	}

	public void setMagazines(String magazines) {
		this.magazines = magazines;
	}

	public String getJobAlerts() {
		return jobAlerts;
	}

	public void setJobAlerts(String jobAlerts) {
		this.jobAlerts = jobAlerts;
	}

}
