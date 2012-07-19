package com.advanceweb.afc.jb.search.engine.solr;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField.Count;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class SolrJobSearchResultDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244307L;
	private List<JobSearchDTO> searchResultList;
	private Map<String, List<Count>> facetMap;
	private long totalNumSearchResult;
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
	public long getTotalNumSearchResult() {
		return totalNumSearchResult;
	}
	public void setTotalNumSearchResult(long totalNumSearchResult) {
		this.totalNumSearchResult = totalNumSearchResult;
	}
	public List<JobSearchDTO> getSearchResultList() {
		return searchResultList;
	}
	public void setSearchResultList(List<JobSearchDTO> searchResultList) {
		this.searchResultList = searchResultList;
	}
	public Map<String, List<Count>> getFacetMap() {
		return facetMap;
	}
	public void setFacetMap(Map<String, List<Count>> facetMap) {
		this.facetMap = facetMap;
	}
	
	
}
