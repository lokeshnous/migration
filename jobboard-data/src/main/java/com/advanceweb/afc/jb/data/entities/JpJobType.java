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
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_type database table.
 * 
 */
@Entity
@Table(name="jp_job_type")
public class JpJobType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job type id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_type_id")
	private int jobTypeId;

	/** The credit amt. */
	@Column(name="credit_amt")
	private int creditAmt;

	/** The description. */
	private String description;

	/** The name. */
	private String name;
	
	//bi-directional many-to-one association to JpJob
	/** The jp jobs. */
	@OneToMany(mappedBy="jpJobType")
	private List<JpJob> jpJobs;


	/** The jp addons. */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "jp_type_addon_xref",joinColumns = { 
			@JoinColumn(name = "job_type_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "addon_id", 
					nullable = false, updatable = false) })
	private Set<JpAddon> jpAddons = new TreeSet<JpAddon>();

	/**
	 * Gets the job type id.
	 *
	 * @return the job type id
	 */
	public int getJobTypeId() {
		return this.jobTypeId;
	}

	/**
	 * Sets the job type id.
	 *
	 * @param jobTypeId the new job type id
	 */
	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	/**
	 * Gets the credit amt.
	 *
	 * @return the credit amt
	 */
	public int getCreditAmt() {
		return this.creditAmt;
	}

	/**
	 * Sets the credit amt.
	 *
	 * @param creditAmt the new credit amt
	 */
	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
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
	 * Gets the jp jobs.
	 *
	 * @return the jp jobs
	 */
	public List<JpJob> getJpJobs() {
		return this.jpJobs;
	}

	/**
	 * Sets the jp jobs.
	 *
	 * @param jpJobs the new jp jobs
	 */
	public void setJpJobs(List<JpJob> jpJobs) {
		this.jpJobs = jpJobs;
	}


	/**
	 * Gets the jp addons.
	 *
	 * @return the jp addons
	 */
	public Set<JpAddon> getJpAddons() {
		return jpAddons;
	}

	/**
	 * Sets the jp addons.
	 *
	 * @param jpAddons the new jp addons
	 */
	public void setJpAddons(Set<JpAddon> jpAddons) {
		this.jpAddons = jpAddons;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}