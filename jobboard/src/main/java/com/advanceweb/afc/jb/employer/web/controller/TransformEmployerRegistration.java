package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.EmployerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerProfileAttribForm;

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
	public MerUserDTO transformEmpFormToMerUserDTO(EmployerRegistrationForm form){
		MerUserDTO dto = new MerUserDTO();
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
	public AddressDTO transformEmpFormToAddressDTO(EmployerRegistrationForm form){
		AddressDTO dto = new AddressDTO();
		dto.setAddress1(form.getAddress());
		dto.setCity(form.getCity());
		dto.setCountry(form.getCountry());
		dto.setMobileNumber(form.getPrimaryPhone());
		dto.setPhone(form.getSecondryPhone());
		dto.setState(form.getState());
		dto.setStreet(form.getAddress());
		dto.setZipCode(form.getPostalCode());
		return dto;		
	}
	
	/**
	 * Converting into CompanyProfileDTO from EmployeeRegistrationForm
	 * 
	 * @param form
	 * @return CompanyProfileDTO
	 */
	public CompanyProfileDTO transformEmpFormToCompProfileDTO(EmployerRegistrationForm form){
		CompanyProfileDTO dto = new CompanyProfileDTO();
//		dto.setCompanyEmail(companyEmail);
		dto.setCompanyName(form.getCompany());
		dto.setPositionTitle(form.getPositionTitle());
//		dto.setCompanyNews(companyNews);
//		dto.setCompanyOverview(companyOverview);
//		dto.setCompanyWebsite(companyWebsite);
		return dto;		
	}
	
public List<EmployerProfileAttribForm> transformDTOToProfileAttribForm(EmployerProfileDTO registerDTO){
		
		List<EmployerProfileAttribForm> listForms = new ArrayList<EmployerProfileAttribForm>();
		
		if(null != registerDTO.getAttribList()){
			for(MerProfileAttribDTO dto : registerDTO.getAttribList()){
				EmployerProfileAttribForm form = new EmployerProfileAttribForm();
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
	
}
