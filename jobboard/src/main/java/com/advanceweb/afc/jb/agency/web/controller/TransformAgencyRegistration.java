package com.advanceweb.afc.jb.agency.web.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AccountProfileDTO;
import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.AgencyProfileDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
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
	public MerUserDTO transformEmpFormToMerUserDTO(AgencyRegistrationForm form) {
		MerUserDTO dto = new MerUserDTO();
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


	
	public List<AgencyProfileAttribForm> transformDTOToProfileAttribForm(AgencyProfileDTO registerDTO){
		
		List<AgencyProfileAttribForm> listForms = new ArrayList<AgencyProfileAttribForm>();

		if (null != registerDTO.getAttribList()) {
			for (MerProfileAttribDTO dto : registerDTO.getAttribList()) {
				AgencyProfileAttribForm form = new AgencyProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelValue(dto.getStrLabelValue());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setbRequired(dto.getbRequired());
				form.setbRequired(dto.getbRequired());
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
	public MerUserDTO createUserDTO(AgencyRegistrationForm form) {

		MerUserDTO dto = new MerUserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());

		return dto;
	}

	
	/**
	 * 
	 * @param attributeList
	 * @return
	 */
	public List<MerProfileAttribDTO> transformProfileAttribFormToDTO(List<AgencyProfileAttribForm> attributeList){
		
		List<MerProfileAttribDTO> dtoList = new ArrayList<MerProfileAttribDTO>();
		
		if(null != attributeList){
			for(AgencyProfileAttribForm form : attributeList){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
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
		
		return dtoList;		
	}
	
}