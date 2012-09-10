package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This interface defines all the DAO operations related to Posting New Job 
 */
public interface JobPostDAO {
	
	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	boolean savePostJob(JobPostDTO dto) ;
	List<JobPostDTO> retrieveAllJobPost(int employerId, int offset, int noOfRecords);
	JobPostDTO editJob(int jobId);
	boolean deleteJob(int jobId , int userId);
	boolean updateManageJob(boolean autoRenew, String brandTemplate, int jobId,
			int userId);
	boolean deactivateJob(int jobId, int userId);
	boolean repostJob(int jobId, int userId);
	List<JobPostDTO> retrieveAllJobByStatus(String jobStatus, int userId, int offset, int noOfRecords);
	
	List<JobPostingPlanDTO> getJobPostingPlans();
	int getTotalNumberOfJobRecords(int employerId);
	int getTotalNumberOfJobRecordsByStatus();
	
	boolean executeActiveJobWorker();
	boolean executeAutoRenewalJobWorker();
	
	boolean decreaseAvailableCredits();

}
