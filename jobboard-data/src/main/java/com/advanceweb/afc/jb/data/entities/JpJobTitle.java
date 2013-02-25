/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the jp_job_title database table.
 * 
 */
@Entity
@Table(name="jp_job_title")
public class JpJobTitle{
	
	/** The job title id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="job_title_id")
	private int jobTitleId;
	
	/** The jobtitle. */
	@Column(name = "job_title")
	private String jobtitle;

	/**
	 * Gets the job title id.
	 *
	 * @return the job title id
	 */
	public int getJobTitleId() {
		return jobTitleId;
	}

	/**
	 * Sets the job title id.
	 *
	 * @param jobTitleId the new job title id
	 */
	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	/**
	 * Gets the jobtitle.
	 *
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}

	/**
	 * Sets the jobtitle.
	 *
	 * @param jobtitle the new jobtitle
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
}