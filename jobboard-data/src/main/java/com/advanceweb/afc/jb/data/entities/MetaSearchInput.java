package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the meta_search_input database table.
 * 
 */
@Entity
@Table(name="meta_search_input")
public class MetaSearchInput implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_input_id")
	private int searchInputId;

	@Column(name="input_name")
	private String inputName;

	@Column(name="input_value")
	private String inputValue;

	//bi-directional many-to-one association to MetaSearchIndex
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_index_id")
	private MetaSearchIndex metaSearchIndex;

	//bi-directional many-to-one association to MetaSearchType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="search_type_id")
	private MetaSearchType metaSearchType;

	public int getSearchInputId() {
		return this.searchInputId;
	}

	public void setSearchInputId(int searchInputId) {
		this.searchInputId = searchInputId;
	}

	public String getInputName() {
		return this.inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputValue() {
		return this.inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public MetaSearchIndex getMetaSearchIndex() {
		return this.metaSearchIndex;
	}

	public void setMetaSearchIndex(MetaSearchIndex metaSearchIndex) {
		this.metaSearchIndex = metaSearchIndex;
	}
	
	public MetaSearchType getMetaSearchType() {
		return this.metaSearchType;
	}

	public void setMetaSearchType(MetaSearchType metaSearchType) {
		this.metaSearchType = metaSearchType;
	}
	
}