package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vst_session_event_info database table.
 * 
 */
@Entity
@Table(name="vst_session_event_info")
public class VstSessionEventInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VstSessionEventInfoPK id;

	@Column(name="info_value")
	private String infoValue;

	//bi-directional many-to-one association to VstSessionEvent
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="session_event_id", insertable=false, updatable=false)
	private VstSessionEvent vstSessionEvent;

	//bi-directional many-to-one association to VstInfoType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="info_type_id", insertable=false, updatable=false)
	private VstInfoType vstInfoType;

    public VstSessionEventInfo() {
    }

	public VstSessionEventInfoPK getId() {
		return this.id;
	}

	public void setId(VstSessionEventInfoPK id) {
		this.id = id;
	}
	
	public String getInfoValue() {
		return this.infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public VstSessionEvent getVstSessionEvent() {
		return this.vstSessionEvent;
	}

	public void setVstSessionEvent(VstSessionEvent vstSessionEvent) {
		this.vstSessionEvent = vstSessionEvent;
	}
	
	public VstInfoType getVstInfoType() {
		return this.vstInfoType;
	}

	public void setVstInfoType(VstInfoType vstInfoType) {
		this.vstInfoType = vstInfoType;
	}
	
}