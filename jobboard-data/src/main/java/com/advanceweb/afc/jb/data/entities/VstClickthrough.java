/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the vst_clickthrough database table.
 * 
 */
@Entity
@Table(name="vst_clickthrough")
public class VstClickthrough implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The clickthrough id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="clickthrough_id")
	private int clickthroughId;

    /** The clickthrough dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="clickthrough_dt")
	private Date clickthroughDt;

	/** The key id. */
	@Column(name="key_id")
	private String keyId;

	/** The key type. */
	@Column(name="key_type")
	private int keyType;

	//bi-directional many-to-one association to VstClickthroughType
	/** The vst clickthrough type. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clickthrough_type_id")
	private VstClickthroughType vstClickthroughType;

	//bi-directional many-to-one association to VstSessioninfo
	/** The vst sessioninfo. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstSearch
	/** The vst search. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_id")
	private VstSearch vstSearch;

	/**
	 * Gets the clickthrough id.
	 *
	 * @return the clickthrough id
	 */
	public int getClickthroughId() {
		return this.clickthroughId;
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
	 * Gets the clickthrough dt.
	 *
	 * @return the clickthrough dt
	 */
	public Date getClickthroughDt() {
		return this.clickthroughDt;
	}

	/**
	 * Sets the clickthrough dt.
	 *
	 * @param clickthroughDt the new clickthrough dt
	 */
	public void setClickthroughDt(Date clickthroughDt) {
		this.clickthroughDt = clickthroughDt;
	}

	/**
	 * Gets the key id.
	 *
	 * @return the key id
	 */
	public String getKeyId() {
		return this.keyId;
	}

	/**
	 * Sets the key id.
	 *
	 * @param keyId the new key id
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * Gets the key type.
	 *
	 * @return the key type
	 */
	public int getKeyType() {
		return this.keyType;
	}

	/**
	 * Sets the key type.
	 *
	 * @param keyType the new key type
	 */
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	/**
	 * Gets the vst clickthrough type.
	 *
	 * @return the vst clickthrough type
	 */
	public VstClickthroughType getVstClickthroughType() {
		return this.vstClickthroughType;
	}

	/**
	 * Sets the vst clickthrough type.
	 *
	 * @param vstClickthroughType the new vst clickthrough type
	 */
	public void setVstClickthroughType(VstClickthroughType vstClickthroughType) {
		this.vstClickthroughType = vstClickthroughType;
	}
	
	/**
	 * Gets the vst sessioninfo.
	 *
	 * @return the vst sessioninfo
	 */
	public VstSessioninfo getVstSessioninfo() {
		return this.vstSessioninfo;
	}

	/**
	 * Sets the vst sessioninfo.
	 *
	 * @param vstSessioninfo the new vst sessioninfo
	 */
	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}
	
	/**
	 * Gets the vst search.
	 *
	 * @return the vst search
	 */
	public VstSearch getVstSearch() {
		return this.vstSearch;
	}

	/**
	 * Sets the vst search.
	 *
	 * @param vstSearch the new vst search
	 */
	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}
	
}