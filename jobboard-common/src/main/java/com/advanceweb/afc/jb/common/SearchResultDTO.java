package com.advanceweb.afc.jb.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField.Count;

public class SearchResultDTO implements Serializable{
	
	private static final long serialVersionUID = -2200423309708244707L;
	private List<JobSearchDTO> searchResultList;
	private Map<String, List<Count>> facetMap;
	private long totalNumSearchResult;
	
	public long getTotalNumSearchResult() {
		return totalNumSearchResult;
	}
	public void setTotalNumSearchResult(long totalNumSearchResult) {
		this.totalNumSearchResult = totalNumSearchResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	/*private static final long serialVersionUID = -2200423309708244707L;
	private String company;
	private String jobtitle;
	private String posted_dt;
	private String city;
	private String state;
	private String job_id;
	private String jobt_number;
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getPosted_dt() {
		return posted_dt;
	}
	public void setPosted_dt(String posted_dt) {
		this.posted_dt = posted_dt;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJobt_number() {
		return jobt_number;
	}
	public void setJobt_number(String jobt_number) {
		this.jobt_number = jobt_number;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	*/

}
