package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the vst_clickthrough database table.
 * 
 */
@Entity
@Table(name = "vst_clickthrough")
public class VstClickthrough implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "clickthrough_dt")
	private Date clickthroughDt;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clickthrough_id", insertable = false, updatable = false)
	private int clickthroughId;

	@Column(name = "key_id")
	private String keyId;

	@Column(name = "key_type")
	private int keyType;

	// bi-directional many-to-one association to VstClickthroughType
	@ManyToOne
	@JoinColumn(name = "clickthrough_type_id")
	private VstClickthroughType vstClickthroughType;

	// bi-directional many-to-one association to VstSearch
	@ManyToOne
	@JoinColumn(name = "search_id")
	private VstSearch vstSearch;

	// bi-directional many-to-one association to VstSessioninfo
	@ManyToOne
	@JoinColumn(name = "sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	public VstClickthrough() {
	}

	public Date getClickthroughDt() {
		return clickthroughDt;
	}

	public int getClickthroughId() {
		return clickthroughId;
	}

	public String getKeyId() {
		return keyId;
	}

	public int getKeyType() {
		return keyType;
	}

	public VstClickthroughType getVstClickthroughType() {
		return vstClickthroughType;
	}

	public VstSearch getVstSearch() {
		return vstSearch;
	}

	public VstSessioninfo getVstSessioninfo() {
		return vstSessioninfo;
	}

	public void setClickthroughDt(Date clickthroughDt) {
		this.clickthroughDt = clickthroughDt;
	}

	public void setClickthroughId(int clickthroughId) {
		this.clickthroughId = clickthroughId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public void setVstClickthroughType(VstClickthroughType vstClickthroughType) {
		this.vstClickthroughType = vstClickthroughType;
	}

	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}

}