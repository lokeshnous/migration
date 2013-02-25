/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
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
 * The persistent class for the vst_search database table.
 * 
 */
@Entity
@Table(name="vst_search")
public class VstSearch implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_id")
	private int searchId;

    /** The search dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="search_dt")
	private Date searchDt;

	/** The search seq. */
	@Column(name="search_seq")
	private int searchSeq;

	//bi-directional many-to-one association to VstClickthrough
	/** The vst clickthroughs. */
	@OneToMany(mappedBy="vstSearch")
	private List<VstClickthrough> vstClickthroughs;

	//bi-directional many-to-one association to VstSessioninfo
	/** The vst sessioninfo. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstSearchResult
	/** The vst search results. */
	@OneToMany(mappedBy="vstSearch")
	private List<VstSearchResult> vstSearchResults;

	/**
	 * Gets the search id.
	 *
	 * @return the search id
	 */
	public int getSearchId() {
		return this.searchId;
	}

	/**
	 * Sets the search id.
	 *
	 * @param searchId the new search id
	 */
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	/**
	 * Gets the search dt.
	 *
	 * @return the search dt
	 */
	public Date getSearchDt() {
		return this.searchDt;
	}

	/**
	 * Sets the search dt.
	 *
	 * @param searchDt the new search dt
	 */
	public void setSearchDt(Date searchDt) {
		this.searchDt = searchDt;
	}

	/**
	 * Gets the search seq.
	 *
	 * @return the search seq
	 */
	public int getSearchSeq() {
		return this.searchSeq;
	}

	/**
	 * Sets the search seq.
	 *
	 * @param searchSeq the new search seq
	 */
	public void setSearchSeq(int searchSeq) {
		this.searchSeq = searchSeq;
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
	 * Gets the vst sessioninfo.
	 *
	 * @return the vst sessioninfo
	 */
	public VstSessioninfo getVstSessioninfo() {
		return this.vstSessioninfo;
	}

	/**
	 * Sets the vst sessioninfo.
	 *
	 * @param vstSessioninfo the new vst sessioninfo
	 */
	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}
	
	/**
	 * Gets the vst search results.
	 *
	 * @return the vst search results
	 */
	public List<VstSearchResult> getVstSearchResults() {
		return this.vstSearchResults;
	}

	/**
	 * Sets the vst search results.
	 *
	 * @param vstSearchResults the new vst search results
	 */
	public void setVstSearchResults(List<VstSearchResult> vstSearchResults) {
		this.vstSearchResults = vstSearchResults;
	}
	
}