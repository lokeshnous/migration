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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the mer_user database table.
 * 
 */
@Entity
@Table(name = "mer_user")
public class MerUser implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	/** The create dt. */
	@Column(name = "create_dt")
	private Timestamp createDt;

	/** The delete dt. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_dt")
	private Date deleteDt;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The first name. */
	@Column(name = "first_name")
	private String firstName;

	/** The last name. */
	@Column(name = "last_name")
	private String lastName;

	/** The middle name. */
	@Column(name = "middle_name")
	private String middleName;

	/** The password. */
	private String password;

	// bi-directional many-to-one association to MerUserApplication
	/** The mer user applications. */
	@OneToMany(mappedBy = "merUser")
	private List<MerUserApplication> merUserApplications;

	// bi-directional many-to-one association to MerUserProfile
	/** The mer user profiles. */
	@OneToMany(mappedBy = "merUser", cascade = CascadeType.ALL)
	private List<MerUserProfile> merUserProfiles;

	// bi-directional many-to-one association to VstSessioninfo
	/** The vst sessioninfos. */
	@OneToMany(mappedBy = "merUser")
	private List<VstSessioninfo> vstSessioninfos;

	// bi-directional many-to-one association to JpTemplate
	// @OneToMany(mappedBy="merUser")
	// private List<JpTemplate> jpTemplates;

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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the new middle name
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the mer user applications.
	 *
	 * @return the mer user applications
	 */
	public List<MerUserApplication> getMerUserApplications() {
		return this.merUserApplications;
	}

	/**
	 * Sets the mer user applications.
	 *
	 * @param merUserApplications the new mer user applications
	 */
	public void setMerUserApplications(
			List<MerUserApplication> merUserApplications) {
		this.merUserApplications = merUserApplications;
	}

	/**
	 * Gets the mer user profiles.
	 *
	 * @return the mer user profiles
	 */
	public List<MerUserProfile> getMerUserProfiles() {
		return this.merUserProfiles;
	}

	/**
	 * Sets the mer user profiles.
	 *
	 * @param merUserProfiles the new mer user profiles
	 */
	public void setMerUserProfiles(List<MerUserProfile> merUserProfiles) {
		this.merUserProfiles = merUserProfiles;
	}

	/**
	 * Gets the vst sessioninfos.
	 *
	 * @return the vst sessioninfos
	 */
	public List<VstSessioninfo> getVstSessioninfos() {
		return this.vstSessioninfos;
	}

	/**
	 * Sets the vst sessioninfos.
	 *
	 * @param vstSessioninfos the new vst sessioninfos
	 */
	public void setVstSessioninfos(List<VstSessioninfo> vstSessioninfos) {
		this.vstSessioninfos = vstSessioninfos;
	}

	// public List<JpTemplate> getJpTemplates() {
	// return jpTemplates;
	// }
	//
	// public void setJpTemplates(List<JpTemplate> jpTemplates) {
	// this.jpTemplates = jpTemplates;
	// }

}