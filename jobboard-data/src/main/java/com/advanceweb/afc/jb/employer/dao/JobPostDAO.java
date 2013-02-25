/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
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
	
	/**
	 * Gets the employer info.
	 *
	 * @param userId the user id
	 * @param roleName the role name
	 * @return the employer info
	 */
	EmployerInfoDTO getEmployerInfo(int userId, String roleName);
	
	/**
	 * Save post job.
	 *
	 * @param dto the dto
	 * @return true, if successful
	 */
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

	/**
	 * Gets the emp jobs count.
	 *
	 * @param companyList the company list
	 * @return the emp jobs count
	 */
	int getEmpJobsCount(List<DropDownDTO> companyList);
	
	/**
	 * Gets the emp jobs count by status.
	 *
	 * @param jobStatus the job status
	 * @param companyList the company list
	 * @return the emp jobs count by status
	 */
	int getEmpJobsCountByStatus(String jobStatus, List<DropDownDTO> companyList);
	
	/**
	 * Retrieve job by id.
	 *
	 * @param jobId the job id
	 * @return the job post dto
	 */
	JobPostDTO retrieveJobById(int jobId);
	
	/**
	 * Delete job.
	 *
	 * @param jobId the job id
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean deleteJob(int jobId , int userId);
	
	/**
	 * Update manage job.
	 *
	 * @param autoRenew the auto renew
	 * @param brandTemplate the brand template
	 * @param jobId the job id
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean updateManageJob(boolean autoRenew, int brandTemplate, int jobId,
			int userId);
	
	/**
	 * Deactivate job.
	 *
	 * @param jobId the job id
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean deactivateJob(int jobId, int userId);
	
	/**
	 * Repost job.
	 *
	 * @param jobId the job id
	 * @param extendDays the extend days
	 * @return true, if successful
	 */
	boolean repostJob(int jobId, int extendDays);
	
	/**
	 * Gets the job posting plans.
	 *
	 * @return the job posting plans
	 */
	List<JobPostingPlanDTO> getJobPostingPlans();
	
	/**
	 * Execute active job worker.
	 *
	 * @param jobsList the jobs list
	 * @return the list
	 */
	List<SchedulerDTO> executeActiveJobWorker(List<JobPostDTO> jobsList);
	
	/**
	 * Execute auto renewal job worker.
	 *
	 * @param jobsList the jobs list
	 * @return the list
	 */
	List<SchedulerDTO> executeAutoRenewalJobWorker(List<JobPostDTO> jobsList);
	
	/**
	 * Retreive all scheduled jobs.
	 *
	 * @return the list
	 */
	List<JobPostDTO> retreiveAllScheduledJobs();
	
	/**
	 * Retreive all expired jobs.
	 *
	 * @return the list
	 */
	List<JobPostDTO> retreiveAllExpiredJobs();
	
	/**
	 * Validate and decrease available credits.
	 *
	 * @param invDtlId the inv dtl id
	 * @param facilityId the facility id
	 * @return true, if successful
	 */
	boolean validateAndDecreaseAvailableCredits(int invDtlId, int facilityId);
	
	/**
	 * Validate available credits.
	 *
	 * @param invDtlId the inv dtl id
	 * @param facilityId the facility id
	 * @return true, if successful
	 */
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
	
	/**
	 * Gets the inv dtl by job id.
	 *
	 * @param jobId the job id
	 * @return the inv dtl by job id
	 */
	AdmFacilityJpAudit getinvDtlByJobId(int jobId);
	
	/**
	 * Retreive active jobs expire soon.
	 *
	 * @return the list
	 */
	List<SchedulerDTO> retreiveActiveJobsExpireSoon();
	
	/**
	 * Execute expire jobs.
	 *
	 * @return the list
	 */
	List<SchedulerDTO> executeExpireJobs();
	
	/**
	 * Check draft and schedule.
	 *
	 * @param avdSearchId the avd search id
	 * @return true, if successful
	 */
	public boolean checkDraftAndSchedule(int avdSearchId);
	
	/**
	 * Validate city state zip.
	 *
	 * @param city the city
	 * @param state the state
	 * @param zip the zip
	 * @param country the country
	 * @return true, if successful
	 * @throws JobBoardDataException the job board data exception
	 */
	boolean validateCityStateZip(String city, String state, String zip,String country) throws JobBoardDataException;
	
}
