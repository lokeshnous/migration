package com.advanceweb.afc.jb.data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the meta_search_param database table.
 * 
 */
@Entity
@Table(name="meta_search_param")
public class MetaSearchParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="search_param_id")
	private int searchParamId;

	@Column(name="parameter_name")
	private String parameterName;

	@Column(name="parameter_value")
	private String parameterValue;

	public int getSearchIndexId() {
		return searchIndexId;
	}

	public void setSearchIndexId(int searchIndexId) {
		this.searchIndexId = searchIndexId;
	}

	@Column(name="search_index_id")
	private int searchIndexId;
	
	@Column(name="search_type_id")
	private int searchTypeId;
	   
	public int getSearchTypeId() {
		return searchTypeId;
	}

	public void setSearchTypeId(int searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	private int seq;

	//bi-directional many-to-one association to MetaSearchIndex
    @ManyToOne
	@JoinColumn(name="search_index_id", insertable = false, updatable = false)
	private MetaSearchIndex metaSearchIndex;

	//bi-directional many-to-one association to MetaSearchType
    @ManyToOne
	@JoinColumn(name="search_type_id", insertable = false, updatable = false)
	private MetaSearchType metaSearchType;

    public MetaSearchParam() {
    }

	public int getSearchParamId() {
		return this.searchParamId;
	}

	public void setSearchParamId(int searchParamId) {
		this.searchParamId = searchParamId;
	}

	public String getParameterName() {
		return this.parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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