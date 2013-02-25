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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the vst_clickthrough_type database table.
 * 
 */
@Entity
@Table(name = "vst_clickthrough_type")
public class VstClickthroughType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clickthrough type id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clickthrough_type_id")
	private int clickthroughTypeId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	/**
	 * Gets the clickthrough type id.
	 *
	 * @return the clickthrough type id
	 */
	public int getClickthroughTypeId() {
		return this.clickthroughTypeId;
	}

	/**
	 * Sets the clickthrough type id.
	 *
	 * @param clickthroughTypeId the new clickthrough type id
	 */
	public void setClickthroughTypeId(int clickthroughTypeId) {
		this.clickthroughTypeId = clickthroughTypeId;
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

}