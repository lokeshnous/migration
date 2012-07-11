package com.advanceweb.afc.jb.data.common.helpers;

import java.util.ArrayList;
import java.util.List;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResDegreeEdu;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
public class ResumeConversionHelper {

	/**
	 * This method transforms ResUploadResume to ResumeDTO 
	 * @param resume
	 * @return resumeDTO
	 */
	public ResumeDTO transformResUploadResumeToResumeDTO(ResUploadResume resume) {
		
		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setUploadResumeId(resume.getUploadResumeId());
		resumeDTO.setResume_name(resume.getResumeName());
		resumeDTO.setUpdateDt(resume.getUpdateDt());
		return resumeDTO;

	}

	/**
	 * This method transforms ResUploadResume list to ResumeDTO list
	 * @param resumes
	 * @return resumeDTOList
	 */
	public List<ResumeDTO> transformResUploadResumeListToResumeDTOList(
			List<ResUploadResume> resumes) {
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();

		for (ResUploadResume resume : resumes) {
			ResumeDTO resumeDTO = new ResumeDTO();
			resumeDTO.setUploadResumeId(resume.getUploadResumeId());
			resumeDTO.setResume_name(resume.getResumeName());
			resumeDTO.setUpdateDt(resume.getUpdateDt());
			resumeDTOList.add(resumeDTO);
		}

		return resumeDTOList;

	}
	
