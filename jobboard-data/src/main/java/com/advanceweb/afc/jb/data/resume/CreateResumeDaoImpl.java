package com.advanceweb.afc.jb.data.resume;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.data.entities.*;
import com.advanceweb.afc.jb.dto.CreateResumeDTO;

@SuppressWarnings("unchecked")
@Transactional
public class CreateResumeDaoImpl implements CreateResumeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public CreateResumeDaoImpl() {

	}


	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveCreateResumeCopyPaste(CreateResumeDTO createResumeDTO) {

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
	public void saveCreateResumeUpload(CreateResumeDTO createResumeDTO) {
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
}
