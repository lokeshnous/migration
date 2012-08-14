package com.advanceweb.afc.jb.pgi.web.controller;

import java.util.Date;

/**
 * @author muralikc
 * 
 */
public class BillingAddressForm {

	// Billing Address Information
	private String fnameForBillingAddr;
	private String lnameForBillingAddr;
	private String useMyAccountAddr;
	private String streetForBillingAddr;
	private String cityOrTownForBillingAddr;
	private String stateBillingAddress;
	private String countryForBillingAddr;
	private String zipCodeForBillingAddr;
	private int facilityContactId;
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
	public String getUseMyAccountAddr() {
		return useMyAccountAddr;
	}

	/**
	 * @param useMyAccountAddr
	 *            the useMyAccountAddr to set
	 */
	public void setUseMyAccountAddr(String useMyAccountAddr) {
		this.useMyAccountAddr = useMyAccountAddr;
	}

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

}
