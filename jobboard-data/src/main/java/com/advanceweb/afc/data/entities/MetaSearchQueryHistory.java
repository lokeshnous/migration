package com.advanceweb.afc.data.entities;

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
@Table(name = "meta_search_query_history")
public class MetaSearchQueryHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte active;

	private String environment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "query_change_dt")
	private Date queryChangeDt;

	@Column(name = "query_params")
	private String queryParams;

	@Column(name = "query_string")
	private String queryString;

	@Column(name = "search_index_name")
	private String searchIndexName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_query_history_id", insertable = false, updatable = false)
	private int searchQueryHistoryId;

	@Column(name = "search_type_name")
	private String searchTypeName;

	public MetaSearchQueryHistory() {
	}

	public byte getActive() {
		return active;
	}

	public String getEnvironment() {
		return environment;
	}

	public Date getQueryChangeDt() {
		return queryChangeDt;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public String getQueryString() {
		return queryString;
	}

	public String getSearchIndexName() {
		return searchIndexName;
	}

	public int getSearchQueryHistoryId() {
		return searchQueryHistoryId;
	}

	public String getSearchTypeName() {
		return searchTypeName;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setQueryChangeDt(Date queryChangeDt) {
		this.queryChangeDt = queryChangeDt;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	public void setSearchQueryHistoryId(int searchQueryHistoryId) {
		this.searchQueryHistoryId = searchQueryHistoryId;
	}

	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
	}

}