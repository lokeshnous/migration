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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the stg_session_event database table.
 * 
 */
@Entity
@Table(name="stg_session_event")
public class StgSessionEvent implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stg session event id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stg_session_event_id")
	private int stgSessionEventId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

//	@Column(name="device_orientation")
//	private Object deviceOrientation;

	/** The event context. */
@Column(name="event_context")
	private String eventContext;

    /** The event dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="event_dt")
	private Date eventDt;

	/** The event name. */
	@Column(name="event_name")
	private String eventName;

	/** The event value. */
	@Column(name="event_value")
	private String eventValue;

    /** The process dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="process_dt")
	private Date processDt;

	/** The process msg. */
	@Column(name="process_msg")
	private String processMsg;

	/** The process status. */
	@Column(name="process_status")
	private String processStatus;

	/** The session id. */
	@Column(name="session_id")
	private String sessionId;

	/** The wifi status. */
	@Column(name="wifi_status")
	private int wifiStatus;


	/**
	 * Gets the stg session event id.
	 *
	 * @return the stg session event id
	 */
	public int getStgSessionEventId() {
		return this.stgSessionEventId;
	}

	/**
	 * Sets the stg session event id.
	 *
	 * @param stgSessionEventId the new stg session event id
	 */
	public void setStgSessionEventId(int stgSessionEventId) {
		this.stgSessionEventId = stgSessionEventId;
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

/*	public Object getDeviceOrientation() {
		return this.deviceOrientation;
	}

	public void setDeviceOrientation(Object deviceOrientation) {
		this.deviceOrientation = deviceOrientation;
	}*/

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
	 * Gets the event dt.
	 *
	 * @return the event dt
	 */
	public Date getEventDt() {
		return this.eventDt;
	}

	/**
	 * Sets the event dt.
	 *
	 * @param eventDt the new event dt
	 */
	public void setEventDt(Date eventDt) {
		this.eventDt = eventDt;
	}

	/**
	 * Gets the event name.
	 *
	 * @return the event name
	 */
	public String getEventName() {
		return this.eventName;
	}

	/**
	 * Sets the event name.
	 *
	 * @param eventName the new event name
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	 * Gets the process dt.
	 *
	 * @return the process dt
	 */
	public Date getProcessDt() {
		return this.processDt;
	}

	/**
	 * Sets the process dt.
	 *
	 * @param processDt the new process dt
	 */
	public void setProcessDt(Date processDt) {
		this.processDt = processDt;
	}

	/**
	 * Gets the process msg.
	 *
	 * @return the process msg
	 */
	public String getProcessMsg() {
		return this.processMsg;
	}

	/**
	 * Sets the process msg.
	 *
	 * @param processMsg the new process msg
	 */
	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}

	/**
	 * Gets the process status.
	 *
	 * @return the process status
	 */
	public String getProcessStatus() {
		return this.processStatus;
	}

	/**
	 * Sets the process status.
	 *
	 * @param processStatus the new process status
	 */
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return this.sessionId;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Gets the wifi status.
	 *
	 * @return the wifi status
	 */
	public int getWifiStatus() {
		return this.wifiStatus;
	}

	/**
	 * Sets the wifi status.
	 *
	 * @param wifiStatus the new wifi status
	 */
	public void setWifiStatus(int wifiStatus) {
		this.wifiStatus = wifiStatus;
	}

}