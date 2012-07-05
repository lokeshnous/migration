package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the mer_application database table.
 * 
 */
@Entity
@Table(name = "mer_application")
public class MerApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id", insertable = false, updatable = false)
	private int applicationId;

	private String description;

	// bi-directional many-to-one association to MerUserApplication
	@OneToMany(mappedBy = "merApplication")
	private List<MerUserApplication> merUserApplications;

	private String name;

	@Column(name = "session_timeout_secs")
	private int sessionTimeoutSecs;

	// bi-directional many-to-one association to VstSessioninfo
	@OneToMany(mappedBy = "merApplication")
	private List<VstSessioninfo> vstSessioninfos;

	public MerApplication() {
	}

	public int getApplicationId() {
		return applicationId;
	}

	public String getDescription() {
		return description;
	}

	public List<MerUserApplication> getMerUserApplications() {
		return merUserApplications;
	}

	public String getName() {
		return name;
	}

	public int getSessionTimeoutSecs() {
		return sessionTimeoutSecs;
	}

	public List<VstSessioninfo> getVstSessioninfos() {
		return vstSessioninfos;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMerUserApplications(
			List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSessionTimeoutSecs(int sessionTimeoutSecs) {
		this.sessionTimeoutSecs = sessionTimeoutSecs;
	}

	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}

}