/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jp_job_stats database table.
 * 
 */
@Entity
@Table(name="jp_job_stats")
public class JpJobStat implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_id")
	private int jobId;

	/** The applies. */
	private int applies;

	/** The clicks. */
	private int clicks;

    /** The stats dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="stats_dt")
	private Date statsDt;

	/** The views. */
	private int views;

	//bi-directional one-to-one association to JpJob
	/** The jp job. */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public int getJobId() {
		return this.jobId;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the applies.
	 *
	 * @return the applies
	 */
	public int getApplies() {
		return this.applies;
	}

	/**
	 * Sets the applies.
	 *
	 * @param applies the new applies
	 */
	public void setApplies(int applies) {
		this.applies = applies;
	}

	/**
	 * Gets the clicks.
	 *
	 * @return the clicks
	 */
	public int getClicks() {
		return this.clicks;
	}

	/**
	 * Sets the clicks.
	 *
	 * @param clicks the new clicks
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	/**
	 * Gets the stats dt.
	 *
	 * @return the stats dt
	 */
	public Date getStatsDt() {
		return this.statsDt;
	}

	/**
	 * Sets the stats dt.
	 *
	 * @param statsDt the new stats dt
	 */
	public void setStatsDt(Date statsDt) {
		this.statsDt = statsDt;
	}

	/**
	 * Gets the views.
	 *
	 * @return the views
	 */
	public int getViews() {
		return this.views;
	}

	/**
	 * Sets the views.
	 *
	 * @param views the new views
	 */
	public void setViews(int views) {
		this.views = views;
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
	
}