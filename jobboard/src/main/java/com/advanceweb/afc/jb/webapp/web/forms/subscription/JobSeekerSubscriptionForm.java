package com.advanceweb.afc.jb.webapp.web.forms.subscription;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerSubscriptionForm {

	private List<String> subscription;
	private List<String> magazine;
	private List<String> jobAlerts;
	public List<String> getSubscription() {
		return subscription;
	}
	public void setSubscription(List<String> subscription) {
		this.subscription = subscription;
	}
	public List<String> getMagazine() {
		return magazine;
	}
	public void setMagazine(List<String> magazine) {
		this.magazine = magazine;
	}
	public List<String> getJobAlerts() {
		return jobAlerts;
	}
	public void setJobAlerts(List<String> jobAlerts) {
		this.jobAlerts = jobAlerts;
	}

	

}
