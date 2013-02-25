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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the res_privacy database table.
 * 
 */
@Entity
@Table(name="res_privacy")
public class ResPrivacy implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The privacy id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="privacy_id")
	private int privacyId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The description. */
	private int description;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to ResCoverletterPriv
	/** The res coverletter privs. */
	@OneToMany(mappedBy="resPrivacy")
	private List<ResCoverletterPriv> resCoverletterPrivs;

	//bi-directional many-to-one association to ResPublishResumePriv
	/** The res publish resume privs. */
	@OneToMany(mappedBy="resPrivacy")
	private List<ResPublishResumePriv> resPublishResumePrivs;

	/**
	 * Gets the privacy id.
	 *
	 * @return the privacy id
	 */
	public int getPrivacyId() {
		return this.privacyId;
	}

	/**
	 * Sets the privacy id.
	 *
	 * @param privacyId the new privacy id
	 */
	public void setPrivacyId(int privacyId) {
		this.privacyId = privacyId;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public int getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(int description) {
		this.description = description;
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
	
	/**
	 * Gets the res publish resume privs.
	 *
	 * @return the res publish resume privs
	 */
	public List<ResPublishResumePriv> getResPublishResumePrivs() {
		return this.resPublishResumePrivs;
	}

	/**
	 * Sets the res publish resume privs.
	 *
	 * @param resPublishResumePrivs the new res publish resume privs
	 */
	public void setResPublishResumePrivs(List<ResPublishResumePriv> resPublishResumePrivs) {
		this.resPublishResumePrivs = resPublishResumePrivs;
	}
	
}