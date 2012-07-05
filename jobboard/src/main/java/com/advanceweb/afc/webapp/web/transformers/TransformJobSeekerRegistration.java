package com.advanceweb.afc.webapp.web.transformers;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.advanceweb.afc.common.AddressDTO;
import com.advanceweb.afc.common.JobSeekerProfileDTO;
import com.advanceweb.afc.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.common.MerUserDTO;
import com.advanceweb.afc.webapp.web.forms.registration.JobSeekerRegistrationForm;

public class TransformJobSeekerRegistration {

	/**
	 * This method is called to convert JobSeekerRegistrationForm to
	 * JobSeekerRegistrationDTO
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerRegistrationForm JobSeekerRegistrationDTOToJobSeekerRegistrationForm(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {

		JobSeekerRegistrationForm jobSeekerRegistrationForm = new JobSeekerRegistrationForm();

		return jobSeekerRegistrationForm;
	}

	/**
	 * This method is called to convert JobSeekerRegistrationDTO to
	 * JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationForm
	 * @return
	 */
	public JobSeekerRegistrationDTO JobSeekerRegistrationFormToJobSeekerRegistrationDTO(
			JobSeekerRegistrationForm jobSeekerRegistrationForm) {

		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = new JobSeekerRegistrationDTO();
/*		CommonsMultipartFile file = jobSeekerRegistrationForm.getFileData();
		System.out.println("File" +jobSeekerRegistrationForm.getFileData());*/
		// Address DTO
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddress1(jobSeekerRegistrationForm.getAddressLine1());
		addressDTO.setAddress2(jobSeekerRegistrationForm.getAddressLine2());
		addressDTO.setCity(jobSeekerRegistrationForm.getCity());
		addressDTO.setCountry(jobSeekerRegistrationForm.getCountry());
		addressDTO.setPhone(jobSeekerRegistrationForm.getPhone());
		addressDTO.setState(jobSeekerRegistrationForm.getState());
		addressDTO.setZipCode(jobSeekerRegistrationForm.getPostalCode());
		jobSeekerRegistrationDTO.setAddressDTO(addressDTO);

		// Mer User DTO
		MerUserDTO merUserDTO = new MerUserDTO();
		merUserDTO.setFirstName(jobSeekerRegistrationForm.getFirstName());
		merUserDTO.setLastName(jobSeekerRegistrationForm.getLastName());
		merUserDTO.setMiddleName(jobSeekerRegistrationForm.getMiddleName());
		merUserDTO.setPassword(jobSeekerRegistrationForm.getPassword());
		merUserDTO.setEmailId(jobSeekerRegistrationForm.getEmailId());
		jobSeekerRegistrationDTO.setMerUserDTO(merUserDTO);

		// JobSeekerProfileDTO
		JobSeekerProfileDTO jobSeekerProfileDTO = new JobSeekerProfileDTO();
		jobSeekerProfileDTO.setEmploymentInformation(jobSeekerRegistrationForm
				.getImCurrentlyIn());
		jobSeekerProfileDTO.setEthinicity(jobSeekerRegistrationForm
				.getEthenticity());
		jobSeekerProfileDTO.setGender(jobSeekerRegistrationForm.getGender());
		// jobSeekerProfileDTO.setJobSeekerId(jobSeekerId);
		jobSeekerProfileDTO.setVeteranStatus(jobSeekerRegistrationForm
				.getVeteranStatus());
		jobSeekerRegistrationDTO.setJobSeekerProfileDTO(jobSeekerProfileDTO);

		return jobSeekerRegistrationDTO;
	}

}
