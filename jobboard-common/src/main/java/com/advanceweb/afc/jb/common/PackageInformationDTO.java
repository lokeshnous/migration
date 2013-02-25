/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:50 PM
 */
public class PackageInformationDTO {

	/** The packageid. */
	private long packageid;
	
	/** The package type. */
	private String packageType;
	
	/** The package name. */
	private String packageName;
	
	/** The effective date. */
	private Date effectiveDate;
	
	/** The expiry date. */
	private Date expiryDate;
	/**
	 * @return the packageid
	 */
	public long getPackageid() {
		return packageid;
	}

	/**
	 * @param packageid the packageid to set
	 */
	public void setPackageid(long packageid) {
		this.packageid = packageid;
	}

	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}

	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}



}