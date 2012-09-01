package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vst_search_result database table.
 * 
 */
@Entity
@Table(name="vst_search_result")
public class VstSearchResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VstSearchResultPK searchResultPK;

	private String result;

	//bi-directional many-to-one association to VstSearch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_id", insertable=false, updatable=false)
	private VstSearch vstSearch;

	public VstSearchResultPK getSearchResultPK() {
		return searchResultPK;
	}

	public void setSearchResultPK(VstSearchResultPK searchResultPK) {
		this.searchResultPK = searchResultPK;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public VstSearch getVstSearch() {
		return this.vstSearch;
	}

	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}
	
}