package com.advanceweb.afc.jb.resume.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.SaveSearchedJobsDTO;

public class SearchResumeForm {

	private String keywords;
	private String rows;
	private String start;
	private String cityState;
	private String radius;
	private String searchtype;
	private String phrase;
	private int userID;
	private List<SaveSearchedJobsDTO> saveSearchedJobsDTOList;
	private String saveSearchName;
	private boolean autoload;
	private boolean refined;
	private String jobTitlePage;
	private String employerPage;
	private String locationPage;
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

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

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

}
