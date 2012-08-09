package com.advanceweb.afc.jb.resume.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddressDTO;
import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.ContactInformationDTO;
import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.MerProfileAttribDTO;
import com.advanceweb.afc.jb.common.PhoneDetailDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderLanguage;
import com.advanceweb.afc.jb.data.entities.ResBuilderPhone;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResDegreeEdu;
import com.advanceweb.afc.jb.data.entities.ResResumeAttrib;
import com.advanceweb.afc.jb.data.entities.ResResumeAttribList;
import com.advanceweb.afc.jb.data.entities.ResResumeProfile;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

/**
 * 
 * @author Anil Malali
 * @version 1.0
 * @created Jul 9, 2012
 */
@Repository("resumeConversionHelper")
public class ResumeConversionHelper {

	/**
	 * This method transforms ResUploadResume to ResumeDTO
	 * 
	 * @param resume
	 * @return resumeDTO
	 */
	public ResumeDTO transformResUploadResumeToResumeDTO(ResUploadResume resume , List<ResResumeProfile> resumeProfileList) {

		ResumeDTO resumeDTO = new ResumeDTO();
		resumeDTO.setUploadResumeId(resume.getUploadResumeId());
		resumeDTO.setResumeName(resume.getResumeName());
		resumeDTO.setResumeType(resume.getResumeType());
		resumeDTO.setResumeVisibility(String.valueOf(resume.getActive()));
		resumeDTO.setIsPublished(String.valueOf(resume.getIsPublished()));
		if (resume.getUpdateDt() != null) {
			resumeDTO.setUpdateDt(DateUtils
					.convertSQLDateTimeToStdDateTime(resume.getUpdateDt()
							.toString()));
		}
		for(ResResumeProfile resumeProfile : resumeProfileList){
					String formType = resumeProfile.getResResumeAttrib().getName();
					
					if(MMJBCommonConstants.DESIRED_JOB_TITLE.equals(formType)){
						resumeDTO.setDesiredJobTitle(resumeProfile.getAttribValue());
					}
					else if(MMJBCommonConstants.EMPLOYMENT_TYPE.equals(formType)){
						resumeDTO.setDesiredEmploymentType(resumeProfile.getAttribValue());
					}
					else if(MMJBCommonConstants.WORK_AUTH_US.equals(formType)){
						resumeDTO.setWorkAuthorizationUS(resumeProfile.getAttribValue());
					}
					else if(MMJBCommonConstants.RELOCATE.equals(formType)){
						resumeDTO.setWillingToRelocate(resumeProfile.getAttribValue());
					}
		}
		if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resume.getResumeType())){
			resumeDTO.setFilePath(resume.getFilePath());
			resumeDTO.setFileServer(resume.getFileServer());
			resumeDTO.setFileName(resume.getFileName());
		}
		else if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resume.getResumeType())){
			resumeDTO.setResumeText(resume.getResumeText());
		}
		return resumeDTO;
	}

	public ResumeDTO transformResBuilderResumeToResumeDTO(ResumeDTO resumeDTO,
			ResBuilderResume resumeBuilder) {

		resumeDTO.setBuilderResumeId(resumeBuilder.getBuilderResumeId());
		resumeDTO.setObjective(resumeBuilder.getJobObjective());
		resumeDTO.setOtherDetails(resumeBuilder.getOtherInterests());
		resumeDTO.setResumeName(resumeBuilder.getResumeName());
		resumeDTO.setUpdateDt(DateUtils.convertSQLDateTimeToStdDateTime(
				resumeBuilder.getUpdateDt().toString()));
		resumeDTO.setUserId(resumeBuilder.getUserId());
		
		resumeDTO.setListCertDTO(transformResBldResToCertDTO(resumeBuilder));
		resumeDTO.setListEduDTO(transformResEduRefToEduDTO(resumeBuilder));
		resumeDTO.setListRefDTO(transformResBldRefToRefDTO(resumeBuilder));
		resumeDTO.setListWorkExpDTO(transformResEmpRefToWorkExpDTO(resumeBuilder));
		resumeDTO.setContactInfoDTO(transformResBuilderToContactInfoDTO(resumeBuilder));



		return resumeDTO;

	}

	public List<CertificationDTO> transformResBldResToCertDTO(
			ResBuilderResume resumeBuilder) {

		List<CertificationDTO> listCertDTO = new ArrayList<CertificationDTO>();
		List<ResBuilderCertification> listCerts = resumeBuilder.getResBuilderCertifications();

		if (null != listCerts) {
			for (ResBuilderCertification entity : listCerts) {
				CertificationDTO dto = new CertificationDTO();
				dto.setBuilderCertId(entity.getBuilderCertificationId());
				dto.setCertificationName(entity.getCertificationName());
				dto.setCertifyingAuthority(entity.getCertifyingAuthority());
				dto.setDateOfReceipt(String.valueOf(entity.getEarnedDt()));
				dto.setSummary(entity.getDescription());
				listCertDTO.add(dto);
			}
		}

		return listCertDTO;
	}

	public List<ReferenceDTO> transformResBldRefToRefDTO(
			ResBuilderResume resumeBuilder) {

		List<ReferenceDTO> listRefDTO = new ArrayList<ReferenceDTO>();
		List<ResBuilderReference> listRefs = resumeBuilder.getResBuilderReferences();

		if (null != listRefs) {
			
			for (ResBuilderReference entity : listRefs) {
				ReferenceDTO dto = new ReferenceDTO();
				
				dto.setBuilderRefId(entity.getBuilderReferenceId());
				dto.setName(entity.getContactName());
				dto.setJobTitle(entity.getJobTitle());
				dto.setCompanyName(entity.getCompanyName());
				dto.setPhoneNo(entity.getWorkPhone());
				dto.setEmail(entity.getEmail());
				dto.setRefType(entity.getReferenceType());
				
				listRefDTO.add(dto);
			}
		}

		return listRefDTO;
	}

	public ContactInformationDTO transformResBuilderToContactInfoDTO(
			ResBuilderResume resumeBuilder) {

		ContactInformationDTO dto = new ContactInformationDTO();

		if (null != resumeBuilder) {
			dto.setEmail(resumeBuilder.getEmail());
			dto.setFirstName(resumeBuilder.getFirstName());
			dto.setLastName(resumeBuilder.getLastName());
			dto.setMiddleNameInitial(resumeBuilder.getMiddleName());
			dto.setAddressDTO(transformResBuilderToAddressDTO(resumeBuilder));
		}

		return dto;
	}

	public AddressDTO transformResBuilderToAddressDTO(
			ResBuilderResume resumeBuilder) {

		AddressDTO dto = new AddressDTO();

		if (null != resumeBuilder) {
			dto.setAddress1(resumeBuilder.getStreet());
			dto.setAddress2(resumeBuilder.getStreet2());
			dto.setCity(resumeBuilder.getCity());
			dto.setCountry(resumeBuilder.getCountry());
			dto.setPhone(resumeBuilder.getPhone2());
			dto.setState(resumeBuilder.getState());
			dto.setZipCode(resumeBuilder.getPostcode());
		}

		return dto;
	}

	public List<EducationDTO> transformResEduRefToEduDTO(
			ResBuilderResume resumeBuilder) {

		List<EducationDTO> listEduDTO = new ArrayList<EducationDTO>();
		List<ResBuilderEdu> listEdu = resumeBuilder.getResBuilderEdus();

		if (null != listEdu) {
			for (ResBuilderEdu entity : listEdu) {
				EducationDTO dto = new EducationDTO();
				
				 dto.setBuilderEduId(entity.getBuilderEduId());
				 dto.setInstituteName(entity.getInstitutionName());
				 dto.setDegreeLvl(String.valueOf(entity.getBuilderEduId()));
				 dto.setFieldOfStudy(entity.getCourseOfStudy());	
				 dto.setStartDate(String.valueOf(entity.getStartDt()));
				 dto.setEndDate(String.valueOf(entity.getCompletionDt()));
				 dto.setDegrees(entity.getDegrees());
				 dto.setCertifications(entity.getCertifications());				 		 
				 
				listEduDTO.add(dto);
			}
		}

		return listEduDTO;
	}

	public EducationDTO transformEduDegreeToEduDTO(EducationDTO eduDTO,
			ResBuilderEdu entity) {

		if (null != entity.getResDegreeEdu()) {
			ResDegreeEdu eduDegree = entity.getResDegreeEdu();
			eduDTO.setDegrees(eduDegree.getName());
			eduDegree.getDegreeEduId();
			eduDegree.getDescription();
			;
		}

		return eduDTO;
	}

	public List<WorkExpDTO> transformResEmpRefToWorkExpDTO(
			ResBuilderResume resumeBuilder) {

		List<WorkExpDTO> listWorkExpDTO = new ArrayList<WorkExpDTO>();
		List<ResBuilderEmployment> listEmp = resumeBuilder
				.getResBuilderEmployments();

		if (null != listEmp) {
			for (ResBuilderEmployment entity : listEmp) {
				WorkExpDTO dto = new WorkExpDTO();
				
				 dto.setJobTitle(entity.getPositionName());
				 dto.setEmployerName(entity.getEmployerName());
				 dto.setEmploymentType(entity.getEmploymentType());
				 dto.setStartDate(String.valueOf(entity.getEmploymentDt()));
				 dto.setEndDate(String.valueOf(entity.getSeparationDt()));
				 dto.setYrsAtPostion(String.valueOf(entity.getEmploymentYears()));
				 dto.setCurrentCareerLvl(entity.getPositionName());
				 dto.setAnnualSalary(entity.getAnnualSalary());
				 dto.setHrlyPayRate(entity.getHourlyRate());
				 dto.setDescription(entity.getJobDescription());
				 dto.setBuilderEmpId(entity.getBuilderEmploymentId());
				 
				 listWorkExpDTO.add(dto);
			}
		}

		return listWorkExpDTO;
	}

	/**
	 * This method transforms ResUploadResume list to ResumeDTO list
	 * 
	 * @param resumes
	 * @return resumeDTOList
	 */
	public List<ResumeDTO> transformResUploadResumeListToResumeDTOList(
			List<ResUploadResume> resumes) {
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();

		for (ResUploadResume resume : resumes) {
			ResumeDTO resumeDTO = new ResumeDTO();
			resumeDTO.setUploadResumeId(resume.getUploadResumeId());
			resumeDTO.setResumeName(resume.getResumeName());
			resumeDTO.setResumeVisibility(String.valueOf(resume.getIsPublished()));
			if (resume.getUpdateDt() != null) {
				resumeDTO.setUpdateDt(DateUtils
						.convertSQLDateTimeToStdDateTime(resume.getUpdateDt()
								.toString()));
			}
			resumeDTOList.add(resumeDTO);
		}

		return resumeDTOList;

	}

	/**
	 * This method is called to convert resumeDTO to ResUploadResume Entity for
	 * upload
	 * 
	 * @param createResumeDTO
	 * @return
	 */

	public ResUploadResume transformUploadResume(ResumeDTO createResumeDTO) {

		ResUploadResume resUploadResume = new ResUploadResume();

		resUploadResume.setUserId(createResumeDTO.getUserId());
		resUploadResume.setResumeType(createResumeDTO.getResumeType());
		resUploadResume.setResumeName(createResumeDTO.getResumeName());

		// commented for the new database
		// resUploadResume.setJobTitle(createResumeDTO.getDesired_job_title());
		// resUploadResume.setEmpTypeLookupId(Integer.parseInt(createResumeDTO.getDesired_employment_type()));
		// resUploadResume.setWorkAuthLookupId(Integer.parseInt(createResumeDTO.getWork_authorization_US()));
		// resUploadResume.setRelocate(createResumeDTO.getWilling_to_relocate());
		// resUploadResume.setVisibility___Public_Private__(createResumeDTO.getResume_visibility());

		resUploadResume.setResumeText(createResumeDTO.getResumeText());
		resUploadResume.setIsPublished(Short.parseShort(createResumeDTO
				.getIsPublished()));
		resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));

		resUploadResume.setFileServer(createResumeDTO.getFileServer());
		resUploadResume.setFilePath(createResumeDTO.getFilePath());
		resUploadResume.setFileName(createResumeDTO.getFileName());

		return resUploadResume;
	}

	/**
	 * This method is called to convert resumeDTO to ResUploadResume Entity for
	 * copy paste
	 * 
	 * @param resumeDTO
	 * @return
	 */
	public ResUploadResume transformResumeDTOToResUploadResume(ResumeDTO resumeDTO) {
		ResUploadResume resUploadResume = new ResUploadResume();

		resUploadResume.setUserId(resumeDTO.getUserId());
		resUploadResume.setResumeType(resumeDTO.getResumeType());
		resUploadResume.setResumeName(resumeDTO.getResumeName());
    	resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));
    	resUploadResume.setActive(Integer.parseInt(resumeDTO.getResumeVisibility()));
    	resUploadResume.setIsPublished(Integer.parseInt(resumeDTO.getResumeVisibility()));
    	if(MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO.getResumeType())){
    		resUploadResume.setFileServer(resumeDTO.getFileServer());
    		resUploadResume.setFilePath(resumeDTO.getFilePath());
    		resUploadResume.setFileName(resumeDTO.getFileName());
    	}
    	if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
    		resUploadResume.setResumeText(resumeDTO.getResumeText());
    	}
		return resUploadResume;
	}

	/**
	 * This method is called to convert resumeDTO to ResUploadResume Entity for
	 * copy paste
	 * 
	 * @param resumeDTO
	 * @return
	 */
	public ResUploadResume transformAdvancedResumeBuilder(
			ResUploadResume resUploadResume, ResumeDTO resumeDTO) {

		resUploadResume.setUserId(resumeDTO.getUserId());
		resUploadResume.setResumeType(resumeDTO.getResumeType());
		resUploadResume.setResumeName(resumeDTO.getResumeName());
		resUploadResume.setActive(Integer.parseInt(resumeDTO.getResumeVisibility()));
		resUploadResume.setIsPublished(Integer.parseInt(resumeDTO.getResumeVisibility()));
		resUploadResume.setUpdateDt(new Timestamp(new Date().getTime()));
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO.getResumeType())){
			resUploadResume.setResumeText(resumeDTO.getResumeText());
		}

		return resUploadResume;
	}
	
	/**
	 * This method is called to convert resumeDTO to ResUploadResume Entity for
	 * copy paste
	 * 
	 * @param resumeDTO
	 * @return
	 */
	public List<ResResumeProfile> transformResumeDTOResResumeProfile(
			ResUploadResume resUploadResume, ResumeDTO resumeDTO ,List<ResResumeAttrib> resumeAttribs) {
		
		List<ResResumeProfile> resumeProfileList = new ArrayList<ResResumeProfile>();
		
		for(ResResumeAttrib resumeAttrib :resumeAttribs){
			ResResumeProfile resumeProfile = new ResResumeProfile();
			if(MMJBCommonConstants.RESUME_TYPE.equals(resumeAttrib.getName())){
				continue;
			}
			if("PhoneType".equals(resumeAttrib.getName())){
				continue;
			}
			else if(MMJBCommonConstants.DESIRED_JOB_TITLE.equals(resumeAttrib.getName())){
				resumeProfile.setAttribValue(resumeDTO.getDesiredJobTitle());
			}
			else if(MMJBCommonConstants.EMPLOYMENT_TYPE.equals(resumeAttrib.getName())){
				resumeProfile.setAttribValue(resumeDTO.getDesiredEmploymentType());
			}
			else if(MMJBCommonConstants.WORK_AUTH_US.equals(resumeAttrib.getName())){
				resumeProfile.setAttribValue(resumeDTO.getWorkAuthorizationUS());
			}
			else if(MMJBCommonConstants.RELOCATE.equals(resumeAttrib.getName())){
				resumeProfile.setAttribValue(resumeDTO.getWillingToRelocate());
			}
			resumeProfile.setResResumeAttrib(resumeAttrib);
			resumeProfile.setResumeId(resUploadResume.getUploadResumeId());
			resumeProfile.setResumeType(resUploadResume.getResumeType());
			resumeProfile.setCreateDt(new Timestamp(new Date().getTime()));
			resumeProfileList.add(resumeProfile);
		}
		

		return resumeProfileList;
	}
	

	/**
	 * This method is called to convert resumeDTO to ResUploadResume Entity for
	 * copy paste
	 * 
	 * @param createResumeDTO
	 * @return
	 */
	public ResUploadResume transformCopyPasteResume(ResumeDTO createResumeDTO) {

		ResUploadResume resUploadResume = new ResUploadResume();

		resUploadResume.setUserId(createResumeDTO.getUserId());
		resUploadResume.setResumeType(createResumeDTO.getResumeType());
		resUploadResume.setResumeName(createResumeDTO.getResumeName());

		// commented for the new database
		// resUploadResume.setJobTitle(createResumeDTO.getDesired_job_title());
		// resUploadResume.setEmpTypeLookupId(Integer.parseInt(createResumeDTO.getDesired_employment_type()));
		// resUploadResume.setWorkAuthLookupId(Integer.parseInt(createResumeDTO.getWork_authorization_US()));
		// resUploadResume.setRelocate(createResumeDTO.getWilling_to_relocate());
		// resUploadResume.setVisibility___Public_Private__(createResumeDTO.getResume_visibility());

		resUploadResume.setResumeText(createResumeDTO.getResumeText());
		resUploadResume.setIsPublished(Short.parseShort(createResumeDTO
				.getIsPublished()));
		resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));

		return resUploadResume;
	}

	/**
	 * This method is called to convert resumeDTO to Resume Builder Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public ResBuilderResume transformBuilderResume(ResumeDTO resumeDTO) {
		ResBuilderResume builderResume = new ResBuilderResume();
		builderResume.setUserId(resumeDTO.getUserId());
		builderResume.setBuilderResumeId(resumeDTO.getBuilderResumeId());
		builderResume.setResumeName(resumeDTO.getResumeName());

		if (null != resumeDTO.getContactInfoDTO()) {
			builderResume.setFirstName(resumeDTO.getContactInfoDTO()
					.getFirstName());
			builderResume.setLastName(resumeDTO.getContactInfoDTO()
					.getLastName());
			builderResume.setMiddleName(resumeDTO.getContactInfoDTO()
					.getMiddleNameInitial());
			builderResume.setEmail(resumeDTO.getContactInfoDTO().getEmail());

			if (null != resumeDTO.getContactInfoDTO().getAddressDTO()) {
				builderResume.setStreet(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getAddress1());
				builderResume.setStreet2(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getAddress2());
				builderResume.setCity(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getCity());
				builderResume.setState(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getState());
				builderResume.setCountry(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getCountry());
				builderResume.setPostcode(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getZipCode());
				builderResume.setPhone(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getPhone());
				builderResume.setPhone2(resumeDTO.getContactInfoDTO()
						.getAddressDTO().getMobileNumber());
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
	 * This method is called to convert List of Certifications DTO to Resume
	 * Builder Certifications Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderCertification> transformBuilderCertifications(
			List<CertificationDTO> listCertDTO, ResBuilderResume builderResume) {
		List<ResBuilderCertification> listCertEntity = new ArrayList<ResBuilderCertification>();
		if (null != listCertDTO) {
			for (CertificationDTO certDTO : listCertDTO) {
				ResBuilderCertification certEntity = new ResBuilderCertification();
				certEntity.setBuilderCertificationId(certDTO.getBuilderCertId());
				certEntity.setDescription(certDTO.getSummary());
				certEntity.setEarnedDt(DateUtils.convertStringToSQLDateTime(certDTO.getDateOfReceipt()));
				certEntity.setExpireDt((null != certDTO.getDateOfReceipt() && certDTO.getDateOfReceipt().length() != 0) 
						? DateUtils.convertStringToSQLDateTime(certDTO.getDateOfReceipt()):null);
				certEntity.setCertificationName(certDTO.getCertificationName());
				certEntity.setCertifyingAuthority(certDTO.getCertifyingAuthority());
				certEntity.setResBuilderResume(builderResume);
				listCertEntity.add(certEntity);
			}
		}
		return listCertEntity;
	}

	/**
	 * This method is called to convert List of References DTO to Resume Builder
	 * References Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderReference> transformBuilderReferences(
			List<ReferenceDTO> listRefDTO, ResBuilderResume builderResume) {
		List<ResBuilderReference> listRefEntity = new ArrayList<ResBuilderReference>();
		if (null != listRefDTO) {
			for (ReferenceDTO refDTO : listRefDTO) {
				ResBuilderReference refEntitiy = new ResBuilderReference();
				refEntitiy.setBuilderReferenceId(refDTO.getBuilderRefId());
				refEntitiy.setCompanyName(refDTO.getCompanyName());
				refEntitiy.setEmail(refDTO.getEmail());
				refEntitiy.setIsAvailable(Integer.valueOf(0).shortValue());
				refEntitiy.setJobTitle(refDTO.getJobTitle());
				refEntitiy.setWorkPhone(refDTO.getPhoneNo());
				refEntitiy.setContactName(refDTO.getName());
				refEntitiy.setReferenceType(refDTO.getRefType());
				refEntitiy.setResBuilderResume(builderResume);			
				listRefEntity.add(refEntitiy);
			}
		}
		return listRefEntity;
	}

	/**
	 * This method is called to convert List of Education DTO to Resume Builder
	 * Education Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderEdu> transformBuilderEducation(
			List<EducationDTO> listEduDTO, ResBuilderResume builderResume) {
		List<ResBuilderEdu> listEduEntity = new ArrayList<ResBuilderEdu>();
		if (null != listEduDTO) {
			for (EducationDTO eduDTO : listEduDTO) {
				ResBuilderEdu eduEntitiy = new ResBuilderEdu();
				
				eduEntitiy.setBuilderEduId(eduDTO.getBuilderEduId());
								
				eduEntitiy.setInstitutionName(eduDTO.getInstituteName());
				eduEntitiy.setDegreeLevel(eduDTO.getDegreeLvl());
				eduEntitiy.setCourseOfStudy(eduDTO.getFieldOfStudy());
				eduEntitiy.setStartDt(DateUtils.convertStringToSQLDateTime(eduDTO.getStartDate()));
				eduEntitiy.setCompletionDt(DateUtils.convertStringToSQLDateTime(eduDTO.getEndDate()));
				eduEntitiy.setDegrees(eduDTO.getDegrees());
				eduEntitiy.setCertifications(eduDTO.getCertifications());
				eduEntitiy.setIsGraduated(eduDTO.getbGraduated());
				eduEntitiy.setResBuilderResume(builderResume);
				listEduEntity.add(eduEntitiy);
			}
		}
		return listEduEntity;
	}

	/**
	 * This method is called to convert List of Work Exp DTO to Resume Builder
	 * Work Exp Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderEmployment> transformBuilderWorkExp(
			List<WorkExpDTO> listWorkExpDTO, ResBuilderResume builderResume) {
		List<ResBuilderEmployment> listWorkExpEntity = new ArrayList<ResBuilderEmployment>();
		if (null != listWorkExpDTO) {
			for (WorkExpDTO workExpDTO : listWorkExpDTO) {
				ResBuilderEmployment workExpEntitiy = new ResBuilderEmployment();
				workExpEntitiy.setAnnualSalary(workExpDTO.getAnnualSalary());
				workExpEntitiy.setBuilderEmploymentId(workExpDTO.getBuilderEmpId());
				workExpEntitiy.setCity(workExpDTO.getCity());
				workExpEntitiy.setCountry(workExpDTO.getCountry());
				workExpEntitiy.setState(workExpDTO.getState());
				workExpEntitiy.setEmployerName(workExpDTO.getEmployerName());
				workExpEntitiy.setEmploymentType(workExpDTO.getEmploymentType());
				workExpEntitiy.setEmploymentDt(DateUtils.convertStringToSQLDateTime(workExpDTO.getStartDate()));
				workExpEntitiy.setEmploymentYears(Integer.valueOf(workExpDTO.getYrsAtPostion()));
				workExpEntitiy.setCareerLevel(workExpDTO.getCurrentCareerLvl());
				workExpEntitiy.setIsCurCareerLevel(workExpDTO.getIsCurrentCareerLvl());
				workExpEntitiy.setJobDescription(workExpDTO.getDescription());
				workExpEntitiy.setPositionName(workExpDTO.getJobTitle());
				workExpEntitiy.setSeparationDt(DateUtils.convertStringToSQLDateTime(workExpDTO.getEndDate()));
				workExpEntitiy.setStillEmployed(workExpDTO.getStillEmployed());
				workExpEntitiy.setResBuilderResume(builderResume);
				listWorkExpEntity.add(workExpEntitiy);
			}
		}
		return listWorkExpEntity;
	}
	
	
	/**
	 * This method is called to convert List of References DTO to Resume Builder
	 * References Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderLanguage> transformBuilderLanguages(
		List<LanguageDTO> langDTOList, ResBuilderResume builderResume) {
		List<ResBuilderLanguage> langList = new ArrayList<ResBuilderLanguage>();
		if (null != langDTOList) {
			for (LanguageDTO langDTO : langDTOList) {
				ResBuilderLanguage langEntity = new ResBuilderLanguage();
				langEntity.setBuilderLanguageId(langDTO.getnLangId());
				langEntity.setExpLevel(langDTO.getExpLvl());
				langEntity.setLanguageName(langDTO.getLanguage());
				langEntity.setResBuilderResume(builderResume);			
				langList.add(langEntity);
			}
		}
		return langList;
	}
	
	
	/**
	 * This method is called to convert List of References DTO to Resume Builder
	 * References Entity
	 * 
	 * @param resumeDTO
	 * @return builderResume
	 */
	public List<ResBuilderPhone> transformBuilderPhoneDetails(
		List<PhoneDetailDTO> phoneDtlDTOList, ResBuilderResume builderResume) {
		List<ResBuilderPhone> phoneDtlList = new ArrayList<ResBuilderPhone>();
		if (null != phoneDtlList) {
			for (PhoneDetailDTO langDTO : phoneDtlDTOList) {
				ResBuilderPhone entity = new ResBuilderPhone();
				
				entity.setBuilderPhoneId(langDTO.getBuilderPhoneId());
				entity.setPhoneNumber(langDTO.getPhoneNumber());
				entity.setPhoneType(langDTO.getPhoneType());
				
				entity.setResBuilderResume(builderResume);			
				phoneDtlList.add(entity);
			}
		}
		return phoneDtlList;
	}
	
	/**
	 * 
	 * @param listProfAttrib
	 * @param countryList
	 * @param stateList
	 * @return
	 */
	public ResumeDTO transformProfileAttrib(List<ResResumeAttrib> listProfAttrib){
		
		ResumeDTO resumeDTO = new ResumeDTO();
		List<MerProfileAttribDTO> listDTO = new ArrayList<MerProfileAttribDTO>();
		if(null != listProfAttrib){
			for(ResResumeAttrib entity : listProfAttrib){
				MerProfileAttribDTO dto = new MerProfileAttribDTO();
				dto.setStrAttribType(entity.getFormType());
				dto.setStrLabelName(entity.getName());
				dto.setStrProfileAttribId(String.valueOf(entity.getResumeAttribId()));
				
				if(dto.getStrAttribType().equals(MMJBCommonConstants.DROP_DOWN)){
					List<ResResumeAttribList> dropdownVals = entity.getResResumeAttribLists();
					dto.setDropdown(transformToDropDownDTO(dropdownVals));
				}
				if(dto.getStrAttribType().equals(MMJBCommonConstants.RADIO_BUTTON)){
					List<ResResumeAttribList> dropdownVals = entity.getResResumeAttribLists();
					dto.setDropdown(transformToDropDownDTO(dropdownVals));
				}
				listDTO.add(dto);
			}
		}
		resumeDTO.setResumeAttribList(listDTO);
		return resumeDTO;		
	}
	
	/**
	 * Converting list of MerProfileAttribList to list of DropDownDTO's
	 */
	public List<DropDownDTO> transformToDropDownDTO(List<ResResumeAttribList> dropdownVals){
		
		List<DropDownDTO> dropdownList = new ArrayList<DropDownDTO>();
		if(null != dropdownVals){
			for(ResResumeAttribList attrib : dropdownVals){
				DropDownDTO dto = new DropDownDTO();
				dto.setOptionId(String.valueOf(attrib.getResumeAttribListId()));
				dto.setOptionName(attrib.getListValue());
				dropdownList.add(dto);
			}
		}
		return dropdownList;
	}

}
