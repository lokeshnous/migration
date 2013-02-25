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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the res_coverletter database table.
 * 
 */
@Entity
@Table(name="res_coverletter")
public class ResCoverletter implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The coverletter id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="coverletter_id")
	private int coverletterId;

	/** The active. */
	private int active;

    /** The coverletter text. */
    @Lob()
	@Column(name="coverletter_text")
	private String coverletterText;

    /** The create dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="create_dt")
	private Date createDt;

    /** The delete dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="delete_dt")
	private Date deleteDt;

	/** The name. */
	private String name;

    /** The update dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="update_dt")
	private Date updateDt;

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	//bi-directional many-to-one association to ResCoverletterPriv
	/** The res coverletter privs. */
	@OneToMany(mappedBy="resCoverletter")
	private List<ResCoverletterPriv> resCoverletterPrivs;

	/**
	 * Gets the coverletter id.
	 *
	 * @return the coverletter id
	 */
	public int getCoverletterId() {
		return this.coverletterId;
	}

	/**
	 * Sets the coverletter id.
	 *
	 * @param coverletterId the new coverletter id
	 */
	public void setCoverletterId(int coverletterId) {
		this.coverletterId = coverletterId;
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
	 * Gets the coverletter text.
	 *
	 * @return the coverletter text
	 */
	public String getCoverletterText() {
		return this.coverletterText;
	}

	/**
	 * Sets the coverletter text.
	 *
	 * @param coverletterText the new coverletter text
	 */
	public void setCoverletterText(String coverletterText) {
		this.coverletterText = coverletterText;
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
	 * Gets the res coverletter privs.
	 *
	 * @return the res coverletter privs
	 */
	public List<ResCoverletterPriv> getResCoverletterPrivs() {
		return this.resCoverletterPrivs;
	}

	/**
	 * Sets the res coverletter privs.
	 *
	 * @param resCoverletterPrivs the new res coverletter privs
	 */
	public void setResCoverletterPrivs(List<ResCoverletterPriv> resCoverletterPrivs) {
		this.resCoverletterPrivs = resCoverletterPrivs;
	}
	
}