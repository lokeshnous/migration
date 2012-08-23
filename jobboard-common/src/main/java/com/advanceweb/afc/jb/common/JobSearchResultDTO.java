package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.advanceweb.afc.jb.search.SearchFacetDTO;

/**
 * @author ReeteshRN
 * @version 1.0
 * @Date 10th July 2012 onwards
 */

public class JobSearchResultDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<JobDTO> jobResultList;
	
	private Map<String, List<SearchFacetDTO>> facetMap;
	
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

	public List<JobDTO> getJobResultList() {
		return jobResultList;
	}
	public void setJobResultList(List<JobDTO> jobResultList) {
		this.jobResultList = jobResultList;
	}
	public Map<String, List<SearchFacetDTO>> getFacetMap() {
		return facetMap;
	}
	public void setFacetMap(Map<String, List<SearchFacetDTO>> facetMap) {
		this.facetMap = facetMap;
	}
	
	
}
