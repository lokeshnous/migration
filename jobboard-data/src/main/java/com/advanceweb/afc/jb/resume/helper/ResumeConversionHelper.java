package com.advanceweb.afc.jb.resume.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
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
@Repository("resumeConversionHelper")
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

	
	public ResumeDTO transformResBuilderResumeToResumeDTO(ResumeDTO resumeDTO, ResBuilderResume resumeBuilder){
		
//		resumeDTO.setAwards(resumeBuilder.get);
		resumeDTO.setBuilderResumeId(resumeBuilder.getBuilderResumeId());
//		resumeDTO.setContactInfoDTO(contactInfoDTO);
//		resumeDTO.setDesired_employment_type(resumeBuilder.get);
//		resumeDTO.setDesired_job_title(resumeBuilder.get);
/*		resumeDTO.setEmploymentType(resumeBuilder.get);
		resumeDTO.setFileData(fileData);
		resumeDTO.setFileName(fileName);
		resumeDTO.setFilePath(filePath);
		resumeDTO.setFileServer(fileServer);
		resumeDTO.setIsPublished(isPublished);*/
		resumeDTO.setListCertDTO(transformResBldResToCertDTO(resumeBuilder));
		resumeDTO.setListEduDTO(transformResEduRefToEduDTO(resumeBuilder));
//		resumeDTO.setListLangDTO(listLangDTO);
		resumeDTO.setListRefDTO(transformResBldRefToRefDTO(resumeBuilder));
		resumeDTO.setListWorkExpDTO(transformResEmpRefToWorkExpDTO(resumeBuilder));
//		resumeDTO.setMemberships(resumeBuilder.);
		resumeDTO.setObjective(resumeBuilder.getJobObjective());
		resumeDTO.setOtherDetails(resumeBuilder.getOtherInterests());
		resumeDTO.setResume_name(resumeBuilder.getResumeName());
//		resumeDTO.setResume_visibility(resumeBuilder.ge);
//		resumeDTO.setResumeText(resumeText);
//		resumeDTO.setResumeType(resumeType);
//		resumeDTO.setSkills(resumeBuilder.ge);
		resumeDTO.setUpdateDt(resumeBuilder.getUpdateDt());
//		resumeDTO.setUploadResumeId(resumeBuilder);
		resumeDTO.setUserId(resumeBuilder.getUserId());
//		resumeDTO.setWilling_to_relocate(resumeBuilder.getw);
//		resumeDTO.setWork_authorization_US(work_authorization_US);
		
		return resumeDTO;
		
	}
	
	
	public List<CertificationDTO> transformResBldResToCertDTO(ResBuilderResume resumeBuilder){
		
		List<CertificationDTO> listCertDTO = new ArrayList<CertificationDTO>();
		List<ResBuilderCertification> listCerts = resumeBuilder.getResBuilderCertifications();
		
		if(null != listCerts){
			for(ResBuilderCertification entity:listCerts){
				CertificationDTO dto = new CertificationDTO();
				dto.setBuilderCertId(entity.getBuilderCertificationId());
				dto.setCertificationName(entity.getCertificationName());
				dto.setDateOfReceipt(String.valueOf(entity.getEarnedDt()));
				dto.setInstituteName(entity.getInstitutionName());
				dto.setSummary(entity.getDescription());
				listCertDTO.add(dto);
			}
		}
		
		return listCertDTO;
	}
	
	
	public List<ReferenceDTO> transformResBldRefToRefDTO(ResBuilderResume resumeBuilder){
		
		List<ReferenceDTO> listRefDTO = new ArrayList<ReferenceDTO>();
		List<ResBuilderReference> listRefs = resumeBuilder.getResBuilderReferences();
		
		if(null != listRefs){
			for(ResBuilderReference entity:listRefs){
				ReferenceDTO dto = new ReferenceDTO();
				dto.setBuilderRefId(entity.getBuilderReferenceId());
				dto.setCompanyName(entity.getCompanyName());
				dto.setEmail(entity.getEmail());
				dto.setJobTitle(entity.getJobTitle());
				dto.setName(entity.getContactName());
				dto.setPhoneNo(entity.getWorkPhone());
				listRefDTO.add(dto);
			}
		}
		
		return listRefDTO;
	}
	
	
	public List<EducationDTO> transformResEduRefToEduDTO(ResBuilderResume resumeBuilder){
		
		List<EducationDTO> listEduDTO = new ArrayList<EducationDTO>();
		List<ResBuilderEdu> listEdu = resumeBuilder.getResBuilderEdus();
		
		if(null != listEdu){
			for(ResBuilderEdu entity:listEdu){
				EducationDTO dto = new EducationDTO();
				dto.setBuilderEduId(entity.getBuilderEduId());
//				dto.setCertifications(entity.get);
//				dto.setDegreeLvl(entity.get);
//				dto.setDegrees(degrees);
//				dto.setEduDegreeDTO(eduDegreeDTO);
				dto.setEndDate(String.valueOf(entity.getEnd_Date()));
				dto.setFieldOfStudy(entity.getCourseOfStudy());
				dto.setInstituteName(entity.getInstitutionName());
				dto.setLanguage(String.valueOf(entity.getLanguageLookupId()));
				dto.setStartDate(String.valueOf(entity.getStartDate()));
				
				listEduDTO.add(dto);
			}
		}
		
		return listEduDTO;
	}
	
	public EducationDTO transformEduDegreeToEduDTO(EducationDTO eduDTO, ResBuilderEdu entity){
			
		if(null != entity.getResDegreeEdu()){
			ResDegreeEdu eduDegree = entity.getResDegreeEdu();
			eduDTO.setDegrees(eduDegree.getName());
			eduDegree.getDegreeEduId();
			eduDegree.getDescription();
			;
		}
		
		return eduDTO;
	}
	
	
	public List<WorkExpDTO> transformResEmpRefToWorkExpDTO(ResBuilderResume resumeBuilder){
		
		List<WorkExpDTO> listWorkExpDTO = new ArrayList<WorkExpDTO>();
		List<ResBuilderEmployment> listEmp = resumeBuilder.getResBuilderEmployments();
		
		if(null != listEmp){
			for(ResBuilderEmployment entity:listEmp){
				WorkExpDTO dto = new WorkExpDTO();
				dto.setAnnualSalary(String.valueOf(entity.getAnnualSalLookupId()));
				dto.setBuilderEmpId(entity.getBuilderEmploymentId());
				dto.setCurrentCareerLvl(entity.getPositionName());
//				dto.setDescription(entity.get);
				dto.setEmployerName(entity.getEmployerName());
				dto.setEmploymentType(String.valueOf(entity.getEmpTypeLookupId()));
				dto.setEndDate(String.valueOf(entity.getSeparationDt()));
				dto.setHrlyPayRate(String.valueOf(entity.getHrPayRateLookupId()));
				dto.setJobTitle(entity.getJobTitle());
				dto.setStartDate(String.valueOf(entity.getEmploymentDt()));
				dto.setYrsAtPostion(String.valueOf(entity.getStillEmployed()));
				
				listWorkExpDTO.add(dto);
			}
		}
		
		return listWorkExpDTO;
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
	 * ResUploadResume Entity for upload
	 * 
	 * @param createResumeDTO
	 * @return
	 */

	public ResUploadResume transformUploadResume(ResumeDTO createResumeDTO){

		ResUploadResume resUploadResume=new ResUploadResume();

		resUploadResume.setUserId(createResumeDTO.getUserId());
		resUploadResume.setResumeType(createResumeDTO.getResumeType());
		resUploadResume.setResumeName(createResumeDTO.getResume_name());
		
		
		resUploadResume.setJobTitle(createResumeDTO.getDesired_job_title());
		resUploadResume.setEmpTypeLookupId(Integer.parseInt(createResumeDTO.getDesired_employment_type()));
		resUploadResume.setWorkAuthLookupId(Integer.parseInt(createResumeDTO.getWork_authorization_US()));
		resUploadResume.setRelocate(createResumeDTO.getWilling_to_relocate());
		resUploadResume.setVisibility___Public_Private__(createResumeDTO.getResume_visibility());
		
		resUploadResume.setResumeText(createResumeDTO.getResumeText());
		resUploadResume.setIsPublished(Short.parseShort(createResumeDTO.getIsPublished()));
		resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));
		
		resUploadResume.setFileServer(createResumeDTO.getFileServer());
		resUploadResume.setFilePath(createResumeDTO.getFilePath());
		resUploadResume.setFileName(createResumeDTO.getFileName());

		return resUploadResume;		
	}
    
	/**
	 * This method is called to convert resumeDTO to 
	 * ResUploadResume Entity for copy paste
	 * 
	 * @param createResumeDTO
	 * @return
	 */
	public ResUploadResume transformCopyPasteResume(ResumeDTO createResumeDTO){

		ResUploadResume resUploadResume=new ResUploadResume();

		resUploadResume.setUserId(createResumeDTO.getUserId());
		resUploadResume.setResumeType(createResumeDTO.getResumeType());
		resUploadResume.setResumeName(createResumeDTO.getResume_name());
		
		
		resUploadResume.setJobTitle(createResumeDTO.getDesired_job_title());
		resUploadResume.setEmpTypeLookupId(Integer.parseInt(createResumeDTO.getDesired_employment_type()));
		resUploadResume.setWorkAuthLookupId(Integer.parseInt(createResumeDTO.getWork_authorization_US()));
		resUploadResume.setRelocate(createResumeDTO.getWilling_to_relocate());
		resUploadResume.setVisibility___Public_Private__(createResumeDTO.getResume_visibility());
		
		resUploadResume.setResumeText(createResumeDTO.getResumeText());
		resUploadResume.setIsPublished(Short.parseShort(createResumeDTO.getIsPublished()));
		resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));

		return resUploadResume;		
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
		
		builderResume.setCanApplyToJobs(Integer.valueOf(0).shortValue());
		builderResume.setActive(Integer.valueOf(0).shortValue());
		builderResume.setCanApplyToJobs(Integer.valueOf(0).shortValue());
		builderResume.setIsPublished(Integer.valueOf(0).shortValue());
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
				refEntitiy.setIsAvailable(Integer.valueOf(0).shortValue());
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
				workExpEntitiy.setStillEmployed(Integer.valueOf(0).shortValue());
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
