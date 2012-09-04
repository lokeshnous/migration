package com.advanceweb.afc.jb.search;

import org.springframework.stereotype.Component;

/**
 * This class has been created for reading the values 
 * directly from the Spring configuration for
 *  the Solr Server Job search functionalities.
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 31st July 2012
 */

@Component("searchIndex")
public class SearchIndex {
	
	private String name;
	
	private String group;
	
	private String environment;
	
	public String getName() {
		return name;
	}

	public void setName(String searchIndexName) {
		this.name = searchIndexName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String searchIndexGroup) {
		this.group = searchIndexGroup;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
