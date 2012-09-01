package com.advanceweb.afc.jb.resume.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.PhoneDetailDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.jobseeker.web.controller.ContactInfoForm;

/**
 * 
 * @author Sasibhushana
 * 
 * @Version 1.0
 * @Since 2nd July, 2012
 */
@Repository("transCreateResume")
public class TransformCreateResume {

	public ResumeDTO transformCreateResumeToResumeDTO(CreateResume createResume) {
		ResumeDTO resumeDTO = new ResumeDTO();
		if (!("".equals(createResume.getUploadResumeId()))
				&& createResume.getUploadResumeId() != null) {
			resumeDTO.setUploadResumeId(Integer.parseInt(createResume
					.getUploadResumeId()));
		}
		resumeDTO.setResumeType(createResume.getResumeType());
		resumeDTO.setResumeName(createResume.getResumeName());
		resumeDTO.setDesiredJobTitle(createResume.getDesiredJobTitle());
		resumeDTO.setDesiredEmploymentType(createResume
				.getDesiredEmploymentType());
		resumeDTO.setResumeVisibility(createResume.getResumeVisibility());
		resumeDTO.setWorkAuthorizationUS(createResume.getWorkAuthorizationUS());
		resumeDTO.setWillingToRelocate(createResume.getWillingToRelocate());
		resumeDTO.setResumeVisibility(createResume.getResumeVisibility());
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
				.getResumeType())) {
			resumeDTO.setFileName(createResume.getFilename());
			resumeDTO.setFilePath(createResume.getFilePath());
		}
		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO
				.getResumeType())) {
			resumeDTO.setResumeText(createResume.getResumeText());
		}
		return resumeDTO;
	}

	public void transformResumeDTOToCreateResume(CreateResume createResume,
			ResumeDTO resumeDTO) {
		createResume.setUploadResumeId(String.valueOf(resumeDTO
				.getUploadResumeId()));
		createResume.setResumeName(resumeDTO.getResumeName());
		createResume.setResumeType(resumeDTO.getResumeType());
		createResume.setDesiredJobTitle(resumeDTO.getDesiredJobTitle());
		createResume.setDesiredEmploymentType(resumeDTO
				.getDesiredEmploymentType());
		createResume.setWorkAuthorizationUS(resumeDTO.getWorkAuthorizationUS());
		createResume.setWillingToRelocate(resumeDTO.getWillingToRelocate());
		createResume.setResumeVisibility(resumeDTO.getResumeVisibility());
		if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
				.getResumeType())) {
			createResume.setFilename(resumeDTO.getFileName());
			createResume.setFilePath(resumeDTO.getFilePath());
		}
		if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO
				.getResumeType())) {
			createResume.setResumeText(resumeDTO.getResumeText());
		}
	}

	/**
	 * Method to convert Certifications form to Reference dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<CertificationDTO> transformCertificationDTO(
			List<CertificationsForm> listCertForms) {

		List<CertificationDTO> listCertDTO = new ArrayList<CertificationDTO>();
		CertificationDTO dto = new CertificationDTO();
		if (null != listCertForms) {
			for (CertificationsForm certForm : listCertForms) {
				dto.setCertificationName(certForm.getCertificationName());
				dto.setDateOfReceipt(certForm.getDateOfReceipt());
				dto.setInstituteName(certForm.getInstituteName());
				dto.setSummary(certForm.getSummary());
				dto.setCertifyingAuthority(certForm.getCertifyingAuthority());
				dto.setBuilderCertId(certForm.getBuilderCertId());
				listCertDTO.add(dto);
			}
		}
		return listCertDTO;

	}

	/**
	 * Method to convert Reference form to Reference dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<ReferenceDTO> transformReferenceDTO(
			List<ReferenceForm> listRefForms) {

		List<ReferenceDTO> listRefDTO = new ArrayList<ReferenceDTO>();
		ReferenceDTO dto = new ReferenceDTO();
		if (null != listRefForms) {
			for (ReferenceForm refForm : listRefForms) {
				dto.setCompanyName(refForm.getCompanyName());
				dto.setEmail(refForm.getEmail());
				dto.setJobTitle(refForm.getJobTitle());
				dto.setName(refForm.getName());
				dto.setPhoneNo(refForm.getPhoneNo());
				dto.setBuilderRefId(refForm.getBuilderRefId());
				dto.setRefType(refForm.getReferenceType());
				listRefDTO.add(dto);
			}
		}
		return listRefDTO;

	}

	/**
	 * Method to convert Education form to Education DTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<EducationDTO> transformEducationDTO(
			List<EducationForm> listEduForms) {

		List<EducationDTO> listEduDTO = new ArrayList<EducationDTO>();
		EducationDTO dto = new EducationDTO();
		if (null != listEduForms) {
			for (EducationForm eduForm : listEduForms) {
				dto.setCertifications(eduForm.getCertifications());
				dto.setDegreeLvl(eduForm.getDegreeLvl());
				dto.setDegrees(eduForm.getDegrees());
				dto.setEndDate(eduForm.getEndDate());
				dto.setFieldOfStudy(eduForm.getFieldOfStudy());
				dto.setInstituteName(eduForm.getInstituteName());
				dto.setLanguage(eduForm.getLanguage());
				dto.setStartDate(eduForm.getStartDate());
				dto.setBuilderEduId(eduForm.getBuilderEduId());
				dto.setbNotGraduatedYet(eduForm.isbNotGraduatedYet());
				listEduDTO.add(dto);
			}
		}
		return listEduDTO;

	}

	/**
	 * Method to convert Education form to Education dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<WorkExpDTO> transformWorkExpDTO(
			List<WorkExpForm> listWorkExpForms) {

		List<WorkExpDTO> listWorkExpDTO = new ArrayList<WorkExpDTO>();
		WorkExpDTO dto = new WorkExpDTO();
		if (null != listWorkExpForms) {
			for (WorkExpForm workExpForm : listWorkExpForms) {
				dto.setAnnualSalary(workExpForm.getAnnualSalary());
				dto.setCurrentCareerLvl(workExpForm.getCurrentCareerLvl());
				dto.setDescription(workExpForm.getDescription());
				dto.setEmployerName(workExpForm.getEmployerName());
				dto.setEmploymentType(workExpForm.getEmploymentType());
				dto.setEndDate(workExpForm.getEndDate());
				dto.setHrlyPayRate(workExpForm.getHrlyPayRate());
				dto.setJobTitle(workExpForm.getJobTitle());
				dto.setStartDate(workExpForm.getStartDate());
				dto.setYrsAtPostion(workExpForm.getYrsAtPostion());
				dto.setBuilderEmpId(workExpForm.getBuilderEmpId());
				dto.setbPresent(workExpForm.isbPresent());
				dto.setbCurrentCareerLevel(workExpForm.isbCurrentCareerLevel());
				listWorkExpDTO.add(dto);
			}
		}
		return listWorkExpDTO;

	}

	/**
	 * Method to convert Language form to Language dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<LanguageDTO> transformLanguageDTO(
			List<LanguageForm> listLangForms) {

		List<LanguageDTO> listWorkExpDTO = new ArrayList<LanguageDTO>();
		LanguageDTO dto = new LanguageDTO();
		if (null != listLangForms) {
			for (LanguageForm langForm : listLangForms) {
				dto.setExpLvl(langForm.getExpLvl());
				dto.setLanguage(langForm.getLanguage());
				dto.setnLangId(langForm.getnLangId());
				listWorkExpDTO.add(dto);
			}
		}
		return listWorkExpDTO;

	}

	/**
	 * Method to convert Language form to Language dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<PhoneDetailDTO> transformPhoneDetailDTO(
			List<PhoneDetailForm> phoneDetails) {

		List<PhoneDetailDTO> phoneDtlDTO = new ArrayList<PhoneDetailDTO>();
		if (null != phoneDetails) {
			PhoneDetailDTO dto = new PhoneDetailDTO();
			for (PhoneDetailForm phoneForm : phoneDetails) {

				dto.setBuilderPhoneId(phoneForm.getBuilderPhoneId());
				dto.setPhoneNumber(phoneForm.getPhoneNumber());
				dto.setPhoneType(phoneForm.getPhoneType());

				phoneDtlDTO.add(dto);
			}
		}
		return phoneDtlDTO;

	}

	/**
	 * Method to Phone Detail DTO to Phone Detail Form
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<PhoneDetailForm> transformPhoneDetailDTOToForm(
			List<PhoneDetailDTO> phoneDetails) {

		List<PhoneDetailForm> phoneDtls = new ArrayList<PhoneDetailForm>();
		if (null == phoneDetails) {
			PhoneDetailForm form = new PhoneDetailForm();
			phoneDtls.add(form);
		} else {
			PhoneDetailForm form = new PhoneDetailForm();
			for (PhoneDetailDTO dto : phoneDetails) {
				form.setBuilderPhoneId(dto.getBuilderPhoneId());
				form.setPhoneNumber(dto.getPhoneNumber());
				form.setPhoneType(dto.getPhoneType());
				phoneDtls.add(form);
			}
		}
		return phoneDtls;

	}

	/**
	 * Method to convert Create Resume form to Resume dto
	 * 
	 * @param listCertForms
	 * @return
	 */
	public ResumeDTO transformResumeDTO(ResumeDTO dto, CreateResume form) {

		if (null != form) {
			dto.setAwards(form.getAwards());
			dto.setObjective(form.getObjective());
			dto.setOtherDetails(form.getOtherDetails());
			dto.setMemberships(form.getMemberships());
			dto.setSkills(form.getSkills());
			dto.setBuilderSkillsId(form.getBuilderSkillsId());
			dto.setBuilderResumeId(form.getBuilderResumeId());
			dto.setResumeName(form.getResumeName());
			dto.setUserId(form.getUserId());
			dto.setUploadResumeId(Integer.valueOf(form.getUploadResumeId()));
		}

		return dto;
	}

	/**
	 * Method to convert CreateResume form to Contact Info dto it contains
	 * 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public ContactInformationDTO transformContactInfoDTO(ContactInfoForm form) {
		ContactInformationDTO dto = new ContactInformationDTO();
		if (null != form) {
			dto.setEmail("");
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setMiddleNameInitial(form.getMiddleName());
		}

		return dto;
	}

	/**
	 * Method to convert Contact Info dto to ContactInfo form it contains
	 * 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public ContactInfoForm transformContactInfoForm(ContactInformationDTO dto) {
		ContactInfoForm form = new ContactInfoForm();
		if (null != dto) {
			form.setFirstName(dto.getFirstName());
			form.setLastName(dto.getLastName());
			form.setMiddleName(dto.getMiddleNameInitial());
			if (null != dto.getAddressDTO()) {
				form.setAddressLine1(dto.getAddressDTO().getAddress1());
				form.setAddressLine2(dto.getAddressDTO().getAddress2());
				form.setCity(dto.getAddressDTO().getCity());
				form.setCountry(dto.getAddressDTO().getCountry());
				form.setPostalCode(dto.getAddressDTO().getZipCode());
				form.setState(dto.getAddressDTO().getState());
				form.setPhoneNo(dto.getAddressDTO().getPhone());
				form.setMobileNo(dto.getAddressDTO().getMobileNumber());
			}
		}

		return form;
	}

	/**
	 * Method to convert education dto to education form it contains
	 * 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<EducationForm> transformEducationForm(
			List<EducationDTO> listEduDTO) {

		List<EducationForm> listEduForms = new ArrayList<EducationForm>();

		if (null == listEduDTO) {
			EducationForm eduForm = new EducationForm();
			listEduForms.add(eduForm);
		} else {
			EducationForm form = new EducationForm();
			for (EducationDTO dto : listEduDTO) {
				form.setBuilderEduId(dto.getBuilderEduId());
				form.setCertifications(dto.getCertifications());
				form.setDegreeLvl(dto.getDegreeLvl());
				form.setDegrees(dto.getDegrees());
				form.setEndDate(dto.getEndDate());
				form.setFieldOfStudy(dto.getFieldOfStudy());
				form.setInstituteName(dto.getInstituteName());
				form.setLanguage(dto.getLanguage());
				form.setStartDate(dto.getStartDate());
				form.setbNotGraduatedYet(dto.isbNotGraduatedYet());
				listEduForms.add(form);
			}
		}
		return listEduForms;
	}

	/**
	 * Method to convert Reference dto to reference form it contains
	 * 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<ReferenceForm> transformReferenceForm(
			List<ReferenceDTO> listRefpDTO) {

		List<ReferenceForm> listWorkExpForms = new ArrayList<ReferenceForm>();

		if (null == listRefpDTO) {
			ReferenceForm refForm = new ReferenceForm();
			refForm.setReferenceType(MMJBCommonConstants.REFERENCE_TYPE_PERSONAL);
			listWorkExpForms.add(refForm);
		} else {
			ReferenceForm form = new ReferenceForm();
			for (ReferenceDTO dto : listRefpDTO) {
				form.setBuilderRefId(dto.getBuilderRefId());
				form.setCompanyName(dto.getCompanyName());
				form.setEmail(dto.getEmail());
				form.setJobTitle(dto.getJobTitle());
				form.setName(dto.getName());
				form.setPhoneNo(dto.getPhoneNo());
				form.setReferenceType(dto.getRefType());
				listWorkExpForms.add(form);
			}
		}
		return listWorkExpForms;
	}

	/**
	 * Method to convert work exp dto to work exp form it contains 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<WorkExpForm> transformWorkExpForm(
			List<WorkExpDTO> listWorkExpDTO) {

		List<WorkExpForm> listWorkExpForms = new ArrayList<WorkExpForm>();

		if (null == listWorkExpDTO) {
			WorkExpForm workExpForm = new WorkExpForm();
			listWorkExpForms.add(workExpForm);
		} else {
			WorkExpForm form = new WorkExpForm();
			for (WorkExpDTO dto : listWorkExpDTO) {
				form.setAnnualSalary(dto.getAnnualSalary());
				form.setBuilderEmpId(dto.getBuilderEmpId());
				form.setCurrentCareerLvl(dto.getCurrentCareerLvl());
				form.setDescription(dto.getDescription());
				form.setEmployerName(dto.getEmployerName());
				form.setEmploymentType(dto.getEmploymentType());
				form.setEndDate(dto.getEndDate());
				form.setHrlyPayRate(dto.getHrlyPayRate());
				form.setJobTitle(dto.getJobTitle());
				form.setStartDate(dto.getStartDate());
				form.setYrsAtPostion(dto.getYrsAtPostion());
				form.setbCurrentCareerLevel(dto.isbCurrentCareerLevel());
				form.setbPresent(dto.isbPresent());
				listWorkExpForms.add(form);
			}
		}
		return listWorkExpForms;
	}

	/**
	 * Method to convert work exp dto to work exp form it contains 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<CertificationsForm> transformCertForm(
			List<CertificationDTO> listCertDTO) {

		List<CertificationsForm> listCertForms = new ArrayList<CertificationsForm>();

		if (null == listCertDTO) {
			CertificationsForm certForm = new CertificationsForm();
			listCertForms.add(certForm);
		} else {
			for (CertificationDTO dto : listCertDTO) {
				CertificationsForm form = new CertificationsForm();
				form.setBuilderCertId(dto.getBuilderCertId());
				form.setCertificationName(dto.getCertificationName());
				form.setDateOfReceipt(dto.getDateOfReceipt());
				form.setCertifyingAuthority(dto.getCertifyingAuthority());
				form.setInstituteName(dto.getInstituteName());
				form.setSummary(dto.getSummary());
				listCertForms.add(form);
			}
		}
		return listCertForms;
	}

	/**
	 * Method to convert language dto to language form it contains 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public List<LanguageForm> transformLanguageForm(
			List<LanguageDTO> listLangDTO) {

		List<LanguageForm> listLangForms = new ArrayList<LanguageForm>();

		if (null == listLangDTO) {
			LanguageForm languageForm = new LanguageForm();
			listLangForms.add(languageForm);
		} else {
			for (LanguageDTO dto : listLangDTO) {
				LanguageForm form = new LanguageForm();
				form.setExpLvl(dto.getExpLvl());
				form.setLanguage(dto.getLanguage());
				form.setnLangId(dto.getnLangId());
				listLangForms.add(form);
			}
		}
		return listLangForms;
	}

	/**
	 * Method to convert language dto to language form it contains 1.AddressDTO
	 * 
	 * @param listCertForms
	 * @return
	 */
	public CreateResume transformCreateResumeForm(ResumeDTO resumeDTO) {

		CreateResume createResume = new CreateResume();
		if (null != resumeDTO) {
			createResume.setAwards(resumeDTO.getAwards());
			createResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
			createResume.setDesiredEmploymentType(resumeDTO
					.getDesiredEmploymentType());
			createResume.setDesiredJobTitle(resumeDTO.getDesiredJobTitle());
			createResume
					.setEmploymentType(resumeDTO.getDesiredEmploymentType());
			createResume.setMemberships(resumeDTO.getMemberships());
			createResume.setObjective(resumeDTO.getObjective());
			createResume.setOtherDetails(resumeDTO.getOtherDetails());
			createResume.setResumeName(resumeDTO.getResumeName());
			createResume.setResumeVisibility(resumeDTO.getResumeVisibility());
			createResume.setResumeText(resumeDTO.getResumeText());
			// createResume.setResumeText1(resumeDTO.get);
			createResume.setResumeType(resumeDTO.getResumeType());
			createResume.setSkills(resumeDTO.getSkills());
			createResume.setBuilderSkillsId(resumeDTO.getBuilderSkillsId());
			createResume.setWillingToRelocate(resumeDTO.getWillingToRelocate());
			createResume.setWorkAuthorizationUS(resumeDTO
					.getWorkAuthorizationUS());
			createResume.setUploadResumeId(String.valueOf(resumeDTO
					.getUploadResumeId()));
			createResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
		}

		return createResume;

	}

}
