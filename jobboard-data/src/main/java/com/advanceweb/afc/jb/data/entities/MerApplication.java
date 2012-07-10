package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mer_application database table.
 * 
 */
@Entity
@Table(name="mer_application")
public class MerApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="application_id")
	private int applicationId;

	private String description;

	private String name;

	@Column(name="session_timeout_secs")
	private int sessionTimeoutSecs;

	//bi-directional many-to-one association to MerUserApplication
	@OneToMany(mappedBy="merApplication")
	private List<MerUserApplication> merUserApplications;

	//bi-directional many-to-one association to VstSessioninfo
	@OneToMany(mappedBy="merApplication")
	private List<VstSessioninfo> vstSessioninfos;

    public MerApplication() {
    }

	public int getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSessionTimeoutSecs() {
		return this.sessionTimeoutSecs;
	}

	public void setSessionTimeoutSecs(int sessionTimeoutSecs) {
		this.sessionTimeoutSecs = sessionTimeoutSecs;
	}

	public List<MerUserApplication> getMerUserApplications() {
		return this.merUserApplications;
	}

	public void setMerUserApplications(List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}
	
	public List<VstSessioninfo> getVstSessioninfos() {
		return this.vstSessioninfos;
	}

	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}
	
}