	/**
	 * This method is called to convert resumeDTO to 
	 * Resume Builder Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	public ResBuilderResume transformBuilderResume(ResumeDTO resumeDTO){
		ResBuilderResume builderResume = new ResBuilderResume();
		builderResume.setUserId(resumeDTO.getUserId());
		builderResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
		builderResume.setResumeName(resumeDTO.getResume_name());
		
		if(null != resumeDTO.getContactInfoDTO()){
			builderResume.setFirstName(resumeDTO.getContactInfoDTO().getFirstName());
			builderResume.setLastName(resumeDTO.getContactInfoDTO().getLastName());
			builderResume.setMiddleName(resumeDTO.getContactInfoDTO().getMiddleNameInitial());
			builderResume.setEmail(resumeDTO.getContactInfoDTO().getEmail());	
		
			if(null != resumeDTO.getContactInfoDTO().getAddressDTO()){
				builderResume.setStreet(resumeDTO.getContactInfoDTO().getAddressDTO().getAddress1());		
				builderResume.setStreet2(resumeDTO.getContactInfoDTO().getAddressDTO().getAddress2());
				builderResume.setCity(resumeDTO.getContactInfoDTO().getAddressDTO().getCity());
				builderResume.setState(resumeDTO.getContactInfoDTO().getAddressDTO().getState());
				builderResume.setCountry(resumeDTO.getContactInfoDTO().getAddressDTO().getCountry());
				builderResume.setPostcode(resumeDTO.getContactInfoDTO().getAddressDTO().getZipCode());
				builderResume.setPhone(resumeDTO.getContactInfoDTO().getAddressDTO().getPhone());
				builderResume.setPhone2(resumeDTO.getContactInfoDTO().getAddressDTO().getMobileNumber());
			}
		}
		
		builderResume.setCanApplyToJobs(new Integer("0").shortValue());
		builderResume.setActive(new Integer("0").shortValue());
		builderResume.setCanApplyToJobs(new Integer("0").shortValue());
		builderResume.setIsPublished(new Integer("0").shortValue());
		builderResume.setJobObjective(resumeDTO.getObjective());
		builderResume.setOtherInterests(resumeDTO.getOtherDetails());
		builderResume.setPrefixName("");
		builderResume.setSuffixName("");
		return builderResume;		
	}
	
	/**
	 * This method is called to convert List of Certifications DTO to 
	 * Resume Builder Certifications Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderCertification> transformBuilderCertifications(List<CertificationDTO> listCertDTO, ResBuilderResume builderResume){
		List<ResBuilderCertification> listCertEntity = new ArrayList<ResBuilderCertification>();
		if(null != listCertDTO){
			for(CertificationDTO certDTO : listCertDTO){
				ResBuilderCertification certEntity = new ResBuilderCertification();
				certEntity.setBuilderCertificationId(certDTO.getBuilderCertId());
				certEntity.setDescription(certDTO.getSummary());
				certEntity.setEarnedDt(DateUtils.convertStringToSQLDate(certDTO.getDateOfReceipt()));
				certEntity.setExpireDt(null);
				certEntity.setCertificationName(certDTO.getCertificationName());
				certEntity.setInstitutionName(certDTO.getInstituteName());
				certEntity.setResBuilderResume(builderResume);
				listCertEntity.add(certEntity);
			}
		}
		return listCertEntity;		
	}
	
	/**
	 * This method is called to convert List of References DTO to 
	 * Resume Builder References Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderReference> transformBuilderReferences(List<ReferenceDTO> listRefDTO, ResBuilderResume builderResume){
		List<ResBuilderReference> listRefEntity = new ArrayList<ResBuilderReference>();
		if(null != listRefDTO){
			for(ReferenceDTO refDTO : listRefDTO){
				ResBuilderReference refEntitiy = new ResBuilderReference();
				refEntitiy.setBuilderReferenceId(refDTO.getBuilderRefId());
				refEntitiy.setCompanyName(refDTO.getCompanyName());
				refEntitiy.setEmail(refDTO.getEmail());
				refEntitiy.setIsAvailable(new Integer("0").shortValue());
				refEntitiy.setJobTitle(refDTO.getJobTitle());
				refEntitiy.setWorkPhone(refDTO.getPhoneNo());	
				refEntitiy.setResBuilderResume(builderResume);
				listRefEntity.add(refEntitiy);
			}
		}
		return listRefEntity;		
	}
	
	
	/**
	 * This method is called to convert List of Education DTO to 
	 * Resume Builder Education Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderEdu> transformBuilderEducation(List<EducationDTO> listEduDTO, ResBuilderResume builderResume){
		List<ResBuilderEdu> listEduEntity = new ArrayList<ResBuilderEdu>();
		if(null != listEduDTO){
			for(EducationDTO eduDTO : listEduDTO){
				ResBuilderEdu eduEntitiy = new ResBuilderEdu();
				eduEntitiy.setBuilderEduId(eduDTO.getBuilderEduId());
				eduEntitiy.setCity("");
				eduEntitiy.setCompletionDt(DateUtils.convertStringToSQLDate(eduDTO.getEndDate()));
				eduEntitiy.setCountry("");
				eduEntitiy.setCourseOfStudy(eduDTO.getFieldOfStudy());
				eduEntitiy.setInstitutionName(eduDTO.getInstituteName());
				if(null != eduDTO.getEduDegreeDTO()){
					ResDegreeEdu resDegreeEdu = new ResDegreeEdu();
						resDegreeEdu.setDegreeEduId(eduDTO.getEduDegreeDTO().getDegreeEduId());
						resDegreeEdu.setDescription(eduDTO.getEduDegreeDTO().getDescription());
						resDegreeEdu.setName(eduDTO.getEduDegreeDTO().getName());
						eduEntitiy.setResDegreeEdu(resDegreeEdu);
				}
				eduEntitiy.setState("");
				eduEntitiy.setResBuilderResume(builderResume);
				listEduEntity.add(eduEntitiy);
			}
		}
		return listEduEntity;		
	}
	
	/**
	 * This method is called to convert List of Work Exp DTO to 
	 * Resume Builder Work Exp Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderEmployment> transformBuilderWorkExp(List<WorkExpDTO> listWorkExpDTO, ResBuilderResume builderResume){
		List<ResBuilderEmployment> listWorkExpEntity = new ArrayList<ResBuilderEmployment>();
		if(null != listWorkExpDTO){
			for(WorkExpDTO workExpDTO : listWorkExpDTO){
				ResBuilderEmployment workExpEntitiy = new ResBuilderEmployment();
				workExpEntitiy.setBuilderEmploymentId(workExpDTO.getBuilderEmpId());
	//			workExpEntitiy.setCity(workExpDTO.get);
	//			workExpEntitiy.setCountry(country);
				workExpEntitiy.setEmployerName(workExpDTO.getEmployerName());
				workExpEntitiy.setEmploymentDt(DateUtils.convertStringToSQLDate(workExpDTO.getStartDate()));
				workExpEntitiy.setJobDescription(workExpDTO.getDescription());
				workExpEntitiy.setPositionName(workExpDTO.getJobTitle());
				workExpEntitiy.setSeparationDt(DateUtils.convertStringToSQLDate(workExpDTO.getEndDate()));
	//			workExpEntitiy.setState(state);
				workExpEntitiy.setStillEmployed(new Integer("0").shortValue());
				workExpEntitiy.setResBuilderResume(builderResume);
				listWorkExpEntity.add(workExpEntitiy);
			}
		}
		return listWorkExpEntity;		
	}
	
	/**
	 * This method is called to convert List of Work Exp DTO to 
	 * Resume Builder Work Exp Entity
	 * @param resumeDTO
	 * @return builderResume
	 */
	/*public List<ResBuilderEmployment> transformBuilderLanguanges(List<LanguageDTO> listWorkExpDTO, ResBuilderResume builderResume){
		List<ResBuilderEmployment> listWorkExpEntity = new ArrayList<ResBuilderEmployment>();
		if(null != listWorkExpDTO){
			for(WorkExpDTO workExpDTO : listWorkExpDTO){
				ResBuilderEmployment workExpEntitiy = new ResBuilderEmployment();
				workExpEntitiy.setBuilderEmploymentId(workExpDTO.getBuilderEmpId());
	//			workExpEntitiy.setCity(workExpDTO.get);
	//			workExpEntitiy.setCountry(country);
				workExpEntitiy.setEmployerName(workExpDTO.getEmployerName());
				workExpEntitiy.setEmploymentDt(DateUtils.convertStringToSQLDate(workExpDTO.getStartDate()));
				workExpEntitiy.setJobDescription(workExpDTO.getDescription());
				workExpEntitiy.setPositionName(workExpDTO.getJobTitle());
				workExpEntitiy.setSeparationDt(DateUtils.convertStringToSQLDate(workExpDTO.getEndDate()));
	//			workExpEntitiy.setState(state);
				workExpEntitiy.setStillEmployed(new Integer("0").shortValue());
				workExpEntitiy.setResBuilderResume(builderResume);
				listWorkExpEntity.add(workExpEntitiy);
			}
		}
		return listWorkExpEntity;	
	}*/	


}
