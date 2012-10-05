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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_id")
	private int searchId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="search_dt")
	private Date searchDt;

	@Column(name="search_seq")
	private int searchSeq;

	//bi-directional many-to-one association to VstClickthrough
	@OneToMany(mappedBy="vstSearch")
	private List<VstClickthrough> vstClickthroughs;

	//bi-directional many-to-one association to VstSessioninfo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	//bi-directional many-to-one association to VstSearchResult
	@OneToMany(mappedBy="vstSearch")
	private List<VstSearchResult> vstSearchResults;

	public int getSearchId() {
		return this.searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public Date getSearchDt() {
		return this.searchDt;
	}

	public void setSearchDt(Date searchDt) {
		this.searchDt = searchDt;
	}

	public int getSearchSeq() {
		return this.searchSeq;
	}

	public void setSearchSeq(int searchSeq) {
		this.searchSeq = searchSeq;
	}

	public List<VstClickthrough> getVstClickthroughs() {
		return this.vstClickthroughs;
	}

	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}
	
	public VstSessioninfo getVstSessioninfo() {
		return this.vstSessioninfo;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}
	
	public List<VstSearchResult> getVstSearchResults() {
		return this.vstSearchResults;
	}

	public void setVstSearchResults(List<VstSearchResult> vstSearchResults) {
		this.vstSearchResults = vstSearchResults;
	}
	
}