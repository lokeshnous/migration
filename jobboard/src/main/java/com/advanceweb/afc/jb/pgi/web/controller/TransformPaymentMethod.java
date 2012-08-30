package com.advanceweb.afc.jb.pgi.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;
import com.advanceweb.afc.jb.common.AccountBillingDTO;
import com.advanceweb.afc.jb.common.AccountProfileDTO;

/**
 * @author muralikc
 * 
 */
@Repository("transformPaymentMethod")
public class TransformPaymentMethod {

	/**
	 * Converting AccontAddressDTO to Form
	 * 
	 * @param accountAddressDTO
	 * @return
	 */
	public AccountAddressForm transformAccountAddrDtoToForm(
			AccountAddressDTO accountAddressDTO) {
		AccountAddressForm accountAddressForm = new AccountAddressForm();
		accountAddressForm.setFirstName(accountAddressDTO.getFirstName());
		accountAddressForm.setLastName(accountAddressDTO.getLastName());
		accountAddressForm.setCompany(accountAddressDTO.getCompany());
		accountAddressForm.setCountry(accountAddressDTO.getCountry());
		accountAddressForm.setEmail(accountAddressDTO.getEmail());
		accountAddressForm.setPhone(accountAddressDTO.getPhone());
		accountAddressForm.setState(accountAddressDTO.getState());
		accountAddressForm.setStreetAddress(accountAddressDTO
				.getStreetAddress());
		accountAddressForm.setMiddleName(accountAddressDTO.getMiddleName());
		accountAddressForm.setZipCode(accountAddressDTO.getZipCode());
		accountAddressForm.setCityOrTown(accountAddressDTO.getCityOrTown());
		return accountAddressForm;
	}

	/**
	 * Converting BillingAddressDTO to Form
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public BillingAddressForm transformBillingAddressDtoToForm(
			BillingAddressDTO billingAddressDTO) {
		BillingAddressForm billingAddressForm = new BillingAddressForm();

		billingAddressForm.setFnameForBillingAddr(billingAddressDTO
				.getBillFirstName());
		billingAddressForm.setLnameForBillingAddr(billingAddressDTO
				.getBillLastName());
		billingAddressForm.setCountryForBillingAddr(billingAddressDTO
				.getBillCountry());
		billingAddressForm.setStateBillingAddress(billingAddressDTO
				.getBillState());
		billingAddressForm.setCityOrTownForBillingAddr(billingAddressDTO
				.getBillCityOrTown());
		billingAddressForm.setZipCodeForBillingAddr(billingAddressDTO
				.getBillZipCode());
		billingAddressForm.setStreetForBillingAddr(billingAddressDTO
				.getBillStreetAddress());
		billingAddressForm.setFacilityContactId(billingAddressDTO
				.getFacilityContactId());
		billingAddressForm.setCreateDate(billingAddressDTO.getCreateDate());

		return billingAddressForm;

	}

	/**
	 * Converting BillingAddressForm To DTO for saving the data
	 * 
	 * @param billingAddressForm
	 * @return
	 */
	public BillingAddressDTO transformBillingAddreFormToDto(
			BillingAddressForm billingAddressForm) {
		BillingAddressDTO billingAddressDTO = new BillingAddressDTO();

		billingAddressDTO.setBillCityOrTown(billingAddressForm
				.getCityOrTownForBillingAddr());
		billingAddressDTO.setFacilityContactId(billingAddressForm
				.getFacilityContactId());
		billingAddressDTO.setBillCountry(billingAddressForm
				.getCountryForBillingAddr());
		billingAddressDTO.setBillFirstName(billingAddressForm
				.getFnameForBillingAddr());
		billingAddressDTO.setBillLastName(billingAddressForm
				.getLnameForBillingAddr());
		billingAddressDTO.setBillState(billingAddressForm
				.getStateBillingAddress());
		billingAddressDTO.setBillStreetAddress(billingAddressForm
				.getStreetForBillingAddr());
		billingAddressDTO.setBillZipCode(billingAddressForm
				.getZipCodeForBillingAddr());
		billingAddressDTO.setCreateDate(billingAddressForm.getCreateDate());
		return billingAddressDTO;
	}
	
	/**
	 * Converting BillingAddressForm To DTO for saving the data
	 * 
	 * @param billingAddressForm
	 * @return
	 */
	public AccountBillingDTO transformDataBillingAddreFormToDto(
			BillingAddressForm billingAddressForm) {
		AccountBillingDTO accountBillingDTO = new AccountBillingDTO();
//		accountBillingDTO.setFacilityContactId(billingAddressForm.getFacilityContactId());
		accountBillingDTO.setFirstName(billingAddressForm.getFnameForBillingAddr());		
		accountBillingDTO.setLastName(billingAddressForm.getLnameForBillingAddr());
		accountBillingDTO.setStreet(billingAddressForm.getStreetForBillingAddr());
		accountBillingDTO.setCity(billingAddressForm.getCityOrTownForBillingAddr());
		accountBillingDTO.setState(billingAddressForm.getStateBillingAddress());
		accountBillingDTO.setCountry(billingAddressForm.getCountryForBillingAddr());
		accountBillingDTO.setZipCode(billingAddressForm.getZipCodeForBillingAddr());
		//accountBillingDTO.setCreateDate(billingAddressForm.getCreateDate());
		return accountBillingDTO;
	}
}
