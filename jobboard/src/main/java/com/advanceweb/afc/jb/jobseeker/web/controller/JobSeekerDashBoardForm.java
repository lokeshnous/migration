/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class JobSeekerDashBoardForm {
	
	/** The user name. */
	private String userName;
	
	/** The saved search count. */
	private int savedSearchCount;
	
	/** The saved jobs count. */
	private int savedJobsCount;
	
	/** The applied jobs count. */
	private int appliedJobsCount;
	
	/** The keywords to saved search. */
	private String keywordsToSavedSearch;
	
	/** The city state to saved search. */
	private String cityStateToSavedSearch;
	
	/** The radius to saved search. */
	private String radiusToSavedSearch;
	
	/** The searchtype to saved search. */
	private String searchtypeToSavedSearch;

	
	/**
	 * Gets the keywords to saved search.
	 *
	 * @return the keywords to saved search
	 */
	public String getKeywordsToSavedSearch() {
		return keywordsToSavedSearch;
	}

	/**
	 * Sets the keywords to saved search.
	 *
	 * @param keywordsToSavedSearch the new keywords to saved search
	 */
	public void setKeywordsToSavedSearch(String keywordsToSavedSearch) {
		this.keywordsToSavedSearch = keywordsToSavedSearch;
	}

	/**
	 * Gets the city state to saved search.
	 *
	 * @return the city state to saved search
	 */
	public String getCityStateToSavedSearch() {
		return cityStateToSavedSearch;
	}

	/**
	 * Sets the city state to saved search.
	 *
	 * @param cityStateToSavedSearch the new city state to saved search
	 */
	public void setCityStateToSavedSearch(String cityStateToSavedSearch) {
		this.cityStateToSavedSearch = cityStateToSavedSearch;
	}

	/**
	 * Gets the radius to saved search.
	 *
	 * @return the radius to saved search
	 */
	public String getRadiusToSavedSearch() {
		return radiusToSavedSearch;
	}

	/**
	 * Sets the radius to saved search.
	 *
	 * @param radiusToSavedSearch the new radius to saved search
	 */
	public void setRadiusToSavedSearch(String radiusToSavedSearch) {
		this.radiusToSavedSearch = radiusToSavedSearch;
	}

	/**
	 * Gets the searchtype to saved search.
	 *
	 * @return the searchtype to saved search
	 */
	public String getSearchtypeToSavedSearch() {
		return searchtypeToSavedSearch;
	}

	/**
	 * Sets the searchtype to saved search.
	 *
	 * @param searchtypeToSavedSearch the new searchtype to saved search
	 */
	public void setSearchtypeToSavedSearch(String searchtypeToSavedSearch) {
		this.searchtypeToSavedSearch = searchtypeToSavedSearch;
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the saved search count.
	 *
	 * @return the saved search count
	 */
	public int getSavedSearchCount() {
		return savedSearchCount;
	}

	/**
	 * Sets the saved search count.
	 *
	 * @param savedSearchCount the new saved search count
	 */
	public void setSavedSearchCount(int savedSearchCount) {
		this.savedSearchCount = savedSearchCount;
	}

	/**
	 * Gets the saved jobs count.
	 *
	 * @return the saved jobs count
	 */
	public int getSavedJobsCount() {
		return savedJobsCount;
	}

	/**
	 * Sets the saved jobs count.
	 *
	 * @param savedJobsCount the new saved jobs count
	 */
	public void setSavedJobsCount(int savedJobsCount) {
		this.savedJobsCount = savedJobsCount;
	}

	/**
	 * Gets the applied jobs count.
	 *
	 * @return the applied jobs count
	 */
	public int getAppliedJobsCount() {
		return appliedJobsCount;
	}

	/**
	 * Sets the applied jobs count.
	 *
	 * @param appliedJobsCount the new applied jobs count
	 */
	public void setAppliedJobsCount(int appliedJobsCount) {
		this.appliedJobsCount = appliedJobsCount;
	}
	
}
