/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of Exclude From field in Jobseekers Advance Search
 */
public class ExcludeFromDTO implements Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The exclude from id. */
	private String excludeFromId;
	
	/** The exclude from value. */
	private String excludeFromValue;
	
	
	/**
	 * @return the excludeFromId
	 */
	public String getExcludeFromId() {
		return excludeFromId;
	}
	/**
	 * @param excludeFromId the excludeFromId to set
	 */
	public void setExcludeFromId(String excludeFromId) {
		this.excludeFromId = excludeFromId;
	}
	/**
	 * @return the excludeFromValue
	 */
	public String getExcludeFromValue() {
		return excludeFromValue;
	}
	/**
	 * @param excludeFromValue the excludeFromValue to set
	 */
	public void setExcludeFromValue(String excludeFromValue) {
		this.excludeFromValue = excludeFromValue;
	}
	
	
	
	
}
