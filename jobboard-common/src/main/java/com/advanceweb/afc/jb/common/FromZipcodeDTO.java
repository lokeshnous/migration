package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act a DTO for the Dropdown of By Zipcode field in Jobseekers Advance Search
 */
public class FromZipcodeDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String fromZipcodeId;
	private String fromZipcodeValue;
	
	
	/**
	 * @return the fromZipcodeId
	 */
	public String getFromZipcodeId() {
		return fromZipcodeId;
	}
	/**
	 * @param fromZipcodeId the fromZipcodeId to set
	 */
	public void setFromZipcodeId(String fromZipcodeId) {
		this.fromZipcodeId = fromZipcodeId;
	}
	/**
	 * @return the fromZipcodeValue
	 */
	public String getFromZipcodeValue() {
		return fromZipcodeValue;
	}
	/**
	 * @param fromZipcodeValue the fromZipcodeValue to set
	 */
	public void setFromZipcodeValue(String fromZipcodeValue) {
		this.fromZipcodeValue = fromZipcodeValue;
	}
	
	
	
}
