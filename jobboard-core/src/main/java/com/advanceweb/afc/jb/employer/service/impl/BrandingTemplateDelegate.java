package com.advanceweb.afc.jb.employer.service.impl;

import com.advanceweb.afc.jb.common.UserDTO;

public interface BrandingTemplateDelegate {
	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return UserDTO
	 */
	UserDTO getCustomerDetails(int facilityId);
	
	/**
	 * This method is used to get the net suite customer id based on adm
	 * facility id.
	 * 
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */

	int getNSCustomerIDFromAdmFacility(int admFacilityID);

}
