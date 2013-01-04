package com.advanceweb.afc.jb.jobseeker.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
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
			JobSeekerRegistrationDTO jsRegistrationDTO,
			JobSeekerRegistrationForm form) {

		if (null != jsRegistrationDTO.getAddressDTO()) {
			AddressDTO dto = jsRegistrationDTO.getAddressDTO();
			form.setAddressLine1(dto.getAddress1());
			form.setAddressLine2(dto.getAddress2());
			form.setCity(dto.getCity());
			form.setCountry(dto.getCountry());
			form.setState(dto.getState());
			form.setPostalCode(dto.getZipCode());
			form.setPhoneNo(dto.getPhone());
		}

		if (null != jsRegistrationDTO.getMerUserDTO()) {
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

		if (null != jsRegistrationDTO.getJobSeekerProfileDTO()) {
			JobSeekerProfileDTO dto = jsRegistrationDTO
					.getJobSeekerProfileDTO();
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
	public AddressDTO createAddressDTO(ContactInfoForm form) {
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
	public UserDTO createUserDTO(JobSeekerRegistrationForm form) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());
		dto.setUserId(null != form.getUserId() ? Integer.valueOf(form
				.getUserId()) : 0);
		dto.setOldUser(form.isOldUser());
		return dto;
	}

	/**
	 * Converting Job Seeker Registration Form to JobSeekerProfileDTO
	 * 
	 * @param form
	 * @return
	 */
	public JobSeekerProfileDTO createJSProfileSettingsDTO(
			JobSeekerRegistrationForm form) {
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
	public UserDTO transformChangePasswordFormToMerUserDTO(
			ChangePasswordForm form) {
		UserDTO dto = new UserDTO();
		dto.setEmailId(form.getEmailId());
		dto.setPassword(form.getPassword());
		dto.setCurrentPassword(form.getCurrentPassword());

		return dto;
	}

	public List<JobSeekerProfileAttribForm> transformDTOToProfileAttribForm(
			JobSeekerRegistrationDTO registerDTO, UserDTO userDTO) {

		List<JobSeekerProfileAttribForm> listForms = new ArrayList<JobSeekerProfileAttribForm>();

		if (null != registerDTO.getAttribList()) {
			for (ProfileAttribDTO dto : registerDTO.getAttribList()) {
				JobSeekerProfileAttribForm form = new JobSeekerProfileAttribForm();
				form.setDropdown(dto.getDropdown());
				form.setStrAttribType(dto.getStrAttribType());
				form.setStrLabelName(dto.getStrLabelName());
				form.setStrLabelValue(dto.getStrLabelValue());
				form.setStrProfileAttribId(dto.getStrProfileAttribId());
				form.setRequired(dto.getRequired());
				form.setRequired(dto.getRequired());
				if (null != userDTO) {
					setValuesToForm(userDTO, form);
				}
				listForms.add(form);
			}
		}

		return listForms;
	}

	/**
	 * @param userDTO
	 * @param form
	 */
	private void setValuesToForm(UserDTO userDTO,
			JobSeekerProfileAttribForm form) {
		if (form.getStrLabelName().equals(MMJBCommonConstants.FIRST_NAME)) {
			form.setStrLabelValue(userDTO.getFirstName());
		}
		if (form.getStrLabelName().equals(MMJBCommonConstants.LAST_NAME)) {
			form.setStrLabelValue(userDTO.getLastName());
		}
		if (form.getStrLabelName().equals(MMJBCommonConstants.MIDDLE_NAME)) {
			form.setStrLabelValue(userDTO.getMiddleName());
		}
	}

	/**
	 * 
	 * @param attributeList
	 * @return
	 */
	public List<ProfileAttribDTO> transformProfileAttribFormToDTO(
			List<JobSeekerProfileAttribForm> attributeList,
			JobSeekerRegistrationForm regForm) {

		List<ProfileAttribDTO> dtoList = new ArrayList<ProfileAttribDTO>();

		if (null != attributeList) {
			setFormAttribToDTOAttributes(attributeList, regForm, dtoList);
		}
		if (regForm.isSocialSignUp()) {
			ProfileAttribDTO newDTO = new ProfileAttribDTO();
			newDTO.setStrLabelValue(regForm.getSocialProfileId());
			newDTO.setStrProfileAttribId(MMJBCommonConstants.LINKEDIN_PROFILE_ATTR_ID);
			if (regForm.getServiceProviderName().equals(
					MMJBCommonConstants.FACEBOOK)) {
				newDTO.setStrProfileAttribId(MMJBCommonConstants.FACEBOOK_PROFILE_ATTR_ID);
			}

			dtoList.add(newDTO);
		}
		return dtoList;
	}

	/**
	 * @param attributeList
	 * @param regForm
	 * @param dtoList
	 */
	private void setFormAttribToDTOAttributes(
			List<JobSeekerProfileAttribForm> attributeList,
			JobSeekerRegistrationForm regForm, List<ProfileAttribDTO> dtoList) {
		for (JobSeekerProfileAttribForm form : attributeList) {
			ProfileAttribDTO dto = new ProfileAttribDTO();
			if (MMJBCommonConstants.LABEL_SUSBSCRIPTION.equals(form
					.getStrLabelName())) {
				// Modified to set the subscription id for modify subscriptions
				// link
				if(form.getSubs() != null){
					dto.setStrLabelValue(StringUtils
							.arrayToCommaDelimitedString(form.getSubs()));
				}
				else{
					dto.setStrLabelValue(form.getStrLabelValue());
				}

			} else {
				if (form.getStrLabelName().equals(
						MMJBCommonConstants.MYINDUSTRY)) {
					dto.setStrLabelValue(MMJBCommonConstants.HEALTHCARE);
				} else {
					dto.setStrLabelValue(form.getStrLabelValue());
				}

				if (regForm != null
						&& regForm.getOtherProfession() != null
						&& form.getStrLabelName().equals(
								MMJBCommonConstants.MYPROFESSION)) {
					setValuesToDropDownDTO(regForm, form, dto);

				}
			}
			dto.setStrAttribType(form.getStrAttribType());
			dto.setStrLabelName(form.getStrLabelName());
			dto.setStrProfileAttribId(form.getStrProfileAttribId());
			dtoList.add(dto);
		}
	}

	/**
	 * @param regForm
	 * @param form
	 * @param dto
	 */
	private void setValuesToDropDownDTO(JobSeekerRegistrationForm regForm,
			JobSeekerProfileAttribForm form, ProfileAttribDTO dto) {
		for (DropDownDTO dropDown : form.getDropdown()) {
			if (MMJBCommonConstants.PROFESSION_OTHERS.equals(dropDown
					.getOptionName())
					&& form.getStrLabelValue().equals(dropDown.getOptionId())) {
				dto.setStrLabelValue(regForm.getOtherProfession());
			}
		}
	}
}
