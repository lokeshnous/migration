/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the jp_source database table.
 * 
 */
@Entity
@Table(name="jp_source")
public class JpSource implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The source id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="source_id")
	private int sourceId;

	/** The active. */
	private byte active;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to JpJob
	/** The jp jobs. */
	@OneToMany(mappedBy="jpSource")
	private List<JpJob> jpJobs;

	//bi-directional many-to-one association to JpJobTemp
	/** The jp job temps. */
	@OneToMany(mappedBy="jpSource")
	private List<JpJobTemp> jpJobTemps;

	/**
	 * Gets the source id.
	 *
	 * @return the source id
	 */
	public int getSourceId() {
		return this.sourceId;
	}

	/**
	 * Sets the source id.
	 *
	 * @param sourceId the new source id
	 */
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public byte getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(byte active) {
		this.active = active;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
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
	 * Gets the jp job temps.
	 *
	 * @return the jp job temps
	 */
	public List<JpJobTemp> getJpJobTemps() {
		return this.jpJobTemps;
	}

	/**
	 * Sets the jp job temps.
	 *
	 * @param jpJobTemps the new jp job temps
	 */
	public void setJpJobTemps(List<JpJobTemp> jpJobTemps) {
		this.jpJobTemps = jpJobTemps;
	}
	
}