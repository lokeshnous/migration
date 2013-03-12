/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.data.entities.ResViewed;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;


/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
public interface ResumeDao {
	
	/**
	 * Retrieve all resumes.
	 *
	 * @param jobSeekerId the job seeker id
	 * @return the list
	 */
	List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
	
	/**
	 * Edits the resume.
	 *
	 * @param resumeId the resume id
	 * @return the resume dto
	 */
	ResumeDTO editResume(int resumeId);
	
	/**
	 * Delete resume.
	 *
	 * @param resumeId the resume id
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean deleteResume(int resumeId , int userId);
	
	/**
	 * Update resume.
	 *
	 * @param resumeDTO the resume dto
	 * @return true, if successful
	 */
	boolean updateResume(ResumeDTO resumeDTO);
	
	/**
	 * Creates the resume.
	 *
	 * @param resumeDTO the resume dto
	 * @return the resume dto
	 */
	ResumeDTO createResume(ResumeDTO resumeDTO);
	
	/**
	 * Creates the resume builder.
	 *
	 * @param resumeDTO the resume dto
	 * @return true, if successful
	 */
	boolean createResumeBuilder(ResumeDTO resumeDTO);
	
	/**
	 * Creates the resume copy paste.
	 *
	 * @param resumeDTO the resume dto
	 * @return true, if successful
	 */
	boolean createResumeCopyPaste ( ResumeDTO resumeDTO );
	
	/**
	 * Update resume copy paste.
	 *
	 * @param resumeDTO the resume dto
	 * @return true, if successful
	 */
	boolean updateResumeCopyPaste(ResumeDTO resumeDTO);
	
	/**
	 * Creates the resume upload.
	 *
	 * @param resumeDTO the resume dto
	 * @return the resume dto
	 */
	ResumeDTO createResumeUpload (ResumeDTO resumeDTO);
	
	/**
	 * Update resume upload.
	 *
	 * @param resumeDTO the resume dto
	 * @return true, if successful
	 */
	boolean updateResumeUpload(ResumeDTO resumeDTO);
	
	/**
	 * Adds the work exp.
	 *
	 * @param listWorkExp the list work exp
	 * @return true, if successful
	 */
	boolean addWorkExp(List<WorkExpDTO> listWorkExp);
	
	/**
	 * Adds the reference.
	 *
	 * @param listRefExp the list ref exp
	 * @return true, if successful
	 */
	boolean addReference(List<ReferenceDTO> listRefExp);
	
	/**
	 * Adds the education.
	 *
	 * @param listEduExp the list edu exp
	 * @return true, if successful
	 */
	boolean addEducation(List<EducationDTO> listEduExp);
	
	/**
	 * Adds the language.
	 *
	 * @param listLangExp the list lang exp
	 * @return true, if successful
	 */
	boolean addLanguage(List<LanguageDTO> listLangExp);
	
	/**
	 * Adds the certifications.
	 *
	 * @param listLangExp the list lang exp
	 * @return true, if successful
	 */
	boolean addCertifications(List<CertificationDTO> listLangExp);
	
	/**
	 * Fetch public resume by user id.
	 *
	 * @param jobSeekerId the job seeker id
	 * @param uploadResumeId the upload resume id
	 * @return the resume dto
	 */
	ResumeDTO fetchPublicResumeByUserId(long jobSeekerId,int uploadResumeId);
	
	/**
	 * Find resume count.
	 *
	 * @param userId the user id
	 * @return the int
	 */
	int findResumeCount(int userId);
	
	/**
	 * Check duplicate resume name.
	 *
	 * @param resumeId the resume id
	 * @param resumeName the resume name
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean checkDuplicateResumeName(String resumeId, String resumeName,int userId);
	
	/**
	 * This method is used to move the resumes into adm_folder_resume table.
	 * @param List<String>, int userId
	 * @return boolean
	 */
	boolean moveResumesToFolder(List<String> publishResumeIdArrList, int userId);
	
	boolean saveBlockedCompanydetails(ResumeDTO resumeDTO) throws JobBoardDataException;
	
	/**
	 * This method is used to save the view detail in res_viewd table.
	 * @param String resumeId, int userId
	 * @return boolean
	 */
	boolean saveViewDetails(int resumeId, int userId) throws JobBoardDataException;

	List<ResViewed> getViewDetails(int resumeId, int userId)
			throws JobBoardDataException;
	
}
