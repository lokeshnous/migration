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
 * The persistent class for the vst_sessioninfo database table.
 * 
 */
@Entity
@Table(name="vst_sessioninfo")
public class VstSessioninfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sessioninfo id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sessioninfo_id")
	private int sessioninfoId;

	/** The create dt. */
	@Column(name="create_dt")
	private Timestamp createDt;

	/** The device id. */
	@Column(name="device_id")
	private String deviceId;

	/** The ip address. */
	@Column(name="ip_address")
	private String ipAddress;

	/** The last session id. */
	@Column(name="last_session_id")
	private String lastSessionId;

	/** The latitude. */
	private float latitude;

	/** The longitude. */
	private float longitude;

	/** The referring url. */
	@Column(name="referring_url")
	private String referringUrl;

	/** The session id. */
	@Column(name="session_id")
	private String sessionId;

    /** The start dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	/** The user agent. */
	@Column(name="user_agent")
	private String userAgent;

	//bi-directional many-to-one association to VstClickthrough
	/** The vst clickthroughs. */
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstClickthrough> vstClickthroughs;

	//bi-directional many-to-one association to VstSearch
	/** The vst searches. */
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstSearch> vstSearches;

	//bi-directional many-to-one association to VstSessionEvent
	/** The vst session events. */
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstSessionEvent> vstSessionEvents;

	//bi-directional many-to-one association to MerApplication
	/** The mer application. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id")
	private MerApplication merApplication;

	//bi-directional many-to-one association to MerUser
	/** The mer user. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private MerUser merUser;

	/**
	 * Gets the sessioninfo id.
	 *
	 * @return the sessioninfo id
	 */
	public int getSessioninfoId() {
		return this.sessioninfoId;
	}

	/**
	 * Sets the sessioninfo id.
	 *
	 * @param sessioninfoId the new sessioninfo id
	 */
	public void setSessioninfoId(int sessioninfoId) {
		this.sessioninfoId = sessioninfoId;
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
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public String getDeviceId() {
		return this.deviceId;
	}

	/**
	 * Sets the device id.
	 *
	 * @param deviceId the new device id
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress the new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the last session id.
	 *
	 * @return the last session id
	 */
	public String getLastSessionId() {
		return this.lastSessionId;
	}

	/**
	 * Sets the last session id.
	 *
	 * @param lastSessionId the new last session id
	 */
	public void setLastSessionId(String lastSessionId) {
		this.lastSessionId = lastSessionId;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public float getLatitude() {
		return this.latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public float getLongitude() {
		return this.longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the referring url.
	 *
	 * @return the referring url
	 */
	public String getReferringUrl() {
		return this.referringUrl;
	}

	/**
	 * Sets the referring url.
	 *
	 * @param referringUrl the new referring url
	 */
	public void setReferringUrl(String referringUrl) {
		this.referringUrl = referringUrl;
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
	 * Gets the user agent.
	 *
	 * @return the user agent
	 */
	public String getUserAgent() {
		return this.userAgent;
	}

	/**
	 * Sets the user agent.
	 *
	 * @param userAgent the new user agent
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * Gets the vst clickthroughs.
	 *
	 * @return the vst clickthroughs
	 */
	public List<VstClickthrough> getVstClickthroughs() {
		return this.vstClickthroughs;
	}

	/**
	 * Sets the vst clickthroughs.
	 *
	 * @param vstClickthroughs the new vst clickthroughs
	 */
	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}
	
	/**
	 * Gets the vst searches.
	 *
	 * @return the vst searches
	 */
	public List<VstSearch> getVstSearches() {
		return this.vstSearches;
	}

	/**
	 * Sets the vst searches.
	 *
	 * @param vstSearches the new vst searches
	 */
	public void setVstSearches(List<VstSearch> vstSearches) {
		this.vstSearches = vstSearches;
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
	
	/**
	 * Gets the mer application.
	 *
	 * @return the mer application
	 */
	public MerApplication getMerApplication() {
		return this.merApplication;
	}

	/**
	 * Sets the mer application.
	 *
	 * @param merApplication the new mer application
	 */
	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}
	
	/**
	 * Gets the mer user.
	 *
	 * @return the mer user
	 */
	public MerUser getMerUser() {
		return this.merUser;
	}

	/**
	 * Sets the mer user.
	 *
	 * @param merUser the new mer user
	 */
	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
}