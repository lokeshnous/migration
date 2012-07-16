package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CompanyProfileDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

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
		dto.setAddress1(form.getAddressLine1());
		dto.setAddress2(form.getAddressLine2());
		dto.setCity(form.getCity());
		dto.setCountry(form.getCountry());
		dto.setMobileNumber(form.getMobileNo());
		dto.setPhone(form.getPhoneNo());
		dto.setState(form.getState());
		dto.setStreet(form.getStreet());
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
	
}
