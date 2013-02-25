/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class WorkExpDTO {
	
	/** The job title. */
	private String jobTitle;
	
	/** The employer name. */
	private String employerName;
	
	/** The employment type. */
	private String employmentType;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The yrs at postion. */
	private String yrsAtPostion;
	
	/** The current career lvl. */
	private String currentCareerLvl;
	
	/** The annual salary. */
	private String annualSalary;
	
	/** The hrly pay rate. */
	private String hrlyPayRate;
	
	/** The description. */
	private String description;
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The still employed. */
	private int stillEmployed;
	
	/** The b current career level. */
	private boolean bCurrentCareerLevel;
	
	/** The b present. */
	private boolean bPresent;
	
	/** The builder emp id. */
	private int builderEmpId;
	
	/** The item id. */
	private int itemId;
	
	/** The edit mode. */
	private boolean editMode;
	
	
	
	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public int getItemId() {
		return itemId;
	}
	
	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * Checks if is edits the mode.
	 *
	 * @return true, if is edits the mode
	 */
	public boolean isEditMode() {
		return editMode;
	}
	
	/**
	 * Sets the edits the mode.
	 *
	 * @param editMode the new edits the mode
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets the employer name.
	 *
	 * @return the employer name
	 */
	public String getEmployerName() {
		return employerName;
	}
	
	/**
	 * Sets the employer name.
	 *
	 * @param employerName the new employer name
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	/**
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}
	
	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Gets the yrs at postion.
	 *
	 * @return the yrs at postion
	 */
	public String getYrsAtPostion() {
		return yrsAtPostion;
	}
	
	/**
	 * Sets the yrs at postion.
	 *
	 * @param yrsAtPostion the new yrs at postion
	 */
	public void setYrsAtPostion(String yrsAtPostion) {
		this.yrsAtPostion = yrsAtPostion;
	}
	
	/**
	 * Gets the current career lvl.
	 *
	 * @return the current career lvl
	 */
	public String getCurrentCareerLvl() {
		return currentCareerLvl;
	}
	
	/**
	 * Sets the current career lvl.
	 *
	 * @param currentCareerLvl the new current career lvl
	 */
	public void setCurrentCareerLvl(String currentCareerLvl) {
		this.currentCareerLvl = currentCareerLvl;
	}
	
	/**
	 * Gets the annual salary.
	 *
	 * @return the annual salary
	 */
	public String getAnnualSalary() {
		return annualSalary;
	}
	
	/**
	 * Sets the annual salary.
	 *
	 * @param annualSalary the new annual salary
	 */
	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}
	
	/**
	 * Gets the hrly pay rate.
	 *
	 * @return the hrly pay rate
	 */
	public String getHrlyPayRate() {
		return hrlyPayRate;
	}
	
	/**
	 * Sets the hrly pay rate.
	 *
	 * @param hrlyPayRate the new hrly pay rate
	 */
	public void setHrlyPayRate(String hrlyPayRate) {
		this.hrlyPayRate = hrlyPayRate;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the builder emp id.
	 *
	 * @return the builder emp id
	 */
	public int getBuilderEmpId() {
		return builderEmpId;
	}
	
	/**
	 * Sets the builder emp id.
	 *
	 * @param builderEmpId the new builder emp id
	 */
	public void setBuilderEmpId(int builderEmpId) {
		this.builderEmpId = builderEmpId;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the still employed.
	 *
	 * @return the still employed
	 */
	public int getStillEmployed() {
		return stillEmployed;
	}
	
	/**
	 * Sets the still employed.
	 *
	 * @param stillEmployed the new still employed
	 */
	public void setStillEmployed(int stillEmployed) {
		this.stillEmployed = stillEmployed;
	}
	
	/**
	 * Checks if is b current career level.
	 *
	 * @return true, if is b current career level
	 */
	public boolean isbCurrentCareerLevel() {
		return bCurrentCareerLevel;
	}
	
	/**
	 * Sets the b current career level.
	 *
	 * @param bCurrentCareerLevel the new b current career level
	 */
	public void setbCurrentCareerLevel(boolean bCurrentCareerLevel) {
		this.bCurrentCareerLevel = bCurrentCareerLevel;
	}
	
	/**
	 * Checks if is b present.
	 *
	 * @return true, if is b present
	 */
	public boolean isbPresent() {
		return bPresent;
	}
	
	/**
	 * Sets the b present.
	 *
	 * @param bPresent the new b present
	 */
	public void setbPresent(boolean bPresent) {
		this.bPresent = bPresent;
	}

	
}
