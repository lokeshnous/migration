/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.search;

import java.io.Serializable;

public class SearchFacetDTO implements Serializable {

	/** The Constant FACET_JOBPOSITION. */
	public static final String FACET_JOBPOSITION = "jobposition";
	
	/** The Constant FACET_CITY. */
	public static final String FACET_CITY = "city";
	
	/** The Constant FACET_STATE. */
	public static final String FACET_STATE = "state";
	
	/** The Constant FACET_COMPANY. */
	public static final String FACET_COMPANY = "company";
	
	/** The Constant FACET_COMPANY_ID_NAME. */
	public static final String FACET_COMPANY_ID_NAME = "facility_id_name";
	
	/** The Constant FACET_POSTED_DATE. */
	public static final String FACET_POSTED_DATE = "posted_dt";
	
	/** The Constant FACET_RADIUS. */
	public static final String FACET_RADIUS = "radius";
	
	/** The Constant FACET_AREA. */
	public static final String FACET_AREA = "area";
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The facet value. */
	private String facetValue;

	/** The count. */
	private long count;

	/**
	 * Instantiates a new search facet dto.
	 *
	 * @param facetValue the facet value
	 * @param count the count
	 */
	public SearchFacetDTO(String facetValue, long count) {
		this.facetValue = facetValue;
		this.count = count;
	}

	/**
	 * Gets the facet value.
	 *
	 * @return the facet value
	 */
	public String getFacetValue() {
		return facetValue;
	}

	/**
	 * Sets the facet value.
	 *
	 * @param facetValue the new facet value
	 */
	public void setFacetValue(String facetValue) {
		this.facetValue = facetValue;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(long count) {
		this.count = count;
	}

}
