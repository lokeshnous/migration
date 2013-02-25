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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the adm_save_job database table.
 * 
 */
@Entity
@Table(name="adm_save_job")
public class AdmSaveJob implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The save job id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="save_job_id")
	private int saveJobId;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

    /** The applied dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="applied_dt")
	private Date appliedDt;
    
	/** The facility name. */
	@Column(name="facility_name")
	private String facilityName;

	/** The jobtitle. */
	private String jobtitle;

	/** The save type. */
	@Column(name="save_type")
	private String saveType;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to JpJob
	/** The jp job. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_id")
	private JpJob jpJob;

	/**
	 * Gets the save job id.
	 *
	 * @return the save job id
	 */
	public int getSaveJobId() {
		return this.saveJobId;
	}

	/**
	 * Sets the save job id.
	 *
	 * @param saveJobId the new save job id
	 */
	public void setSaveJobId(int saveJobId) {
		this.saveJobId = saveJobId;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Date getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Date createDt) {
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
	 * Gets the facility name.
	 *
	 * @return the facility name
	 */
	public String getFacilityName() {
		return this.facilityName;
	}

	/**
	 * Sets the facility name.
	 *
	 * @param facilityName the new facility name
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * Gets the jobtitle.
	 *
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return this.jobtitle;
	}

	/**
	 * Sets the jobtitle.
	 *
	 * @param jobtitle the new jobtitle
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	/**
	 * Gets the save type.
	 *
	 * @return the save type
	 */
	public String getSaveType() {
		return this.saveType;
	}

	/**
	 * Sets the save type.
	 *
	 * @param saveType the new save type
	 */
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	 * Gets the applied dt.
	 *
	 * @return the applied dt
	 */
	public Date getAppliedDt() {
		return appliedDt;
	}

	/**
	 * Sets the applied dt.
	 *
	 * @param appliedDt the new applied dt
	 */
	public void setAppliedDt(Date appliedDt) {
		this.appliedDt = appliedDt;
	}
	
	
}