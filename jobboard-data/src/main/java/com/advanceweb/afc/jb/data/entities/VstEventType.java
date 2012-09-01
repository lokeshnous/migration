package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vst_event_type database table.
 * 
 */
@Entity
@Table(name="vst_event_type")
public class VstEventType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_type_id")
	private int eventTypeId;

	private String description;

	private String name;

	//bi-directional many-to-one association to VstSessionEvent
	@OneToMany(mappedBy="vstEventType")
	private List<VstSessionEvent> vstSessionEvents;

	public int getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VstSessionEvent> getVstSessionEvents() {
		return this.vstSessionEvents;
	}

	public void setVstSessionEvents(List<VstSessionEvent> vstSessionEvents) {
		this.vstSessionEvents = vstSessionEvents;
	}
	
}