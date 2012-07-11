package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of By Metro Area field in Jobseekers Advance Search
 */
public class MetroAreaDTO implements Serializable {
	
	private String metroAreaId;
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
