package com.advanceweb.afc.jb.resume.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.entities.ResBuilderResume;

/**
 * This class has been created as a service interface for getting the resume 
 * list from the DB.
 * @author Reetesh Ranjan Nayak
 * @version 1.0
 * @since 15th October 2012
 */

@Repository("resumeSearchDAO")
public class ResumeSearchDAOImpl implements ResumeSearchDAO{
	
	private static final Logger LOGGER = Logger.getLogger("ResumeSearchDAOImpl.class");
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	/**
	 * This method is used to get the resume details from the DB
	 * @param String searchString
	 * @return List<ResumeDTO>
	 */
	public List<ResumeDTO> getResumeSearchDetails(String searchString){
		
		List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
		@SuppressWarnings("unchecked")
		List<ResBuilderResume> resBuilderResumeList = hibernateTemplate.find("select rbr from ResBuilderResume rbr, ResBuilderEmployment rbe where " +
				" rbr.builderResumeId=rbe.resBuilderResume.builderResumeId and rbr.resUploadResumeId in ( select rur.uploadResumeId from ResUploadResume rur, "+
				" ResResumeProfile rrp where rur.uploadResumeId=rrp.resumeId and rrp.attribValue like '%"+searchString+"%' and rrp.resResumeAttrib= "+
				" (select resumeAttribId from ResResumeAttrib where name='JobTitle'))");
		
		for(ResBuilderResume obj: resBuilderResumeList){
			ResumeDTO resumeDTO = new ResumeDTO();
			resumeDTO.setPublishResumeId(obj.getResPublishResume().getPublishResumeId());
			resumeDTO.setUploadResumeId(obj.getResUploadResumeId());
			resumeDTO.setResumeName(obj.getResumeName());
			resumeDTO.setFullName(obj.getFirstName()+" "+ obj.getLastName());
			resumeDTO.setCity(obj.getCity());
			resumeDTO.setState(obj.getState());
			resumeDTO.setExperience(obj.getResBuilderEmployments().get(0).getEmploymentYears());
			resumeDTO.setEmploymentType(obj.getResBuilderEmployments().get(0).getEmploymentType());
			resumeDTO.setPostDt(obj.getCreateDt());
			resumeDTOList.add(resumeDTO);
		}
		LOGGER.info("Size of resume list = "+resumeDTOList.size());
		return resumeDTOList;
	}
	

}
