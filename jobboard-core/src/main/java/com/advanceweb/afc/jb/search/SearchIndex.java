/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/** The name. */
	private String name;
	
	/** The group. */
	private String group;
	
	/** The environment. */
	private String environment;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param searchIndexName the new name
	 */
	public void setName(String searchIndexName) {
		this.name = searchIndexName;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param searchIndexGroup the new group
	 */
	public void setGroup(String searchIndexGroup) {
		this.group = searchIndexGroup;
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

}
