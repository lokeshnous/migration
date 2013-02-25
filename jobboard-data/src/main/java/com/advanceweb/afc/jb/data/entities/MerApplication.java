/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
@Table(name="mer_application")
public class MerApplication implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The application id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="application_id")
	private int applicationId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	/** The session timeout secs. */
	@Column(name="session_timeout_secs")
	private int sessionTimeoutSecs;

	//bi-directional many-to-one association to MerUserApplication
	/** The mer user applications. */
	@OneToMany(mappedBy="merApplication")
	private List<MerUserApplication> merUserApplications;

	//bi-directional many-to-one association to VstSessioninfo
	/** The vst sessioninfos. */
	@OneToMany(mappedBy="merApplication")
	private List<VstSessioninfo> vstSessioninfos;

	/**
	 * Gets the application id.
	 *
	 * @return the application id
	 */
	public int getApplicationId() {
		return this.applicationId;
	}

	/**
	 * Sets the application id.
	 *
	 * @param applicationId the new application id
	 */
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the session timeout secs.
	 *
	 * @return the session timeout secs
	 */
	public int getSessionTimeoutSecs() {
		return this.sessionTimeoutSecs;
	}

	/**
	 * Sets the session timeout secs.
	 *
	 * @param sessionTimeoutSecs the new session timeout secs
	 */
	public void setSessionTimeoutSecs(int sessionTimeoutSecs) {
		this.sessionTimeoutSecs = sessionTimeoutSecs;
	}

	/**
	 * Gets the mer user applications.
	 *
	 * @return the mer user applications
	 */
	public List<MerUserApplication> getMerUserApplications() {
		return this.merUserApplications;
	}

	/**
	 * Sets the mer user applications.
	 *
	 * @param merUserApplications the new mer user applications
	 */
	public void setMerUserApplications(List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}
	
	/**
	 * Gets the vst sessioninfos.
	 *
	 * @return the vst sessioninfos
	 */
	public List<VstSessioninfo> getVstSessioninfos() {
		return this.vstSessioninfos;
	}

	/**
	 * Sets the vst sessioninfos.
	 *
	 * @param vstSessioninfos the new vst sessioninfos
	 */
	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}
	
}