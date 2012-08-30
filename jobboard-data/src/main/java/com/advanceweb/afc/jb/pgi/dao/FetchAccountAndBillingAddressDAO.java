package com.advanceweb.afc.jb.pgi.dao;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;

/**
 * @author muralikc
 *
 */
public interface FetchAccountAndBillingAddressDAO {
	
	/**
	 * Get the address details by using the userId
	 * 
	 * @param userId
	 * @return
	 */
	AccountAddressDTO getAccountAddressByFacilityId(int facilityId);
	
	/**
	 * Get the billing address details by using facliytId
	 * 
	 * @param facilityId
	 * @return
	 */
	BillingAddressDTO getBillingAddressByFacilityId(int facilityId);
	
	/**
	 * saving the billing address
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public boolean saveBillingAddress(BillingAddressDTO billingAddressDTO);
	/**
	 * saving the billing address
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO);
}
