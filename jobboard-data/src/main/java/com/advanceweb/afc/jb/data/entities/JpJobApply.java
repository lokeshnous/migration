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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_apply database table.
 * 
 */
@Entity
@Table(name="jp_job_apply")
public class JpJobApply implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The job apply id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_apply_id")
	private int jobApplyId;

	/** The active. */
	private int active;

	/** The apply link. */
	@Column(name="apply_link")
	private String applyLink;

	/** The apply method. */
	@Column(name="apply_method")
	private String applyMethod;

	//bi-directional many-to-one association to JpJob
	/** The jp job. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	/**
	 * Gets the job apply id.
	 *
	 * @return the job apply id
	 */
	public int getJobApplyId() {
		return this.jobApplyId;
	}

	/**
	 * Sets the job apply id.
	 *
	 * @param jobApplyId the new job apply id
	 */
	public void setJobApplyId(int jobApplyId) {
		this.jobApplyId = jobApplyId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * Gets the apply link.
	 *
	 * @return the apply link
	 */
	public String getApplyLink() {
		return this.applyLink;
	}

	/**
	 * Sets the apply link.
	 *
	 * @param applyLink the new apply link
	 */
	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}

	/**
	 * Gets the apply method.
	 *
	 * @return the apply method
	 */
	public String getApplyMethod() {
		return this.applyMethod;
	}

	/**
	 * Sets the apply method.
	 *
	 * @param applyMethod the new apply method
	 */
	public void setApplyMethod(String applyMethod) {
		this.applyMethod = applyMethod;
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