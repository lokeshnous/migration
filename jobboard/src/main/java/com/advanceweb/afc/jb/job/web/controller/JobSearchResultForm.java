package com.advanceweb.afc.jb.job.web.controller;

public class JobSearchResultForm {

	private String keywords;
	private String rows;
	private String start;
	private String cityState;
	private String radius;
	private String searchtype;
	private String saveSearchName;
	private boolean autoload;
	private boolean refined;
	private String jobTitlePage;
	private String employerPage;
	private String locationPage;
	private boolean browseBy;
	// Added for Browse JObs
	private String jobPosition;

	public boolean isBrowseBy() {
		return browseBy;
	}

	public void setBrowseBy(boolean browseBy) {
		this.browseBy = browseBy;
	}

	public boolean isAutoload() {
		return autoload;
	}

	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
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

	public String getSaveSearchName() {
		return saveSearchName;
	}

	public void setSaveSearchName(String saveSearchName) {
		this.saveSearchName = saveSearchName;
	}

	public boolean isRefined() {
		return refined;
	}

	public void setRefined(boolean refined) {
		this.refined = refined;
	}

	public String getJobTitlePage() {
		return jobTitlePage;
	}

	public void setJobTitlePage(String jobTitlePage) {
		this.jobTitlePage = jobTitlePage;
	}

	public String getEmployerPage() {
		return employerPage;
	}

	public void setEmployerPage(String employerPage) {
		this.employerPage = employerPage;
	}

	public String getLocationPage() {
		return locationPage;
	}

	public void setLocationPage(String locationPage) {
		this.locationPage = locationPage;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

}
