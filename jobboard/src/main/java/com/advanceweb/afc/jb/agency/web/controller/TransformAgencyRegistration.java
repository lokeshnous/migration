/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.agency.web.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.FacilityContactDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;


/**
 * @author muralikc
 *
 */
@Repository("transformAgencyRegistration")
public class TransformAgencyRegistration {

	/**
	 * Converting into MerUserDTO from EmployeeRegistrationForm
	 * 
	 * @param form
	 * @return MerUserDTO
	 */
	public UserDTO transformEmpFormToMerUserDTO(AgencyRegistrationForm form) {
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
	public AddressDTO transformEmpFormToAddressDTO(AgencyRegistrationForm form) {
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
			AgencyRegistrationForm form) {
		CompanyProfileDTO dto = new CompanyProfileDTO();
		// dto.setCompanyEmail(companyEmail);
		dto.setCompanyName(form.getCompany());
		dto.setPositionTitle(form.getPositionTitle());
		// dto.setCompanyNews(companyNews);
		// dto.setCompanyOverview(companyOverview);
		// dto.setCompanyWebsite(companyWebsite);
		return dto;
	}


	
	/**
	 * Transform dto to profile attrib form.
	 *
	 * @param registerDTO the register dto
	 * @param userDTO the user dto
	 * @return the list
	 */
	public List<AgencyProfileAttribForm> transformDTOToProfileAttribForm(
			AgencyProfileDTO registerDTO, UserDTO userDTO){
		
		List<AgencyProfileAttribForm> listForms = new ArrayList<AgencyProfileAttribForm>();

		if (null != registerDTO.getAttribList()) {
			for (ProfileAttribDTO dto : registerDTO.getAttribList()) {
				AgencyProfileAttribForm form = new AgencyProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelValue(dto.getStrLabelValue());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setRequired(dto.getRequired());
				if(null != userDTO){
					if(form.getStrLabelName().equals(MMJBCommonConstants.FIRST_NAME)){
						form.setStrLabelValue(userDTO.getFirstName());
					}
					if(form.getStrLabelName().equals(MMJBCommonConstants.LAST_NAME)){
						form.setStrLabelValue(userDTO.getLastName());
					}
					if(form.getStrLabelName().equals(MMJBCommonConstants.MIDDLE_NAME)){
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
	public UserDTO createUserDTO(AgencyRegistrationForm form) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		dto.setUserId(form.getUserId());
		dto.setOldUser(form.isOldUSer());
		dto.setAdvPassUser(form.isAdvPassUser());
		return dto;
	}

	
	/**
	 * 
	 * @param attributeList
	 * @return
	 */
	public List<ProfileAttribDTO> transformProfileAttribFormToDTO(AgencyRegistrationForm agencyRegForm){
		
		List<ProfileAttribDTO> dtoList = new ArrayList<ProfileAttribDTO>();
		
		if(null != agencyRegForm
				.getListProfAttribForms()){
			for(AgencyProfileAttribForm form : agencyRegForm
					.getListProfAttribForms()){
				ProfileAttribDTO dto = new ProfileAttribDTO();
				if(MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(form.getStrLabelName())){					
					dto.setStrLabelValue(StringUtils.arrayToCommaDelimitedString(form.getSubs()));					
				}else{
					dto.setStrLabelValue(form.getStrLabelValue());
				}
				dto.setStrAttribType(form.getStrAttribType());
				dto.setStrLabelName(form.getStrLabelName());
				dto.setStrProfileAttribId(form.getStrProfileAttribId());				
				dtoList.add(dto);
			}
		}
		if(agencyRegForm.isSocialSignUp()){
			ProfileAttribDTO newDTO = new ProfileAttribDTO();
			newDTO.setStrLabelName(agencyRegForm.getServiceProviderName());
			newDTO.setStrLabelValue(agencyRegForm.getSocialProfileId());
			newDTO.setStrProfileAttribId(MMJBCommonConstants.LINKEDIN_PROFILE_ATTR_ID);
			if(agencyRegForm.getServiceProviderName().equals(MMJBCommonConstants.FACEBOOK)){
				newDTO.setStrProfileAttribId(MMJBCommonConstants.FACEBOOK_PROFILE_ATTR_ID);
			}
			
			dtoList.add(newDTO);
		}
		return dtoList;		
	}

	/**
	 * Transform dto to profile attrib form.
	 *
	 * @param registerDTO the register dto
	 * @param userDTO the user dto
	 * @return the list
	 */
	public List<AgencyProfileAttribForm> transformContactDTOToProfileAttribForm(
			AgencyProfileDTO registerDTO, FacilityContactDTO facilityContactDTO, UserDTO userDTO) {
		List<AgencyProfileAttribForm> listForms=new ArrayList<AgencyProfileAttribForm>();
		
		if (null != registerDTO.getAttribList()) {
			for (ProfileAttribDTO dto : registerDTO.getAttribList()) {
				AgencyProfileAttribForm form = new AgencyProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelValue(dto.getStrLabelValue());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setRequired(dto.getRequired());
				if (null != facilityContactDTO) {
					setContactValuesToForm(facilityContactDTO, form,userDTO);
				}
				listForms.add(form);
			}
		}

		return listForms;
	}
	
	/**
	 * Sets the values to form.
	 *
	 * @param userDTO the user dto
	 * @param form the form
	 */
	private void setContactValuesToForm(FacilityContactDTO contactDTO, AgencyProfileAttribForm form,UserDTO userDTO) {
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
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.STREET_ADD)) {
			form.setStrLabelValue(contactDTO.getStreet());
		}
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.CITY_EMP)) {
			form.setStrLabelValue(contactDTO.getCity());
		}
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.STATE_PROVINCE)) {
			form.setStrLabelValue(contactDTO.getState());
		}
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.ZIP_CODE)) {
			form.setStrLabelValue(contactDTO.getPostcode());
		}
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.COUNTRY)) {
			form.setStrLabelValue(contactDTO.getCountry());
		}
		if (form.getStrLabelName().equals(
				MMJBCommonConstants.COMPANY_EMP)) {
			form.setStrLabelValue(contactDTO.getCompany());
		}
	}	
}
