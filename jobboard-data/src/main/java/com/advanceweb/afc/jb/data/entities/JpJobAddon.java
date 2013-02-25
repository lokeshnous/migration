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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jp_job_addon database table.
 * 
 */
@Entity
@Table(name="jp_job_addon")
public class JpJobAddon implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job addon pk. */
	@EmbeddedId
	private JpJobAddonPK jobAddonPK;

	/** The active. */
	@Column(name="active")
	private byte active;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	//bi-directional many-to-one association to JpJob
	/** The jp job. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id", insertable=false, updatable=false) 
	private JpJob jpJob;

	//bi-directional many-to-one association to JpAddon
	/** The jp addon. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addon_id", insertable=false, updatable=false)
	private JpAddon jpAddon;

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
	 * Gets the delete dt.
	 *
	 * @return the delete dt
	 */
	public Date getDeleteDt() {
		return this.deleteDt;
	}

	/**
	 * Sets the delete dt.
	 *
	 * @param deleteDt the new delete dt
	 */
	public void setDeleteDt(Date deleteDt) {
		this.deleteDt = deleteDt;
	}

	/**
	 * Gets the jp job.
	 *
	 * @return the jp job
	 */
	public JpJob getJpJob() {
		return this.jpJob;
	}

	/**
	 * Sets the jp job.
	 *
	 * @param jpJob the new jp job
	 */
	public void setJpJob(JpJob jpJob) {
		this.jpJob = jpJob;
	}
	
	/**
	 * Gets the jp addon.
	 *
	 * @return the jp addon
	 */
	public JpAddon getJpAddon() {
		return this.jpAddon;
	}

	/**
	 * Sets the jp addon.
	 *
	 * @param jpAddon the new jp addon
	 */
	public void setJpAddon(JpAddon jpAddon) {
		this.jpAddon = jpAddon;
	}

	/**
	 * @return the jobAddonPK
	 */
	public JpJobAddonPK getJobAddonPK() {
		return jobAddonPK;
	}

	/**
	 * @param jobAddonPK the jobAddonPK to set
	 */
	public void setJobAddonPK(JpJobAddonPK jobAddonPK) {
		this.jobAddonPK = jobAddonPK;
	}
	
}