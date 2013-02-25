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
   @Purpose: This class will act as a DTO for the Dropdown of By Metro Area field in Jobseekers Advance Search
 */
public class MetroAreaDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The metro area id. */
	private String metroAreaId;
	
	/** The metro area value. */
	private String metroAreaValue;
	
	
	/**
	 * @return the metroAreaId
	 */
	public String getMetroAreaId() {
		return metroAreaId;
	}
	/**
	 * @param metroAreaId the metroAreaId to set
	 */
	public void setMetroAreaId(String metroAreaId) {
		this.metroAreaId = metroAreaId;
	}
	/**
	 * @return the metroAreaValue
	 */
	public String getMetroAreaValue() {
		return metroAreaValue;
	}
	/**
	 * @param metroAreaValue the metroAreaValue to set
	 */
	public void setMetroAreaValue(String metroAreaValue) {
		this.metroAreaValue = metroAreaValue;
	}
	
	
	
}
