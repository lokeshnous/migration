package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.AdmBrndngTempDTO;


/**
 * anilm
 * @version 1.0
 * @created Jul 19, 2012
 */
public interface JobPostBrandingTemplate {

	boolean createTemaplate(AdmBrndngTempDTO templateDTO);
	boolean updateTemplate(AdmBrndngTempDTO templateDTO);
	boolean deleteTemplate(int templateId);
	List<AdmBrndngTempDTO> retrieveAllTemplates(int userId);
	
}
