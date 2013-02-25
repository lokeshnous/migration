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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the res_resume_attrib database table.
 * 
 */
@Entity
@Table(name="res_resume_attrib")
public class ResResumeAttrib implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The resume attrib id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_attrib_id")
	private int resumeAttribId;

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

	//bi-directional many-to-one association to ResResumeAttribList
	/** The res resume attrib lists. */
	@OneToMany(mappedBy="resResumeAttrib",fetch=FetchType.EAGER)
	private List<ResResumeAttribList> resResumeAttribLists;

	//bi-directional many-to-one association to ResResumeProfile
	/** The res resume profiles. */
	@OneToMany(mappedBy="resResumeAttrib")
	private List<ResResumeProfile> resResumeProfiles;

	/**
	 * Gets the resume attrib id.
	 *
	 * @return the resume attrib id
	 */
	public int getResumeAttribId() {
		return this.resumeAttribId;
	}

	/**
	 * Sets the resume attrib id.
	 *
	 * @param resumeAttribId the new resume attrib id
	 */
	public void setResumeAttribId(int resumeAttribId) {
		this.resumeAttribId = resumeAttribId;
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
	 * Gets the res resume attrib lists.
	 *
	 * @return the res resume attrib lists
	 */
	public List<ResResumeAttribList> getResResumeAttribLists() {
		return this.resResumeAttribLists;
	}

	/**
	 * Sets the res resume attrib lists.
	 *
	 * @param resResumeAttribLists the new res resume attrib lists
	 */
	public void setResResumeAttribLists(List<ResResumeAttribList> resResumeAttribLists) {
		this.resResumeAttribLists = resResumeAttribLists;
	}
	
	/**
	 * Gets the res resume profiles.
	 *
	 * @return the res resume profiles
	 */
	public List<ResResumeProfile> getResResumeProfiles() {
		return this.resResumeProfiles;
	}

	/**
	 * Sets the res resume profiles.
	 *
	 * @param resResumeProfiles the new res resume profiles
	 */
	public void setResResumeProfiles(List<ResResumeProfile> resResumeProfiles) {
		this.resResumeProfiles = resResumeProfiles;
	}
	
}