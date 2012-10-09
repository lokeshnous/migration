package com.advanceweb.afc.jb.jobseeker.web.controller;

/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 10, 2012
 * @Purpose: This class will be used as a FormBean for the Advance Search
 */
public class JobseekerAdvanceSearchForm {

	private String keywords;
	private String rows;
	private String start;
	private String cityState;
	private String radius;
	private String searchtype;
	private String saveSearchName;
	private boolean autoload;
	private String excludeFrom;
	private String fromZipCode;
	private String state;
	private String metroArea;
	private String employmentType;
	private String jobpostedDate;
	private String resultPerPage;
	private String stateProvince;
	
	
	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public String getCityState() {
		return cityState;
	}

	public void setCityState(String cityState) {
		this.cityState = cityState;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getSaveSearchName() {
		return saveSearchName;
	}

	public void setSaveSearchName(String saveSearchName) {
		this.saveSearchName = saveSearchName;
	}

	public boolean isAutoload() {
		return autoload;
	}

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
