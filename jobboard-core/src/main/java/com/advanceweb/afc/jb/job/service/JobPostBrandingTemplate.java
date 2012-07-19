package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumeDTO;

/**
 * anilm
 * @version 1.0
 * @created Jul 19, 2012
 */
public interface JobPostBrandingTemplate {

	List<ResumeDTO> retrieveAllResumes(long jobSeekerId);
	ResumeDTO editResume(int resumeId);
	boolean deleteResume(int resumeId);
	boolean createResumeBuilder(ResumeDTO resumeDTO);
}
