package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This will work as a service interface for  Employer for posting new job
 */
public interface JobPostService {

	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	List<StateDTO> getStateList();
	boolean savePostJob(JobPostDTO dto);
	List<JobPostDTO> retrieveAllJobPost(int employerId);
	JobPostDTO editJob(int jobId);
}
