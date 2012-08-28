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
	boolean deleteJob(int jobId , int userId);
	boolean updateManageJob(boolean autoRenew,String brandTemplate,int jobId , int userId);
	boolean deactivateJob(int jobId , int userId);
	boolean repostJob(int jobId , int userId);
	List<JobPostDTO> retrieveAllJobByStatus( String jobStatus,
			int userId);
}
