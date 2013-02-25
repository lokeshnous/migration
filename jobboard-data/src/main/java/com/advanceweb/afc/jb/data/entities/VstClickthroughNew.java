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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the vst_clickthrough_new database table.
 * 
 */
@Entity
@Table(name = "vst_clickthrough_new")
public class VstClickthroughNew implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clickthrough new pk. */
	@EmbeddedId
	private VstClickthroughNewPK clickthroughNewPK;

	/** The clickthrough id. */
	@Column(name = "clickthrough_id")
	private int clickthroughId;

	/** The click count. */
	@Column(name = "clickcount")
	private int clickCount;

	/**
	 * Gets the clickthrough id.
	 *
	 * @return the clickthrough id
	 */
	public int getClickthroughId() {
		return clickthroughId;
	}

	/**
	 * Sets the clickthrough id.
	 *
	 * @param clickthroughId the new clickthrough id
	 */
	public void setClickthroughId(int clickthroughId) {
		this.clickthroughId = clickthroughId;
	}

	/**
	 * Gets the clickthrough new pk.
	 *
	 * @return the clickthrough new pk
	 */
	public VstClickthroughNewPK getClickthroughNewPK() {
		return clickthroughNewPK;
	}

	/**
	 * Sets the clickthrough new pk.
	 *
	 * @param clickthroughNewPK the new clickthrough new pk
	 */
	public void setClickthroughNewPK(VstClickthroughNewPK clickthroughNewPK) {
		this.clickthroughNewPK = clickthroughNewPK;
	}

	/**
	 * Gets the click count.
	 *
	 * @return the click count
	 */
	public int getClickCount() {
		return clickCount;
	}

	/**
	 * Sets the click count.
	 *
	 * @param clickCount the new click count
	 */
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

}