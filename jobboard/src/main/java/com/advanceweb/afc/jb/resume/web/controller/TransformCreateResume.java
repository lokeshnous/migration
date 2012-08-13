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
		ResumeDTO resumeDTO=new ResumeDTO();
		if(!("".equals(createResume.getUploadResumeId())) && createResume.getUploadResumeId() != null){
			resumeDTO.setUploadResumeId(Integer.parseInt(createResume.getUploadResumeId()));
		}
		resumeDTO.setResumeType(createResume.getResumeType());
		resumeDTO.setResumeName(createResume.getResumeName());
		resumeDTO.setDesiredJobTitle(createResume.getDesiredJobTitle());
		resumeDTO.setDesiredEmploymentType(createResume.getDesiredEmploymentType());
		resumeDTO.setResumeVisibility(createResume.getResumeVisibility());
		resumeDTO.setWorkAuthorizationUS(createResume.getWorkAuthorizationUS());
		resumeDTO.setWillingToRelocate(createResume.getWillingToRelocate());
		resumeDTO.setResumeVisibility(createResume.getResumeVisibility());
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType())){
			resumeDTO.setFileName(createResume.getFilename());
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
			resumeDTO.setResumeText(createResume.getResumeText());
		}
		return resumeDTO;
	}
	
	public void transformResumeDTOToCreateResume(CreateResume createResume,ResumeDTO resumeDTO) {
		createResume.setUploadResumeId(String.valueOf(resumeDTO.getUploadResumeId()));
		createResume.setResumeName(resumeDTO.getResumeName());
		createResume.setResumeType(resumeDTO.getResumeType());
		createResume.setDesiredJobTitle(resumeDTO.getDesiredJobTitle());
		createResume.setDesiredEmploymentType(resumeDTO.getDesiredEmploymentType());
		createResume.setWorkAuthorizationUS(resumeDTO.getWorkAuthorizationUS());
		createResume.setWillingToRelocate(resumeDTO.getWillingToRelocate());
		createResume.setResumeVisibility(resumeDTO.getResumeVisibility());
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType())){
			createResume.setFilename(resumeDTO.getFileName());
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
			createResume.setResumeText(resumeDTO.getResumeText());
		}
	}
	
	/**
	 * Method to convert Certifications form to Reference dto
	 * @param listCertForms
	 * @return
	 */
	public List<CertificationDTO> transformCertificationDTO(List<CertificationsForm> listCertForms){
		
		List<CertificationDTO> listCertDTO = new ArrayList<CertificationDTO>();
		if(null != listCertForms){
			for(CertificationsForm certForm : listCertForms){
				CertificationDTO dto = new CertificationDTO();
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
	 * @param listCertForms
	 * @return
	 */
	public List<ReferenceDTO> transformReferenceDTO(List<ReferenceForm> listRefForms){
		
		List<ReferenceDTO> listRefDTO = new ArrayList<ReferenceDTO>();
		if(null != listRefForms){
			for(ReferenceForm refForm : listRefForms){
				ReferenceDTO dto = new ReferenceDTO();
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
	 * @param listCertForms
	 * @return
	 */
	public List<EducationDTO> transformEducationDTO(List<EducationForm> listEduForms){
		
		List<EducationDTO> listEduDTO = new ArrayList<EducationDTO>();
		if(null != listEduForms){
			for(EducationForm eduForm : listEduForms){
				EducationDTO dto = new EducationDTO();
				dto.setCertifications(eduForm.getCertifications());
				dto.setDegreeLvl(eduForm.getDegreeLvl());
				dto.setDegrees(eduForm.getDegrees());
				dto.setEndDate(eduForm.getEndDate());
				dto.setFieldOfStudy(eduForm.getFieldOfStudy());
				dto.setInstituteName(eduForm.getInstituteName());
				dto.setLanguage(eduForm.getLanguage());
				dto.setStartDate(eduForm.getStartDate());
				dto.setBuilderEduId(eduForm.getBuilderEduId());
				listEduDTO.add(dto);
			}
		}
		return listEduDTO;
		
	}
	
	/**
	 * Method to convert Education form to Education dto
	 * @param listCertForms
	 * @return
	 */
	public List<WorkExpDTO> transformWorkExpDTO(List<WorkExpForm> listWorkExpForms){
		
		List<WorkExpDTO> listWorkExpDTO = new ArrayList<WorkExpDTO>();
		if(null != listWorkExpForms){
			for(WorkExpForm workExpForm : listWorkExpForms){
				WorkExpDTO dto = new WorkExpDTO();
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
				listWorkExpDTO.add(dto);
			}
		}
		return listWorkExpDTO;
		
	}
	
	/**
	 * Method to convert Language form to Language dto
	 * @param listCertForms
	 * @return
	 */
	public List<LanguageDTO> transformLanguageDTO(List<LanguageForm> listLangForms){
		
		List<LanguageDTO> listWorkExpDTO = new ArrayList<LanguageDTO>();
		if(null != listLangForms){
			for(LanguageForm langForm : listLangForms){
				LanguageDTO dto = new LanguageDTO();
				dto.setExpLvl(langForm.getExpLvl());
				dto.setLanguage(langForm.getLanguage());
				listWorkExpDTO.add(dto);
			}
		}
		return listWorkExpDTO;
		
	}
	
	
	/**
	 * Method to convert Language form to Language dto
	 * @param listCertForms
	 * @return
	 */
	public List<PhoneDetailDTO> transformPhoneDetailDTO(List<PhoneDetailForm> phoneDetails){
		
		List<PhoneDetailDTO> phoneDtlDTO = new ArrayList<PhoneDetailDTO>();
		if(null != phoneDetails){
			for(PhoneDetailForm phoneForm : phoneDetails){
				PhoneDetailDTO dto = new PhoneDetailDTO();
				
				dto.setBuilderPhoneId(phoneForm.getBuilderPhoneId());
				dto.setPhoneNumber(phoneForm.getPhoneNumber());
				dto.setPhoneType(phoneForm.getPhoneType());
				
				phoneDtlDTO.add(dto);
			}
		}
		return phoneDtlDTO;
		
	}
	
	/**
	 * Method to convert Create Resume form to Resume dto
	 * @param listCertForms
	 * @return
	 */
	public ResumeDTO transformResumeDTO(ResumeDTO dto, CreateResume form){

		if(null!= form){
			dto.setAwards(form.getAwards());
			dto.setObjective(form.getObjective());
			dto.setOtherDetails(form.getOtherDetails());
			dto.setMemberships(form.getMemberships());
			dto.setSkills(form.getSkills());
			dto.setBuilderResumeId(form.getBuilderResumeId());
			dto.setResumeName(form.getResumeName());
			dto.setUserId(form.getUserId());
			dto.setUploadResumeId(Integer.valueOf(form.getUploadResumeId()));
		}
		
		return dto;
	}
	
	
	/**
	 * Method to convert CreateResume form to Contact Info  dto
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public ContactInformationDTO transformContactInfoDTO(ContactInfoForm form){
		ContactInformationDTO dto = new ContactInformationDTO();
		if(null!= form){
			dto.setEmail("");
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setMiddleNameInitial(form.getMiddleName());
		}
		
		return dto;
	}
	
	/**
	 * Method to convert Contact Info dto to ContactInfo form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public ContactInfoForm transformContactInfoForm(ContactInformationDTO dto){
		ContactInfoForm form = new ContactInfoForm();
		if(null!= dto){
			form.setFirstName(dto.getFirstName());
			form.setLastName(dto.getLastName());
			form.setMiddleName(dto.getMiddleNameInitial());
			if(null != dto.getAddressDTO()){
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
	 * Method to convert education dto to education form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public List<EducationForm> transformEducationForm(List<EducationDTO> listEduDTO){
		
		List<EducationForm> listEduForms = new ArrayList<EducationForm>();
		
		if(null!= listEduDTO){
			for(EducationDTO dto : listEduDTO){
				EducationForm form = new EducationForm();
				form.setBuilderEduId(dto.getBuilderEduId());
				form.setCertifications(dto.getCertifications());
				form.setDegreeLvl(dto.getDegreeLvl());
				form.setDegrees(dto.getDegrees());
				form.setEndDate(dto.getEndDate());
				form.setFieldOfStudy(dto.getFieldOfStudy());
				form.setInstituteName(dto.getInstituteName());
				form.setLanguage(dto.getLanguage());
				form.setStartDate(dto.getStartDate());
				listEduForms.add(form);
			}
		}else{
			EducationForm eduForm = new EducationForm();
			listEduForms.add(eduForm);
		}
		return listEduForms;
	}
	
	/**
	 * Method to convert Reference dto to reference form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public List<ReferenceForm> transformReferenceForm(List<ReferenceDTO> listRefpDTO){
		
		List<ReferenceForm> listWorkExpForms = new ArrayList<ReferenceForm>();
		
		if(null!= listRefpDTO){
			for(ReferenceDTO dto : listRefpDTO){
				ReferenceForm form = new ReferenceForm();
				form.setBuilderRefId(dto.getBuilderRefId());
				form.setCompanyName(dto.getCompanyName());
				form.setEmail(dto.getEmail());
				form.setJobTitle(dto.getJobTitle());
				form.setName(dto.getName());
				form.setPhoneNo(dto.getPhoneNo());
				form.setReferenceType(dto.getRefType());
				listWorkExpForms.add(form);
			}
		}else{
			ReferenceForm refForm = new ReferenceForm();
			listWorkExpForms.add(refForm);
		}
		return listWorkExpForms;
	}
	
	/**
	 * Method to convert work exp dto to work exp form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public List<WorkExpForm> transformWorkExpForm(List<WorkExpDTO> listWorkExpDTO){
		
		List<WorkExpForm> listWorkExpForms = new ArrayList<WorkExpForm>();
		
		if(null!= listWorkExpDTO){
			for(WorkExpDTO dto : listWorkExpDTO){
				WorkExpForm form = new WorkExpForm();
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
				listWorkExpForms.add(form);
			}
		}else{
			WorkExpForm workExpForm = new WorkExpForm();
			listWorkExpForms.add(workExpForm);
		}
		return listWorkExpForms;
	}
	
	/**
	 * Method to convert work exp dto to work exp form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public List<CertificationsForm> transformCertForm(List<CertificationDTO> listCertDTO){
		
		List<CertificationsForm> listCertForms = new ArrayList<CertificationsForm>();
		
		if(null!= listCertDTO){
			for(CertificationDTO dto : listCertDTO){
				CertificationsForm form = new CertificationsForm();
				form.setBuilderCertId(dto.getBuilderCertId());
				form.setCertificationName(dto.getCertificationName());
				form.setDateOfReceipt(dto.getDateOfReceipt());
				form.setCertifyingAuthority(dto.getCertifyingAuthority());
				form.setInstituteName(dto.getInstituteName());
				form.setSummary(dto.getSummary());
				listCertForms.add(form);
			}
		}else{
			CertificationsForm certForm = new CertificationsForm();
			listCertForms.add(certForm);
		}
		return listCertForms;
	}
	
	
	/**
	 * Method to convert language dto to language form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public List<LanguageForm> transformLanguageForm(List<LanguageDTO> listLangDTO){
		
		List<LanguageForm> listLangForms = new ArrayList<LanguageForm>();
		
		if(null!= listLangDTO){
			for(LanguageDTO dto : listLangDTO){
				LanguageForm form = new LanguageForm();
				form.setExpLvl(dto.getExpLvl());
				form.setLanguage(dto.getLanguage());
				form.setnLangId(dto.getnLangId());
				listLangForms.add(form);
			}
		}else{
			LanguageForm languageForm = new LanguageForm();
			listLangForms.add(languageForm);
		}
		return listLangForms;
	}
	
	
	/**
	 * Method to convert language dto to language form
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public CreateResume transformCreateResumeForm(ResumeDTO resumeDTO){
		
		CreateResume createResume = new CreateResume();
		if(null != resumeDTO){
			createResume.setAwards(resumeDTO.getAwards());
			createResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
			createResume.setDesiredEmploymentType(resumeDTO.getDesiredEmploymentType());
			createResume.setDesiredJobTitle(resumeDTO.getDesiredJobTitle());
			createResume.setEmploymentType(resumeDTO.getDesiredEmploymentType());
			createResume.setMemberships(resumeDTO.getMemberships());
			createResume.setObjective(resumeDTO.getObjective());
			createResume.setOtherDetails(resumeDTO.getOtherDetails());
			createResume.setResumeName(resumeDTO.getResumeName());
			createResume.setResumeVisibility(resumeDTO.getResumeVisibility());
			createResume.setResumeText(resumeDTO.getResumeText());
//			createResume.setResumeText1(resumeDTO.get);
			createResume.setResumeType(resumeDTO.getResumeType());
			createResume.setSkills(resumeDTO.getSkills());
			createResume.setWillingToRelocate(resumeDTO.getWillingToRelocate());
			createResume.setWorkAuthorizationUS(resumeDTO.getWorkAuthorizationUS());
			createResume.setUploadResumeId(String.valueOf(resumeDTO.getUploadResumeId()));
			createResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
		}
		
		return createResume;
		
	}
	
}
