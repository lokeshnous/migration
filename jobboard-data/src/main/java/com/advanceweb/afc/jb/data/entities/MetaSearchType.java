package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the meta_search_type database table.
 * 
 */
@Entity
@Table(name="meta_search_type")
public class MetaSearchType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_type_id")
	private int searchTypeId;

	@Column(name="search_type_name")
	private String searchTypeName;

	//bi-directional many-to-one association to MetaSearchInput
	@OneToMany(mappedBy="metaSearchType")
	private List<MetaSearchInput> metaSearchInputs;

	//bi-directional many-to-one association to MetaSearchParam
	@OneToMany(mappedBy="metaSearchType")
	private List<MetaSearchParam> metaSearchParams;

    public MetaSearchType() {
    }

	public int getSearchTypeId() {
		return this.searchTypeId;
	}

	public void setSearchTypeId(int searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public String getSearchTypeName() {
		return this.searchTypeName;
	}

	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
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