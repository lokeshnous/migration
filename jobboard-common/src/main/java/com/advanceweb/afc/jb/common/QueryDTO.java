/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

import com.advanceweb.afc.jb.search.SearchParamDTO;

/**
 * This class has been created for reading the values from the Database which will be
 * used for creating the SOLR query  
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31st July 2012
 */

public class QueryDTO {
	
	/** The search index name. */
	private String searchIndexName;
	
	/** The search index group. */
	private String searchIndexGroup;
	
	/** The environment. */
	private String environment;
	
	/** The search name. */
	private String searchName;
	
	/** The search host. */
	private String searchHost;
	
	/** The m srch param list. */
	private List<SearchParamDTO> mSrchParamList;
	
	/**
	 * Gets the search host.
	 *
	 * @return the search host
	 */
	public String getSearchHost() {
		return searchHost;
	}

	/**
	 * Sets the search host.
	 *
	 * @param searchHost the new search host
	 */
	public void setSearchHost(String searchHost) {
		this.searchHost = searchHost;
	}

	/**
	 * Gets the search index name.
	 *
	 * @return the search index name
	 */
	public String getSearchIndexName() {
		return searchIndexName;
	}

	/**
	 * Sets the search index name.
	 *
	 * @param searchIndexName the new search index name
	 */
	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	/**
	 * Gets the search index group.
	 *
	 * @return the search index group
	 */
	public String getSearchIndexGroup() {
		return searchIndexGroup;
	}

	/**
	 * Sets the search index group.
	 *
	 * @param searchIndexGroup the new search index group
	 */
	public void setSearchIndexGroup(String searchIndexGroup) {
		this.searchIndexGroup = searchIndexGroup;
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * Sets the environment.
	 *
	 * @param environment the new environment
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * Gets the search name.
	 *
	 * @return the search name
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * Sets the search name.
	 *
	 * @param searchName the new search name
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * Gets the m srch param list.
	 *
	 * @return the m srch param list
	 */
	public List<SearchParamDTO> getmSrchParamList() {
		return mSrchParamList;
	}

	/**
	 * Sets the m srch param list.
	 *
	 * @param mSrchParamList the new m srch param list
	 */
	public void setmSrchParamList(List<SearchParamDTO> mSrchParamList) {
		this.mSrchParamList = mSrchParamList;
	}
	

}
