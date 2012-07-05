package com.advanceweb.afc.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "meta_search_input")
public class MetaSearchInput implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "input_name")
	private String inputName;

	@Column(name = "input_value")
	private String inputValue;

	// bi-directional many-to-one association to MetaSearchIndex
	@ManyToOne
	@JoinColumn(name = "search_index_id")
	private MetaSearchIndex metaSearchIndex;

	// bi-directional many-to-one association to MetaSearchType
	@ManyToOne
	@JoinColumn(name = "search_type_id")
	private MetaSearchType metaSearchType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_input_id", insertable = false, updatable = false)
	private int searchInputId;

	public MetaSearchInput() {
	}

	public String getInputName() {
		return inputName;
	}

	public String getInputValue() {
		return inputValue;
	}

	public MetaSearchIndex getMetaSearchIndex() {
		return metaSearchIndex;
	}

	public MetaSearchType getMetaSearchType() {
		return metaSearchType;
	}

	public int getSearchInputId() {
		return searchInputId;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public void setMetaSearchIndex(MetaSearchIndex metaSearchIndex) {
		this.metaSearchIndex = metaSearchIndex;
	}

	public void setMetaSearchType(MetaSearchType metaSearchType) {
		this.metaSearchType = metaSearchType;
	}

	public void setSearchInputId(int searchInputId) {
		this.searchInputId = searchInputId;
	}

}