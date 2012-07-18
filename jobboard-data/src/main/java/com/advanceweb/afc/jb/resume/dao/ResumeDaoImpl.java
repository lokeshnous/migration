package com.advanceweb.afc.jb.resume.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.data.entities.ResPublishResume;
import com.advanceweb.afc.jb.data.entities.ResPublishResumePriv;
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

		
		List<ResUploadResume> resumes = hibernateTemplate.find("from ResUploadResume where userId = "+ jobSeekerId);
		List<ResumeDTO> resumeDTOList = resumeConversionHelper.transformResUploadResumeListToResumeDTOList(resumes);
		return resumeDTOList;
	}

	/**
	 * This method is called to edit the resume
	 * 
	 * @param resumeId
	 * @return ResumeDTO
	 */
	@Override
	public ResumeDTO editResume(int resumeId) {
		ResUploadResume resume = hibernateTemplate.get(ResUploadResume.class, resumeId);
		ResBuilderResume resumeBuilder = hibernateTemplate.get(ResBuilderResume.class, resume.getUploadResumeId());
		ResumeDTO dto = resumeConversionHelper.transformResUploadResumeToResumeDTO(resume);
		if(resumeBuilder != null){
			dto = resumeConversionHelper.transformResBuilderResumeToResumeDTO(dto, resumeBuilder);
		}
		return dto;
	}

	/**
	 * This method is called to delete the resume
	 * 
	 * @param resumeId
	 * @return delete status
	 */
	@Override
	public boolean deleteResume(int resumeId) {
//		Session session = sessionFactory.getCurrentSession();
		ResUploadResume resume = new ResUploadResume();
		resume.setUploadResumeId(resumeId);
		hibernateTemplate.delete(resume);
//		session.delete(resume);
		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean createResumeCopyPaste(ResumeDTO createResumeDTO) {
		Boolean result=false;
		ResUploadResume resUploadResume= resumeConversionHelper.transformCopyPasteResume(createResumeDTO);
		try {
			hibernateTemplate.saveOrUpdate(resUploadResume);
			result=true;
		} catch (HibernateException e) {
			result=false;
			e.printStackTrace();
		}		
		return result;

	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean createResumeUpload(ResumeDTO createResumeDTO) {
		Boolean result=false;
		ResUploadResume resUploadResume= resumeConversionHelper.transformUploadResume(createResumeDTO);
		try {
			hibernateTemplate.saveOrUpdate(resUploadResume);
			result=true;
		} catch (HibernateException e) {
			result=false;
			e.printStackTrace();
		}		
		return result;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean createResume(ResumeDTO resumeDTO) {
		ResBuilderResume builderResume = resumeConversionHelper.transformBuilderResume(resumeDTO);
		List<ResBuilderCertification> builderCerts = resumeConversionHelper.transformBuilderCertifications(resumeDTO.getListCertDTO(), builderResume);
		List<ResBuilderEdu> builderEducations = resumeConversionHelper.transformBuilderEducation(resumeDTO.getListEduDTO(), builderResume);
		List<ResBuilderReference> builderRefs = resumeConversionHelper.transformBuilderReferences(resumeDTO.getListRefDTO(), builderResume);
		List<ResBuilderEmployment> builderWorkExp = resumeConversionHelper.transformBuilderWorkExp(resumeDTO.getListWorkExpDTO(), builderResume);
		builderResume.setResBuilderCertifications(builderCerts);
		builderResume.setResBuilderEdus(builderEducations);
		builderResume.setResBuilderEmployments(builderWorkExp);
		builderResume.setResBuilderReferences(builderRefs);
		try {
//			sessionFactory.getCurrentSession().saveOrUpdate(builderResume);
			hibernateTemplate.saveOrUpdate(builderResume);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		return false;
	}

	@Override
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		//Saving only Work Experience 
		List<ResBuilderEmployment> listBuilderWorkExp = resumeConversionHelper.transformBuilderWorkExp(listWorkExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderWorkExp);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addReference(List<ReferenceDTO> listRefExp) {

		//Saving only Work Experience 
		List<ResBuilderReference> listBuilderRef = resumeConversionHelper.transformBuilderReferences(listRefExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderRef);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addEducation(List<EducationDTO> listEduExp) {
		//Saving only Work Experience 
		List<ResBuilderEdu> listBuilderEdu = resumeConversionHelper.transformBuilderEducation(listEduExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderEdu);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		//Saving only Work Experience 
//		List<ResBuilderEmployment> builderWorkExp = resumeConversionHelper.transformBuilderWorkExp(listWorkExp, null);
		try {
//			hibernateTemplate.saveOrUpdateAll(builderWorkExp);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addCertifications(List<CertificationDTO> listLangExp) {
		//Saving only Work Experience 
		List<ResBuilderCertification> listBuilderCerts = resumeConversionHelper.transformBuilderCertifications(listLangExp, null);
		try {
			hibernateTemplate.saveOrUpdateAll(listBuilderCerts);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}

}