/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.dao;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.SalesItemDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;

/**
 * @author muralikc
 *
 */
public interface PaymentGatewayDao {
	
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
	AccountAddressDTO getBillingAddressByFacilityId(int facilityId);
	
	/**
	 * saving the billing address
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	boolean saveBillingAddress(AccountAddressDTO billingAddressDTO);
	/**
	 * saving the billing address
	 * 
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
	boolean saveOrderDetails(OrderDetailsDTO orderDetailsDTO);
	
	/**
	 * This method is used to save the inventory details by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return
	 */
	boolean saveInventoryDetails(OrderDetailsDTO orderDetailsDTO);
	
	/**
	 * This method is used to create the sale order items for the job post selected by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return
	 */
	void createJobPostOrderItems(OrderDetailsDTO orderDetailsDTO);
}
