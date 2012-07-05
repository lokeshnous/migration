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
 * The persistent class for the meta_search_param database table.
 * 
 */
@Entity
@Table(name = "meta_search_param")
public class MetaSearchParam implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to MetaSearchIndex
	@ManyToOne
	@JoinColumn(name = "search_index_id")
	private MetaSearchIndex metaSearchIndex;

	// bi-directional many-to-one association to MetaSearchType
	@ManyToOne
	@JoinColumn(name = "search_type_id")
	private MetaSearchType metaSearchType;

	@Column(name = "parameter_name")
	private String parameterName;

	@Column(name = "parameter_value")
	private String parameterValue;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_param_id", insertable = false, updatable = false)
	private int searchParamId;

	private int seq;

	public MetaSearchParam() {
	}

	public MetaSearchIndex getMetaSearchIndex() {
		return metaSearchIndex;
	}

	public MetaSearchType getMetaSearchType() {
		return metaSearchType;
	}

	public String getParameterName() {
		return parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public int getSearchParamId() {
		return searchParamId;
	}

	public int getSeq() {
		return seq;
	}

	public void setMetaSearchIndex(MetaSearchIndex metaSearchIndex) {
		this.metaSearchIndex = metaSearchIndex;
	}

	public void setMetaSearchType(MetaSearchType metaSearchType) {
		this.metaSearchType = metaSearchType;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public void setSearchParamId(int searchParamId) {
		this.searchParamId = searchParamId;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}