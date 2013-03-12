/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
import com.advanceweb.afc.jb.data.entities.ResBlockedCompanies;
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
import com.advanceweb.afc.jb.data.entities.ResViewed;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
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

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("ResumeDaoImpl.class");

	/** The Constant FIND_RES_BUILD_RESUME. */
	private static final String FIND_RES_BUILD_RESUME = "from ResBuilderResume res where res.resUploadResumeId=?";
	
	/** The Constant ALL_CANDIDATES_FOLDER_NAME. */
	private static final String ALL_CANDIDATES_FOLDER_NAME = "Default Folder";

	/** The resume conversion helper. */
	@Autowired
	private ResumeConversionHelper resumeConversionHelper;

	/** The hibernate template. */
	private HibernateTemplate hibernateTemplate;

	/**
	 * Sets the hibernate template.
	 *
	 * @param sessionFactory the new hibernate template
	 */
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

		List<ResUploadResume> resumes = hibernateTemplate
				.find("from ResUploadResume where userId = " + jobSeekerId
						+ " and deleteDt is NULL");
		return resumeConversionHelper
				.transformResUploadResumeListToResumeDTOList(resumes);
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
		ResUploadResume resume = hibernateTemplate.get(ResUploadResume.class,
				resumeId);
		List<ResResumeProfile> resumeProfile = hibernateTemplate
				.find("from ResResumeProfile where resumeId = " + resumeId);
		if (resumeProfile != null && !resumeProfile.isEmpty()) {

			dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(
					resume, resumeProfile);

			List<ResBuilderResume> resBuilderList = hibernateTemplate.find(
					FIND_RES_BUILD_RESUME, resume.getUploadResumeId());

			if (resBuilderList != null && !resBuilderList.isEmpty()) {
				ResBuilderResume resBuilder = resBuilderList.get(0);
				dto = resumeConversionHelper
						.transformResBuilderResumeToResumeDTO(dto, resBuilder);
				return dto;
			}
		}
		if (null != resume
				&& MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resume
						.getResumeType())) {
			dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(
					resume, resumeProfile);
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

		ResUploadResume resume = hibernateTemplate.get(ResUploadResume.class,
				resumeDTO.getUploadResumeId());

		resume = resumeConversionHelper.transformAdvancedResumeBuilder(resume,
				resumeDTO);
		hibernateTemplate.update(resume);

		hibernateTemplate.deleteAll(hibernateTemplate
				.find("from ResResumeProfile where resumeId = "
						+ resume.getUploadResumeId()));

		List<ResResumeAttrib> resumeAttrib = hibernateTemplate
				.find("from ResResumeAttrib");
		List<ResResumeProfile> resumeProfileList = resumeConversionHelper
				.transformResumeDTOResResumeProfile(resume, resumeDTO,
						resumeAttrib);
		hibernateTemplate.saveOrUpdateAll(resumeProfileList);

		// update the resume title in the resume builder table
		Query query = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"update ResBuilderResume set resumeName = :resumeName"
								+ " where resUploadResumeId = :resUploadResumeId");
		query.setParameter("resumeName", resumeDTO.getResumeName());
		query.setParameter("resUploadResumeId", resumeDTO.getUploadResumeId());
		query.executeUpdate();

		return true;
	}

	/**
	 * Resume visibility public to private.
	 *
	 * @param resumeDTO the resume dto
	 */
	private void resumeVisibilityPublicToPrivate(ResumeDTO resumeDTO) {
		if (MMJBCommonConstants.VISIBILITY_PUBLIC.equals(resumeDTO
				.getResumeVisibility())) {
			List<ResUploadResume> resumes = hibernateTemplate
					.find("from ResUploadResume where userId = "
							+ resumeDTO.getUserId() + " and uploadResumeId !="
							+ resumeDTO.getUploadResumeId() + " and active='"
							+ MMJBCommonConstants.VISIBILITY_PUBLIC + "'");
			for (ResUploadResume resume : resumes) {
				resume.setActive(Integer
						.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
				resume.setIsPublished(Integer
						.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
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
		resume = hibernateTemplate.get(ResUploadResume.class, resumeId);
		// resume.setDeleteUserId(userId);
		resume.setDeleteDt(new Timestamp(new Date().getTime()));
		hibernateTemplate.save(resume);
		List<ResResumeProfile> resumeProfileAttribs = hibernateTemplate
				.find("from ResResumeProfile where resumeId=" + resumeId
						+ " and deleteDt is NULL");
		List<ResResumeProfile> resumeProfileList = new ArrayList<ResResumeProfile>();
		Date deleteDt = new Timestamp(new Date().getTime());
		for (ResResumeProfile resumeAttrib : resumeProfileAttribs) {
			resumeAttrib.setDeleteDt(deleteDt);
			resumeProfileList.add(resumeAttrib);
		}
		hibernateTemplate.saveOrUpdateAll(resumeProfileList);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#createResume(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResumeDTO createResume(ResumeDTO resumeDTO) {
		// if any public resumes , make it private
		resumeVisibilityPublicToPrivate(resumeDTO);
		ResumeDTO newResumeDTO = null;
		ResUploadResume resUploadResume = resumeConversionHelper
				.transformResumeDTOToResUploadResume(resumeDTO);
		try {
			hibernateTemplate.save(resUploadResume);
			List<ResResumeAttrib> resumeAttrib = hibernateTemplate
					.find("from ResResumeAttrib");
			List<ResResumeProfile> resumeProfileList = resumeConversionHelper
					.transformResumeDTOResResumeProfile(resUploadResume,
							resumeDTO, resumeAttrib);
			hibernateTemplate.saveOrUpdateAll(resumeProfileList);
			newResumeDTO = resumeConversionHelper
					.transformResUploadResumeToResumeDTO(resUploadResume, null);
		} catch (HibernateException e) {
			LOGGER.error("Error while Creating Resume", e);
		}
		return newResumeDTO;

	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#createResumeCopyPaste(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResumeCopyPaste(ResumeDTO resumeDTO) {
		resumeVisibilityPublicToPrivate(resumeDTO);
		Boolean result = false;
		ResUploadResume resUploadResume = resumeConversionHelper
				.transformResumeDTOToResUploadResume(resumeDTO);
		try {
			hibernateTemplate.save(resUploadResume);
			
			result = true;
		} catch (HibernateException e) {
			result = false;
			LOGGER.error("Hibernate Error while Copy Paste", e);
		}
		catch (Exception exp) {
			result = false;
			LOGGER.error("Error while Copy Paste", exp);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#updateResumeCopyPaste(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	public boolean updateResumeCopyPaste(ResumeDTO resumeDTO) {
		return updateResume(resumeDTO);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#createResumeUpload(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResumeDTO createResumeUpload(ResumeDTO resumeDTO) {
		/**
		 * Introduced a new variable "templateForm" to resolve PMD issue.
		 */
		ResumeDTO resDTO = resumeDTO;

		resumeVisibilityPublicToPrivate(resDTO);
		ResUploadResume resUploadResume = resumeConversionHelper
				.transformResumeDTOToResUploadResume(resDTO);

		try {
			hibernateTemplate.saveOrUpdate(resUploadResume);

			resUploadResume.setFilePath(resUploadResume.getFilePath()
					+ resUploadResume.getUploadResumeId() + "_"
					+ resDTO.getFileName());

			hibernateTemplate.update(resUploadResume);
			
		} catch (HibernateException e) {
			LOGGER.error("Error while Resume Upload", e);
		}
		resDTO.setFilePath(resUploadResume.getFilePath());
		return resDTO;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#updateResumeUpload(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateResumeUpload(ResumeDTO resumeDTO) {
		return updateResume(resumeDTO);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#createResumeBuilder(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResumeBuilder(ResumeDTO resumeDTO) {
		ResBuilderResume builderResume = resumeConversionHelper
				.transformBuilderResume(resumeDTO);
		List<ResBuilderCertification> builderCerts = resumeConversionHelper
				.transformBuilderCertifications(resumeDTO.getListCertDTO(),
						builderResume);
		List<ResBuilderEdu> builderEducations = resumeConversionHelper
				.transformBuilderEducation(resumeDTO.getListEduDTO(),
						builderResume);
		List<ResBuilderReference> builderRefs = resumeConversionHelper
				.transformBuilderReferences(resumeDTO.getListRefDTO(),
						builderResume);
		List<ResBuilderEmployment> builderWorkExp = resumeConversionHelper
				.transformBuilderWorkExp(resumeDTO.getListWorkExpDTO(),
						builderResume);
		List<ResBuilderLanguage> builderLangList = resumeConversionHelper
				.transformBuilderLanguages(resumeDTO.getListLangDTO(),
						builderResume);
		List<ResBuilderPhone> builderPhoneList = resumeConversionHelper
				.transformBuilderPhoneDetails(resumeDTO.getListPhoneDtl(),
						builderResume);
		List<ResBuilderSkill> builderSkillSet = resumeConversionHelper
				.transformBuilderSkills(resumeDTO, builderResume);

		builderResume.setResBuilderCertifications(builderCerts);
		builderResume.setResBuilderEdus(builderEducations);
		builderResume.setResBuilderEmployments(builderWorkExp);
		builderResume.setResBuilderReferences(builderRefs);
		builderResume.setResBuilderLanguages(builderLangList);
		builderResume.setResBuilderPhones(builderPhoneList);
		builderResume.setResBuilderSkills(builderSkillSet);
		try {
			if (MMJBCommonConstants.RESUME_TYPE_UPLOAD.equals(resumeDTO
					.getResumeType())) {
				if (resumeDTO.getUploadResumeId() > 0
						&& builderResume.getBuilderResumeId() > 0) {
					updateResumeUpload(resumeDTO);
				} else if (resumeDTO.getUploadResumeId() <= 0
						&& builderResume.getBuilderResumeId() <= 0) {
					resumeDTO = createResumeUpload(resumeDTO);
				}
				builderResume.setResUploadResumeId(resumeDTO
						.getUploadResumeId());
			}
			if (MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resumeDTO
					.getResumeType())) {
				builderResume.setResUploadResumeId(resumeDTO
						.getUploadResumeId());
			}
			hibernateTemplate.merge(builderResume);

			return true;
		} catch (HibernateException e) {
			LOGGER.error("Error in create Resume Builder", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#addWorkExp(java.util.List)
	 */
	@Override
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		// Saving only Work Experience
		List<ResBuilderEmployment> listBuilderWorkExp = resumeConversionHelper
				.transformBuilderWorkExp(listWorkExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderWorkExp);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding work experience", e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#addReference(java.util.List)
	 */
	@Override
	public boolean addReference(List<ReferenceDTO> listRefExp) {

		// Saving only Work Experience
		List<ResBuilderReference> listBuilderRef = resumeConversionHelper
				.transformBuilderReferences(listRefExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderRef);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding reference", e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#addEducation(java.util.List)
	 */
	@Override
	public boolean addEducation(List<EducationDTO> listEduExp) {
		// Saving only Work Experience
		List<ResBuilderEdu> listBuilderEdu = resumeConversionHelper
				.transformBuilderEducation(listEduExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderEdu);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding education", e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#addLanguage(java.util.List)
	 */
	@Override
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		// Saving only Work Experience
		// List<ResBuilderEmployment> builderWorkExp =
		// resumeConversionHelper.transformBuilderWorkExp(listWorkExp, null);
		try {
			// hibernateTemplate.saveOrUpdateAll(builderWorkExp);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding language", e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#addCertifications(java.util.List)
	 */
	@Override
	public boolean addCertifications(List<CertificationDTO> listLangExp) {
		// Saving only Work Experience
		List<ResBuilderCertification> listBuilderCerts = resumeConversionHelper
				.transformBuilderCertifications(listLangExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderCerts);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding certifications", e);
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
	public ResumeDTO fetchPublicResumeByUserId(long jobSeekerId,int uploadResumeId) {
		List<ResUploadResume> resumes = hibernateTemplate
				.find("from ResUploadResume where userId = " + jobSeekerId
						+ " AND uploadResumeId = "
						+ uploadResumeId
						+ "and deleteDt is null");
		// ResumeDTO resumeDTO = resumeConversionHelper
		// .transformResUploadResumeToResumeDTO(resumes.get(0), null);
		ResUploadResume resUploadResume = resumes.get(0);
		return editResume(resUploadResume.getUploadResumeId());
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#findResumeCount(int)
	 */
	@Override
	public int findResumeCount(int userId) {
		return DataAccessUtils.intResult(hibernateTemplate
				.find("select count(*) from ResUploadResume where userId ="
						+ userId + " and deleteDt is NULL"));
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.dao.ResumeDao#checkDuplicateResumeName(java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean checkDuplicateResumeName(String resumeId, String resumeName,
			int userId) {
		int resumePresence = 0;
		if (!"".equals(resumeId) && resumeId != null) {
			resumePresence = DataAccessUtils.intResult(hibernateTemplate
					.find("select count(*) from ResUploadResume where userId ="
							+ userId + " and uploadResumeId != " + resumeId
							+ " and resumeName = '" + resumeName + "'"
							+ " and deleteDt is NULL"));
			return resumePresence > 0;
		}
		resumePresence = DataAccessUtils.intResult(hibernateTemplate
				.find("select count(*) from ResUploadResume where userId ="
						+ userId + " and resumeName = '" + resumeName + "'"
						+ " and deleteDt is NULL"));
		return resumePresence > 0;
	}

	/**
	 * This method is used to move the resumes into adm_folder_resume table.
	 * 
	 * @param List
	 *            <String>, int userId
	 * @return boolean
	 */

	public boolean moveResumesToFolder(List<String> publishResumeIdArrList,
			int userId) {
		boolean status = true;

		try {
			List<AdmFolderResume> admFolderResumeList = new ArrayList<AdmFolderResume>();
			int folderId = 0;

			// check if resumes are already moved to folder. If duplicate then
			// return the duplicate resumes ids.
			// List<AdmFolder> existingResumeList =
			// hibernateTemplate.find("select adm from  AdmFolder adm where adm.userId="+userId);

			// Check if common folder is present in adm_folder. If not insert
			// one row with user id.

			List<AdmFolder> admFolderList = hibernateTemplate
					.find("select adm from  AdmFolder adm where adm.userId="
							+ userId + " and folderName='"
							+ ALL_CANDIDATES_FOLDER_NAME + "'");
			if (admFolderList.size() == 0) {
				List<AdmFolder> admFolderSearchList = new ArrayList<AdmFolder>();
				AdmFolder admFolder = new AdmFolder();
				admFolder.setFolderName(ALL_CANDIDATES_FOLDER_NAME);
				admFolder.setParentFolderId(0);
				admFolder.setUserId(userId);
				admFolderSearchList.add(admFolder);
				hibernateTemplate.saveOrUpdateAll(admFolderSearchList);

				List<AdmFolder> admFolderListforFolderID = hibernateTemplate
						.find("select adm from  AdmFolder adm where adm.userId="
								+ userId);
				folderId = admFolderListforFolderID.get(0).getFolderId();
			} else {
				List<AdmFolder> admFolderListforFolderID = hibernateTemplate
						.find("select adm from  AdmFolder adm where adm.userId="
								+ userId);
				folderId = admFolderListforFolderID.get(0).getFolderId();
			}

			// List<Integer> alreadyPresentIDList = new ArrayList<Integer>();

			for (int i = 0; i < publishResumeIdArrList.size(); i++) {
				AdmFolderResume admFolderResume = new AdmFolderResume();
				// Checking whether the resume is already moved to folder.
				List<AdmFolderResume> isPresentList = hibernateTemplate
						.find("select afr from  AdmFolderResume afr where afr.folderId="
								+ folderId
								+ " and afr.publishResumeId="
								+ Integer.parseInt(publishResumeIdArrList
										.get(i)));
				if (isPresentList.size() == 0) {
					admFolderResume.setPublishResumeId(Integer
							.parseInt(publishResumeIdArrList.get(i)));
					admFolderResume.setFolderId(folderId);
					admFolderResume.setCreateDt(CommonUtil
							.stringDateToSQLDate(publishResumeIdArrList
									.get(++i)));
					admFolderResumeList.add(admFolderResume);
				} else {
					++i;
				}

			}

			hibernateTemplate.saveOrUpdateAll(admFolderResumeList);

		} catch (HibernateException e) {
			LOGGER.error("Error occurred while saving the resume details into Adm_folder_resume table.",e);
			status = false;
		}

		return status;
	}

	@Override
	public boolean saveBlockedCompanydetails(ResumeDTO resumeDTO)
			throws JobBoardDataException {
		int resumeId = resumeDTO.getUploadResumeId();
		//if already some company are blocked, delete those and insert the newly added company list
		List<ResBlockedCompanies> resBlockedCompaniesList = hibernateTemplate
				.find("select rbc from  ResBlockedCompanies rbc where rbc.resumeId="
						+ resumeId);
		if (null != resBlockedCompaniesList
				&& resBlockedCompaniesList.size() > 0) {
			hibernateTemplate.deleteAll(resBlockedCompaniesList);
		}
		if (null != resumeDTO.getSelectedList()
				&& resumeDTO.getSelectedList().size() > 0 && Integer.valueOf(resumeDTO.getResumeVisibility()) > 0) {
			List<Integer> blockedCompnyList = resumeDTO.getSelectedList();
			
			for (Integer blockedCompany : blockedCompnyList) {
				ResBlockedCompanies blockedCompanies = new ResBlockedCompanies();

				blockedCompanies.setCompanyId(blockedCompany);
				blockedCompanies.setResumeId(resumeId);
				blockedCompanies.setCreateDt(new Date());
				hibernateTemplate.saveOrUpdate(blockedCompanies);
			}
		}
		return true;
	}

	@Override
	public boolean saveViewDetails(int resumeId, int userId)
			throws JobBoardDataException {
		try {
			List<ResViewed> resViewedList = hibernateTemplate
					.find("select rv from  ResViewed rv where rv.resumeId="
							+ resumeId + "and rv.userId=" + userId);
			if (null != resViewedList
					&& resViewedList.size() <= 0) {
				ResViewed resViewed = new ResViewed();
				resViewed.setResumeId(resumeId);
				resViewed.setUserId(userId);
				resViewed.setCreateDt(new Date());
				hibernateTemplate.saveOrUpdate(resViewed);
			}
		} catch (HibernateException e) {
			LOGGER.error("Error while adding education", e);
		}
		return true;
	}
	@Override
	public List<ResViewed>  getViewDetails(int resumeId, int userId)
			throws JobBoardDataException {
		List<ResViewed> resViewedList = new ArrayList<ResViewed>();
		try {
			resViewedList = hibernateTemplate
					.find("select rv from  ResViewed rv where rv.resumeId="
							+ resumeId + "and rv.userId=" + userId);
		} catch (HibernateException e) {
			LOGGER.error("Error while adding education", e);
		}
		return resViewedList;
	}
}