package com.advanceweb.afc.jb.data.resume;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ResumeDTO;
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
	private SessionFactory sessionFactory;

	@Autowired
	private ResumeConversionHelper resumeConversionHelper;

	public ResumeDaoImpl() {

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

		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createQuery("from ResUploadResume where userId = "
						+ jobSeekerId);
		List<ResUploadResume> resumes = query.list();

		List<ResumeDTO> resumeDTOList = resumeConversionHelper
				.transformResUploadResumeListToResumeDTOList(resumes);
		session.close();
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
		Session session = sessionFactory.getCurrentSession();
		ResUploadResume resume = (ResUploadResume) session.get(
				ResUploadResume.class, resumeId);
		return resumeConversionHelper
				.transformResUploadResumeToResumeDTO(resume);
	}

	/**
	 * This method is called to delete the resume
	 * 
	 * @param resumeId
	 * @return delete status
	 */
	@Override
	public boolean deleteResume(int resumeId) {
		Session session = sessionFactory.getCurrentSession();
		ResUploadResume resume = new ResUploadResume();
		resume.setUploadResumeId(resumeId);
		session.delete(resume);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCreateResumeCopyPaste(ResumeDTO createResumeDTO) {

		Session session = null;

		String visibility=createResumeDTO.getResume_visibility();
		if(visibility.equalsIgnoreCase("Pu")){
			ResPublishResume resPublishResume=new ResPublishResume();
			resPublishResume.setUserId(Integer.parseInt("4"));
			resPublishResume.setResumeName(createResumeDTO.getResume_name());
			resPublishResume.setResumeText(createResumeDTO.getResumeText());
			resPublishResume.setActive(Short.parseShort("1"));
			resPublishResume.setCreateUserId(Integer.parseInt("1"));
			resPublishResume.setCreateDt(new Timestamp(new Date().getTime()));
			System.out.println("In public");
			//em.persist(resPublishResume);
			//em.flush();

			try {
				session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(resPublishResume);
			} catch (HibernateException e) {
				e.printStackTrace();
			}		finally{
				//session.close();
			}



		}else{
			System.out.println("In private");
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

			System.out.println("In public");
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
				session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(resPublishResume);
				session.saveOrUpdate(resPrivacy);
				session.saveOrUpdate(resPublishResumePriv);
			} catch (HibernateException e) {
				e.printStackTrace();
			}		finally{
				//session.close();
			}


		}
	}



	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCreateResumeUpload(ResumeDTO createResumeDTO) {
		Session session = null;

		ResUploadResume resUploadResume=new ResUploadResume();
		ResPublishResume resPublishResume=new ResPublishResume();

		resPublishResume.setUserId(Integer.parseInt("41"));
		resPublishResume.setResumeName(createResumeDTO.getResume_name());
		resPublishResume.setResumeText(createResumeDTO.getResumeText());
		resPublishResume.setActive(Short.parseShort("1"));
		resPublishResume.setCreateUserId(Integer.parseInt("1"));
		resPublishResume.setCreateDt(new Timestamp(new Date().getTime()));

		//em.persist(resPublishResume);
        System.out.println(createResumeDTO.getResume_name()+"xxxxxxxxxxxxxxxxxxxxx"+createResumeDTO.getFilePath());

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
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(resPublishResume);
			session.saveOrUpdate(resUploadResume);
			//session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
		}		finally{
			//session.close();
		}





	}
	

	@Override
	public boolean createResume(ResumeDTO resumeDTO) {
		ResBuilderResume builderResume = resumeConversionHelper.transformBuilderResume(resumeDTO);
		List<ResBuilderCertification> builderCerts = resumeConversionHelper.transformBuilderCertifications(resumeDTO.getListCertDTO());
		List<ResBuilderEdu> builderEducations = resumeConversionHelper.transformBuilderEducation(resumeDTO.getListEduDTO());
		List<ResBuilderReference> builderRefs = resumeConversionHelper.transformBuilderReferences(resumeDTO.getListRefDTO());
		List<ResBuilderEmployment> builderWorkExp = resumeConversionHelper.transformBuilderWorkExp(resumeDTO.getListWorkExpDTO());
		builderResume.setResBuilderCertifications(builderCerts);
		builderResume.setResBuilderEdus(builderEducations);
		builderResume.setResBuilderEmployments(builderWorkExp);
		builderResume.setResBuilderReferences(builderRefs);
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(builderResume);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		return true;
	}

}