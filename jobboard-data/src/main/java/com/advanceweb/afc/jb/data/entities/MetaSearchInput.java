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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the meta_search_input database table.
 * 
 */
@Entity
@Table(name="meta_search_input")
public class MetaSearchInput implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search input id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_input_id")
	private int searchInputId;

	/** The input name. */
	@Column(name="input_name")
	private String inputName;

	/** The input value. */
	@Column(name="input_value")
	private String inputValue;

	//bi-directional many-to-one association to MetaSearchIndex
	/** The meta search index. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_index_id")
	private MetaSearchIndex metaSearchIndex;

	//bi-directional many-to-one association to MetaSearchType
	/** The meta search type. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_type_id")
	private MetaSearchType metaSearchType;

	/**
	 * Gets the search input id.
	 *
	 * @return the search input id
	 */
	public int getSearchInputId() {
		return this.searchInputId;
	}

	/**
	 * Sets the search input id.
	 *
	 * @param searchInputId the new search input id
	 */
	public void setSearchInputId(int searchInputId) {
		this.searchInputId = searchInputId;
	}

	/**
	 * Gets the input name.
	 *
	 * @return the input name
	 */
	public String getInputName() {
		return this.inputName;
	}

	/**
	 * Sets the input name.
	 *
	 * @param inputName the new input name
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	/**
	 * Gets the input value.
	 *
	 * @return the input value
	 */
	public String getInputValue() {
		return this.inputValue;
	}

	/**
	 * Sets the input value.
	 *
	 * @param inputValue the new input value
	 */
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	/**
	 * Gets the meta search index.
	 *
	 * @return the meta search index
	 */
	public MetaSearchIndex getMetaSearchIndex() {
		return this.metaSearchIndex;
	}

	/**
	 * Sets the meta search index.
	 *
	 * @param metaSearchIndex the new meta search index
	 */
	public void setMetaSearchIndex(MetaSearchIndex metaSearchIndex) {
		this.metaSearchIndex = metaSearchIndex;
	}
	
	/**
	 * Gets the meta search type.
	 *
	 * @return the meta search type
	 */
	public MetaSearchType getMetaSearchType() {
		return this.metaSearchType;
	}

	/**
	 * Sets the meta search type.
	 *
	 * @param metaSearchType the new meta search type
	 */
	public void setMetaSearchType(MetaSearchType metaSearchType) {
		this.metaSearchType = metaSearchType;
	}
	
}