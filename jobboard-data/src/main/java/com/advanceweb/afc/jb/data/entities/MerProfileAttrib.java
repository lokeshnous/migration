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
 * The persistent class for the mer_profile_attrib database table.
 * 
 */
@Entity
@Table(name="mer_profile_attrib")
public class MerProfileAttrib implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The profile attrib id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_attrib_id")
	private int profileAttribId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The description. */
	private String description;

	/** The form type. */
	@Column(name="form_type")
	private String formType;

	/** The name. */
	private String name;
	

	//bi-directional many-to-one association to MerProfileAttribList
	/** The mer profile attrib lists. */
	@OneToMany(mappedBy="merProfileAttrib")
	private List<MerProfileAttribList> merProfileAttribLists;

	//bi-directional many-to-one association to MerUserProfile
	/** The mer user profiles. */
	@OneToMany(mappedBy="merProfileAttrib")
	private List<MerUserProfile> merUserProfiles;

	/**
	 * Gets the profile attrib id.
	 *
	 * @return the profile attrib id
	 */
	public int getProfileAttribId() {
		return this.profileAttribId;
	}

	/**
	 * Sets the profile attrib id.
	 *
	 * @param profileAttribId the new profile attrib id
	 */
	public void setProfileAttribId(int profileAttribId) {
		this.profileAttribId = profileAttribId;
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
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the form type.
	 *
	 * @return the form type
	 */
	public String getFormType() {
		return this.formType;
	}

	/**
	 * Sets the form type.
	 *
	 * @param formType the new form type
	 */
	public void setFormType(String formType) {
		this.formType = formType;
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
	 * Gets the mer profile attrib lists.
	 *
	 * @return the mer profile attrib lists
	 */
	public List<MerProfileAttribList> getMerProfileAttribLists() {
		return this.merProfileAttribLists;
	}

	/**
	 * Sets the mer profile attrib lists.
	 *
	 * @param merProfileAttribLists the new mer profile attrib lists
	 */
	public void setMerProfileAttribLists(List<MerProfileAttribList> merProfileAttribLists) {
		this.merProfileAttribLists = merProfileAttribLists;
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
	
}