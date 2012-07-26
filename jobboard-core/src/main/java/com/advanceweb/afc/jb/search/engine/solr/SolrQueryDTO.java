package com.advanceweb.afc.jb.search.engine.solr;

public class SolrQueryDTO {
	
	private String searchName;
	
	private String searchIndexName;
	
	private String environment;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchIndexName() {
		return searchIndexName;
	}

	public void setSearchIndexName(String searchIndexName) {
		this.searchIndexName = searchIndexName;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	

}
