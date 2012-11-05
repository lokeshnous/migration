package com.advanceweb.afc.jb.resume.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFolder;
import com.advanceweb.afc.jb.data.entities.AdmFolderResume;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderLanguage;
import com.advanceweb.afc.jb.data.entities.ResBuilderPhone;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResBuilderSkill;
import com.advanceweb.afc.jb.data.entities.ResResumeAttrib;
import com.advanceweb.afc.jb.data.entities.ResResumeProfile;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;
import com.advanceweb.afc.jb.resume.helper.ResumeConversionHelper;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("resumeDao")
public class ResumeDaoImpl implements ResumeDao {

	private static final Logger LOGGER = Logger
			.getLogger("ResumeDaoImpl.class");
	
	private static final String FIND_RES_BUILD_RESUME="from ResBuilderResume res where res.resUploadResumeId=?";
	private static final String ALL_CANDIDATES_FOLDER_NAME = "Default Folder";
	
	@Autowired
	private ResumeConversionHelper resumeConversionHelper;

	private HibernateTemplate hibernateTemplate;


	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is called to retrieve the resume list belonging to a logged
	 * in jobSeeker
	 * 
	 * @param jobSeekerId
	 * @return resumeDTOList
	 */
	@Override
	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId) {

		List<ResUploadResume> resumes = hibernateTemplate.find("from ResUploadResume where userId = " + jobSeekerId +" and deleteDt is NULL");
		return resumeConversionHelper.transformResUploadResumeListToResumeDTOList(resumes);
	}

	/**
	 * This method is called to edit the resume
	 * 
	 * @param resumeId
	 * @return ResumeDTO
	 */
	@Override
	public ResumeDTO editResume(int resumeId) {
		ResumeDTO dto = new ResumeDTO();
		ResUploadResume resume = hibernateTemplate.get(ResUploadResume.class,resumeId);
		List<ResResumeProfile> resumeProfile = hibernateTemplate.find("from ResResumeProfile where resumeId = " + resumeId);
		if(resumeProfile != null && !resumeProfile.isEmpty()) {
			
			dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(resume, resumeProfile);

			List<ResBuilderResume> resBuilderList = hibernateTemplate.find(FIND_RES_BUILD_RESUME, resume.getUploadResumeId());

			if (resBuilderList != null && !resBuilderList.isEmpty()) {
				ResBuilderResume resBuilder = resBuilderList.get(0);
				dto = resumeConversionHelper.transformResBuilderResumeToResumeDTO(dto,resBuilder);
				return dto;
			}
		}
		if(null != resume && MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resume.getResumeType())){
			dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(resume, resumeProfile);
			return dto;
		}
		return dto;
	}

	/**
	 * This method is called to update the resume
	 * 
	 * @param resumeDTO
	 * @return boolean
	 */
	@Override
	public boolean updateResume(ResumeDTO resumeDTO) {

		resumeVisibilityPublicToPrivate(resumeDTO);
		
		ResUploadResume resume = hibernateTemplate.get(ResUploadResume.class,resumeDTO.getUploadResumeId());

		resume = resumeConversionHelper.transformAdvancedResumeBuilder(resume,resumeDTO);
		hibernateTemplate.update(resume);
		
		hibernateTemplate.deleteAll(hibernateTemplate.find("from ResResumeProfile where resumeId = " + resume.getUploadResumeId()));
		
		List<ResResumeAttrib> resumeAttrib =hibernateTemplate.find("from ResResumeAttrib");
		List<ResResumeProfile> resumeProfileList = resumeConversionHelper.transformResumeDTOResResumeProfile(resume,resumeDTO,resumeAttrib);
		hibernateTemplate.saveOrUpdateAll(resumeProfileList);
		
		return true;
	}

	private void resumeVisibilityPublicToPrivate(ResumeDTO resumeDTO) {
		if (MMJBCommonConstants.VISIBILITY_PUBLIC.equals(resumeDTO.getResumeVisibility())) {
			List<ResUploadResume> resumes = hibernateTemplate.find("from ResUploadResume where userId = "
							+ resumeDTO.getUserId() + " and uploadResumeId !="
							+ resumeDTO.getUploadResumeId()
							+ " and active='"
							+ MMJBCommonConstants.VISIBILITY_PUBLIC + "'");
			for(ResUploadResume resume : resumes){
				resume.setActive(Integer.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
				resume.setIsPublished(Integer.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
				hibernateTemplate.update(resume);
			}
		}
	}

	/**
	 * This method is called to delete the resume
	 * 
	 * @param resumeId
	 * @return delete status
	 */
	@Override
	public boolean deleteResume(int resumeId, int userId) {
		ResUploadResume resume = new ResUploadResume();
		resume.setUploadResumeId(resumeId);
		resume = hibernateTemplate.get(ResUploadResume.class,resumeId);
		//resume.setDeleteUserId(userId);
		resume.setDeleteDt(new Timestamp(new Date().getTime()));
		hibernateTemplate.save(resume);
		List<ResResumeProfile> resumeProfileAttribs =hibernateTemplate.find("from ResResumeProfile where resumeId="+resumeId+" and deleteDt is NULL");
		List<ResResumeProfile> resumeProfileList = new ArrayList<ResResumeProfile>(); 
		Date deleteDt=new Timestamp(new Date().getTime());
		for(ResResumeProfile resumeAttrib : resumeProfileAttribs)
		{
			resumeAttrib.setDeleteDt(deleteDt);
			resumeProfileList.add(resumeAttrib);
		}
		hibernateTemplate.saveOrUpdateAll(resumeProfileList);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResumeDTO createResume(ResumeDTO resumeDTO) {
		//if any public resumes , make it private 
		resumeVisibilityPublicToPrivate(resumeDTO);
		ResumeDTO newResumeDTO = null;
		ResUploadResume resUploadResume = resumeConversionHelper.transformResumeDTOToResUploadResume(resumeDTO);
		try {
			hibernateTemplate.save(resUploadResume);
			List<ResResumeAttrib> resumeAttrib =hibernateTemplate.find("from ResResumeAttrib");
			List<ResResumeProfile> resumeProfileList = resumeConversionHelper.transformResumeDTOResResumeProfile(resUploadResume,resumeDTO,resumeAttrib);
			hibernateTemplate.saveOrUpdateAll(resumeProfileList);
			newResumeDTO = resumeConversionHelper.transformResUploadResumeToResumeDTO(resUploadResume, null);
		} catch (HibernateException e) {
			LOGGER.info("Error while Creating Resume",e);
		}
		return newResumeDTO;

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResumeCopyPaste(ResumeDTO resumeDTO) {
		resumeVisibilityPublicToPrivate(resumeDTO);
		Boolean result = false;
		ResUploadResume resUploadResume = resumeConversionHelper.transformResumeDTOToResUploadResume(resumeDTO);
		try {
			hibernateTemplate.save(resUploadResume);
			List<ResResumeAttrib> resumeAttrib =hibernateTemplate.find("from ResResumeAttrib");
			List<ResResumeProfile> resumeProfileList = resumeConversionHelper.transformResumeDTOResResumeProfile(resUploadResume,resumeDTO,resumeAttrib);
			hibernateTemplate.saveOrUpdateAll(resumeProfileList);
			
			result = true;
		} catch (HibernateException e) {
			result = false;
			LOGGER.info("Error while Copy Paste",e);
		}
		return result;
	}
	
	@Override
	public boolean updateResumeCopyPaste(ResumeDTO resumeDTO) {
		return updateResume(resumeDTO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResumeDTO createResumeUpload(ResumeDTO resumeDTO) {
		/**
		 *  Introduced a new variable "templateForm" to resolve PMD issue. 
		 */
		ResumeDTO resDTO =resumeDTO; 
		
		resumeVisibilityPublicToPrivate(resDTO);
		ResUploadResume resUploadResume = resumeConversionHelper.transformResumeDTOToResUploadResume(resDTO);
		
		try {
			hibernateTemplate.save(resUploadResume);
			
			resUploadResume.setFilePath(resUploadResume.getFilePath()+resUploadResume.getUploadResumeId() + "_"+ resDTO.getFileName());
			
			hibernateTemplate.update(resUploadResume);
			
			List<ResResumeAttrib> resumeAttrib =hibernateTemplate.find("from ResResumeAttrib");
			List<ResResumeProfile> resumeProfileList = resumeConversionHelper.transformResumeDTOResResumeProfile(resUploadResume,resDTO,resumeAttrib);
			hibernateTemplate.saveOrUpdateAll(resumeProfileList);
			resDTO = resumeConversionHelper.transformResUploadResumeToResumeDTO(resUploadResume, resumeProfileList);
		} catch (HibernateException e) {
			LOGGER.info("Error while Resume Upload", e);
		}
		resDTO.setFilePath(resUploadResume.getFilePath());
		return resDTO;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateResumeUpload(ResumeDTO resumeDTO) {
		return updateResume(resumeDTO);
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResumeBuilder(ResumeDTO resumeDTO) {
		ResBuilderResume builderResume = resumeConversionHelper.transformBuilderResume(resumeDTO);
		List<ResBuilderCertification> builderCerts = resumeConversionHelper.transformBuilderCertifications(resumeDTO.getListCertDTO(),builderResume);
		List<ResBuilderEdu> builderEducations = resumeConversionHelper.transformBuilderEducation(resumeDTO.getListEduDTO(),builderResume);
		List<ResBuilderReference> builderRefs = resumeConversionHelper.transformBuilderReferences(resumeDTO.getListRefDTO(),builderResume);
		List<ResBuilderEmployment> builderWorkExp = resumeConversionHelper.transformBuilderWorkExp(resumeDTO.getListWorkExpDTO(),builderResume);
		List<ResBuilderLanguage> builderLangList = resumeConversionHelper.transformBuilderLanguages(resumeDTO.getListLangDTO(),builderResume);	
		List<ResBuilderPhone> builderPhoneList = resumeConversionHelper.transformBuilderPhoneDetails(resumeDTO.getListPhoneDtl(), builderResume);
		List<ResBuilderSkill> builderSkillSet = resumeConversionHelper.transformBuilderSkills(resumeDTO,builderResume);
		
		builderResume.setResBuilderCertifications(builderCerts);
		builderResume.setResBuilderEdus(builderEducations);
		builderResume.setResBuilderEmployments(builderWorkExp);
		builderResume.setResBuilderReferences(builderRefs);
		builderResume.setResBuilderLanguages(builderLangList);
		builderResume.setResBuilderPhones(builderPhoneList);
		builderResume.setResBuilderSkills(builderSkillSet);
		try {
			hibernateTemplate.saveOrUpdate(builderResume);
			return true;
		} catch (HibernateException e) {
			LOGGER.info("Error in create Resume Builder",e);
		}
		return false;
	}

	@Override
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		// Saving only Work Experience
		List<ResBuilderEmployment> listBuilderWorkExp = resumeConversionHelper
				.transformBuilderWorkExp(listWorkExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderWorkExp);
		} catch (HibernateException e) {
			LOGGER.info("Error while adding work experience", e);
		}
		return true;
	}

	@Override
	public boolean addReference(List<ReferenceDTO> listRefExp) {

		// Saving only Work Experience
		List<ResBuilderReference> listBuilderRef = resumeConversionHelper
				.transformBuilderReferences(listRefExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderRef);
		} catch (HibernateException e) {
			LOGGER.info("Error while adding reference", e);
		}
		return true;
	}

	@Override
	public boolean addEducation(List<EducationDTO> listEduExp) {
		// Saving only Work Experience
		List<ResBuilderEdu> listBuilderEdu = resumeConversionHelper
				.transformBuilderEducation(listEduExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderEdu);
		} catch (HibernateException e) {
			LOGGER.info("Error while adding education", e);
		}
		return true;
	}

	@Override
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		// Saving only Work Experience
		// List<ResBuilderEmployment> builderWorkExp =
		// resumeConversionHelper.transformBuilderWorkExp(listWorkExp, null);
		try {
			// hibernateTemplate.saveOrUpdateAll(builderWorkExp);
		} catch (HibernateException e) {
			LOGGER.info("Error while adding language", e);
		}
		return true;
	}

	@Override
	public boolean addCertifications(List<CertificationDTO> listLangExp) {
		// Saving only Work Experience
		List<ResBuilderCertification> listBuilderCerts = resumeConversionHelper
				.transformBuilderCertifications(listLangExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderCerts);
		} catch (HibernateException e) {
			LOGGER.info("Error while adding certifications", e);
		}
		return true;
	}

	/**
	 * This method is called to fetch the public visibility resume of Job seeker
	 * 
	 * @param jobSeekerId
	 * @return ResumeDTO
	 */
	@Override
	public ResumeDTO fetchPublicResumeByUserId(long jobSeekerId) {
		List<ResUploadResume> resumes = hibernateTemplate
				.find("from ResUploadResume where userId = " + jobSeekerId
						+ " AND active = " + MMJBCommonConstants.VISIBILITY_PUBLIC+ "and deleteDt is null");
//		ResumeDTO resumeDTO = resumeConversionHelper
//				.transformResUploadResumeToResumeDTO(resumes.get(0), null);
		ResUploadResume resUploadResume = resumes.get(0);
		return editResume(resUploadResume.getUploadResumeId());
	}

	@Override
	public int findResumeCount(int userId) {
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from ResUploadResume where userId ="+userId+" and deleteDt is NULL"));
	}

	@Override
	public boolean checkDuplicateResumeName(String resumeId, String resumeName, int userId) {
		int resumePresence = 0;
		if(!"".equals(resumeId) && resumeId != null){
			resumePresence = DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from ResUploadResume where userId ="+userId+" and uploadResumeId != "+resumeId+" and resumeName = '"+resumeName+"'"+" and deleteDt is NULL"));
			return resumePresence > 0;
		}
		resumePresence = DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from ResUploadResume where userId ="+userId+" and resumeName = '"+resumeName+"'"+" and deleteDt is NULL"));
		return resumePresence > 0;
	}
	
	/**
	 * This method is used to move the resumes into adm_folder_resume table.
	 * @param List<String>, int userId
	 * @return boolean
	 */
	
	public boolean moveResumesToFolder(List<String> publishResumeIdArrList, int userId){
		boolean status = true;
		
		try{
			List<AdmFolderResume> admFolderResumeList = new ArrayList<AdmFolderResume>();
			int folderId = 0;
			
			//check if resumes are already moved to folder. If duplicate then return the duplicate resumes ids.
			//List<AdmFolder> existingResumeList = hibernateTemplate.find("select adm from  AdmFolder adm where adm.userId="+userId);
			
			//Check if common folder is present in adm_folder. If not insert one row with user id.
			
			List<AdmFolder> admFolderList = hibernateTemplate.find("select adm from  AdmFolder adm where adm.userId="+userId);
			if(admFolderList.size() == 0){
				List<AdmFolder> admFolderSearchList = new ArrayList<AdmFolder>();
				AdmFolder admFolder = new AdmFolder();
				admFolder.setFolderName(ALL_CANDIDATES_FOLDER_NAME);
				admFolder.setParentFolderId(0);
				admFolder.setUserId(userId);
				admFolderSearchList.add(admFolder);
				hibernateTemplate.saveOrUpdateAll(admFolderSearchList);
				
				List<AdmFolder> admFolderListforFolderID = hibernateTemplate.find("select adm from  AdmFolder adm where adm.userId="+userId);
				folderId = admFolderListforFolderID.get(0).getFolderId();
			}else{
				List<AdmFolder> admFolderListforFolderID = hibernateTemplate.find("select adm from  AdmFolder adm where adm.userId="+userId);
				folderId = admFolderListforFolderID.get(0).getFolderId();
			}
			
			
				
			for(int i=0; i < publishResumeIdArrList.size(); i++){
				AdmFolderResume admFolderResume = new AdmFolderResume();
				admFolderResume.setPublishResumeId(Integer.parseInt(publishResumeIdArrList.get(i)));
				admFolderResume.setFolderId(folderId);
				admFolderResume.setCreateDt(CommonUtil.stringDateToSQLDate(publishResumeIdArrList.get(++i)));
				admFolderResumeList.add(admFolderResume);
			}
			
			hibernateTemplate.saveOrUpdateAll(admFolderResumeList);
		
		}catch (HibernateException e) {
			LOGGER.info("Error occurred while saving the resume details into Adm_folder_resume table.");
			status = false;
		}
		
		return status;
	}
	
	
	
}