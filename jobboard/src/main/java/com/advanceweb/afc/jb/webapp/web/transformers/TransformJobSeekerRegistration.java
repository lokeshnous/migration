package com.advanceweb.afc.jb.webapp.web.transformers;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerProfileSettingsForm;
import com.advanceweb.afc.jb.webapp.web.forms.registration.JobSeekerRegistrationForm;

public class TransformJobSeekerRegistration {

	/**
	 * This method is called to convert JobSeekerRegistrationForm to
	 * JobSeekerRegistrationDTO
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerRegistrationForm jsRegistrationDTOToJobSeekerRegistrationForm(
			JobSeekerRegistrationDTO jobSeekerRegistrationDTO) {

		JobSeekerRegistrationForm jobSeekerRegistrationForm = new JobSeekerRegistrationForm();

		return jobSeekerRegistrationForm;
	}

	/**
	 * This method is called to convert JobSeekerRegistrationDTO to
	 * JobSeekerRegistrationForm
	 * @param jobSeekerRegistrationForm
	 * @return
	 */
	public JobSeekerRegistrationDTO jsRegistrationFormTojsRegistrationDTO(
			JobSeekerRegistrationForm jobSeekerRegistrationForm) {

		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = new JobSeekerRegistrationDTO();
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
	
	/**
	 * This method is called to convert JobSeekerRegistrationDTO to
	 * JobSeekerRegistrationForm
	 * @param jobSeekerRegistrationForm
	 * @return
	 */
	public JobSeekerRegistrationDTO jsProfileSettingsFormTojsRegistrationDTO(
			JobSeekerProfileSettingsForm jsProfileSettingsForm) {

		JobSeekerRegistrationDTO jobSeekerRegistrationDTO = new JobSeekerRegistrationDTO();
		// Address DTO
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCountry(jsProfileSettingsForm.getCountry());		
		addressDTO.setZipCode(jsProfileSettingsForm.getPostalCode());
		addressDTO.setMobileNumber(jsProfileSettingsForm.getMobileNo());
		jobSeekerRegistrationDTO.setAddressDTO(addressDTO);

		// Mer User DTO
		MerUserDTO merUserDTO = new MerUserDTO();
		merUserDTO.setFirstName(jsProfileSettingsForm.getFirstName());
		merUserDTO.setLastName(jsProfileSettingsForm.getLastName());
		merUserDTO.setEmailId(jsProfileSettingsForm.getEmailId());
		jobSeekerRegistrationDTO.setMerUserDTO(merUserDTO);

		return jobSeekerRegistrationDTO;
	}
	
	/**
	 * This method is called to convert job seeker registration dto to job seeker
	 * profile settings form
	 * @param jsRegistrationDTO
	 * @param jsProfileSettingsForm
	 * @return
	 */
	public JobSeekerProfileSettingsForm jsRegistrationDTOTojsProfilesSettingsForm(JobSeekerRegistrationDTO jsRegistrationDTO,
			JobSeekerProfileSettingsForm jsProfileSettingsForm){
//		AddressDTO addDTO = jsRegistrationDTO.getAddressDTO();
		MerUserDTO userDTO = jsRegistrationDTO.getMerUserDTO();
		if(userDTO != null){
			jsProfileSettingsForm.setFirstName(userDTO.getFirstName());
			jsProfileSettingsForm.setLastName(userDTO.getLastName());
			jsProfileSettingsForm.setEmailId(userDTO.getEmailId());
		}
		
		/*if(addDTO != null){
			jsProfileSettingsForm.setCountry(addDTO.getCountry());
//			jsProfileSettingsForm.setJobTitle(jobTitle);
			jsProfileSettingsForm.setMobileNo(addDTO.getMobileNumber());
//			jsProfileSettingsForm.setMyIndustry(myIndustry);
//			jsProfileSettingsForm.setMyProfession(myProfession);
//			jsProfileSettingsForm.setMySpeciality(mySpeciality);
			jsProfileSettingsForm.setPostalCode(addDTO.getZipCode());
		}*/
		
		return jsProfileSettingsForm;		
	}

}
