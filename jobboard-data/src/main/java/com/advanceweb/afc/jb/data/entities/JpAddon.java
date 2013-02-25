/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the jp_addon database table.
 * 
 */
@Entity
@Table(name="jp_addon")
public class JpAddon implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The addon id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="addon_id")
	private int addonId;

	/** The credit amt. */
	@Column(name="credit_amt")
	private int creditAmt;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to JpJobAddon
	/** The jp job addons. */
	@OneToMany(mappedBy="jpAddon")
	private List<JpJobAddon> jpJobAddons;
	
	/** The jp job type. */
	@ElementCollection
	private Set<JpJobType> jpJobType = new TreeSet<JpJobType>();

	/**
	 * Gets the addon id.
	 *
	 * @return the addon id
	 */
	public int getAddonId() {
		return this.addonId;
	}

	/**
	 * Sets the addon id.
	 *
	 * @param addonId the new addon id
	 */
	public void setAddonId(int addonId) {
		this.addonId = addonId;
	}

	/**
	 * Gets the credit amt.
	 *
	 * @return the credit amt
	 */
	public int getCreditAmt() {
		return this.creditAmt;
	}

	/**
	 * Sets the credit amt.
	 *
	 * @param creditAmt the new credit amt
	 */
	public void setCreditAmt(int creditAmt) {
		this.creditAmt = creditAmt;
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

	/**
	 * Gets the jp job addons.
	 *
	 * @return the jp job addons
	 */
	public List<JpJobAddon> getJpJobAddons() {
		return this.jpJobAddons;
	}

	/**
	 * Sets the jp job addons.
	 *
	 * @param jpJobAddons the new jp job addons
	 */
	public void setJpJobAddons(List<JpJobAddon> jpJobAddons) {
		this.jpJobAddons = jpJobAddons;
	}
	
	/**
	 * Gets the jp job type.
	 *
	 * @return the jp job type
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "JpAddons")
	public Set<JpJobType> getJpJobType() {
		return jpJobType;
	}

	/**
	 * Sets the jp job type.
	 *
	 * @param jpJobType the new jp job type
	 */
	public void setJpJobType(Set<JpJobType> jpJobType) {
		this.jpJobType = jpJobType;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}