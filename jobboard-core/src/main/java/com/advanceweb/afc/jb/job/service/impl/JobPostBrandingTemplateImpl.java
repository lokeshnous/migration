package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.AdmBrndngTempDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.job.dao.JobPostBrandingTempDAO;
import com.advanceweb.afc.jb.job.service.JobPostBrandingTemplate;

/**
 * anilm
 * @version 1.0
 * @created Jul 19, 2012
 */
@Service("jobPostBrandingTempl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class JobPostBrandingTemplateImpl implements JobPostBrandingTemplate{

	@Autowired
	JobPostBrandingTempDAO jobPostBrandingTempDAO;
	
	/**
	 * This method is called to create a Job Posting Branding Template 
	 * @param templateDTO
	 * @return boolean
	 */
	@Override
	public boolean createTemaplate(AdmBrndngTempDTO templateDTO) {
		jobPostBrandingTempDAO.createTemaplate(templateDTO);
		return true;
	}

	/**
	 * This method is called to update a Job Posting Branding Template 
	 * @param templateDTO
	 * @return boolean
	 */
	@Override
	public boolean updateTemplate(AdmBrndngTempDTO templateDTO) {
		jobPostBrandingTempDAO.updateTemplate(templateDTO);
		return true;
	}

	/**
	 * This method is called to delete a Job Posting Branding Template 
	 * @param templateId
	 * @return boolean
	 */
	@Override
	public boolean deleteTemplate(int templateId) {
		jobPostBrandingTempDAO.deleteTemplate(templateId);
		return true;
	}
	
	/**
	 * This method is called to list Job Posting Branding Templates
	 * @param userId
	 * @return AdmBrndngTempDTO
	 */
	@Override
	public List<AdmBrndngTempDTO> retrieveAllTemplates(int userId) {
		
		return jobPostBrandingTempDAO.retrieveAllTemplates(userId);
	}

	

}
