/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

/**
 * @author muralikc
 * 
 */
public class EmpSearchDTO {
	
	/** The company name. */
	private String companyName;
	
	/** The user id. */
	private int userId;
	
	/** The facility id. */
	private int facilityId;
	
	/** The ns id. */
	private int nsId;
	
	/** The fac parent id. */
	private int facParentId;
	
	/** The facility type. */
	private String facilityType;
	
	/** The health system. */
	private boolean healthSystem; 



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

	/**
	 * @return the facilityType
	 */
	public String getFacilityType() {
		return facilityType;
	}

	/**
	 * @param facilityType the facilityType to set
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	/**
	 * @return the facParentId
	 */
	public int getFacParentId() {
		return facParentId;
	}

	/**
	 * @param facParentId the facParentId to set
	 */
	public void setFacParentId(int facParentId) {
		this.facParentId = facParentId;
	}

	/**
	 * @return the nsId
	 */
	public int getNsId() {
		return nsId;
	}

	/**
	 * @param nsId the nsId to set
	 */
	public void setNsId(int nsId) {
		this.nsId = nsId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
