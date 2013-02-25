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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the meta_search_query_history database table.
 * 
 */
@Entity
@Table(name="meta_search_query_history")
public class MetaSearchQueryHistory implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search query history id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_query_history_id")
	private int searchQueryHistoryId;

	/** The active. */
	private byte active;

	/** The environment. */
	private String environment;

    /** The query change dt. */
    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="query_change_dt")
	private Date queryChangeDt;

	/** The query params. */
	@Column(name="query_params")
	private String queryParams;

	/** The query string. */
	@Column(name="query_string")
	private String queryString;

	/** The search index name. */
	@Column(name="search_index_name")
	private String searchIndexName;

	/** The search type name. */
	@Column(name="search_type_name")
	private String searchTypeName;

	/**
	 * Gets the search query history id.
	 *
	 * @return the search query history id
	 */
	public int getSearchQueryHistoryId() {
		return this.searchQueryHistoryId;
	}

	/**
	 * Sets the search query history id.
	 *
	 * @param searchQueryHistoryId the new search query history id
	 */
	public void setSearchQueryHistoryId(int searchQueryHistoryId) {
		this.searchQueryHistoryId = searchQueryHistoryId;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public byte getActive() {
		return this.active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(byte active) {
		this.active = active;
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public String getEnvironment() {
		return this.environment;
	}

	/**
	 * Sets the environment.
	 *
	 * @param environment the new environment
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * Gets the query change dt.
	 *
	 * @return the query change dt
	 */
	public Date getQueryChangeDt() {
		return this.queryChangeDt;
	}

	/**
	 * Sets the query change dt.
	 *
	 * @param queryChangeDt the new query change dt
	 */
	public void setQueryChangeDt(Date queryChangeDt) {
		this.queryChangeDt = queryChangeDt;
	}

	/**
	 * Gets the query params.
	 *
	 * @return the query params
	 */
	public String getQueryParams() {
		return this.queryParams;
	}

	/**
	 * Sets the query params.
	 *
	 * @param queryParams the new query params
	 */
	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * Gets the query string.
	 *
	 * @return the query string
	 */
	public String getQueryString() {
		return this.queryString;
	}

	/**
	 * Sets the query string.
	 *
	 * @param queryString the new query string
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * Gets the search index name.
	 *
	 * @return the search index name
	 */
	public String getSearchIndexName() {
		return this.searchIndexName;
	}

	/**
	 * Sets the search index name.
	 *
	 * @param searchIndexName the new search index name
	 */
	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	/**
	 * Gets the search type name.
	 *
	 * @return the search type name
	 */
	public String getSearchTypeName() {
		return this.searchTypeName;
	}

	/**
	 * Sets the search type name.
	 *
	 * @param searchTypeName the new search type name
	 */
	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
	}

}