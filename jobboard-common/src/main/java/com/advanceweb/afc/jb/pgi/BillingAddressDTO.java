package com.advanceweb.afc.jb.pgi;

import java.util.Date;

/**
 * @author muralikc
 * 
 */
public class BillingAddressDTO {

	private String billFirstName;
	private String billLastName;
	private String billStreetAddress;
	private String billCityOrTown;
	private String billState;
	private String billCountry;
	private String billZipCode;
	private int facilityContactId;
	private Date createDate;
	private int facilityId;

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
	 * @return the facilityContactId
	 */
	public int getFacilityContactId() {
		return facilityContactId;
	}

	/**
	 * @param facilityContactId
	 */
	public void setFacilityContactId(int facilityContactId) {
		this.facilityContactId = facilityContactId;
	}

	/**
	 * @return the billFirstName
	 */
	public String getBillFirstName() {
		return billFirstName;
	}

	/**
	 * @param billFirstName
	 *            the billFirstName to set
	 */
	public void setBillFirstName(String billFirstName) {
		this.billFirstName = billFirstName;
	}

	/**
	 * @return the billLastName
	 */
	public String getBillLastName() {
		return billLastName;
	}

	/**
	 * @param billLastName
	 *            the billLastName to set
	 */
	public void setBillLastName(String billLastName) {
		this.billLastName = billLastName;
	}

	/**
	 * @return the billStreetAddress
	 */
	public String getBillStreetAddress() {
		return billStreetAddress;
	}

	/**
	 * @param billStreetAddress
	 *            the billStreetAddress to set
	 */
	public void setBillStreetAddress(String billStreetAddress) {
		this.billStreetAddress = billStreetAddress;
	}

	/**
	 * @return the billCityOrTown
	 */
	public String getBillCityOrTown() {
		return billCityOrTown;
	}

	/**
	 * @param billCityOrTown
	 *            the billCityOrTown to set
	 */
	public void setBillCityOrTown(String billCityOrTown) {
		this.billCityOrTown = billCityOrTown;
	}

	/**
	 * @return the billState
	 */
	public String getBillState() {
		return billState;
	}

	/**
	 * @param billState
	 *            the billState to set
	 */
	public void setBillState(String billState) {
		this.billState = billState;
	}

	/**
	 * @return the billCountry
	 */
	public String getBillCountry() {
		return billCountry;
	}

	/**
	 * @param billCountry
	 *            the billCountry to set
	 */
	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}

	/**
	 * @return the billZipCode
	 */
	public String getBillZipCode() {
		return billZipCode;
	}

	/**
	 * @param billZipCode
	 *            the billZipCode to set
	 */
	public void setBillZipCode(String billZipCode) {
		this.billZipCode = billZipCode;
	}

}
