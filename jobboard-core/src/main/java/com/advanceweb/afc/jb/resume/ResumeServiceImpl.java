package com.advanceweb.afc.jb.resume;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;
import com.advanceweb.afc.jb.data.resume.ResumeDao;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */

public class ResumeServiceImpl implements ResumeService {

	
	public ResumeDTO resumeDTO;
	
	@Autowired
	public ResumeDao resumeDao;


	public ResumeServiceImpl() {

	}

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
	 * This method is called to delete the resume 
	 * @param resumeId
	 * @return delete status
	 */
	@Override
	public boolean deleteResume(int resumeId) {
		return resumeDao.deleteResume(resumeId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCreateResumeCopyPaste(ResumeDTO resumeDTO) {
		resumeDao.saveCreateResumeCopyPaste(resumeDTO);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCreateResumeUpload(ResumeDTO resumeDTO) {
		resumeDao.saveCreateResumeUpload(resumeDTO);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createResumeBuilder(ResumeDTO resumeDTO) {
		
		return resumeDao.createResume(resumeDTO);
	}

	@Override
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp) {
		
		return false;
	}

	@Override
	public boolean addReference(List<ReferenceDTO> listRefExp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEducation(List<EducationDTO> listEduExp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addLanguage(List<LanguageDTO> listLangExp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCertifications(List<CertificationDTO> listLangExp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
