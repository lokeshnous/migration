package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the stg_session_event database table.
 * 
 */
@Entity
@Table(name="stg_session_event")
public class StgSessionEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="device_orientation")
	private Object deviceOrientation;

	@Column(name="event_context")
	private String eventContext;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="event_dt")
	private Date eventDt;

	@Column(name="event_name")
	private String eventName;

	@Column(name="event_value")
	private String eventValue;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="process_dt")
	private Date processDt;

	@Column(name="process_msg")
	private String processMsg;

	@Column(name="process_status")
	private String processStatus;

	@Column(name="session_id")
	private String sessionId;

	@Column(name="wifi_status")
	private short wifiStatus;

    public StgSessionEvent() {
    }

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Object getDeviceOrientation() {
		return this.deviceOrientation;
	}

	public void setDeviceOrientation(Object deviceOrientation) {
		this.deviceOrientation = deviceOrientation;
	}

	public String getEventContext() {
		return this.eventContext;
	}

	public void setEventContext(String eventContext) {
		this.eventContext = eventContext;
	}

	public Date getEventDt() {
		return this.eventDt;
	}

	public void setEventDt(Date eventDt) {
		this.eventDt = eventDt;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventValue() {
		return this.eventValue;
	}

	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	public Date getProcessDt() {
		return this.processDt;
	}

	public void setProcessDt(Date processDt) {
		this.processDt = processDt;
	}

	public String getProcessMsg() {
		return this.processMsg;
	}

	public void setProcessMsg(String processMsg) {
		this.processMsg = processMsg;
	}

	public String getProcessStatus() {
		return this.processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public short getWifiStatus() {
		return this.wifiStatus;
	}

	public void setWifiStatus(short wifiStatus) {
		this.wifiStatus = wifiStatus;
	}

}