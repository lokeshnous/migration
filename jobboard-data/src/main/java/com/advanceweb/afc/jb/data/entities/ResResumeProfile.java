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
 * The persistent class for the res_resume_profile database table.
 * 
 */
@Entity
@Table(name="res_resume_profile")
public class ResResumeProfile implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The resume profile id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_profile_id")
	private int resumeProfileId;

	/** The attrib value. */
	@Column(name="attrib_value")
	private String attribValue;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The resume id. */
	@Column(name="resume_id")
	private int resumeId;

	/** The resume type. */
	@Column(name="resume_type")
	private String resumeType;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	//bi-directional many-to-one association to ResResumeAttrib
	/** The res resume attrib. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resume_attrib_id")
	private ResResumeAttrib resResumeAttrib;

	/**
	 * Gets the resume profile id.
	 *
	 * @return the resume profile id
	 */
	public int getResumeProfileId() {
		return this.resumeProfileId;
	}

	/**
	 * Sets the resume profile id.
	 *
	 * @param resumeProfileId the new resume profile id
	 */
	public void setResumeProfileId(int resumeProfileId) {
		this.resumeProfileId = resumeProfileId;
	}

	/**
	 * Gets the attrib value.
	 *
	 * @return the attrib value
	 */
	public String getAttribValue() {
		return this.attribValue;
	}

	/**
	 * Sets the attrib value.
	 *
	 * @param attribValue the new attrib value
	 */
	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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
	 * Gets the resume id.
	 *
	 * @return the resume id
	 */
	public int getResumeId() {
		return this.resumeId;
	}

	/**
	 * Sets the resume id.
	 *
	 * @param resumeId the new resume id
	 */
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	/**
	 * Gets the resume type.
	 *
	 * @return the resume type
	 */
	public String getResumeType() {
		return this.resumeType;
	}

	/**
	 * Sets the resume type.
	 *
	 * @param resumeType the new resume type
	 */
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}

	/**
	 * Gets the update dt.
	 *
	 * @return the update dt
	 */
	public Date getUpdateDt() {
		return this.updateDt;
	}

	/**
	 * Sets the update dt.
	 *
	 * @param updateDt the new update dt
	 */
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	/**
	 * Gets the res resume attrib.
	 *
	 * @return the res resume attrib
	 */
	public ResResumeAttrib getResResumeAttrib() {
		return this.resResumeAttrib;
	}

	/**
	 * Sets the res resume attrib.
	 *
	 * @param resResumeAttrib the new res resume attrib
	 */
	public void setResResumeAttrib(ResResumeAttrib resResumeAttrib) {
		this.resResumeAttrib = resResumeAttrib;
	}
	
}