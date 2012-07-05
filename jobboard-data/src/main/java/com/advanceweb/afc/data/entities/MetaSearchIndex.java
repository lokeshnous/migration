package com.advanceweb.afc.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the meta_search_index database table.
 * 
 */
@Entity
@Table(name = "meta_search_index")
public class MetaSearchIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	private String environment;

	// bi-directional many-to-one association to MetaSearchInput
	@OneToMany(mappedBy = "metaSearchIndex")
	private List<MetaSearchInput> metaSearchInputs;

	// bi-directional many-to-one association to MetaSearchParam
	@OneToMany(mappedBy = "metaSearchIndex")
	private List<MetaSearchParam> metaSearchParams;

	@Column(name = "search_host")
	private String searchHost;

	@Column(name = "search_index_group")
	private String searchIndexGroup;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_index_id", insertable = false, updatable = false)
	private int searchIndexId;

	@Column(name = "search_index_name")
	private String searchIndexName;

	public MetaSearchIndex() {
	}

	public String getEnvironment() {
		return environment;
	}

	public List<MetaSearchInput> getMetaSearchInputs() {
		return metaSearchInputs;
	}

	public List<MetaSearchParam> getMetaSearchParams() {
		return metaSearchParams;
	}

	public String getSearchHost() {
		return searchHost;
	}

	public String getSearchIndexGroup() {
		return searchIndexGroup;
	}

	public int getSearchIndexId() {
		return searchIndexId;
	}

	public String getSearchIndexName() {
		return searchIndexName;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void setMetaSearchInputs(List<MetaSearchInput> metaSearchInputs) {
		this.metaSearchInputs = metaSearchInputs;
	}

	public void setMetaSearchParams(List<MetaSearchParam> metaSearchParams) {
		this.metaSearchParams = metaSearchParams;
	}

	public void setSearchHost(String searchHost) {
		this.searchHost = searchHost;
	}

	public void setSearchIndexGroup(String searchIndexGroup) {
		this.searchIndexGroup = searchIndexGroup;
	}

	public void setSearchIndexId(int searchIndexId) {
		this.searchIndexId = searchIndexId;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

}