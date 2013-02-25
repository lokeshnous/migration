/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

/**
 * @Author : Reetesh RN
 * @Version: 1.0
 * @Created: Jul 25, 2012
 * @Purpose: This class is the DTO for the attributes of the Meta_Search_Param table.
 */

public class SearchParamDTO {

	/** The Constant KEYWORDS. */
	public static final String KEYWORDS = "keywords";
	
	/** The Constant CITY_STATE. */
	public static final String CITY_STATE = "cityState";
	
	/** The Constant RADIUS. */
	public static final String RADIUS = "radius";
	
	/** The Constant SEARCH_SEQ. */
	public static final String SEARCH_SEQ = "search_seq";
	
	/** The Constant SEARCH_NAME. */
	public static final String SEARCH_NAME = "searchName";
	
	/** The Constant SESSION_ID. */
	public static final String SESSION_ID = "sessionid";
	
	/** The Constant ROWS. */
	public static final String ROWS = "rows";
	
	/** The Constant START. */
	public static final String START = "start";
	
	/** The Constant SEARCH_SESSION_MAP. */
	public static final String SEARCH_SESSION_MAP = "sessionMap";
	
	/** The Constant RESUME_SEARCH_SESSION_MAP. */
	public static final String RESUME_SEARCH_SESSION_MAP = "resumeSessionMap";
	
	/** The Constant PHRASE. */
	public static final String PHRASE = "phrase";
	
	/** The Constant REFINED. */
	public static final String REFINED = "refined";

	/** The search param id. */
	private int searchParamId;

	/** The parameter name. */
	private String parameterName;

	/** The parameter value. */
	private String parameterValue;
	
	/** The seq. */
	private int seq;

	/**
	 * Gets the search param id.
	 *
	 * @return the search param id
	 */
	public int getSearchParamId() {
		return searchParamId;
	}

	/**
	 * Sets the search param id.
	 *
	 * @param searchParamId the new search param id
	 */
	public void setSearchParamId(int searchParamId) {
		this.searchParamId = searchParamId;
	}

	/**
	 * Gets the parameter name.
	 *
	 * @return the parameter name
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * Sets the parameter name.
	 *
	 * @param parameterName the new parameter name
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * Gets the parameter value.
	 *
	 * @return the parameter value
	 */
	public String getParameterValue() {
		return parameterValue;
	}

	/**
	 * Sets the parameter value.
	 *
	 * @param parameterValue the new parameter value
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}


}
