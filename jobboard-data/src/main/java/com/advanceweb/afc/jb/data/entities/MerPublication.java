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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name = "mer_publication")
public class MerPublication implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The publication id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "publication_id")
	private int publicationId;

	/** The publication code. */
	@Column(name = "publication_code")
	private String publicationCode;

	/** The publication name. */
	@Column(name = "publication_name")
	private String publicationName;

	/** The publicationdesc. */
	@Column(name = "publication_description")
	private String publicationdesc;

	/** The is print. */
	@Column(name = "is_print")
	private int isPrint;

	/** The is digital. */
	@Column(name = "is_digital")
	private int isDigital;

	/** The is enewsletter. */
	@Column(name = "is_enewsletter")
	private int isEnewsletter;

	/** The is regional. */
	@Column(name = "is_regional")
	private int isRegional;  
	
	/** The is email. */
	@Column(name = "is_email")
	private int isEmail;

	/** The active. */
	@Column(name = "active")
	private int active;

	/** The create dt. */
	@Column(name = "create_dt")
	private Timestamp createDt;

	/**
	 * Gets the publication id.
	 *
	 * @return the publication id
	 */
	public int getPublicationId() {
		return publicationId;
	}

	/**
	 * Sets the publication id.
	 *
	 * @param publicationId the new publication id
	 */
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}

	/**
	 * Gets the publication code.
	 *
	 * @return the publication code
	 */
	public String getPublicationCode() {
		return publicationCode;
	}

	/**
	 * Sets the publication code.
	 *
	 * @param publicationCode the new publication code
	 */
	public void setPublicationCode(String publicationCode) {
		this.publicationCode = publicationCode;
	}

	/**
	 * Gets the publication name.
	 *
	 * @return the publication name
	 */
	public String getPublicationName() {
		return publicationName;
	}

	/**
	 * Sets the publication name.
	 *
	 * @param publicationName the new publication name
	 */
	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	/**
	 * Gets the publicationdesc.
	 *
	 * @return the publicationdesc
	 */
	public String getPublicationdesc() {
		return publicationdesc;
	}

	/**
	 * Sets the publicationdesc.
	 *
	 * @param publicationdesc the new publicationdesc
	 */
	public void setPublicationdesc(String publicationdesc) {
		this.publicationdesc = publicationdesc;
	}

	/**
	 * Gets the checks if is print.
	 *
	 * @return the checks if is print
	 */
	public int getIsPrint() {
		return isPrint;
	}

	/**
	 * Sets the checks if is print.
	 *
	 * @param isPrint the new checks if is print
	 */
	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}

	/**
	 * Gets the checks if is digital.
	 *
	 * @return the checks if is digital
	 */
	public int getIsDigital() {
		return isDigital;
	}

	/**
	 * Sets the checks if is digital.
	 *
	 * @param isDigital the new checks if is digital
	 */
	public void setIsDigital(int isDigital) {
		this.isDigital = isDigital;
	}

	/**
	 * Gets the checks if is enewsletter.
	 *
	 * @return the checks if is enewsletter
	 */
	public int getIsEnewsletter() {
		return isEnewsletter;
	}

	/**
	 * Sets the checks if is enewsletter.
	 *
	 * @param isEnewsletter the new checks if is enewsletter
	 */
	public void setIsEnewsletter(int isEnewsletter) {
		this.isEnewsletter = isEnewsletter;
	}

	/**
	 * Gets the checks if is regional.
	 *
	 * @return the checks if is regional
	 */
	public int getIsRegional() {
		return isRegional;
	}

	/**
	 * Sets the checks if is regional.
	 *
	 * @param isRegional the new checks if is regional
	 */
	public void setIsRegional(int isRegional) {
		this.isRegional = isRegional;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public int getActive() {
		return active;
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
		return createDt;
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the checks if is email.
	 *
	 * @return the checks if is email
	 */
	public int getIsEmail() {
		return isEmail;
	}

	/**
	 * Sets the checks if is email.
	 *
	 * @param isEmail the new checks if is email
	 */
	public void setIsEmail(int isEmail) {
		this.isEmail = isEmail;
	}

}
