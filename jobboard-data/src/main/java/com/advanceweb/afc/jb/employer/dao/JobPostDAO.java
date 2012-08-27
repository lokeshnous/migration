package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.StateDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This interface defines all the DAO operations related to Posting New Job 
 */
public interface JobPostDAO {
	
	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	List<StateDTO> getStateList();
	boolean savePostJob(JobPostDTO dto) ;
	List<JobPostDTO> retrieveAllJobPost(int employerId);
	JobPostDTO editJob(int jobId);
	boolean deleteJob(String status,int jobId , int userId);
	boolean updateManageJob(boolean autoRenew, String brandTemplate, int jobId,
			int userId);
	boolean deactivateJob(String status,int jobId, int userId);
	boolean repostJob(String status,int jobId, int userId);
	List<JobPostDTO> retrieveAllJobByStatus(String status,int jobId, int userId);

}
