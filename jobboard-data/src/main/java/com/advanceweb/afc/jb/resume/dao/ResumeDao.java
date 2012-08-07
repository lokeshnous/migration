package com.advanceweb.afc.jb.resume.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.CertificationDTO;
import com.advanceweb.afc.jb.common.EducationDTO;
import com.advanceweb.afc.jb.common.LanguageDTO;
import com.advanceweb.afc.jb.common.ReferenceDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.WorkExpDTO;


/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */
public interface ResumeDao {
	
	List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
	ResumeDTO editResume(int resumeId);
	boolean deleteResume(int resumeId);
	boolean updateResume(ResumeDTO resumeDTO);
	boolean createResume(ResumeDTO resumeDTO);
	boolean createResumeCopyPaste ( ResumeDTO resumeDTO );
	boolean createResumeUpload (ResumeDTO resumeDTO);
	
	boolean addWorkExp(List<WorkExpDTO> listWorkExp);
	boolean addReference(List<ReferenceDTO> listRefExp);
	boolean addEducation(List<EducationDTO> listEduExp);
	boolean addLanguage(List<LanguageDTO> listLangExp);
	boolean addCertifications(List<CertificationDTO> listLangExp);
	public ResumeDTO getProfileAttributes();
	/**
	 * Get the public visibility resume of Job seeker
	 * 
	 * @param jobSeekerId
	 * @return
	 */
	ResumeDTO fetchPublicResumeByUserId(long jobSeekerId);
}
