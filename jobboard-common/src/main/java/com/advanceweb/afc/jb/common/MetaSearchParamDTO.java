package com.advanceweb.afc.jb.common;



public class MetaSearchParamDTO {
	
	private int searchParamId;

	private String parameterName;

	private String parameterValue;

	public int getSearchParamId() {
		return searchParamId;
	}

	public void setSearchParamId(int searchParamId) {
		this.searchParamId = searchParamId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public MetaSearchIndexDTO getMetaSearchIndexDTO() {
		return metaSearchIndexDTO;
	}

	public void setMetaSearchIndexDTO(MetaSearchIndexDTO metaSearchIndexDTO) {
		this.metaSearchIndexDTO = metaSearchIndexDTO;
	}

	public MetaSearchTypeDTO getMetaSearchTypeDTO() {
		return metaSearchTypeDTO;
	}

	public void setMetaSearchTypeDTO(MetaSearchTypeDTO metaSearchTypeDTO) {
		this.metaSearchTypeDTO = metaSearchTypeDTO;
	}

	private int seq;

	private MetaSearchIndexDTO metaSearchIndexDTO;

	private MetaSearchTypeDTO metaSearchTypeDTO;

	

}
