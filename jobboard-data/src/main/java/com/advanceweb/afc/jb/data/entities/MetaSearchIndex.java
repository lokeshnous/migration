package com.advanceweb.afc.jb.data.entities;

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
@Table(name="meta_search_index")
public class MetaSearchIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_index_id")
	private int searchIndexId;

	private String environment;

	@Column(name="search_host")
	private String searchHost;

	@Column(name="search_index_group")
	private String searchIndexGroup;

	@Column(name="search_index_name")
	private String searchIndexName;

	//bi-directional many-to-one association to MetaSearchInput
	@OneToMany(mappedBy="metaSearchIndex")
	private List<MetaSearchInput> metaSearchInputs;

	//bi-directional many-to-one association to MetaSearchParam
	@OneToMany(mappedBy="metaSearchIndex")
	private List<MetaSearchParam> metaSearchParams;

	public int getSearchIndexId() {
		return this.searchIndexId;
	}

	public void setSearchIndexId(int searchIndexId) {
		this.searchIndexId = searchIndexId;
	}

	public String getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSearchHost() {
		return this.searchHost;
	}

	public void setSearchHost(String searchHost) {
		this.searchHost = searchHost;
	}

	public String getSearchIndexGroup() {
		return this.searchIndexGroup;
	}

	public void setSearchIndexGroup(String searchIndexGroup) {
		this.searchIndexGroup = searchIndexGroup;
	}

	public String getSearchIndexName() {
		return this.searchIndexName;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	public List<MetaSearchInput> getMetaSearchInputs() {
		return this.metaSearchInputs;
	}

	public void setMetaSearchInputs(List<MetaSearchInput> metaSearchInputs) {
		this.metaSearchInputs = metaSearchInputs;
	}
	
	public List<MetaSearchParam> getMetaSearchParams() {
		return this.metaSearchParams;
	}

	public void setMetaSearchParams(List<MetaSearchParam> metaSearchParams) {
		this.metaSearchParams = metaSearchParams;
	}
	
}