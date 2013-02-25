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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the vst_info_type database table.
 * 
 */
@Entity
@Table(name="vst_info_type")
public class VstInfoType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The info type id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="info_type_id")
	private int infoTypeId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to VstSessionEventInfo
	/** The vst session event infos. */
	@OneToMany(mappedBy="vstInfoType")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	/**
	 * Gets the info type id.
	 *
	 * @return the info type id
	 */
	public int getInfoTypeId() {
		return this.infoTypeId;
	}

	/**
	 * Sets the info type id.
	 *
	 * @param infoTypeId the new info type id
	 */
	public void setInfoTypeId(int infoTypeId) {
		this.infoTypeId = infoTypeId;
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
	 * Gets the vst session event infos.
	 *
	 * @return the vst session event infos
	 */
	public List<VstSessionEventInfo> getVstSessionEventInfos() {
		return this.vstSessionEventInfos;
	}

	/**
	 * Sets the vst session event infos.
	 *
	 * @param vstSessionEventInfos the new vst session event infos
	 */
	public void setVstSessionEventInfos(List<VstSessionEventInfo> vstSessionEventInfos) {
		this.vstSessionEventInfos = vstSessionEventInfos;
	}
	
}