package com.advanceweb.afc.jb.resume.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.JpAttribList;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderLanguage;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResResumeAttrib;
import com.advanceweb.afc.jb.data.entities.ResResumeProfile;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;
import com.advanceweb.afc.jb.lookup.dao.PopulateDropdownsDAO;
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

	@Autowired
	private ResumeConversionHelper resumeConversionHelper;

	private HibernateTemplate hibernateTemplate;

	@Autowired
	private PopulateDropdownsDAO populateDropdownsDAO;

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
		if(resumeProfile != null && resumeProfile.size() > 0) {
			dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(resume, resumeProfile);

			ResBuilderResume resumeBuilder = hibernateTemplate.get(ResBuilderResume.class, resume.getUploadResumeId());

			if (resumeBuilder != null) {
				dto = resumeConversionHelper.transformResBuilderResumeToResumeDTO(dto,resumeBuilder);
				return dto;
			}
		}
		if(MMJBCommonConstants.RESUME_TYPE_COPY_PASTE.equals(resume.getResumeType())){
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
			if (resumes.size() > 0) {
				resumes.get(0).setActive(Integer.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
				resumes.get(0).setIsPublished(Integer.parseInt(MMJBCommonConstants.VISIBILITY_PRIVATE));
				hibernateTemplate.update(resumes.get(0));
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
		for(ResResumeProfile resumeAttrib : resumeProfileAttribs)
		{
			resumeAttrib.setDeleteDt(new Timestamp(new Date().getTime()));
			resumeProfileList.add(resumeAttrib);
		}
		hibernateTemplate.saveOrUpdateAll(resumeProfileList);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResume(ResumeDTO resumeDTO) {
		//if any public resumes , make it private 
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
			e.printStackTrace();
		}
		return result;

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
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean updateResumeCopyPaste(ResumeDTO resumeDTO) {
		return updateResume(resumeDTO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean createResumeUpload(ResumeDTO resumeDTO) {
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
			e.printStackTrace();
		}
		return result;
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
		builderResume.setResBuilderCertifications(builderCerts);
		builderResume.setResBuilderEdus(builderEducations);
		builderResume.setResBuilderEmployments(builderWorkExp);
		builderResume.setResBuilderReferences(builderRefs);
		builderResume.setResBuilderLanguages(builderLangList);
		try {
			// sessionFactory.getCurrentSession().saveOrUpdate(builderResume);
			hibernateTemplate.saveOrUpdate(builderResume);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
		List<JpAttribList> merLookupList = hibernateTemplate
				.find("from JpAttribList e where e.lookupCategory='"
						+ MMJBCommonConstants.VISIBILITY_PUBLIC + "'");
		JpAttribList JpAttribList = merLookupList.get(0);
		List<ResUploadResume> resumes = hibernateTemplate
				.find("from ResUploadResume where userId = " + jobSeekerId
						+ " AND visibility = " + JpAttribList.getAttribListId());
		ResumeDTO resumeDTO = resumeConversionHelper
				.transformResUploadResumeListToResumeDTOList(resumes).get(0);
		return resumeDTO;
	}

	@Override
	public ResumeDTO getProfileAttributes() {
		ResumeDTO dto = null;
		try {
			List<ResResumeAttrib> listProfAttrib = hibernateTemplate
					.find("from ResResumeAttrib");
			dto = resumeConversionHelper.transformProfileAttrib(listProfAttrib);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	@Override
	public int findResumeCount(int userId) {
		int resumeCount = DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from ResUploadResume where userId ="+userId+" and deleteDt is NULL"));
		return resumeCount;
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
}