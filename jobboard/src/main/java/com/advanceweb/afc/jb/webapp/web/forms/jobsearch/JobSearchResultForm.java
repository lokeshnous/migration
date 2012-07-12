package com.advanceweb.afc.jb.webapp.web.forms.jobsearch;

import com.advanceweb.afc.jb.common.SearchResultDTO;

public class JobSearchResultForm {
	
	private SearchResultDTO searchResultDTO;
	private String searchString;

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
