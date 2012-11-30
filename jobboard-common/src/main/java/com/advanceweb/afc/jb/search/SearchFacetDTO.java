package com.advanceweb.afc.jb.search;

import java.io.Serializable;

public class SearchFacetDTO implements Serializable {

	public static final String FACET_JOBPOSITION = "jobposition";
	public static final String FACET_CITY = "city";
	public static final String FACET_STATE = "state";
	public static final String FACET_COMPANY = "company";
	public static final String FACET_POSTED_DATE = "posted_dt";
	public static final String FACET_RADIUS = "radius";
	public static final String FACET_AREA = "area";
	private static final long serialVersionUID = 1L;

	private String facetValue;

	private long count;

	public SearchFacetDTO(String facetValue, long count) {
		this.facetValue = facetValue;
		this.count = count;
	}

	public String getFacetValue() {
		return facetValue;
	}

	public void setFacetValue(String facetValue) {
		this.facetValue = facetValue;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
