/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.List;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class ProfileAttribDTO {
	
	/** The str label name. */
	private String strLabelName;
	
	/** The str label value. */
	private String strLabelValue;
	
	/** The str profile attrib id. */
	private String strProfileAttribId;
	
	/** The str attrib type. */
	private String strAttribType;
	
	/** The required. */
	private int required;
	
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
	
	/**
	 * Gets the required.
	 *
	 * @return the required
	 */
	public int getRequired() {
		return required;
	}
	
	/**
	 * Sets the required.
	 *
	 * @param required the new required
	 */
	public void setRequired(int required) {
		this.required = required;
	}

}
