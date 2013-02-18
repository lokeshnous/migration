package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the vst_search_result database table.
 * 
 */
@Entity
@Table(name = "vst_search_result_new")
public class VstSearchResultNew implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VstSearchResultNewPK searchResultNewPK;

	@Column(name = "search_id")
	private int searchId;

	@Column(name = "resultcount")
	private int resultCount;

	public VstSearchResultNewPK getSearchResultNewPK() {
		return searchResultNewPK;
	}

	public void setSearchResultNewPK(VstSearchResultNewPK searchResultNewPK) {
		this.searchResultNewPK = searchResultNewPK;
	}

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}