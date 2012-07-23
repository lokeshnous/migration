package com.advanceweb.afc.jb.job.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AdmBrndngTempDTO;
import com.advanceweb.afc.jb.data.entities.AdmBrandingTemp;
import com.advanceweb.afc.jb.job.helper.JobPostBrandTempConvnHelper;

/**
 * anilm
 * @version 1.0
 * @created Jul 20, 2012
 */
@Repository("jobPostBrandingTempDAO")
public class JobPostBrandingTempDAOImpl implements JobPostBrandingTempDAO{

	private static final Logger LOGGER = Logger
			.getLogger(JobPostBrandingTempDAOImpl.class);
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Autowired
	JobPostBrandTempConvnHelper jobPostBrandTempConvnHelper;
	
	/**
	 * This method is called to create a Job Posting Branding Template 
	 * @param templateDTO
	 * @return boolean
	 */
	@Override
	public boolean createTemaplate(AdmBrndngTempDTO templateDTO) {
		
		AdmBrandingTemp brandingTemp= jobPostBrandTempConvnHelper.transformBrndngTempDTOToAdmBrandingTemp(templateDTO);
		hibernateTemplate.save(brandingTemp);
		return true;
	}
	
	/**
	 * This method is called to update a Job Posting Branding Template 
	 * @param templateDTO
	 * @return boolean
	 */
	@Override
	public boolean updateTemplate(AdmBrndngTempDTO templateDTO) {
		AdmBrandingTemp brandingTemp= jobPostBrandTempConvnHelper.transformBrndngTempDTOToAdmBrandingTemp(templateDTO);
		hibernateTemplate.merge(brandingTemp);
		return true;
	}
	
	/**
	 * This method is called to delete a Job Posting Branding Template 
	 * @param templateId
	 * @return boolean
	 */
	@Override
	public boolean deleteTemplate(int templateId) {
		AdmBrandingTemp brandingTemp = new AdmBrandingTemp();
		brandingTemp.setBrandTempId(templateId);
		hibernateTemplate.delete(brandingTemp);
		return true;
	}
	
	/**
	 * This method is called to list Job Posting Branding Templates 
	 * @param templateId
	 * @return AdmBrndngTempDTO
	 */
	@Override
	public List<AdmBrndngTempDTO> retrieveAllTemplates(int userId) {
		return hibernateTemplate.find("from  AdmBrandingTemp where userId = " + userId);
	}

	
}
