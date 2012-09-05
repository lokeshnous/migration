package com.advanceweb.afc.jb.netsuite;

import java.util.ArrayList;
import java.util.List;


public class CustomerDTO {

	//refers to internalid in netSuite
	//private int internalID;
	
	private String phone;
	
	private String recordType;

	private String companyName;
	
	private String ccNumber;
	
	private String ccExpireDate;
	
	private String ccName;
	
	private String ccZipCode;
	
	private String ccStreet;
	
	private List<Item> item = new ArrayList<Item>();
	
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

	/*public int getInternalID() {
		return internalID;
	}

	public void setInternalID(int internalID) {
		this.internalID = internalID;
	}*/

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
