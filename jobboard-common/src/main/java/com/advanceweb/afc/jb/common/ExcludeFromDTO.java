package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of Exclude From field in Jobseekers Advance Search
 */
public class ExcludeFromDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String excludeFromId;
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
