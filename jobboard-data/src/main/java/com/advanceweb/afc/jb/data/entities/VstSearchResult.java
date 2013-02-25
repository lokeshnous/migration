/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the vst_search_result database table.
 * 
 */
@Entity
@Table(name="vst_search_result")
public class VstSearchResult implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search result pk. */
	@EmbeddedId
	private VstSearchResultPK searchResultPK;

	/** The result. */
	private String result;

	//bi-directional many-to-one association to VstSearch
	/** The vst search. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_id", insertable=false, updatable=false)
	private VstSearch vstSearch;

	/**
	 * Gets the search result pk.
	 *
	 * @return the search result pk
	 */
	public VstSearchResultPK getSearchResultPK() {
		return searchResultPK;
	}

	/**
	 * Sets the search result pk.
	 *
	 * @param searchResultPK the new search result pk
	 */
	public void setSearchResultPK(VstSearchResultPK searchResultPK) {
		this.searchResultPK = searchResultPK;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return this.result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Gets the vst search.
	 *
	 * @return the vst search
	 */
	public VstSearch getVstSearch() {
		return this.vstSearch;
	}

	/**
	 * Sets the vst search.
	 *
	 * @param vstSearch the new vst search
	 */
	public void setVstSearch(VstSearch vstSearch) {
		this.vstSearch = vstSearch;
	}
	
}