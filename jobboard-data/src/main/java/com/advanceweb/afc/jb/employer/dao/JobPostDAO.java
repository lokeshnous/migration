package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;


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
	JobPostDTO retrieveJobById(int jobId);
	boolean deleteJob(int jobId , int userId);
	boolean updateManageJob(boolean autoRenew, int brandTemplate, int jobId,
			int userId);
	boolean deactivateJob(int jobId, int userId);

	boolean repostJob(int jobId);
	List<JobPostDTO> retrieveAllJobByStatus(String jobStatus, int userId, int offset, int noOfRecords);
	
	List<JobPostingPlanDTO> getJobPostingPlans();
	int getTotalNumberOfJobRecords(int employerId);
	int getTotalNumberOfJobRecordsByStatus();
	
	boolean executeActiveJobWorker(List<JobPostDTO> jobsList);
	boolean executeAutoRenewalJobWorker(List<JobPostDTO> jobsList);
	List<JobPostDTO> retreiveAllScheduledJobs();
	List<JobPostDTO> retreiveAllExpiredJobs();
	
	boolean validateAndDecreaseAvailableCredits(int invDtlId, int facilityId);
	boolean validateAvailableCredits(int invDtlId, int facilityId);
	/**
	 * 
	 * @param advSearchId
	 * @return JobPostDTO
	 */
	List<JobPostDTO> retrieveAllJobPostByADvSearch(int advSearchId);
	/**
	 * 
	 * @param apd
	 * @param jobId
	 * @return boolean value
	 * @throws JobBoardServiceException
	 */
	boolean jobSaveByAdmin(JobPostDTO apd, int jobId);
	/**
	 * 
	 * @param jobId
	 * @return
	 */
	int getinvDetIdByJobId(int jobId,int facilityId,int userId);
	AdmFacilityJpAudit getinvDtlByJobId(int jobId);
}
