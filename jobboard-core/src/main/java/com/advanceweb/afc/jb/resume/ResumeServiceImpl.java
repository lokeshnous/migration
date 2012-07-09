package com.advanceweb.afc.jb.resume;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanceweb.afc.jb.common.ResumeDTO;
import com.advanceweb.afc.jb.data.resume.ResumeDao;

/**
 * anilm
 * @version 1.0
 * @created Jul 9, 2012
 */

public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeDao resumeDao;

	public ResumeServiceImpl() {

	}

	/**
	 * This method is called to retrieve the resume list belonging to a logged
	 * in jobSeeker
	 * 
	 * @param jobSeekerId
	 * @return list of ResumeDTO
	 */
	public List<ResumeDTO> retrieveAllResumes(long jobSeekerId) {

		return resumeDao.retrieveAllResumes(jobSeekerId);
	}

}
