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
 * The persistent class for the meta_search_type database table.
 * 
 */
@Entity
@Table(name="meta_search_type")
public class MetaSearchType implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The search type id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_type_id")
	private int searchTypeId;

	/** The search type name. */
	@Column(name="search_type_name")
	private String searchTypeName;

	//bi-directional many-to-one association to MetaSearchInput
	/** The meta search inputs. */
	@OneToMany(mappedBy="metaSearchType")
	private List<MetaSearchInput> metaSearchInputs;

	//bi-directional many-to-one association to MetaSearchParam
	/** The meta search params. */
	@OneToMany(mappedBy="metaSearchType")
	private List<MetaSearchParam> metaSearchParams;

	/**
	 * Gets the search type id.
	 *
	 * @return the search type id
	 */
	public int getSearchTypeId() {
		return this.searchTypeId;
	}

	/**
	 * Sets the search type id.
	 *
	 * @param searchTypeId the new search type id
	 */
	public void setSearchTypeId(int searchTypeId) {
		this.searchTypeId = searchTypeId;
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