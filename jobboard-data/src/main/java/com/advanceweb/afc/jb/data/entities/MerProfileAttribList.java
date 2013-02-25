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
 * The persistent class for the mer_profile_attrib_list database table.
 * 
 */
@Entity
@Table(name="mer_profile_attrib_list")
public class MerProfileAttribList implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The profile attrib list id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_attrib_list_id")
	private int profileAttribListId;

	/** The active. */
	private int active;

	/** The list value. */
	@Column(name="list_value")
	private String listValue;

	/** The position. */
	private int position;

	//bi-directional many-to-one association to MerProfileAttrib
	/** The mer profile attrib. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_attrib_id")
	private MerProfileAttrib merProfileAttrib;

	/**
	 * Gets the profile attrib list id.
	 *
	 * @return the profile attrib list id
	 */
	public int getProfileAttribListId() {
		return this.profileAttribListId;
	}

	/**
	 * Sets the profile attrib list id.
	 *
	 * @param profileAttribListId the new profile attrib list id
	 */
	public void setProfileAttribListId(int profileAttribListId) {
		this.profileAttribListId = profileAttribListId;
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
	 * Gets the mer profile attrib.
	 *
	 * @return the mer profile attrib
	 */
	public MerProfileAttrib getMerProfileAttrib() {
		return this.merProfileAttrib;
	}

	/**
	 * Sets the mer profile attrib.
	 *
	 * @param merProfileAttrib the new mer profile attrib
	 */
	public void setMerProfileAttrib(MerProfileAttrib merProfileAttrib) {
		this.merProfileAttrib = merProfileAttrib;
	}
	
}