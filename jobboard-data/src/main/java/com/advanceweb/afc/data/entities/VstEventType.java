package com.advanceweb.afc.data.entities;

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
@Table(name = "vst_event_type")
public class VstEventType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_type_id", insertable = false, updatable = false)
	private int eventTypeId;

	private String name;

	// bi-directional many-to-one association to VstSessionEvent
	@OneToMany(mappedBy = "vstEventType")
	private List<VstSessionEvent> vstSessionEvents;

	public VstEventType() {
	}

	public String getDescription() {
		return description;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public String getName() {
		return name;
	}

	public List<VstSessionEvent> getVstSessionEvents() {
		return vstSessionEvents;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVstSessionEvents(List<VstSessionEvent> vstSessionEvents) {
		this.vstSessionEvents = vstSessionEvents;
	}

}