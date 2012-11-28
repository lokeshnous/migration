package com.advanceweb.afc.jb.employer.service.impl;

import java.util.List;

public interface BrandingTemplateDelegate {
	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return List<String>
	 */
	List<String> getPurchaseInfo(int facilityId);
	
	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */

	int getNSCustomerIDFromAdmFacility(int admFacilityID);

}
