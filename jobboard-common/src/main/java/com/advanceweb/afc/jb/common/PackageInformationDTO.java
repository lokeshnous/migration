package com.advanceweb.afc.jb.common;

import java.util.Date;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:24:50 PM
 */
public class PackageInformationDTO {

	private long packageid;
	private String packageType;
	private String packageName;
	private Date effectiveDate;
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