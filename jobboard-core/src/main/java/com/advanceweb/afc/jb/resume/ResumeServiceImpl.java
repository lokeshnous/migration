/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.resume;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.ResumeViewedDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.resume.dao.ResumeDao;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	
	/** The resume dto. */
	public ResumeDTO resumeDTO;
	
	/** The resume dao. */
	@Autowired
	public ResumeDao resumeDao;

	/**
	 * This method is called to retrieve the resume list belonging to a logged in jobSeeker
	 * @param jobSeekerId
	 * @return list of ResumeDTO
	 */
	@Override
	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId) {

		return resumeDao.retrieveAllResumes(jobSeekerId);
	}

	/**
	 * This method is called to edit the resume
	 * @param resumeId
	 * @return ResumeDTO
	 */
	@Override
	public ResumeDTO editResume(int resumeId) {
		return resumeDao.editResume(resumeId);
	}
	
	/**
	 * This method is called to update the resume
	 * @param resumeDTO
	 * @return boolean
	 */
	@Override
	public boolean updateResume(ResumeDTO resumeDTO) {
		return resumeDao.updateResume(resumeDTO);
	}

	/**
	 * This method is called to delete the resume 
	 * @param resumeId
	 * @return delete status
	 */
	@Override
	public boolean deleteResume(int resumeId, int userId) {
		return resumeDao.deleteResume(resumeId, userId);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#createResume(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ResumeDTO createResume(ResumeDTO resumeDTO) {
		return resumeDao.createResume(resumeDTO);
		
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#createResumeCopyPaste(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeCopyPaste(ResumeDTO resumeDTO) {
		return resumeDao.createResumeCopyPaste(resumeDTO);
		
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#createResumeUpload(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ResumeDTO createResumeUpload(ResumeDTO resumeDTO) {
		return resumeDao.createResumeUpload(resumeDTO);
		
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#updateResumeUpload(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateResumeUpload(ResumeDTO resumeDTO) {
		return resumeDao.updateResumeUpload(resumeDTO);
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#createResumeBuilder(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeBuilder(ResumeDTO resumeDTO) {
		
		return resumeDao.createResumeBuilder(resumeDTO);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#addWorkExp(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		
		return resumeDao.addWorkExp(listWorkExp);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#addReference(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addReference(List<ReferenceDTO> listRefExp) {		
		return resumeDao.addReference(listRefExp);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#addEducation(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addEducation(List<EducationDTO> listEduExp) {		
		return resumeDao.addEducation(listEduExp);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#addLanguage(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		return resumeDao.addLanguage(listLangExp);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#addCertifications(java.util.List)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addCertifications(List<CertificationDTO> listLangExp) {
		return resumeDao.addCertifications(listLangExp);
	}
	
	/**
	 * This method is called to fetch the public visibility resume of Job seeker
	 * 
	 * @param jobSeekerId
	 * @return ResumeDTO
	 */
	@Override
	public ResumeDTO fetchPublicResumeByUserId(long jobSeekerId,int uploadResumeId) {
		return resumeDao.fetchPublicResumeByUserId(jobSeekerId,uploadResumeId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#findResumeCount(int)
	 */
	@Override
	public int findResumeCount(int userId) {
		return resumeDao.findResumeCount(userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#checkDuplicateResumeName(java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean checkDuplicateResumeName(String resumeId, String resumeName, int userId) {
		return resumeDao.checkDuplicateResumeName(resumeId, resumeName, userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.resume.ResumeService#updateResumeCopyPaste(com.advanceweb.afc.jb.common.ResumeDTO)
	 */
	@Override
	public boolean updateResumeCopyPaste(ResumeDTO resumeDTO) {
		return resumeDao.updateResumeCopyPaste(resumeDTO);
	}
	


	/**
	 * This method is used to move the resumes into adm_folder_resume table.
	 * @param List<String>, int userId
	 * @return boolean
	 */
	public boolean moveResumesToFolder(List<String> publishResumeIdArrList, int userId){
		return resumeDao.moveResumesToFolder(publishResumeIdArrList, userId);
	}

	@Override
	public boolean saveBlockedCompanydetails(ResumeDTO resumeDTO)throws JobBoardServiceException {
		boolean result = false;
		try {
			result= resumeDao.saveBlockedCompanydetails(resumeDTO);
		} catch (JobBoardDataException jdex) {
			throw new JobBoardServiceException(
					"Error while saving Blocked comapny details..." + jdex);
		}
		return result;
	}

	@Override
	public boolean saveViewDetails(int resumeId, int userId) throws JobBoardServiceException {
		boolean result = false;
		try {
			result= resumeDao.saveViewDetails(resumeId,userId);
		} catch (JobBoardDataException jdex) {
			throw new JobBoardServiceException(
					"Error while saving viewed resume details..." + jdex);
		}
		return result;
	}

	@Override
	public List<ResumeViewedDTO> getViewDetails(int resumeId, int userId)
			throws JobBoardServiceException {
		List<ResumeViewedDTO> resViewedList = new ArrayList<ResumeViewedDTO>();
		try {
			resViewedList = resumeDao.getViewDetails(resumeId, userId);
		} catch (JobBoardDataException jdex) {
			throw new JobBoardServiceException(
					"Error while saving viewed resume details..." + jdex);
		}
		return resViewedList;
	}

}
