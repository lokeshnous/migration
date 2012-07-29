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
	public class JobApplicationDTO {

		private long jobId;
		private long employerId;
		private ResumeDTO resumeDTO;
		private CoverLetterDTO coverLetterDTO;

		public JobApplicationDTO(){

		}

		public void finalize() throws Throwable {

		}

	}


	/**
	 * 
	 * @param jobsDTO
	 */
	public boolean createNewJob(JobDTO jobsDTO);

	/**
	 * 
	 * @param jobApplicationDTO
	 */
	public boolean applyForJob(JobApplicationDTO jobApplicationDTO);

	public List<JobDTO> retrieveJobPostings();

}