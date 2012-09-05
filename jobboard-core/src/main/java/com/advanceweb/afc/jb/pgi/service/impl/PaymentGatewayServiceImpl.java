package com.advanceweb.afc.jb.pgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;
import com.advanceweb.afc.jb.pgi.dao.PaymentGatewayDao;
import com.advanceweb.afc.jb.pgi.service.PaymentGatewayService;

/**
 * @author muralikc
 * 
 */
@Service("paymentGatewayService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

	@Autowired(required = true)
	private PaymentGatewayDao paymentGatewayDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AccountAddressDTO getConatactByFacilityId(int facilityId) {
		return paymentGatewayDao
				.getAccountAddressByFacilityId(facilityId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public BillingAddressDTO getBillingAddByFacilityId(int facilityId) {
		return paymentGatewayDao
				.getBillingAddressByFacilityId(facilityId);
	}

	@Override
	public boolean saveBillingAddress(BillingAddressDTO billingAddressDTO) {
		return paymentGatewayDao
				.saveBillingAddress(billingAddressDTO);
	}
	@Override
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO) {
		return paymentGatewayDao
				.saveDataBillingAddress(billingAddressDTO);
	}
}
