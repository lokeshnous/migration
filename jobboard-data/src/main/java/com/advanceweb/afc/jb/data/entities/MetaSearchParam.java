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
 * The persistent class for the meta_search_param database table.
 * 
 */
@Entity
@Table(name="meta_search_param")
public class MetaSearchParam implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search param id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_param_id")
	private int searchParamId;

	/** The parameter name. */
	@Column(name="parameter_name")
	private String parameterName;

	/** The parameter value. */
	@Column(name="parameter_value")
	private String parameterValue;

	/** The seq. */
	private int seq;

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
	 * Gets the search param id.
	 *
	 * @return the search param id
	 */
	public int getSearchParamId() {
		return this.searchParamId;
	}

	/**
	 * Sets the search param id.
	 *
	 * @param searchParamId the new search param id
	 */
	public void setSearchParamId(int searchParamId) {
		this.searchParamId = searchParamId;
	}

	/**
	 * Gets the parameter name.
	 *
	 * @return the parameter name
	 */
	public String getParameterName() {
		return this.parameterName;
	}

	/**
	 * Sets the parameter name.
	 *
	 * @param parameterName the new parameter name
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * Gets the parameter value.
	 *
	 * @return the parameter value
	 */
	public String getParameterValue() {
		return this.parameterValue;
	}

	/**
	 * Sets the parameter value.
	 *
	 * @param parameterValue the new parameter value
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public int getSeq() {
		return this.seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(int seq) {
		this.seq = seq;
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