package com.advanceweb.afc.jb.common;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: Jul 25, 2012
 * @Purpose: This class is the DTO for the attributes of the Meta_Search_Param table.
 */

public class SearchParamDTO {
	
	private int searchParamId;

	private String parameterName;

	private String parameterValue;
	
	private int seq;

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


}
