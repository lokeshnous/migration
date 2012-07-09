package com.advanceweb.afc.jb.data.resume;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.common.helpers.ResumeConversionHelper;
import com.advanceweb.afc.jb.data.entities.ResUploadResume;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
@SuppressWarnings("unchecked")
@Transactional
public class ResumeDaoImpl implements ResumeDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	public ResumeDaoImpl() {

	}

	/**
	 * This method is called to retrieve the resume list belonging to a logged in jobSeeker 
	 * @param jobSeekerId
	 * @return resumeDTOList
	 */
	@Override
	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId) {

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from ResUploadResume where userId = "+jobSeekerId);
		List<ResUploadResume> resumes = query.list();

		List<ResumeDTO> resumeDTOList = new ResumeConversionHelper()
				.transformResUploadResumeToResumeDTO(resumes);

		return resumeDTOList;
	}

}