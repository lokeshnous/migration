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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="session_event_id")
	private int sessionEventId;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="event_context")
	private String eventContext;

	@Column(name="event_value")
	private String eventValue;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	//bi-directional many-to-one association to VstSessioninfo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstEventType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_type_id")
	private VstEventType vstEventType;

	//bi-directional many-to-one association to VstSessionEventInfo
	@OneToMany(mappedBy="vstSessionEvent")
	private List<VstSessionEventInfo> vstSessionEventInfos;

	public int getSessionEventId() {
		return this.sessionEventId;
	}

	public void setSessionEventId(int sessionEventId) {
		this.sessionEventId = sessionEventId;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public String getEventContext() {
		return this.eventContext;
	}

	public void setEventContext(String eventContext) {
		this.eventContext = eventContext;
	}

	public String getEventValue() {
		return this.eventValue;
	}

	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public VstSessioninfo getVstSessioninfo() {
		return this.vstSessioninfo;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}
	
	public VstEventType getVstEventType() {
		return this.vstEventType;
	}

	public void setVstEventType(VstEventType vstEventType) {
		this.vstEventType = vstEventType;
	}
	
	public List<VstSessionEventInfo> getVstSessionEventInfos() {
		return this.vstSessionEventInfos;
	}

	public void setVstSessionEventInfos(List<VstSessionEventInfo> vstSessionEventInfos) {
		this.vstSessionEventInfos = vstSessionEventInfos;
	}
	
}