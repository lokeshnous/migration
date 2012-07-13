package com.advanceweb.afc.jb.webapp.web.forms.subscription;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JobSeekerSubscriptionForm {

	private String[] currentsubs;
	private String[] currentmagazines;
	private String[] currentJobAlerts;
	
	private int userId;
	
	
	public String[] getCurrentsubs() {
		return currentsubs;
	}
	public void setCurrentsubs(String[] currentsubs) {
		this.currentsubs = currentsubs;
	}
	public String[] getCurrentmagazines() {
		return currentmagazines;
	}
	public void setCurrentmagazines(String[] currentmagazines) {
		this.currentmagazines = currentmagazines;
	}
	public String[] getCurrentJobAlerts() {
		return currentJobAlerts;
	}
	public void setCurrentJobAlerts(String[] currentJobAlerts) {
		this.currentJobAlerts = currentJobAlerts;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
				
}
