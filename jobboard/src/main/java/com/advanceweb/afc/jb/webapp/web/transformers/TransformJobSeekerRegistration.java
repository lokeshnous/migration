package com.advanceweb.afc.jb.webapp.web.transformers;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.webapp.web.forms.registration.ContactInfoForm;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;

@Repository("transformJobSeekerRegistration")
public class TransformJobSeekerRegistration {

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerRegistrationForm jsRegistrationDTOToJobSeekerRegistrationForm(
			JobSeekerRegistrationDTO jsRegistrationDTO) {

		JobSeekerRegistrationForm form = new JobSeekerRegistrationForm();
		ContactInfoForm contactInfoForm = new ContactInfoForm();
		
		if(null != jsRegistrationDTO.getAddressDTO()){
			AddressDTO dto = jsRegistrationDTO.getAddressDTO();
			
			contactInfoForm.setAddressLine1(dto.getAddress1());
			contactInfoForm.setAddressLine2(dto.getAddress2());
			contactInfoForm.setCity(dto.getCity());
			contactInfoForm.setCountry(dto.getCountry());
			contactInfoForm.setState(dto.getState());
			contactInfoForm.setPostalCode(dto.getZipCode());
		
//			form.setAddressLine1(dto.getAddress1());
//			form.setAddressLine2(dto.getAddress2());
//			form.setCity(dto.getCity());
//			form.setCountry(dto.getCountry());
//			form.setState(dto.getState());
//			form.setPostalCode(dto.getZipCode());
		}
		
		if(null != jsRegistrationDTO.getMerUserDTO()){
			MerUserDTO dto = jsRegistrationDTO.getMerUserDTO();
			form.setEmailId(dto.getEmailId());
			
			contactInfoForm.setFirstName(dto.getFirstName());
			contactInfoForm.setLastName(dto.getLastName());
			contactInfoForm.setMiddleName(dto.getMiddleName());
			
//			form.setFirstName(dto.getFirstName());
//			form.setLastName(dto.getLastName());
//			form.setMiddleName(dto.getMiddleName());
			form.setPassword(dto.getPassword());
		}
		
		if(null != jsRegistrationDTO.getJobSeekerProfileDTO()){
			JobSeekerProfileDTO dto = jsRegistrationDTO.getJobSeekerProfileDTO();
			form.setImCurrentlyIn(dto.getEmploymentInformation());
			form.setEthenticity(dto.getEthinicity());
//			form.setGender(dto.getGender());
			form.setVeteranStatus(dto.getVeteranStatus());
//			form.setPassword(dto.getJobSeekerId());
		}

		return form;
	}


	
	/**
	 * Convering Job Seeker Registration Form to AddressDTO
	 * 
	 * @param form
	 * @return
	 */
	public AddressDTO createAddressDTO(ContactInfoForm contactInfoForm){
		// Address DTO
		AddressDTO dto = new AddressDTO();
		
		dto.setAddress1(contactInfoForm.getAddressLine1());
		dto.setAddress2(contactInfoForm.getAddressLine2());
		dto.setCity(contactInfoForm.getCity());
		dto.setCountry(contactInfoForm.getCountry());
//		dto.setPhone(contactInfoForm.getPhone());
		dto.setState(contactInfoForm.getState());
		dto.setZipCode(contactInfoForm.getPostalCode());
		
		
//		dto.setAddress1(form.getAddressLine1());
//		dto.setAddress2(form.getAddressLine2());
//		dto.setCity(form.getCity());
//		dto.setCountry(form.getCountry());
//		dto.setPhone(form.getPhone());
//		dto.setState(form.getState());
//		dto.setZipCode(form.getPostalCode());
		
		return dto;

	}
	
	/**
	 * Convering Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public MerUserDTO createUserDTO(JobSeekerRegistrationForm form){
		MerUserDTO dto = new MerUserDTO();
		ContactInfoForm contactInfoForm = form.getContactForm();
		
		dto.setFirstName(contactInfoForm.getFirstName());
		dto.setLastName(contactInfoForm.getLastName());
		dto.setMiddleName(contactInfoForm.getMiddleName());
		
//		dto.setFirstName(form.getFirstName());
//		dto.setLastName(form.getLastName());
//		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		
		return dto;
	}
	
	/**
	 * Convering Job Seeker Registration Form to JobSeekerProfileDTO
	 * 
	 * @param form
	 * @return
	 */
	public JobSeekerProfileDTO createJSProfileSettingsDTO(JobSeekerRegistrationForm form){
		// JobSeekerProfileDTO
		JobSeekerProfileDTO dto = new JobSeekerProfileDTO();
		dto.setEmploymentInformation(form.getImCurrentlyIn());
		dto.setEthinicity(form.getEthenticity());
//		dto.setGender(form.getGender());
		// jobSeekerProfileDTO.setJobSeekerId(jobSeekerId);
		dto.setVeteranStatus(form.getVeteranStatus());
		
		return dto;
	}
	

}
