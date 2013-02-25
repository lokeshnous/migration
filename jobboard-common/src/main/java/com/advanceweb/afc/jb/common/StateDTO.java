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
   @Purpose: This class will act as a DTO for the Dropdown of State field in Jobseekers Advance Search
 */
public class StateDTO implements Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The state id. */
	private int stateId;
	
	/** The state key. */
	private String stateKey;
	
	/** The state value. */
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
	
	/**
	 * Gets the state key.
	 *
	 * @return the state key
	 */
	public String getStateKey() {
		return stateKey;
	}
	
	/**
	 * Sets the state key.
	 *
	 * @param stateKey the new state key
	 */
	public void setStateKey(String stateKey) {
		this.stateKey = stateKey;
	}
	
	
}
