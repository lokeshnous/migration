package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vst_sessioninfo database table.
 * 
 */
@Entity
@Table(name="vst_sessioninfo")
public class VstSessioninfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sessioninfo_id")
	private int sessioninfoId;

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

	@Column(name="referring_url")
	private String referringUrl;

	@Column(name="session_id")
	private String sessionId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	@Column(name="user_agent")
	private String userAgent;

	//bi-directional many-to-one association to VstClickthrough
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstClickthrough> vstClickthroughs;

	//bi-directional many-to-one association to VstSearch
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstSearch> vstSearches;

	//bi-directional many-to-one association to VstSessionEvent
	@OneToMany(mappedBy="vstSessioninfo")
	private List<VstSessionEvent> vstSessionEvents;

	//bi-directional many-to-one association to MerApplication
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id")
	private MerApplication merApplication;

	//bi-directional many-to-one association to MerUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private MerUser merUser;

    public VstSessioninfo() {
    }

	public int getSessioninfoId() {
		return this.sessioninfoId;
	}

	public void setSessioninfoId(int sessioninfoId) {
		this.sessioninfoId = sessioninfoId;
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

	public List<VstClickthrough> getVstClickthroughs() {
		return this.vstClickthroughs;
	}

	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}
	
	public List<VstSearch> getVstSearches() {
		return this.vstSearches;
	}

	public void setVstSearches(List<VstSearch> vstSearches) {
		this.vstSearches = vstSearches;
	}
	
	public List<VstSessionEvent> getVstSessionEvents() {
		return this.vstSessionEvents;
	}

	public void setVstSessionEvents(List<VstSessionEvent> vstSessionEvents) {
		this.vstSessionEvents = vstSessionEvents;
	}
	
	public MerApplication getMerApplication() {
		return this.merApplication;
	}

	public void setMerApplication(MerApplication merApplication) {
		this.merApplication = merApplication;
	}
	
	public MerUser getMerUser() {
		return this.merUser;
	}

	public void setMerUser(MerUser merUser) {
		this.merUser = merUser;
	}
	
}