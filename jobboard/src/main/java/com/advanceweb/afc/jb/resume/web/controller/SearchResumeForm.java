/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

public class SearchResumeForm {

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
	
	/** The phrase. */
	private String phrase;
	
	/** The user id. */
	private int userID;
	
	/** The save searched jobs dto list. */
	private List<SaveSearchedJobsDTO> saveSearchedJobsDTOList;
	
	/** The save search name. */
	private String saveSearchName;
	
	/** The autoload. */
	private boolean autoload;
	
	/** The refined. */
	private boolean refined;
	
	/** The job title page. */
	private String jobTitlePage;
	
	/** The employer page. */
	private String employerPage;
	
	/** The location page. */
	private String locationPage;
	
	/** The browse by. */
	private boolean browseBy;
	
	//private String saveSearchName;
	/*private boolean autoload;*/

	/**
	 * @return the saveSearchName
	 */
	public String getSaveSearchName() {
		return saveSearchName;
	}

	/**
	 * @param saveSearchName the saveSearchName to set
	 */
	public void setSaveSearchName(String saveSearchName) {
		this.saveSearchName = saveSearchName;
	}

	/**
	 * @return the autoload
	 */
	public boolean isAutoload() {
		return autoload;
	}

	/**
	 * @param autoload the autoload to set
	 */
	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}

	/**
	 * @return the refined
	 */
	public boolean isRefined() {
		return refined;
	}

	/**
	 * @param refined the refined to set
	 */
	public void setRefined(boolean refined) {
		this.refined = refined;
	}

	/**
	 * @return the jobTitlePage
	 */
	public String getJobTitlePage() {
		return jobTitlePage;
	}

	/**
	 * @param jobTitlePage the jobTitlePage to set
	 */
	public void setJobTitlePage(String jobTitlePage) {
		this.jobTitlePage = jobTitlePage;
	}

	/**
	 * @return the employerPage
	 */
	public String getEmployerPage() {
		return employerPage;
	}

	/**
	 * @param employerPage the employerPage to set
	 */
	public void setEmployerPage(String employerPage) {
		this.employerPage = employerPage;
	}

	/**
	 * @return the locationPage
	 */
	public String getLocationPage() {
		return locationPage;
	}

	/**
	 * @param locationPage the locationPage to set
	 */
	public void setLocationPage(String locationPage) {
		this.locationPage = locationPage;
	}

	/**
	 * @return the browseBy
	 */
	public boolean isBrowseBy() {
		return browseBy;
	}

	/**
	 * @param browseBy the browseBy to set
	 */
	public void setBrowseBy(boolean browseBy) {
		this.browseBy = browseBy;
	}

	/**
	 * @return the saveSearchedJobsDTOList
	 */
	public List<SaveSearchedJobsDTO> getSaveSearchedJobsDTOList() {
		return saveSearchedJobsDTOList;
	}

	/**
	 * @param saveSearchedJobsDTOList the saveSearchedJobsDTOList to set
	 */
	public void setSaveSearchedJobsDTOList(
			List<SaveSearchedJobsDTO> saveSearchedJobsDTOList) {
		this.saveSearchedJobsDTOList = saveSearchedJobsDTOList;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Gets the phrase.
	 *
	 * @return the phrase
	 */
	public String getPhrase() {
		return phrase;
	}

	/**
	 * Sets the phrase.
	 *
	 * @param phrase the new phrase
	 */
	public void setPhrase(String phrase) {
		this.phrase = phrase;
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

}
