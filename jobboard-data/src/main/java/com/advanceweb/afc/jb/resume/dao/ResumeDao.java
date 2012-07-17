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
	
	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
	public ResumeDTO editResume(int resumeId);
	public boolean deleteResume(int resumeId);
	public boolean createResume(ResumeDTO resumeDTO);
	public boolean createResumeCopyPaste ( ResumeDTO resumeDTO );
	public boolean createResumeUpload (ResumeDTO resumeDTO);
	
	public boolean addWorkExp(List<WorkExpDTO> listWorkExp);
	public boolean addReference(List<ReferenceDTO> listRefExp);
	public boolean addEducation(List<EducationDTO> listEduExp);
	public boolean addLanguage(List<LanguageDTO> listLangExp);
	public boolean addCertifications(List<CertificationDTO> listLangExp);
}
