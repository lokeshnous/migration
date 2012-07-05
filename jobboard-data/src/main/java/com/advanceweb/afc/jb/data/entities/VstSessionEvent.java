package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "vst_session_event")
public class VstSessionEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Column(name = "event_context")
	private String eventContext;

	@Column(name = "event_value")
	private String eventValue;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "session_event_id", insertable = false, updatable = false)
	private int sessionEventId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dt")
	private Date startDt;

	// bi-directional many-to-one association to VstEventType
	@ManyToOne
	@JoinColumn(name = "event_type_id")
	private VstEventType vstEventType;

	// bi-directional many-to-one association to VstSessionEventInfo
	@OneToMany(mappedBy = "vstSessionEvent")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	// bi-directional many-to-one association to VstSessioninfo
	@ManyToOne
	@JoinColumn(name = "sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	public VstSessionEvent() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public String getEventContext() {
		return eventContext;
	}

	public String getEventValue() {
		return eventValue;
	}

	public int getSessionEventId() {
		return sessionEventId;
	}

	public Date getStartDt() {
		return startDt;
	}

	public VstEventType getVstEventType() {
		return vstEventType;
	}

	public List<VstSessionEventInfo> getVstSessionEventInfos() {
		return vstSessionEventInfos;
	}

	public VstSessioninfo getVstSessioninfo() {
		return vstSessioninfo;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setEventContext(String eventContext) {
		this.eventContext = eventContext;
	}

	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	public void setSessionEventId(int sessionEventId) {
		this.sessionEventId = sessionEventId;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public void setVstEventType(VstEventType vstEventType) {
		this.vstEventType = vstEventType;
	}

	public void setVstSessionEventInfos(
			List<VstSessionEventInfo> vstSessionEventInfos) {
		this.vstSessionEventInfos = vstSessionEventInfos;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}

}