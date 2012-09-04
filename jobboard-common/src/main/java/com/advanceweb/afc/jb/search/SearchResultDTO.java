package com.advanceweb.afc.jb.search;

import java.util.List;
import java.util.Map;

public class SearchResultDTO<T> {

	private long resultCount;

	private List<T> resultList;

	private Map<String, List<SearchFacetDTO>> facetMap;

	private long start;

	private long rows;

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getResultCount() {
		return resultCount;
	}

	public void setResultCount(long count) {
		this.resultCount = count;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> jobResultList) {
		this.resultList = jobResultList;
	}

	public Map<String, List<SearchFacetDTO>> getFacetMap() {
		return facetMap;
	}

	public void setFacetMap(Map<String, List<SearchFacetDTO>> facetMap) {
		this.facetMap = facetMap;
	}
}
