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
 * The persistent class for the res_publish_resume_stats database table.
 * 
 */
@Entity
@Table(name="res_publish_resume_stats")
public class ResPublishResumeStat implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The publish resume id. */
	@Id
	@Column(name="publish_resume_id")
	private int publishResumeId;

	/** The employer impressions. */
	@Column(name="employer_impressions")
	private int employerImpressions;

	/** The employer views. */
	@Column(name="employer_views")
	private int employerViews;

    /** The stats dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="stats_dt")
	private Date statsDt;

	//bi-directional one-to-one association to ResPublishResume
	/** The res publish resume. */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

	/**
	 * Gets the publish resume id.
	 *
	 * @return the publish resume id
	 */
	public int getPublishResumeId() {
		return this.publishResumeId;
	}

	/**
	 * Sets the publish resume id.
	 *
	 * @param publishResumeId the new publish resume id
	 */
	public void setPublishResumeId(int publishResumeId) {
		this.publishResumeId = publishResumeId;
	}

	/**
	 * Gets the employer impressions.
	 *
	 * @return the employer impressions
	 */
	public int getEmployerImpressions() {
		return this.employerImpressions;
	}

	/**
	 * Sets the employer impressions.
	 *
	 * @param employerImpressions the new employer impressions
	 */
	public void setEmployerImpressions(int employerImpressions) {
		this.employerImpressions = employerImpressions;
	}

	/**
	 * Gets the employer views.
	 *
	 * @return the employer views
	 */
	public int getEmployerViews() {
		return this.employerViews;
	}

	/**
	 * Sets the employer views.
	 *
	 * @param employerViews the new employer views
	 */
	public void setEmployerViews(int employerViews) {
		this.employerViews = employerViews;
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
	 * Gets the res publish resume.
	 *
	 * @return the res publish resume
	 */
	public ResPublishResume getResPublishResume() {
		return this.resPublishResume;
	}

	/**
	 * Sets the res publish resume.
	 *
	 * @param resPublishResume the new res publish resume
	 */
	public void setResPublishResume(ResPublishResume resPublishResume) {
		this.resPublishResume = resPublishResume;
	}
	
}