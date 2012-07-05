package com.advanceweb.afc.data.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the vst_search_result database table.
 * 
 */
@Entity
@Table(name = "vst_search_result")
public class VstSearchResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VstSearchResultPK id;

	private String result;

	// bi-directional many-to-one association to VstSearch
	@ManyToOne
	@JoinColumn(name = "search_id", insertable = false, updatable = false)
	private VstSearch vstSearch;

	public VstSearchResult() {
	}

	public VstSearchResultPK getId() {
		return id;
	}

	public String getResult() {
		return result;
	}

	public VstSearch getVstSearch() {
		return vstSearch;
	}

	public void setId(VstSearchResultPK id) {
		this.id = id;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}

}