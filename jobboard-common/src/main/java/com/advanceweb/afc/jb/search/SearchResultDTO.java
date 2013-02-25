/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

import java.util.List;
import java.util.Map;

public class SearchResultDTO<T> {

	/** The result count. */
	private long resultCount;

	/** The result list. */
	private List<T> resultList;

	/** The facet map. */
	private Map<String, List<SearchFacetDTO>> facetMap;

	/** The start. */
	private long start;

	/** The rows. */
	private long rows;
	
	/** The location. */
	private String location;

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public long getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public long getRows() {
		return rows;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(long rows) {
		this.rows = rows;
	}

	/**
	 * Gets the result count.
	 *
	 * @return the result count
	 */
	public long getResultCount() {
		return resultCount;
	}

	/**
	 * Sets the result count.
	 *
	 * @param count the new result count
	 */
	public void setResultCount(long count) {
		this.resultCount = count;
	}

	/**
	 * Gets the result list.
	 *
	 * @return the result list
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * Sets the result list.
	 *
	 * @param jobResultList the new result list
	 */
	public void setResultList(List<T> jobResultList) {
		this.resultList = jobResultList;
	}

	/**
	 * Gets the facet map.
	 *
	 * @return the facet map
	 */
	public Map<String, List<SearchFacetDTO>> getFacetMap() {
		return facetMap;
	}

	/**
	 * Sets the facet map.
	 *
	 * @param facetMap the facet map
	 */
	public void setFacetMap(Map<String, List<SearchFacetDTO>> facetMap) {
		this.facetMap = facetMap;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
