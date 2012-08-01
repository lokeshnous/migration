package com.advanceweb.afc.jb.common.util;

import org.springframework.stereotype.Component;

/**
 * This class has been created for reading the values 
 * directly from the Spring configuration for
 *  the Solr Server Job search functionalities
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31st July 2012
 */

@Component("solrParameter")
public class SolrParameter {
	
	private String searchIndexName;
	
	private String searchIndexGroup;
	
	private String environment;
	
	private String searchName;

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
	
	
	


}
