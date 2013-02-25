/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 10, 2012
 * @Purpose: This class will be used as a FormBean for the Advance Search
 */
public class JobseekerAdvanceSearchForm {

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
	
	/** The save search name. */
	private String saveSearchName;
	
	/** The autoload. */
	private boolean autoload;
	
	/** The exclude from. */
	private String excludeFrom;
	
	/** The from zip code. */
	private String fromZipCode;
	
	/** The state. */
	private String state;
	
	/** The metro area. */
	private String metroArea;
	
	/** The employment type. */
	private String employmentType;
	
	/** The jobposted date. */
	private String jobpostedDate;
	
	/** The result per page. */
	private String resultPerPage;
	
	/** The state province. */
	private String stateProvince;
	
	
	/**
	 * Gets the state province.
	 *
	 * @return the state province
	 */
	public String getStateProvince() {
		return stateProvince;
	}

	/**
	 * Sets the state province.
	 *
	 * @param stateProvince the new state province
	 */
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
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
	 * Gets the save search name.
	 *
	 * @return the save search name
	 */
	public String getSaveSearchName() {
		return saveSearchName;
	}

	/**
	 * Sets the save search name.
	 *
	 * @param saveSearchName the new save search name
	 */
	public void setSaveSearchName(String saveSearchName) {
		this.saveSearchName = saveSearchName;
	}

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
	 * @return the radius
	 */
	public String getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(String radius) {
		this.radius = radius;
	}

	/**
	 * @return the excludeFrom
	 */
	public String getExcludeFrom() {
		return excludeFrom;
	}

	/**
	 * @param excludeFrom
	 *            the excludeFrom to set
	 */
	public void setExcludeFrom(String excludeFrom) {
		this.excludeFrom = excludeFrom;
	}

	/**
	 * @return the fromZipCode
	 */
	public String getFromZipCode() {
		return fromZipCode;
	}

	/**
	 * @param fromZipCode
	 *            the fromZipCode to set
	 */
	public void setFromZipCode(String fromZipCode) {
		this.fromZipCode = fromZipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the metroArea
	 */
	public String getMetroArea() {
		return metroArea;
	}

	/**
	 * @param metroArea
	 *            the metroArea to set
	 */
	public void setMetroArea(String metroArea) {
		this.metroArea = metroArea;
	}

	/**
	 * @return the employmentType
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * @param employmentType
	 *            the employmentType to set
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * @return the jobpostedDate
	 */
	public String getJobpostedDate() {
		return jobpostedDate;
	}

	/**
	 * @param jobpostedDate
	 *            the jobpostedDate to set
	 */
	public void setJobpostedDate(String jobpostedDate) {
		this.jobpostedDate = jobpostedDate;
	}

	/**
	 * @return the resultPerPage
	 */
	public String getResultPerPage() {
		return resultPerPage;
	}

	/**
	 * @param resultPerPage
	 *            the resultPerPage to set
	 */
	public void setResultPerPage(String resultPerPage) {
		this.resultPerPage = resultPerPage;
	}

}
