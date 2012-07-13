package com.advanceweb.afc.jb.webapp.web.forms.jobsearch;

import com.advanceweb.afc.jb.common.SearchResultDTO;

public class JobSearchResultForm {
	
	private SearchResultDTO searchResultDTO;
	private String searchString;
	private String rows;
	private String start;


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

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public SearchResultDTO getSearchResultDTO() {
		return searchResultDTO;
	}

	public void setSearchResultDTO(SearchResultDTO searchResultDTO) {
		this.searchResultDTO = searchResultDTO;
	}

}
