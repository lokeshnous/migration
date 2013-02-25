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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the vst_session_event_info database table.
 * 
 */
@Entity
@Table(name="vst_session_event_info")
public class VstSessionEventInfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event info pk. */
	@EmbeddedId
	private VstSessionEventInfoPK eventInfoPK;

	/** The info value. */
	@Column(name="info_value")
	private String infoValue;

	//bi-directional many-to-one association to VstSessionEvent
	/** The vst session event. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="session_event_id", insertable=false, updatable=false)
	private VstSessionEvent vstSessionEvent;

	//bi-directional many-to-one association to VstInfoType
	/** The vst info type. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="info_type_id", insertable=false, updatable=false)
	private VstInfoType vstInfoType;

	/**
	 * Gets the info value.
	 *
	 * @return the info value
	 */
	public String getInfoValue() {
		return this.infoValue;
	}

	/**
	 * Sets the info value.
	 *
	 * @param infoValue the new info value
	 */
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	/**
	 * Gets the vst session event.
	 *
	 * @return the vst session event
	 */
	public VstSessionEvent getVstSessionEvent() {
		return this.vstSessionEvent;
	}

	/**
	 * Sets the vst session event.
	 *
	 * @param vstSessionEvent the new vst session event
	 */
	public void setVstSessionEvent(VstSessionEvent vstSessionEvent) {
		this.vstSessionEvent = vstSessionEvent;
	}
	
	/**
	 * Gets the vst info type.
	 *
	 * @return the vst info type
	 */
	public VstInfoType getVstInfoType() {
		return this.vstInfoType;
	}

	/**
	 * Sets the vst info type.
	 *
	 * @param vstInfoType the new vst info type
	 */
	public void setVstInfoType(VstInfoType vstInfoType) {
		this.vstInfoType = vstInfoType;
	}

	/**
	 * @return the eventInfoPK
	 */
	public VstSessionEventInfoPK getEventInfoPK() {
		return eventInfoPK;
	}

	/**
	 * @param eventInfoPK the eventInfoPK to set
	 */
	public void setEventInfoPK(VstSessionEventInfoPK eventInfoPK) {
		this.eventInfoPK = eventInfoPK;
	}
	
}