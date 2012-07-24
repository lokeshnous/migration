package com.advanceweb.afc.jb.job.web.controller;

import com.advanceweb.afc.jb.search.engine.solr.SearchResultDTO;

public class JobSearchResultForm {
	
	private SearchResultDTO searchResultDTO;
	private String keywords;
	private String rows;
	private String start;
	private String cityState;
	private String radius;


	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getCityState() {
		return cityState;
	}

	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public SearchResultDTO getSearchResultDTO() {
		return searchResultDTO;
	}

	public void setSearchResultDTO(SearchResultDTO searchResultDTO) {
		this.searchResultDTO = searchResultDTO;
	}

}
