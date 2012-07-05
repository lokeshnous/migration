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
 * The persistent class for the meta_search_type database table.
 * 
 */
@Entity
@Table(name = "meta_search_type")
public class MetaSearchType implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to MetaSearchInput
	@OneToMany(mappedBy = "metaSearchType")
	private List<MetaSearchInput> metaSearchInputs;

	// bi-directional many-to-one association to MetaSearchParam
	@OneToMany(mappedBy = "metaSearchType")
	private List<MetaSearchParam> metaSearchParams;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_type_id", insertable = false, updatable = false)
	private int searchTypeId;

	@Column(name = "search_type_name")
	private String searchTypeName;

	public MetaSearchType() {
	}

	public List<MetaSearchInput> getMetaSearchInputs() {
		return metaSearchInputs;
	}

	public List<MetaSearchParam> getMetaSearchParams() {
		return metaSearchParams;
	}

	public int getSearchTypeId() {
		return searchTypeId;
	}

	public String getSearchTypeName() {
		return searchTypeName;
	}

	public void setMetaSearchInputs(List<MetaSearchInput> metaSearchInputs) {
		this.metaSearchInputs = metaSearchInputs;
	}

	public void setMetaSearchParams(List<MetaSearchParam> metaSearchParams) {
		this.metaSearchParams = metaSearchParams;
	}

	public void setSearchTypeId(int searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
	}

}