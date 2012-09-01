package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vst_clickthrough database table.
 * 
 */
@Entity
@Table(name="vst_clickthrough")
public class VstClickthrough implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="clickthrough_id")
	private int clickthroughId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="clickthrough_dt")
	private Date clickthroughDt;

	@Column(name="key_id")
	private String keyId;

	@Column(name="key_type")
	private int keyType;

	//bi-directional many-to-one association to VstClickthroughType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clickthrough_type_id")
	private VstClickthroughType vstClickthroughType;

	//bi-directional many-to-one association to VstSessioninfo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstSearch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_id")
	private VstSearch vstSearch;

	public int getClickthroughId() {
		return this.clickthroughId;
	}

	public void setClickthroughId(int clickthroughId) {
		this.clickthroughId = clickthroughId;
	}

	public Date getClickthroughDt() {
		return this.clickthroughDt;
	}

	public void setClickthroughDt(Date clickthroughDt) {
		this.clickthroughDt = clickthroughDt;
	}

	public String getKeyId() {
		return this.keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public int getKeyType() {
		return this.keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public VstClickthroughType getVstClickthroughType() {
		return this.vstClickthroughType;
	}

	public void setVstClickthroughType(VstClickthroughType vstClickthroughType) {
		this.vstClickthroughType = vstClickthroughType;
	}
	
	public VstSessioninfo getVstSessioninfo() {
		return this.vstSessioninfo;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}
	
	public VstSearch getVstSearch() {
		return this.vstSearch;
	}

	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}
	
}