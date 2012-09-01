package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the meta_search_query_history database table.
 * 
 */
@Entity
@Table(name="meta_search_query_history")
public class MetaSearchQueryHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_query_history_id")
	private int searchQueryHistoryId;

	private byte active;

	private String environment;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="query_change_dt")
	private Date queryChangeDt;

	@Column(name="query_params")
	private String queryParams;

	@Column(name="query_string")
	private String queryString;

	@Column(name="search_index_name")
	private String searchIndexName;

	@Column(name="search_type_name")
	private String searchTypeName;

	public int getSearchQueryHistoryId() {
		return this.searchQueryHistoryId;
	}

	public void setSearchQueryHistoryId(int searchQueryHistoryId) {
		this.searchQueryHistoryId = searchQueryHistoryId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Date getQueryChangeDt() {
		return this.queryChangeDt;
	}

	public void setQueryChangeDt(Date queryChangeDt) {
		this.queryChangeDt = queryChangeDt;
	}

	public String getQueryParams() {
		return this.queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getSearchIndexName() {
		return this.searchIndexName;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	public String getSearchTypeName() {
		return this.searchTypeName;
	}

	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
	}

}