package com.advanceweb.afc.jb.admin.web.controller;

/**
 * @author muralikc
 *
 */
public class AdminForm {
	
	private boolean isHealthSystem;
	private String compName;
	private String nsId;
	

	/**
	 * @return the isHealthSystem
	 */
	public boolean isHealthSystem() {
		return isHealthSystem;
	}

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
	 * @param isHealthSystem the isHealthSystem to set
	 */
	public void setHealthSystem(boolean isHealthSystem) {
		this.isHealthSystem = isHealthSystem;
	}

}
