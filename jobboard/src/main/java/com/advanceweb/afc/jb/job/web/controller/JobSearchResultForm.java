/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.web.controller;

public class JobSearchResultForm {

	/** The keywords. */
	private String keywords;
	
	/** The rows. */
	private String rows;
	
	/** The start. */
	private String start;
	
	/** The city state. */
	private String cityState;
	
	/** The radius. */
	private String radius;
	
	/** The searchtype. */
	private String searchtype;
	
	/** The search name. */
	private String searchName;
	
	/** The autoload. */
	private boolean autoload;
	
	/** The refined. */
	private boolean refined;
	
	// Added for Browse JObs
	/** The job position. */
	private String jobPosition;

	/**
	 * Checks if is autoload.
	 *
	 * @return true, if is autoload
	 */
	public boolean isAutoload() {
		return autoload;
	}

	/**
	 * Sets the autoload.
	 *
	 * @param autoload the new autoload
	 */
	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}

	/**
	 * Gets the searchtype.
	 *
	 * @return the searchtype
	 */
	public String getSearchtype() {
		return searchtype;
	}

	/**
	 * Sets the searchtype.
	 *
	 * @param searchtype the new searchtype
	 */
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public String getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 *
	 * @param radius the new radius
	 */
	public void setRadius(String radius) {
		this.radius = radius;
	}

	/**
	 * Gets the city state.
	 *
	 * @return the city state
	 */
	public String getCityState() {
		return cityState;
	}

	/**
	 * Sets the city state.
	 *
	 * @param cityState the new city state
	 */
	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * Checks if is refined.
	 *
	 * @return true, if is refined
	 */
	public boolean isRefined() {
		return refined;
	}

	/**
	 * Sets the refined.
	 *
	 * @param refined the new refined
	 */
	public void setRefined(boolean refined) {
		this.refined = refined;
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * Gets the search name.
	 *
	 * @return the search name
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * Sets the search name.
	 *
	 * @param searchName the new search name
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
