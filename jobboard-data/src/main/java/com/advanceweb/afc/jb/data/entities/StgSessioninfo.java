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
 * The persistent class for the stg_sessioninfo database table.
 * 
 */
@Entity
@Table(name="stg_sessioninfo")
public class StgSessioninfo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The stg sessioninfo id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stg_sessioninfo_id")
	private int stgSessioninfoId;

	/** The application name. */
	@Column(name="application_name")
	private String applicationName;

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

	/** The user id. */
	@Column(name="user_id")
	private int userId;

	/**
	 * Gets the stg sessioninfo id.
	 *
	 * @return the stg sessioninfo id
	 */
	public int getStgSessioninfoId() {
		return this.stgSessioninfoId;
	}

	/**
	 * Sets the stg sessioninfo id.
	 *
	 * @param stgSessioninfoId the new stg sessioninfo id
	 */
	public void setStgSessioninfoId(int stgSessioninfoId) {
		this.stgSessioninfoId = stgSessioninfoId;
	}

	/**
	 * Gets the application name.
	 *
	 * @return the application name
	 */
	public String getApplicationName() {
		return this.applicationName;
	}

	/**
	 * Sets the application name.
	 *
	 * @param applicationName the new application name
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}