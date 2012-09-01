package com.advanceweb.afc.jb.search.engine.solr;

import java.util.List;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.common.ResumeWordDocumentDTO;

/**
 * @author rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:23:39 PM
 */
public class ResumeService {

	public ResumeService() {
	}

	/**
	 * 
	 * @param resumeId
	 */
	public boolean deleteResume(long resumeId) {
		return false;
	}

	@Override
	public void finalize() throws Throwable {

	}

	ResumeDTO retrieveAllResumes(long jobSeekerId) {
		return null;
	}

	/**
	 * 
	 * @param jobId
	 */
	public List<ResumeDTO> retrieveResumesMatchingJob(long jobId) {
		return null;
	}

	/**
	 * 
	 * @param agencyId
	 */
	public List<ResumeDTO> retrieveResumesPostedTowardsAgency(long agencyId) {
		return null;
	}

	
	/**
	 * 
	 * @param employerId
	 */
	public List<ResumeDTO> retrieveResumesPostedTowardsEmployer(long employerId) {
		return null;
	}

	/**
	 * 
	 * @param resumeDTO
	 */
	public boolean saveResume(ResumeDTO resumeDTO) {
		return false;
	}

	/**
	 * 
	 * @param resumeDTO
	 */
	public boolean updateResume(ResumeDTO resumeDTO) {
		return false;
	}

	/**
	 * 
	 * @param resumeDocDTO
	 */
	public boolean uploadResume(ResumeWordDocumentDTO resumeDocDTO) {
		return false;
	}

}