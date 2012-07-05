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
 * The persistent class for the vst_sessioninfo database table.
 * 
 */
@Entity
@Table(name = "vst_sessioninfo")
public class VstSessioninfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "create_dt")
	private Timestamp createDt;

	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "last_session_id")
	private String lastSessionId;

	private float latitude;

	private float longitude;

	// bi-directional many-to-one association to MerApplication
	@ManyToOne
	@JoinColumn(name = "application_id")
	private MerApplication merApplication;

	// bi-directional many-to-one association to MerUser
	@ManyToOne
	@JoinColumn(name = "user_id")
	private MerUser merUser;

	@Column(name = "referring_url")
	private String referringUrl;

	@Column(name = "session_id")
	private String sessionId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sessioninfo_id", insertable = false, updatable = false)
	private int sessioninfoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_dt")
	private Date startDt;

	@Column(name = "user_agent")
	private String userAgent;

	// bi-directional many-to-one association to VstClickthrough
	@OneToMany(mappedBy = "vstSessioninfo")
	private List<VstClickthrough> vstClickthroughs;

	// bi-directional many-to-one association to VstSearch
	@OneToMany(mappedBy = "vstSessioninfo")
	private List<VstSearch> vstSearches;

	// bi-directional many-to-one association to VstSessionEvent
	@OneToMany(mappedBy = "vstSessioninfo")
	private List<VstSessionEvent> vstSessionEvents;

	public VstSessioninfo() {
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getLastSessionId() {
		return lastSessionId;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public MerApplication getMerApplication() {
		return merApplication;
	}

	public MerUser getMerUser() {
		return merUser;
	}

	public String getReferringUrl() {
		return referringUrl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public int getSessioninfoId() {
		return sessioninfoId;
	}

	public Date getStartDt() {
		return startDt;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public List<VstClickthrough> getVstClickthroughs() {
		return vstClickthroughs;
	}

	public List<VstSearch> getVstSearches() {
		return vstSearches;
	}

	public List<VstSessionEvent> getVstSessionEvents() {
		return vstSessionEvents;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setLastSessionId(String lastSessionId) {
		this.lastSessionId = lastSessionId;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}

	public void setReferringUrl(String referringUrl) {
		this.referringUrl = referringUrl;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setSessioninfoId(int sessioninfoId) {
		this.sessioninfoId = sessioninfoId;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}

	public void setVstSearches(List<VstSearch> vstSearches) {
		this.vstSearches = vstSearches;
	}

	public void setVstSessionEvents(List<VstSessionEvent> vstSessionEvents) {
		this.vstSessionEvents = vstSessionEvents;
	}

}