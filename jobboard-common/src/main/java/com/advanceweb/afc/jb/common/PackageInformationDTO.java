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

	public PackageInformationDTO(){

	}

	public void finalize() throws Throwable {

	}

}