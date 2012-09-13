package com.advanceweb.afc.jb.pgi.service;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;

/**
 * @author muralikc
 * 
 */
public interface PaymentGatewayService {

	/**
	 * @param billingAddressDTO
	 * @return
	 */
	boolean saveBillingAddress(AccountAddressDTO billingAddressDTO);

	/**
	 * @param userId
	 * @return
	 */
	AccountAddressDTO getConatactByFacilityId(int facilityId);

	/**
	 * @param facilityId
	 * @return
	 */
	AccountAddressDTO getBillingAddByFacilityId(int facilityId);

	/**
	 * @param billingAddressDTO
	 * @return
	 */
	boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO);

	/**
	 * This method is used to save the order details by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return boolean 
	 */
	UserDTO createOrder(OrderDetailsDTO orderDetailsDTO);

}
