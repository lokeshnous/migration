/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.pgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.OrderDetailsDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayDelegate;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;

/**
 * @author muralikc
 * 
 */
@Service("paymentGatewayService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

	/** The payment gateway dao. */
	@Autowired(required = true)
	private PaymentGatewayDao paymentGatewayDao;

	/** The payment gateway delegate. */
	@Autowired(required = true)
	private PaymentGatewayDelegate paymentGatewayDelegate;
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.service.PaymentGatewayService#getConatactByFacilityId(int)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AccountAddressDTO getConatactByFacilityId(int facilityId) {
		return paymentGatewayDao
				.getAccountAddressByFacilityId(facilityId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.service.PaymentGatewayService#getBillingAddByFacilityId(int)
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AccountAddressDTO getBillingAddByFacilityId(int facilityId) {
		return paymentGatewayDao
				.getBillingAddressByFacilityId(facilityId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.service.PaymentGatewayService#saveBillingAddress(com.advanceweb.afc.jb.pgi.AccountAddressDTO)
	 */
	@Override
	public boolean saveBillingAddress(AccountAddressDTO billingAddressDTO) {
		return paymentGatewayDao
				.saveBillingAddress(billingAddressDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.pgi.service.PaymentGatewayService#saveDataBillingAddress(com.advanceweb.afc.jb.common.AccountBillingDTO)
	 */
	@Override
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO) {
		return paymentGatewayDao
				.saveDataBillingAddress(billingAddressDTO);
	}

	/**
	 * This method is used to save the order details by taking the following
	 * parameters.
	 * 
	 * @param orderDetailsDTO
	 * @return boolean 
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public UserDTO createOrder(OrderDetailsDTO orderDetailsDTO) {
		return paymentGatewayDelegate.createOrder(orderDetailsDTO);
		
	}
}
