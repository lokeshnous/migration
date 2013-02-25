/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the adm_application_status database table.
 * 
 */
@Entity
@Table(name="adm_application_status")
public class AdmApplicationStatus implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The application status id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="application_status_id")
	private int applicationStatusId;

	/** The description. */
	@Column(name="Description")
	private String description;

	/** The name. */
	private String name;

    /**
     * Instantiates a new adm application status.
     */
    public AdmApplicationStatus() {
    }

	/**
	 * Gets the application status id.
	 *
	 * @return the application status id
	 */
	public int getApplicationStatusId() {
		return this.applicationStatusId;
	}

	/**
	 * Sets the application status id.
	 *
	 * @param applicationStatusId the new application status id
	 */
	public void setApplicationStatusId(int applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
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

}