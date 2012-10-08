package com.advanceweb.afc.jb.search;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: Jul 25, 2012
 * @Purpose: This class is the DTO for the attributes of the Meta_Search_Param table.
 */

public class SearchParamDTO {

	public static final String KEYWORDS = "keywords";
	public static final String CITY_STATE = "cityState";
	public static final String RADIUS = "radius";
	public static final String SEARCH_SEQ = "search_seq";
	public static final String SEARCH_NAME = "searchName";
	public static final String SESSION_ID = "sessionid";
	public static final String ROWS = "rows";
	public static final String START = "start";
	public static final String SEARCH_SESSION_MAP = "sessionMap";
	public static final String PHRASE = "phrase";

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
