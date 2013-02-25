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
 * The persistent class for the res_coverletter_priv database table.
 * 
 */
@Entity
@Table(name="res_coverletter_priv")
public class ResCoverletterPriv implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The coverletter priv id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coverletter_priv_id")
	private int coverletterPrivId;

	/** The active. */
	private int active;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

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

	//bi-directional many-to-one association to ResPrivacy
	/** The res privacy. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="privacy_id")
	private ResPrivacy resPrivacy;

	//bi-directional many-to-one association to ResCoverletter
	/** The res coverletter. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coverletter_id")
	private ResCoverletter resCoverletter;

	/**
	 * Gets the coverletter priv id.
	 *
	 * @return the coverletter priv id
	 */
	public int getCoverletterPrivId() {
		return this.coverletterPrivId;
	}

	/**
	 * Sets the coverletter priv id.
	 *
	 * @param coverletterPrivId the new coverletter priv id
	 */
	public void setCoverletterPrivId(int coverletterPrivId) {
		this.coverletterPrivId = coverletterPrivId;
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
	
	/**
	 * Gets the res coverletter.
	 *
	 * @return the res coverletter
	 */
	public ResCoverletter getResCoverletter() {
		return this.resCoverletter;
	}

	/**
	 * Sets the res coverletter.
	 *
	 * @param resCoverletter the new res coverletter
	 */
	public void setResCoverletter(ResCoverletter resCoverletter) {
		this.resCoverletter = resCoverletter;
	}
	
}