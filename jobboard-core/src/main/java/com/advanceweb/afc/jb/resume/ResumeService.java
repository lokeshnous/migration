package com.advanceweb.afc.jb.resume;

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

public interface ResumeService {

	List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
	ResumeDTO editResume(int resumeId);
	boolean deleteResume(int resumeId , int userId);
	boolean updateResume(ResumeDTO resumeDTO);
	ResumeDTO createResume(ResumeDTO resumeDTO);
	boolean createResumeBuilder(ResumeDTO resumeDTO);
	
	boolean createResumeCopyPaste(ResumeDTO resumeDTO);
	boolean updateResumeCopyPaste(ResumeDTO resumeDTO);
	
	ResumeDTO createResumeUpload(ResumeDTO resumeDTO);
	boolean updateResumeUpload(ResumeDTO resumeDTO);
	
	boolean addWorkExp(List<WorkExpDTO> listWorkExp);
	boolean addReference(List<ReferenceDTO> listRefExp);
	boolean addEducation(List<EducationDTO> listEduExp);
	boolean addLanguage(List<LanguageDTO> listLangExp);
	boolean addCertifications(List<CertificationDTO> listLangExp);
	
	ResumeDTO fetchPublicResumeByUserId(long jobSeekerId);
	int findResumeCount(int userId);
	boolean checkDuplicateResumeName(String resumeId, String resumeName,int userId);
	

	/**
	 * This method is used to move the resumes into adm_folder_resume table.
	 * @param List<String>, int userId
	 * @return boolean
	 */
	boolean moveResumesToFolder(List<String> publishResumeIdArrList, int userId);
	
}

