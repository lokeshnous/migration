package com.advanceweb.afc.jb.admin.web.controller;

/**
 * @author muralikc
 *
 */
public class AdminForm {
	
	private boolean healthSystem;
	private String compName;
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
