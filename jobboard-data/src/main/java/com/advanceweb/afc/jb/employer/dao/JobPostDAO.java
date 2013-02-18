package com.advanceweb.afc.jb.employer.dao;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;


/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This interface defines all the DAO operations related to Posting New Job 
 */
public interface JobPostDAO {
	
	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	boolean savePostJob(JobPostDTO dto) ;
	
	/**
	 * Method to retrieve all job Posted by the employer and sub facilities
	 * 
	 * @param companyList 
	 * @param offset
	 * @param noOfRecords
	 * @param sortBy
	 * @return
	 */
	List<JobPostDTO> retrieveAllJobPost(List<DropDownDTO> companyList,
			int offset, int noOfRecords, String sortBy);

	/**
	 * Method to retrieve all job Posted by the employer and sub facilities by filtering 
	 * on job status
	 * 
	 * @param jobStatus
	 * @param companyList
	 * @param offset
	 * @param noOfRecords
	 * @return
	 */
	List<JobPostDTO> retrieveAllJobByStatus(String jobStatus,
			List<DropDownDTO> companyList,int offset,
			int noOfRecords);

	int getEmpJobsCount(List<DropDownDTO> companyList);
	int getEmpJobsCountByStatus(String jobStatus, List<DropDownDTO> companyList);
	
	JobPostDTO retrieveJobById(int jobId);
	boolean deleteJob(int jobId , int userId);
	boolean updateManageJob(boolean autoRenew, int brandTemplate, int jobId,
			int userId);
	boolean deactivateJob(int jobId, int userId);
	
	boolean repostJob(int jobId, int extendDays);
	
	List<JobPostingPlanDTO> getJobPostingPlans();
	List<SchedulerDTO> executeActiveJobWorker(List<JobPostDTO> jobsList);
	List<SchedulerDTO> executeAutoRenewalJobWorker(List<JobPostDTO> jobsList);
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
	
	List<SchedulerDTO> retreiveActiveJobsExpireSoon();
	
	List<SchedulerDTO> executeExpireJobs();
	public boolean checkDraftAndSchedule(int avdSearchId);
	boolean validateCityStateZip(String city, String state, String zip,String country) throws JobBoardDataException;
	
}
