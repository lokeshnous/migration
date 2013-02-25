/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.web.controller;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;

/**
 * anilm
 * @version 1.0
 * @created Aug 4, 2012
 */
public class ResumeProfileAttribForm {
	
	/** The str label name. */
	private String strLabelName;
	
	/** The str label value. */
	private String strLabelValue;
	
	/** The str profile attrib id. */
	private String strProfileAttribId;
	
	/** The str attrib type. */
	private String strAttribType;
	
	/** The str section name. */
	private String strSectionName;
	
	/** The str screen name. */
	private String strScreenName;
	
	/** The dropdown. */
	private List<DropDownDTO> dropdown;
	
	
	/**
	 * Gets the str label name.
	 *
	 * @return the str label name
	 */
	public String getStrLabelName() {
		return strLabelName;
	}
	
	/**
	 * Sets the str label name.
	 *
	 * @param strLabelName the new str label name
	 */
	public void setStrLabelName(String strLabelName) {
		this.strLabelName = strLabelName;
	}
	
	/**
	 * Gets the str label value.
	 *
	 * @return the str label value
	 */
	public String getStrLabelValue() {
		return strLabelValue;
	}
	
	/**
	 * Sets the str label value.
	 *
	 * @param strLabelValue the new str label value
	 */
	public void setStrLabelValue(String strLabelValue) {
		this.strLabelValue = strLabelValue;
	}
	
	/**
	 * Gets the str profile attrib id.
	 *
	 * @return the str profile attrib id
	 */
	public String getStrProfileAttribId() {
		return strProfileAttribId;
	}
	
	/**
	 * Sets the str profile attrib id.
	 *
	 * @param strProfileAttribId the new str profile attrib id
	 */
	public void setStrProfileAttribId(String strProfileAttribId) {
		this.strProfileAttribId = strProfileAttribId;
	}
	
	/**
	 * Gets the str attrib type.
	 *
	 * @return the str attrib type
	 */
	public String getStrAttribType() {
		return strAttribType;
	}
	
	/**
	 * Sets the str attrib type.
	 *
	 * @param strAttribType the new str attrib type
	 */
	public void setStrAttribType(String strAttribType) {
		this.strAttribType = strAttribType;
	}
	
	/**
	 * Gets the str section name.
	 *
	 * @return the str section name
	 */
	public String getStrSectionName() {
		return strSectionName;
	}
	
	/**
	 * Sets the str section name.
	 *
	 * @param strSectionName the new str section name
	 */
	public void setStrSectionName(String strSectionName) {
		this.strSectionName = strSectionName;
	}
	
	/**
	 * Gets the str screen name.
	 *
	 * @return the str screen name
	 */
	public String getStrScreenName() {
		return strScreenName;
	}
	
	/**
	 * Sets the str screen name.
	 *
	 * @param strScreenName the new str screen name
	 */
	public void setStrScreenName(String strScreenName) {
		this.strScreenName = strScreenName;
	}
	
	/**
	 * Gets the dropdown.
	 *
	 * @return the dropdown
	 */
	public List<DropDownDTO> getDropdown() {
		return dropdown;
	}
	
	/**
	 * Sets the dropdown.
	 *
	 * @param dropdown the new dropdown
	 */
	public void setDropdown(List<DropDownDTO> dropdown) {
		this.dropdown = dropdown;
	}
	
}
