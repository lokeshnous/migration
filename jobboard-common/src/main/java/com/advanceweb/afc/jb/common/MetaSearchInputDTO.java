package com.advanceweb.afc.jb.common;

public class MetaSearchInputDTO {
	
	private  MetaSearchIndexDTO metaSearchIndex;
	
	private MetaSearchTypeDTO metaSearchType;
	
	private int searchTypeId;
	

	private int searchIndexId;

	private String inputName;

	private String inputValue;
	
	private int searchInputId;

	public MetaSearchIndexDTO getMetaSearchIndex() {
		return metaSearchIndex;
	}

	public void setMetaSearchIndex(MetaSearchIndexDTO metaSearchIndex) {
		this.metaSearchIndex = metaSearchIndex;
	}

	public MetaSearchTypeDTO getMetaSearchType() {
		return metaSearchType;
	}

	public void setMetaSearchType(MetaSearchTypeDTO metaSearchType) {
		this.metaSearchType = metaSearchType;
	}

	public int getSearchTypeId() {
		return searchTypeId;
	}

	public void setSearchTypeId(int searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public int getSearchIndexId() {
		return searchIndexId;
	}

	public void setSearchIndexId(int searchIndexId) {
		this.searchIndexId = searchIndexId;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public int getSearchInputId() {
		return searchInputId;
	}

	public void setSearchInputId(int searchInputId) {
		this.searchInputId = searchInputId;
	}
	
	
	
	



}
