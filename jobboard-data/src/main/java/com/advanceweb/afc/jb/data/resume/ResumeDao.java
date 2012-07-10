package com.advanceweb.afc.jb.data.resume;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumeDTO;


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
}
