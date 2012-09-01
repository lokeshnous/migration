package com.advanceweb.afc.jb.job.service;


import java.util.List;

import com.advanceweb.afc.jb.common.CoverLetterDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.ResumeDTO;

/**
 * @author Rajeshkb
 * @version 1.0
 * @created 21-Jun-2012 2:22:08 PM
 */
public interface Job {

	/**
	 * @author Rajeshkb
	 * @version 1.0
	 * @created 21-Jun-2012 2:22:08 PM
	 */
	@SuppressWarnings("unused")
	class JobApplicationDTO {

		private long jobId;
		private long employerId;
		private ResumeDTO resumeDTO;
		private CoverLetterDTO coverLetterDTO;

		// Commented to fix PMD issue
		/*protected void finalize() throws Throwable {

		}*/

	}


	/**
	 * Create new Job
	 * 
	 * @param jobsDTO
	 */
	boolean createNewJob(JobDTO jobsDTO);

	/**
	 * Apply for Job
	 * 
	 * @param jobApplicationDTO
	 */
	boolean applyForJob(JobApplicationDTO jobApplicationDTO);
	
	/**
	 * Retrieve Job Postings
	 * 
	 * @return
	 */
	List<JobDTO> retrieveJobPostings();

}