package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.login.web.controller.ChangePasswordForm;

@Repository("transformJobSeekerRegistration")
public class TransformJobSeekerRegistration {

	/**
	 * Converting JobSeekerRegistrationDTO to JobSeekerRegistrationForm
	 * 
	 * @param jobSeekerRegistrationDTO
	 * @return
	 */
	public JobSeekerRegistrationForm jsRegistrationDTOToJobSeekerRegistrationForm(
			JobSeekerRegistrationDTO jsRegistrationDTO, JobSeekerRegistrationForm form) {

				
		if(null != jsRegistrationDTO.getAddressDTO()){
			AddressDTO dto = jsRegistrationDTO.getAddressDTO();
			form.setAddressLine1(dto.getAddress1());
			form.setAddressLine2(dto.getAddress2());
			form.setCity(dto.getCity());
			form.setCountry(dto.getCountry());
			form.setState(dto.getState());
			form.setPostalCode(dto.getZipCode());
			form.setPhoneNo(dto.getPhone());
		}
		
		if(null != jsRegistrationDTO.getMerUserDTO()){
			MerUserDTO dto = jsRegistrationDTO.getMerUserDTO();
			form.setEmailId(dto.getEmailId());
			form.setFirstName(dto.getFirstName());
			form.setLastName(dto.getLastName());
			form.setMiddleName(dto.getMiddleName());
			form.setPassword(dto.getPassword());
			form.setMyIndustry(dto.getIndustry());
			form.setMyJobTitle(dto.getJobTitle());
			form.setMyProfession(dto.getProfession());
			form.setMySpeciality(dto.getSpeciality());		
		}
		
		if(null != jsRegistrationDTO.getJobSeekerProfileDTO()){
			JobSeekerProfileDTO dto = jsRegistrationDTO.getJobSeekerProfileDTO();
			form.setEthenticity(dto.getEthinicity());
			form.setGender(dto.getGender());
			form.setVeteranStatus(dto.getVeteranStatus());	
			form.setEmploymentType(dto.getEmploymentInformation());
		}

		return form;
	}


	
	/**
	 * Convering Job Seeker Registration Form to AddressDTO
	 * 
	 * @param form
	 * @return
	 */
	public AddressDTO createAddressDTO(ContactInfoForm form){
		// Address DTO
		AddressDTO dto = new AddressDTO();
		
		dto.setAddress1(form.getAddressLine1());
		dto.setAddress2(form.getAddressLine2());
		dto.setCity(form.getCity());
		dto.setCountry(form.getCountry());
		dto.setPhone(form.getPhoneNo());
		dto.setState(form.getState());
		dto.setZipCode(form.getPostalCode());

		return dto;

	}
	
	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public MerUserDTO createUserDTO(JobSeekerRegistrationForm form){
		
		MerUserDTO dto = new MerUserDTO();		
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		dto.setIndustry(form.getMyIndustry());
		dto.setProfession(form.getMyProfession());
		dto.setSpeciality(form.getMySpeciality());
		dto.setJobTitle(form.getMyJobTitle());		
//		dto.setUserId(322);
//		dto.setUserId(Integer.valueOf(form.getUserId()));
		return dto;
	}
	
	/**
	 * Converting Job Seeker Registration Form to JobSeekerProfileDTO
	 * 
	 * @param form
	 * @return
	 */
	public JobSeekerProfileDTO createJSProfileSettingsDTO(JobSeekerRegistrationForm form){
		// JobSeekerProfileDTO
		JobSeekerProfileDTO dto = new JobSeekerProfileDTO();
		dto.setEmploymentInformation(form.getEmploymentType());
		dto.setGender(form.getGender());
		dto.setEthinicity(form.getEthenticity());
		dto.setVeteranStatus(form.getVeteranStatus());
		
		// jobSeekerProfileDTO.setJobSeekerId(jobSeekerId);

		
		return dto;
	}
	
	/**
	 * Convering Change Password Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public MerUserDTO transformChangePasswordFormToMerUserDTO(ChangePasswordForm form){
		MerUserDTO dto = new MerUserDTO();
		dto.setUserId(323);
		dto.setEmailId(form.getEmailId());
		dto.setPassword(form.getPassword());
		dto.setCurrentPassword(form.getCurrentPassword());
		
		return dto;
	}

	public List<JobSeekerProfileAttribForm> transformDTOToProfileAttribForm(ResumeDTO resumeDTO){
		
		List<JobSeekerProfileAttribForm> listForms = new ArrayList<JobSeekerProfileAttribForm>();
		
		if(null != resumeDTO.getAttribList()){
			for(MerProfileAttribDTO dto : resumeDTO.getAttribList()){
				JobSeekerProfileAttribForm form = new JobSeekerProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setStrScreenName(dto.getStrScreenName());
				form.setStrSectionName(dto.getStrSectionName());
				
				listForms.add(form);
			}
		}
		
		return listForms;
		
	}
	
	
}
