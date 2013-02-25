/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.admin.web.controller;

/**
 * @author muralikc
 *
 */
public class AdminForm {
	
	/** The health system. */
	private boolean healthSystem;
	
	/** The comp name. */
	private String compName;
	
	/** The ns id. */
	private String nsId;
	


	/**
	 * @return the compName
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * @param compName the compName to set
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	 * @return the nsId
	 */
	public String getNsId() {
		return nsId;
	}

	/**
	 * @param nsId the nsId to set
	 */
	public void setNsId(String nsId) {
		this.nsId = nsId;
	}

	/**
	 * @return the healthSystem
	 */
	public boolean isHealthSystem() {
		return healthSystem;
	}

	/**
	 * @param healthSystem the healthSystem to set
	 */
	public void setHealthSystem(boolean healthSystem) {
		this.healthSystem = healthSystem;
	}


}
