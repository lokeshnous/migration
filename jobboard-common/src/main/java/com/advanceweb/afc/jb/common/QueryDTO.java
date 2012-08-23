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
	
	private String searchIndexName;
	
	private String searchIndexGroup;
	
	private String environment;
	
	private String searchName;
	
	private String searchHost;
	
	private List<SearchParamDTO> mSrchParamList;
	
	public String getSearchHost() {
		return searchHost;
	}

	public void setSearchHost(String searchHost) {
		this.searchHost = searchHost;
	}

	public String getSearchIndexName() {
		return searchIndexName;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	public String getSearchIndexGroup() {
		return searchIndexGroup;
	}

	public void setSearchIndexGroup(String searchIndexGroup) {
		this.searchIndexGroup = searchIndexGroup;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<SearchParamDTO> getmSrchParamList() {
		return mSrchParamList;
	}

	public void setmSrchParamList(List<SearchParamDTO> mSrchParamList) {
		this.mSrchParamList = mSrchParamList;
	}
	

}
