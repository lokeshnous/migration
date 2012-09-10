package com.advanceweb.afc.jb.netsuite;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the DTO class for Customer(which means Employer and Agency in JB).
 * @author Reetesh RN 
 * @Version 1.0
 * @Since 3rd Aug 2012
 */

public class NSCustomer {

	//refers to internalid in netSuite
	private int internalID;
	
	private String phone;
	
	private String recordType;

	private String companyName;
	
	private String ccNumber;
	
	private String ccExpireDate;
	
	private String ccName;
	
	private String ccZipCode;
	
	private String ccStreet;
	
	private List<NSItem> item = new ArrayList<NSItem>();
	
	private String firstName;
	
	private String middleName;
	
	private String lastname;
	
	private String email;
	
	private String zip;
	
	private String state;
	
	private String country;
	
	private String altPhone;
	
	private String isPerson;
	
	private String addr1;
	
	private String city;
	
	
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	public String getIsPerson() {
		return isPerson;
	}

	public void setIsPerson(String isPerson) {
		this.isPerson = isPerson;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public int getInternalID() {
		return internalID;
	}

	public void setInternalID(int internalID) {
		this.internalID = internalID;
	}

	public List<NSItem> getItem() {
		return item;
	}

	public void setItem(List<NSItem> item) {
		this.item = item;
	}
	
	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcExpireDate() {
		return ccExpireDate;
	}

	public void setCcExpireDate(String ccExpireDate) {
		this.ccExpireDate = ccExpireDate;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcZipCode() {
		return ccZipCode;
	}

	public void setCcZipCode(String ccZipCode) {
		this.ccZipCode = ccZipCode;
	}

	public String getCcStreet() {
		return ccStreet;
	}

	public void setCcStreet(String ccStreet) {
		this.ccStreet = ccStreet;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}


}
