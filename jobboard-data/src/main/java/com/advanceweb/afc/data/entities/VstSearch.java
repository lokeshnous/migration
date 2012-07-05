package com.advanceweb.afc.data.entities;

import java.io.Serializable;
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
 * The persistent class for the vst_search database table.
 * 
 */
@Entity
@Table(name = "vst_search")
public class VstSearch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "search_dt")
	private Date searchDt;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_id", insertable = false, updatable = false)
	private int searchId;

	@Column(name = "search_seq")
	private int searchSeq;

	// bi-directional many-to-one association to VstClickthrough
	@OneToMany(mappedBy = "vstSearch")
	private List<VstClickthrough> vstClickthroughs;

	// bi-directional many-to-one association to VstSearchResult
	@OneToMany(mappedBy = "vstSearch")
	private List<VstSearchResult> vstSearchResults;

	// bi-directional many-to-one association to VstSessioninfo
	@ManyToOne
	@JoinColumn(name = "sessioninfo_id")
	private VstSessioninfo vstSessioninfo;

	public VstSearch() {
	}

	public Date getSearchDt() {
		return searchDt;
	}

	public int getSearchId() {
		return searchId;
	}

	public int getSearchSeq() {
		return searchSeq;
	}

	public List<VstClickthrough> getVstClickthroughs() {
		return vstClickthroughs;
	}

	public List<VstSearchResult> getVstSearchResults() {
		return vstSearchResults;
	}

	public VstSessioninfo getVstSessioninfo() {
		return vstSessioninfo;
	}

	public void setSearchDt(Date searchDt) {
		this.searchDt = searchDt;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public void setSearchSeq(int searchSeq) {
		this.searchSeq = searchSeq;
	}

	public void setVstClickthroughs(List<VstClickthrough> vstClickthroughs) {
		this.vstClickthroughs = vstClickthroughs;
	}

	public void setVstSearchResults(List<VstSearchResult> vstSearchResults) {
		this.vstSearchResults = vstSearchResults;
	}

	public void setVstSessioninfo(VstSessioninfo vstSessioninfo) {
		this.vstSessioninfo = vstSessioninfo;
	}

}