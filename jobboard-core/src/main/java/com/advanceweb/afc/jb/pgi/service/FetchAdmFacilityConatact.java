package com.advanceweb.afc.jb.pgi.service;

import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;

/**
 * @author muralikc
 * 
 */
public interface FetchAdmFacilityConatact {
	
	
	/**
	 * @param billingAddressDTO
	 * @return
	 */
	public boolean saveBillingAddress(BillingAddressDTO billingAddressDTO);

	/**
	 * @param userId
	 * @return
	 */
	AccountAddressDTO getConatactByFacilityId(int facilityId);

	/**
	 * @param facilityId
	 * @return
	 */
	BillingAddressDTO getBillingAddByFacilityId(int facilityId);

}
