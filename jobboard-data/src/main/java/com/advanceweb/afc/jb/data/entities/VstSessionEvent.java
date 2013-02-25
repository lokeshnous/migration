/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the vst_session_event database table.
 * 
 */
@Entity
@Table(name="vst_session_event")
public class VstSessionEvent implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The session event id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="session_event_id")
	private int sessionEventId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The event context. */
	@Column(name="event_context")
	private String eventContext;

	/** The event value. */
	@Column(name="event_value")
	private String eventValue;

    /** The start dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	//bi-directional many-to-one association to VstSessioninfo
	/** The vst sessioninfo. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstEventType
	/** The vst event type. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_type_id")
	private VstEventType vstEventType;

	//bi-directional many-to-one association to VstSessionEventInfo
	/** The vst session event infos. */
	@OneToMany(mappedBy="vstSessionEvent")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	/**
	 * Gets the session event id.
	 *
	 * @return the session event id
	 */
	public int getSessionEventId() {
		return this.sessionEventId;
	}

	/**
	 * Sets the session event id.
	 *
	 * @param sessionEventId the new session event id
	 */
	public void setSessionEventId(int sessionEventId) {
		this.sessionEventId = sessionEventId;
	}

	/**
	 * Gets the creates the dt.
	 *
	 * @return the creates the dt
	 */
	public Timestamp getCreateDt() {
		return this.createDt;
	}

	/**
	 * Sets the creates the dt.
	 *
	 * @param createDt the new creates the dt
	 */
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	/**
	 * Gets the event context.
	 *
	 * @return the event context
	 */
	public String getEventContext() {
		return this.eventContext;
	}

	/**
	 * Sets the event context.
	 *
	 * @param eventContext the new event context
	 */
	public void setEventContext(String eventContext) {
		this.eventContext = eventContext;
	}

	/**
	 * Gets the event value.
	 *
	 * @return the event value
	 */
	public String getEventValue() {
		return this.eventValue;
	}

	/**
	 * Sets the event value.
	 *
	 * @param eventValue the new event value
	 */
	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	/**
	 * Gets the start dt.
	 *
	 * @return the start dt
	 */
	public Date getStartDt() {
		return this.startDt;
	}

	/**
	 * Sets the start dt.
	 *
	 * @param startDt the new start dt
	 */
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
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
	 * Gets the vst event type.
	 *
	 * @return the vst event type
	 */
	public VstEventType getVstEventType() {
		return this.vstEventType;
	}

	/**
	 * Sets the vst event type.
	 *
	 * @param vstEventType the new vst event type
	 */
	public void setVstEventType(VstEventType vstEventType) {
		this.vstEventType = vstEventType;
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