package com.advanceweb.afc.jb.pgi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;
import com.advanceweb.afc.jb.pgi.dao.FetchAccountAndBillingAddressDAO;
import com.advanceweb.afc.jb.pgi.service.FetchAdmFacilityConatact;

/**
 * @author muralikc
 * 
 */
@Service("fetchAdmFacilityConatact")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class FetchAdmFacilityConatactImpl implements FetchAdmFacilityConatact {

	@Autowired(required = true)
	private FetchAccountAndBillingAddressDAO fetchAccountAndBillingAddressDAO;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public AccountAddressDTO getConatactByFacilityId(int facilityId) {
		return fetchAccountAndBillingAddressDAO
				.getAccountAddressByFacilityId(facilityId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public BillingAddressDTO getBillingAddByFacilityId(int facilityId) {
		return fetchAccountAndBillingAddressDAO
				.getBillingAddressByFacilityId(facilityId);
	}

	@Override
	public boolean saveBillingAddress(BillingAddressDTO billingAddressDTO) {
		return fetchAccountAndBillingAddressDAO
				.saveBillingAddress(billingAddressDTO);
	}
	@Override
	public boolean saveDataBillingAddress(AccountBillingDTO billingAddressDTO) {
		return fetchAccountAndBillingAddressDAO
				.saveDataBillingAddress(billingAddressDTO);
	}
}
