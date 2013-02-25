/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search result new pk. */
	@EmbeddedId
	private VstSearchResultNewPK searchResultNewPK;

	/** The search id. */
	@Column(name = "search_id")
	private int searchId;

	/** The result count. */
	@Column(name = "resultcount")
	private int resultCount;

	/**
	 * Gets the search result new pk.
	 *
	 * @return the search result new pk
	 */
	public VstSearchResultNewPK getSearchResultNewPK() {
		return searchResultNewPK;
	}

	/**
	 * Sets the search result new pk.
	 *
	 * @param searchResultNewPK the new search result new pk
	 */
	public void setSearchResultNewPK(VstSearchResultNewPK searchResultNewPK) {
		this.searchResultNewPK = searchResultNewPK;
	}

	/**
	 * Gets the search id.
	 *
	 * @return the search id
	 */
	public int getSearchId() {
		return searchId;
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
	 * Gets the result count.
	 *
	 * @return the result count
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * Sets the result count.
	 *
	 * @param resultCount the new result count
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}