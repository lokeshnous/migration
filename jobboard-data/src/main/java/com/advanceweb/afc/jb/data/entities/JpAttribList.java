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
 * The persistent class for the jp_attrib_list database table.
 * 
 */
@Entity
@Table(name="jp_attrib_list")
public class JpAttribList implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The attrib list id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attrib_list_id")
	private int attribListId;

	/** The attrib type. */
	@Column(name="attrib_type")
	private String attribType;

	/** The attrib value. */
	@Column(name="attrib_value")
	private String attribValue;

	/** The position. */
	@Column(name="position")
	private int position;

	/**
	 * Gets the attrib list id.
	 *
	 * @return the attrib list id
	 */
	public int getAttribListId() {
		return this.attribListId;
	}

	/**
	 * Sets the attrib list id.
	 *
	 * @param attribListId the new attrib list id
	 */
	public void setAttribListId(int attribListId) {
		this.attribListId = attribListId;
	}

	/**
	 * Gets the attrib type.
	 *
	 * @return the attrib type
	 */
	public String getAttribType() {
		return this.attribType;
	}

	/**
	 * Sets the attrib type.
	 *
	 * @param attribType the new attrib type
	 */
	public void setAttribType(String attribType) {
		this.attribType = attribType;
	}

	/**
	 * Gets the attrib value.
	 *
	 * @return the attrib value
	 */
	public String getAttribValue() {
		return this.attribValue;
	}

	/**
	 * Sets the attrib value.
	 *
	 * @param attribValue the new attrib value
	 */
	public void setAttribValue(String attribValue) {
		this.attribValue = attribValue;
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

}