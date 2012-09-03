package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.JobSeekerProfileDTO;
import com.advanceweb.afc.jb.common.JobSeekerRegistrationDTO;
import com.advanceweb.afc.jb.common.ProfileAttribDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
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
			UserDTO dto = jsRegistrationDTO.getMerUserDTO();
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
	public UserDTO createUserDTO(JobSeekerRegistrationForm form){
		
		UserDTO dto = new UserDTO();		
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		
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
	public UserDTO transformChangePasswordFormToMerUserDTO(ChangePasswordForm form){
		UserDTO dto = new UserDTO();
		dto.setEmailId(form.getEmailId());
		dto.setPassword(form.getPassword());
		dto.setCurrentPassword(form.getCurrentPassword());
		
		return dto;
	}

	public List<JobSeekerProfileAttribForm> transformDTOToProfileAttribForm(JobSeekerRegistrationDTO registerDTO){
		
		List<JobSeekerProfileAttribForm> listForms = new ArrayList<JobSeekerProfileAttribForm>();
		
		if(null != registerDTO.getAttribList()){
			for(ProfileAttribDTO dto : registerDTO.getAttribList()){
				JobSeekerProfileAttribForm form = new JobSeekerProfileAttribForm();
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
	 * 
	 * @param attributeList
	 * @return
	 */
	public List<ProfileAttribDTO> transformProfileAttribFormToDTO(List<JobSeekerProfileAttribForm> attributeList){
		
		List<ProfileAttribDTO> dtoList = new ArrayList<ProfileAttribDTO>();
		
		if(null != attributeList){
			for(JobSeekerProfileAttribForm form : attributeList){
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
		
		return dtoList;		
	}
}
