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
