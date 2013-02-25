/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search index id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_index_id")
	private int searchIndexId;

	/** The environment. */
	private String environment;

	/** The search host. */
	@Column(name="search_host")
	private String searchHost;

	/** The search index group. */
	@Column(name="search_index_group")
	private String searchIndexGroup;

	/** The search index name. */
	@Column(name="search_index_name")
	private String searchIndexName;

	//bi-directional many-to-one association to MetaSearchInput
	/** The meta search inputs. */
	@OneToMany(mappedBy="metaSearchIndex")
	private List<MetaSearchInput> metaSearchInputs;

	//bi-directional many-to-one association to MetaSearchParam
	/** The meta search params. */
	@OneToMany(mappedBy="metaSearchIndex")
	private List<MetaSearchParam> metaSearchParams;

	/**
	 * Gets the search index id.
	 *
	 * @return the search index id
	 */
	public int getSearchIndexId() {
		return this.searchIndexId;
	}

	/**
	 * Sets the search index id.
	 *
	 * @param searchIndexId the new search index id
	 */
	public void setSearchIndexId(int searchIndexId) {
		this.searchIndexId = searchIndexId;
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
	 * Gets the search host.
	 *
	 * @return the search host
	 */
	public String getSearchHost() {
		return this.searchHost;
	}

	/**
	 * Sets the search host.
	 *
	 * @param searchHost the new search host
	 */
	public void setSearchHost(String searchHost) {
		this.searchHost = searchHost;
	}

	/**
	 * Gets the search index group.
	 *
	 * @return the search index group
	 */
	public String getSearchIndexGroup() {
		return this.searchIndexGroup;
	}

	/**
	 * Sets the search index group.
	 *
	 * @param searchIndexGroup the new search index group
	 */
	public void setSearchIndexGroup(String searchIndexGroup) {
		this.searchIndexGroup = searchIndexGroup;
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
	 * Gets the meta search inputs.
	 *
	 * @return the meta search inputs
	 */
	public List<MetaSearchInput> getMetaSearchInputs() {
		return this.metaSearchInputs;
	}

	/**
	 * Sets the meta search inputs.
	 *
	 * @param metaSearchInputs the new meta search inputs
	 */
	public void setMetaSearchInputs(List<MetaSearchInput> metaSearchInputs) {
		this.metaSearchInputs = metaSearchInputs;
	}
	
	/**
	 * Gets the meta search params.
	 *
	 * @return the meta search params
	 */
	public List<MetaSearchParam> getMetaSearchParams() {
		return this.metaSearchParams;
	}

	/**
	 * Sets the meta search params.
	 *
	 * @param metaSearchParams the new meta search params
	 */
	public void setMetaSearchParams(List<MetaSearchParam> metaSearchParams) {
		this.metaSearchParams = metaSearchParams;
	}
	
}