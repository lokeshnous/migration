/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the res_resume_attrib_list database table.
 * 
 */
@Entity
@Table(name="res_resume_attrib_list")
public class ResResumeAttribList implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The resume attrib list id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resume_attrib_list_id")
	private int resumeAttribListId;

	/** The active. */
	private int active;

	/** The list value. */
	@Column(name="list_value")
	private String listValue;

	/** The position. */
	private int position;

	//bi-directional many-to-one association to ResResumeAttrib
	/** The res resume attrib. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resume_attrib_id")
	private ResResumeAttrib resResumeAttrib;

	/**
	 * Gets the resume attrib list id.
	 *
	 * @return the resume attrib list id
	 */
	public int getResumeAttribListId() {
		return this.resumeAttribListId;
	}

	/**
	 * Sets the resume attrib list id.
	 *
	 * @param resumeAttribListId the new resume attrib list id
	 */
	public void setResumeAttribListId(int resumeAttribListId) {
		this.resumeAttribListId = resumeAttribListId;
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
	 * Gets the list value.
	 *
	 * @return the list value
	 */
	public String getListValue() {
		return this.listValue;
	}

	/**
	 * Sets the list value.
	 *
	 * @param listValue the new list value
	 */
	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
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