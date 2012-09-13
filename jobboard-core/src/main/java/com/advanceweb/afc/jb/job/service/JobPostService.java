package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.UserDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This will work as a service interface for  Employer for posting new job
 */
public interface JobPostService {

	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	//List<StateDTO> getStateList();
	boolean savePostJob(JobPostDTO dto);
	List<JobPostDTO> retrieveAllJobPost(int employerId, int offset, int noOfRecords);
	JobPostDTO editJob(int jobId);
	boolean deleteJob(int jobId , int userId);
	boolean updateManageJob(boolean autoRenew,String brandTemplate,int jobId , int userId);
	boolean deactivateJob(int jobId , int userId);
	boolean repostJob(int jobId , int userId);
	List<JobPostDTO> retrieveAllJobByStatus( String jobStatus,
			int employerId, int offset, int noOfRecords);
	
	List<JobPostingPlanDTO> getJobPostingPlans();
	int getTotalNumberOfJobRecords(int employerId);
	int getTotalNumberOfJobRecordsByStatus();
	boolean validateAndDecreaseAvailableCredits(int invDtlId, int facilityId);
	boolean validateAvailableCredits(int invDtlId, int facilityId);

	UserDTO getNSCustomerDetails(int nsCustomerID);

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
	public boolean jobSaveByAdmin(JobPostDTO apd, int jobId);
}
