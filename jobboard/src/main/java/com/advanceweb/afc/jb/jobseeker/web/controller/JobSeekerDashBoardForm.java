package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobSeekerDashBoardForm {
	private String userName;
	private int savedSearchCount;
	private int savedJobsCount;
	private int appliedJobsCount;
	private String keywordsToSavedSearch;
	
	private String cityStateToSavedSearch;
	
	private String radiusToSavedSearch;
	
	private String searchtypeToSavedSearch;

	
	public String getKeywordsToSavedSearch() {
		return keywordsToSavedSearch;
	}

	public void setKeywordsToSavedSearch(String keywordsToSavedSearch) {
		this.keywordsToSavedSearch = keywordsToSavedSearch;
	}

	public String getCityStateToSavedSearch() {
		return cityStateToSavedSearch;
	}

	public void setCityStateToSavedSearch(String cityStateToSavedSearch) {
		this.cityStateToSavedSearch = cityStateToSavedSearch;
	}

	public String getRadiusToSavedSearch() {
		return radiusToSavedSearch;
	}

	public void setRadiusToSavedSearch(String radiusToSavedSearch) {
		this.radiusToSavedSearch = radiusToSavedSearch;
	}

	public String getSearchtypeToSavedSearch() {
		return searchtypeToSavedSearch;
	}

	public void setSearchtypeToSavedSearch(String searchtypeToSavedSearch) {
		this.searchtypeToSavedSearch = searchtypeToSavedSearch;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSavedSearchCount() {
		return savedSearchCount;
	}

	public void setSavedSearchCount(int savedSearchCount) {
		this.savedSearchCount = savedSearchCount;
	}

	public int getSavedJobsCount() {
		return savedJobsCount;
	}

	public void setSavedJobsCount(int savedJobsCount) {
		this.savedJobsCount = savedJobsCount;
	}

	public int getAppliedJobsCount() {
		return appliedJobsCount;
	}

	public void setAppliedJobsCount(int appliedJobsCount) {
		this.appliedJobsCount = appliedJobsCount;
	}
	
}
