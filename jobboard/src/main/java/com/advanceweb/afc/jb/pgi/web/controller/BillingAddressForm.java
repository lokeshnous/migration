/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.Date;

/**
 * This class has been created to hold the values of Billing address
 * @author muralikc
 * @version 1.0
 * @created Aug 27, 2012
 */
public class BillingAddressForm {

	// Billing Address Information
	/** The fname for billing addr. */
	private String fnameForBillingAddr;
	
	/** The lname for billing addr. */
	private String lnameForBillingAddr;
	//private String useMyAccountAddr;
	/** The use my account addr. */
	private boolean useMyAccountAddr;
	
	/** The street for billing addr. */
	private String streetForBillingAddr;
	
	/** The city or town for billing addr. */
	private String cityOrTownForBillingAddr;
	
	/** The state billing address. */
	private String stateBillingAddress;
	
	/** The country for billing addr. */
	private String countryForBillingAddr;
	
	/** The zip code for billing addr. */
	private String zipCodeForBillingAddr;
	
	/** The phone. */
	private String phone;
	
	/** The facility contact id. */
	private int facilityContactId;
	
	/** The create date. */
	private Date createDate;

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the fnameForBillingAddr
	 */
	public String getFnameForBillingAddr() {
		return fnameForBillingAddr;
	}

	/**
	 * @param fnameForBillingAddr
	 *            the fnameForBillingAddr to set
	 */
	public void setFnameForBillingAddr(String fnameForBillingAddr) {
		this.fnameForBillingAddr = fnameForBillingAddr;
	}

	/**
	 * @return the lnameForBillingAddr
	 */
	public String getLnameForBillingAddr() {
		return lnameForBillingAddr;
	}

	/**
	 * @param lnameForBillingAddr
	 *            the lnameForBillingAddr to set
	 */
	public void setLnameForBillingAddr(String lnameForBillingAddr) {
		this.lnameForBillingAddr = lnameForBillingAddr;
	}

	/**
	 * @return the useMyAccountAddr
	 */
	/*public String getUseMyAccountAddr() {
		return useMyAccountAddr;
	}

	*//**
	 * @param useMyAccountAddr
	 *            the useMyAccountAddr to set
	 *//*
	public void setUseMyAccountAddr(String useMyAccountAddr) {
		this.useMyAccountAddr = useMyAccountAddr;
	}
*/
	/**
	 * @return the streetForBillingAddr
	 */
	public String getStreetForBillingAddr() {
		return streetForBillingAddr;
	}

	/**
	 * @param streetForBillingAddr
	 *            the streetForBillingAddr to set
	 */
	public void setStreetForBillingAddr(String streetForBillingAddr) {
		this.streetForBillingAddr = streetForBillingAddr;
	}

	/**
	 * @return the cityOrTownForBillingAddr
	 */
	public String getCityOrTownForBillingAddr() {
		return cityOrTownForBillingAddr;
	}

	/**
	 * @param cityOrTownForBillingAddr
	 *            the cityOrTownForBillingAddr to set
	 */
	public void setCityOrTownForBillingAddr(String cityOrTownForBillingAddr) {
		this.cityOrTownForBillingAddr = cityOrTownForBillingAddr;
	}

	/**
	 * @return the stateBillingAddress
	 */
	public String getStateBillingAddress() {
		return stateBillingAddress;
	}

	/**
	 * @param stateBillingAddress
	 *            the stateBillingAddress to set
	 */
	public void setStateBillingAddress(String stateBillingAddress) {
		this.stateBillingAddress = stateBillingAddress;
	}

	/**
	 * @return the countryForBillingAddr
	 */
	public String getCountryForBillingAddr() {
		return countryForBillingAddr;
	}

	/**
	 * @param countryForBillingAddr
	 *            the countryForBillingAddr to set
	 */
	public void setCountryForBillingAddr(String countryForBillingAddr) {
		this.countryForBillingAddr = countryForBillingAddr;
	}

	/**
	 * @return the zipCodeForBillingAddr
	 */
	public String getZipCodeForBillingAddr() {
		return zipCodeForBillingAddr;
	}

	/**
	 * @param zipCodeForBillingAddr
	 *            the zipCodeForBillingAddr to set
	 */
	public void setZipCodeForBillingAddr(String zipCodeForBillingAddr) {
		this.zipCodeForBillingAddr = zipCodeForBillingAddr;
	}

	/**
	 * @return the facilityContactId
	 */
	public int getFacilityContactId() {
		return facilityContactId;
	}

	/**
	 * @param facilityContactId
	 *            the facilityContactId to set
	 */
	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Checks if is use my account addr.
	 *
	 * @return true, if is use my account addr
	 */
	public boolean isUseMyAccountAddr() {
		return useMyAccountAddr;
	}

	/**
	 * Sets the use my account addr.
	 *
	 * @param useMyAccountAddr the new use my account addr
	 */
	public void setUseMyAccountAddr(boolean useMyAccountAddr) {
		this.useMyAccountAddr = useMyAccountAddr;
	}
}
