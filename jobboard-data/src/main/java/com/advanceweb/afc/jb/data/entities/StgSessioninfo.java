package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the stg_sessioninfo database table.
 * 
 */
@Entity
@Table(name="stg_sessioninfo")
public class StgSessioninfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stg_sessioninfo_id")
	private int stgSessioninfoId;

	@Column(name="application_name")
	private String applicationName;

	@Column(name="create_dt")
	private Timestamp createDt;

	@Column(name="device_id")
	private String deviceId;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="last_session_id")
	private String lastSessionId;

	private float latitude;

	private float longitude;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="process_dt")
	private Date processDt;

	@Column(name="process_msg")
	private String processMsg;

	@Column(name="process_status")
	private String processStatus;

	@Column(name="referring_url")
	private String referringUrl;

	@Column(name="session_id")
	private String sessionId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="user_agent")
	private String userAgent;

	@Column(name="user_id")
	private int userId;

    public StgSessioninfo() {
    }

	public int getStgSessioninfoId() {
		return this.stgSessioninfoId;
	}

	public void setStgSessioninfoId(int stgSessioninfoId) {
		this.stgSessioninfoId = stgSessioninfoId;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastSessionId() {
		return this.lastSessionId;
	}

	public void setLastSessionId(String lastSessionId) {
		this.lastSessionId = lastSessionId;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
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

	public String getReferringUrl() {
		return this.referringUrl;
	}

	public void setReferringUrl(String referringUrl) {
		this.referringUrl = referringUrl;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}