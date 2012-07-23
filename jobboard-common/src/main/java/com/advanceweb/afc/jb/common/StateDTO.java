package com.advanceweb.afc.jb.common;

import java.io.Serializable;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 10, 2012
   @Purpose: This class will act as a DTO for the Dropdown of State field in Jobseekers Advance Search
 */
public class StateDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int stateId;
	private String stateValue;
	
	/**
	 * @return the stateId
	 */
	public int getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateValue
	 */
	public String getStateValue() {
		return stateValue;
	}
	/**
	 * @param stateValue the stateValue to set
	 */
	public void setStateValue(String stateValue) {
		this.stateValue = stateValue;
	}
	
	
}
