package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.ManageAccessPermissionDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("transformEmployerRegistration")
public class TransformEmployerRegistration {

	/**
	 * Converting into MerUserDTO from EmployeeRegistrationForm
	 * 
	 * @param form
	 * @return MerUserDTO
	 */
	public UserDTO transformEmpFormToMerUserDTO(EmployerRegistrationForm form) {
		UserDTO dto = new UserDTO();
		form.getListProfAttribForms();
		dto.setEmailId(form.getEmailId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setUserId(form.getUserId());
		return dto;
	}

	/**
	 * Converting into AddressDTO from EmployeeRegistrationForm
	 * 
	 * @param form
	 * @return AddressDTO
	 */
	public AddressDTO transformEmpFormToAddressDTO(EmployerRegistrationForm form) {
		AddressDTO dto = new AddressDTO();
		dto.setCity(form.getCity());
		dto.setCountry(form.getCountry());
		dto.setMobileNumber(form.getPrimaryPhone());
		dto.setPhone(form.getSecondryPhone());
		dto.setState(form.getState());
		dto.setStreet(form.getStreet());
		dto.setZipCode(form.getZipCode());
		return dto;
	}

	/**
	 * Converting into CompanyProfileDTO from EmployeeRegistrationForm
	 * 
	 * @param form
	 * @return CompanyProfileDTO
	 */
	public CompanyProfileDTO transformEmpFormToCompProfileDTO(
			EmployerRegistrationForm form) {
		CompanyProfileDTO dto = new CompanyProfileDTO();
		// dto.setCompanyEmail(companyEmail);
		dto.setCompanyName(form.getCompany());
		dto.setPositionTitle(form.getPositionTitle());
		// dto.setCompanyNews(companyNews);
		// dto.setCompanyOverview(companyOverview);
		// dto.setCompanyWebsite(companyWebsite);
		return dto;
	}

	public List<EmployerProfileAttribForm> transformDTOToProfileAttribForm(
			EmployerProfileDTO registerDTO, UserDTO userDTO) {

		List<EmployerProfileAttribForm> listForms = new ArrayList<EmployerProfileAttribForm>();

		if (null != registerDTO.getAttribList()) {
			for (ProfileAttribDTO dto : registerDTO.getAttribList()) {
				EmployerProfileAttribForm form = new EmployerProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelValue(dto.getStrLabelValue());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setbRequired(dto.getbRequired());
				if (null != userDTO) {
					if (form.getStrLabelName().equals(
							MMJBCommonConstants.FIRST_NAME)) {
						form.setStrLabelValue(userDTO.getFirstName());
					}
					if (form.getStrLabelName().equals(
							MMJBCommonConstants.LAST_NAME)) {
						form.setStrLabelValue(userDTO.getLastName());
					}
					if (form.getStrLabelName().equals(
							MMJBCommonConstants.MIDDLE_NAME)) {
						form.setStrLabelValue(userDTO.getMiddleName());
					}
				}
				listForms.add(form);
			}
		}

		return listForms;
	}

	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public UserDTO createUserDTO(EmployerRegistrationForm form) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		dto.setUserId(form.getUserId());

		return dto;
	}

	/**
	 * 
	 * @param attributeList
	 * @return
	 */
	public List<ProfileAttribDTO> transformProfileAttribFormToDTO(
			List<EmployerProfileAttribForm> attributeList) {

		List<ProfileAttribDTO> dtoList = new ArrayList<ProfileAttribDTO>();

		if (null != attributeList) {
			for (EmployerProfileAttribForm form : attributeList) {
				ProfileAttribDTO dto = new ProfileAttribDTO();
				if (MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(form
						.getStrLabelName())) {
					dto.setStrLabelValue(StringUtils
							.arrayToCommaDelimitedString(form.getSubs()));
				} else {
					dto.setStrLabelValue(form.getStrLabelValue());
				}
				dto.setStrAttribType(form.getStrAttribType());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrProfileAttribId(form.getStrProfileAttribId());
				dtoList.add(dto);
			}
		}

		return dtoList;
	}

	public AccountProfileDTO transformAccountProfileFormToDto(
			EmployeeAccountForm form) {
		AccountProfileDTO dto = new AccountProfileDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setCompanyName(form.getCompany());
		dto.setState(form.getState());
		dto.setStreet(form.getStreetAddress());
		dto.setCountry(form.getCountry());
		dto.setEmail(form.getEmail());
		dto.setZipCode(form.getZipCode());
		dto.setPhone(form.getPhone());
		dto.setCity(form.getCityOrTown());

		return dto;
	}

	public AccountProfileDTO transformBillingProfileFormToDto(
			EmployeeAccountForm form) {
		AccountProfileDTO dto = new AccountProfileDTO();
		dto.setFirstName(form.billingAddressForm.getFnameForBillingAddr());
		dto.setLastName(form.billingAddressForm.getLnameForBillingAddr());
		dto.setCompanyName(form.getCompany());
		dto.setState(form.billingAddressForm.getStateBillingAddress());
		dto.setStreet(form.billingAddressForm.getStreetForBillingAddr());
		dto.setCountry(form.billingAddressForm.getCountryForBillingAddr());
		dto.setEmail(form.getEmail());
		dto.setZipCode(form.billingAddressForm.getZipCodeForBillingAddr());
		dto.setPhone(form.getPhone());
		dto.setCity(form.billingAddressForm.getCityOrTownForBillingAddr());

		return dto;
	}

	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public UserDTO createUserDTOFromManageAccessForm(
			ManageAccessPermissionForm manageAccessPermissionForm) {
		// TODO Need to Modify the hard code value
		UserDTO dto = new UserDTO();
		if (null != manageAccessPermissionForm.getOwnerName()
				&& !manageAccessPermissionForm.getOwnerName().isEmpty()) {
			String[] names = manageAccessPermissionForm.getOwnerName().split(
					" ");
			dto.setFirstName(names[1]);
			dto.setLastName(names[0]);
			dto.setPassword("password");
			dto.setEmailId(manageAccessPermissionForm.getOwnerEmail());
		}

		return dto;
	}

	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public ManageAccessPermissionDTO createManageAccessPermissionDTOFromManageAccessForm(
			ManageAccessPermissionForm manageAccessPermissionForm) {

		ManageAccessPermissionDTO accessPermissionDTO = new ManageAccessPermissionDTO();
		if (null != manageAccessPermissionForm) {
			accessPermissionDTO.setOwnerId(manageAccessPermissionForm
					.getOwnerId());
			accessPermissionDTO.setOwnerName(manageAccessPermissionForm
					.getOwnerName());
			accessPermissionDTO.setOwnerEmail(manageAccessPermissionForm
					.getOwnerEmail());
			// accessPermissionDTO.set(manageAccessPermissionForm.getManageAccessPermissiondetails());
		}

		return accessPermissionDTO;
	}

	public AccountProfileDTO transformEmployerFormToDto(
			EmployerRegistrationForm form) {
		AccountProfileDTO dto = new AccountProfileDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setCompanyName(form.getCompany());
		dto.setState(form.getState());
		dto.setStreet(form.getStreet());
		dto.setCountry(form.getCountry());
		dto.setZipCode(form.getZipCode());
		dto.setPhone(form.getPrimaryPhone());
		dto.setCity(form.getCity());
		dto.setFacilityId(Integer.parseInt(form.getFacilityId().toString()));

		return dto;
	}
}
