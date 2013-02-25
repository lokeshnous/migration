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
 * The persistent class for the vst_event_type database table.
 * 
 */
@Entity
@Table(name="vst_event_type")
public class VstEventType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The event type id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_type_id")
	private int eventTypeId;

	/** The description. */
	private String description;

	/** The name. */
	private String name;

	//bi-directional many-to-one association to VstSessionEvent
	/** The vst session events. */
	@OneToMany(mappedBy="vstEventType")
	private List<VstSessionEvent> vstSessionEvents;

	/**
	 * Gets the event type id.
	 *
	 * @return the event type id
	 */
	public int getEventTypeId() {
		return this.eventTypeId;
	}

	/**
	 * Sets the event type id.
	 *
	 * @param eventTypeId the new event type id
	 */
	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
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
	 * Gets the vst session events.
	 *
	 * @return the vst session events
	 */
	public List<VstSessionEvent> getVstSessionEvents() {
		return this.vstSessionEvents;
	}

	/**
	 * Sets the vst session events.
	 *
	 * @param vstSessionEvents the new vst session events
	 */
	public void setVstSessionEvents(List<VstSessionEvent> vstSessionEvents) {
		this.vstSessionEvents = vstSessionEvents;
	}
	
}