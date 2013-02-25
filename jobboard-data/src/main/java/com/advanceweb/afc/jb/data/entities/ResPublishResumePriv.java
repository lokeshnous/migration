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
 * The persistent class for the res_publish_resume_priv database table.
 * 
 */
@Entity
@Table(name="res_publish_resume_priv")
public class ResPublishResumePriv implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The publish resume priv id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publish_resume_priv_id")
	private int publishResumePrivId;

	/** The active. */
	private int active;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The create user id. */
	@Column(name="create_user_id")
	private int createUserId;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The delete user id. */
	@Column(name="delete_user_id")
	private int deleteUserId;

	//bi-directional many-to-one association to ResPublishResume
	/** The res publish resume. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publish_resume_id")
	private ResPublishResume resPublishResume;

	//bi-directional many-to-one association to ResPrivacy
	/** The res privacy. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="privacy_id")
	private ResPrivacy resPrivacy;

	/**
	 * Gets the publish resume priv id.
	 *
	 * @return the publish resume priv id
	 */
	public int getPublishResumePrivId() {
		return this.publishResumePrivId;
	}

	/**
	 * Sets the publish resume priv id.
	 *
	 * @param publishResumePrivId the new publish resume priv id
	 */
	public void setPublishResumePrivId(int publishResumePrivId) {
		this.publishResumePrivId = publishResumePrivId;
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
	 * Gets the creates the user id.
	 *
	 * @return the creates the user id
	 */
	public int getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Sets the creates the user id.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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
	 * Gets the delete user id.
	 *
	 * @return the delete user id
	 */
	public int getDeleteUserId() {
		return this.deleteUserId;
	}

	/**
	 * Sets the delete user id.
	 *
	 * @param deleteUserId the new delete user id
	 */
	public void setDeleteUserId(int deleteUserId) {
		this.deleteUserId = deleteUserId;
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
	
	/**
	 * Gets the res privacy.
	 *
	 * @return the res privacy
	 */
	public ResPrivacy getResPrivacy() {
		return this.resPrivacy;
	}

	/**
	 * Sets the res privacy.
	 *
	 * @param resPrivacy the new res privacy
	 */
	public void setResPrivacy(ResPrivacy resPrivacy) {
		this.resPrivacy = resPrivacy;
	}
	
}