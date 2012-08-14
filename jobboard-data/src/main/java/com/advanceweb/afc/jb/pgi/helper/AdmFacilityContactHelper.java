package com.advanceweb.afc.jb.pgi.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.data.entities.AdmFacilityContact;
import com.advanceweb.afc.jb.pgi.AccountAddressDTO;
import com.advanceweb.afc.jb.pgi.BillingAddressDTO;

/**
 * @author muralikc
 * 
 */

@Repository("admFacilityContactHelper")
public class AdmFacilityContactHelper {

	/**
	 * Converting AccountAddressDAO to DTO
	 * 
	 * @param entity
	 * @return
	 */
	public AccountAddressDTO convertAccountAddressDaoToDto(
			AdmFacilityContact entity) {
		AccountAddressDTO admFacilityContactDTO = new AccountAddressDTO();

		if (entity != null) {
			admFacilityContactDTO.setFirstName(entity.getFirstName());
			admFacilityContactDTO.setLastName(entity.getLastName());
			admFacilityContactDTO.setMiddleName(entity.getMiddleName());
			admFacilityContactDTO.setEmail(entity.getEmail());
			admFacilityContactDTO.setCompany(entity.getCompany());
			admFacilityContactDTO.setStreetAddress(entity.getStreet());
			admFacilityContactDTO.setCityOrTown(entity.getCity());
			admFacilityContactDTO.setState(entity.getState());
			admFacilityContactDTO.setCountry(entity.getCountry());
			admFacilityContactDTO.setZipCode(entity.getPostcode());
			admFacilityContactDTO.setPhone(entity.getPhone());
		}
		return admFacilityContactDTO;

	}

	/**
	 * Converting BillingAddressDAO to DTo
	 * 
	 * @param entity
	 * @return
	 */
	public BillingAddressDTO convertBillingAddressDaoToDto(
			AdmFacilityContact entity) {
		BillingAddressDTO billingAddressDTO = new BillingAddressDTO();
		if (entity != null) {
			billingAddressDTO.setBillFirstName(entity.getFirstName());
			billingAddressDTO.setBillLastName(entity.getLastName());
			billingAddressDTO.setBillCityOrTown(entity.getCity());
			billingAddressDTO.setBillCountry(entity.getCountry());
			billingAddressDTO.setBillState(entity.getState());
			billingAddressDTO.setBillStreetAddress(entity.getStreet());
			billingAddressDTO.setBillZipCode(entity.getPostcode());
			billingAddressDTO.setFacilityContactId(entity.getFacilityContactId());
			billingAddressDTO.setCreateDate(entity.getCreateDt());
		}

		return billingAddressDTO;

	}

	/**
	 * Converting BillingAddressDTO to Entity
	 * 
	 * @param billingAddressDTO
	 * @return
	 */
	public AdmFacilityContact convertBillingAddressDtoToEntity(
			BillingAddressDTO billingAddressDTO) {
		AdmFacilityContact admFacilityContact = new AdmFacilityContact();
		if (billingAddressDTO != null) {
			admFacilityContact.setFirstName(billingAddressDTO
					.getBillFirstName());
			admFacilityContact.setLastName(billingAddressDTO.getBillLastName());
			admFacilityContact.setCity(billingAddressDTO.getBillCityOrTown());
			admFacilityContact.setCountry(billingAddressDTO.getBillCountry());
			admFacilityContact.setState(billingAddressDTO.getBillState());
			admFacilityContact.setStreet(billingAddressDTO
					.getBillStreetAddress());
			admFacilityContact.setPostcode(billingAddressDTO.getBillZipCode());
		}

		return admFacilityContact;

	}

}
