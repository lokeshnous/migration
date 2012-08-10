package com.advanceweb.afc.jb.resume;

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
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.resume.dao.ResumeDao;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

	
	public ResumeDTO resumeDTO;
	
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
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ResumeDTO createResume(ResumeDTO resumeDTO) {
		return resumeDao.createResume(resumeDTO);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeCopyPaste(ResumeDTO resumeDTO) {
		return resumeDao.createResumeCopyPaste(resumeDTO);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeUpload(ResumeDTO resumeDTO) {
		return resumeDao.createResumeUpload(resumeDTO);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateResumeUpload(ResumeDTO resumeDTO) {
		return resumeDao.updateResumeUpload(resumeDTO);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeBuilder(ResumeDTO resumeDTO) {
		
		return resumeDao.createResumeBuilder(resumeDTO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		
		return resumeDao.addWorkExp(listWorkExp);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addReference(List<ReferenceDTO> listRefExp) {		
		return resumeDao.addReference(listRefExp);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addEducation(List<EducationDTO> listEduExp) {		
		return resumeDao.addEducation(listEduExp);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		return resumeDao.addLanguage(listLangExp);
	}

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
	public ResumeDTO fetchPublicResumeByUserId(long jobSeekerId) {
		return resumeDao.fetchPublicResumeByUserId(jobSeekerId);
	}

	@Override
	public ResumeDTO getProfileAttributes() {
		return resumeDao.getProfileAttributes();
	}
	
	@Override
	public int findResumeCount(int userId) {
		return resumeDao.findResumeCount(userId);
	}

	@Override
	public boolean checkDuplicateResumeName(String resumeId, String resumeName, int userId) {
		return resumeDao.checkDuplicateResumeName(resumeId, resumeName, userId);
	}

	@Override
	public boolean updateResumeCopyPaste(ResumeDTO resumeDTO) {
		return resumeDao.updateResumeCopyPaste(resumeDTO);
	}

}
