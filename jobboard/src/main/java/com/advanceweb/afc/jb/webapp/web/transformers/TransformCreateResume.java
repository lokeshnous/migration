package com.advanceweb.afc.jb.webapp.web.transformers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.webapp.web.forms.registration.ContactInfoForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CertificationsForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.CreateResume;
import com.advanceweb.afc.jb.webapp.web.forms.resume.EducationForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.LanguageForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.ReferenceForm;
import com.advanceweb.afc.jb.webapp.web.forms.resume.WorkExpForm;

/**
 * 
 * @author Sasibhushana
 *
 * @Version 1.0
 * @Since 2nd July, 2012
 */
public class TransformCreateResume {
	
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
				listRefDTO.add(dto);
			}
		}
		return listRefDTO;
		
	}
	
	/**
	 * Method to convert Education form to Education dto
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
	public ContactInformationDTO transformContactInfoForm(ContactInfoForm form){
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
	 * Method to convert CreateResume form to Contact Info  dto
	 * it contains
	 * 	1.AddressDTO
	 * @param listCertForms
	 * @return
	 */
	public ContactInformationDTO createContactInfoDTO(ContactInfoForm form){
		ContactInformationDTO dto = new ContactInformationDTO();
		if(null!= form){
			dto.setEmail("");
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setMiddleNameInitial(form.getMiddleName());
		}
		
		return dto;
	}
	
}
