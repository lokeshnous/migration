package com.advanceweb.afc.jb.data.resume;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.data.common.helpers.ResumeConversionHelper;
import com.advanceweb.afc.jb.data.entities.ResBuilderCertification;
import com.advanceweb.afc.jb.data.entities.ResBuilderEdu;
import com.advanceweb.afc.jb.data.entities.ResBuilderEmployment;
import com.advanceweb.afc.jb.data.entities.ResBuilderReference;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;
import com.advanceweb.afc.jb.data.entities.ResPrivacy;
import com.advanceweb.afc.jb.data.entities.ResPublishResume;
import com.advanceweb.afc.jb.data.entities.ResPublishResumePriv;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

/**
 * anilm
 * 
 * @version 1.0
 * @created Jul 9, 2012
 */
@SuppressWarnings("unchecked")
@Transactional
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
		dto = resumeConversionHelper.transformResBuilderResumeToResumeDTO(dto, resumeBuilder);
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
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCreateResumeCopyPaste(ResumeDTO createResumeDTO) {

//		Session session = null;

		String visibility=createResumeDTO.getResume_visibility();
		if(visibility.equalsIgnoreCase("Pu")){
			ResPublishResume resPublishResume=new ResPublishResume();
			resPublishResume.setUserId(Integer.parseInt("4"));
			resPublishResume.setResumeName(createResumeDTO.getResume_name());
			resPublishResume.setResumeText(createResumeDTO.getResumeText());
			resPublishResume.setActive(Short.parseShort("1"));
			resPublishResume.setCreateUserId(Integer.parseInt("1"));
			resPublishResume.setCreateDt(new Timestamp(new Date().getTime()));
			//em.persist(resPublishResume);
			//em.flush();

			try {
				hibernateTemplate.saveOrUpdate(resPublishResume);
//				session = sessionFactory.getCurrentSession();
//				session.saveOrUpdate(resPublishResume);
			} catch (HibernateException e) {
				e.printStackTrace();
			}		



		}else{
			ResPublishResumePriv resPublishResumePriv=new ResPublishResumePriv();
			ResPublishResume resPublishResume=new ResPublishResume();
			ResPrivacy resPrivacy=new ResPrivacy();

			resPublishResume.setUserId(Integer.parseInt("4"));
			resPublishResume.setResumeName(createResumeDTO.getResume_name());
			resPublishResume.setResumeText(createResumeDTO.getResumeText());
			resPublishResume.setActive(Short.parseShort("1"));
			resPublishResume.setCreateUserId(Integer.parseInt("1"));
			resPublishResume.setCreateDt(new Timestamp(new Date().getTime()));

			resPrivacy.setName(createResumeDTO.getResume_name());

			resPublishResumePriv.setResPublishResume(resPublishResume);
			resPublishResumePriv.setResPrivacy(resPrivacy);
			resPublishResumePriv.setActive(Short.parseShort("1"));
			resPublishResumePriv.setCreateUserId(Integer.parseInt("1"));
			resPublishResumePriv.setCreateDt(new Timestamp(new Date().getTime()));

			//em.persist(resPublishResume);
			//em.persist(resPrivacy);

			resPublishResumePriv.setResPublishResume(resPublishResume);
			resPublishResumePriv.setResPrivacy(resPrivacy);
			resPublishResumePriv.setActive(Short.parseShort("1"));
			resPublishResumePriv.setCreateUserId(Integer.parseInt("1"));
			resPublishResumePriv.setCreateDt(new Timestamp(new Date().getTime()));

			//em.persist(resPublishResumePriv);
			//em.flush();
			try {
//				session = sessionFactory.getCurrentSession();
//				session.saveOrUpdate(resPublishResume);
//				session.saveOrUpdate(resPrivacy);
//				session.saveOrUpdate(resPublishResumePriv);
				hibernateTemplate.saveOrUpdate(resPublishResume);
				hibernateTemplate.saveOrUpdate(resPrivacy);
				hibernateTemplate.saveOrUpdate(resPublishResumePriv);
				
			} catch (HibernateException e) {
				e.printStackTrace();
			}		


		}
	}



	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCreateResumeUpload(ResumeDTO createResumeDTO) {
		//Session session = null;

		ResUploadResume resUploadResume=new ResUploadResume();
		ResPublishResume resPublishResume=new ResPublishResume();

		resPublishResume.setUserId(Integer.parseInt("41"));
		resPublishResume.setResumeName(createResumeDTO.getResume_name());
		resPublishResume.setResumeText(createResumeDTO.getResumeText());
		resPublishResume.setActive(Short.parseShort("1"));
		resPublishResume.setCreateUserId(Integer.parseInt("1"));
		resPublishResume.setCreateDt(new Timestamp(new Date().getTime()));

		//em.persist(resPublishResume);
 
		resUploadResume.setResPublishResume(resPublishResume);
		resUploadResume.setUserId(Integer.parseInt("1"));
		resUploadResume.setResumeType(createResumeDTO.getResumeType());
		resUploadResume.setResumeName(createResumeDTO.getResume_name());
		resUploadResume.setFileServer(createResumeDTO.getFileServer());
		resUploadResume.setFilePath("filepath");
		//resUploadResume.setFilePath(createResumeDTO.getFilePath());
		resUploadResume.setFileName(createResumeDTO.getFileName());
		resUploadResume.setResumeText(createResumeDTO.getResumeText());
		resUploadResume.setIsPublished(Short.parseShort(createResumeDTO.getIsPublished()));
		resUploadResume.setCreateDt(new Timestamp(new Date().getTime()));

		//em.persist(resUploadResume);
		//em.flush();
		try {
//			session = sessionFactory.getCurrentSession();
//			session.saveOrUpdate(resPublishResume);
//			session.saveOrUpdate(resUploadResume);
			hibernateTemplate.saveOrUpdate(resPublishResume);
			hibernateTemplate.saveOrUpdate(resUploadResume);
			//session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}	
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
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		return true;
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