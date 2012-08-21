package com.advanceweb.afc.jb.common;

import java.io.Serializable;

public class SearchFacetDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String facetValue;
	
	private long count;
	
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
