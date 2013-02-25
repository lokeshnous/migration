/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;


/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 18, 2012
 * @Purpose: This will work as a service interface for Employer for posting new
 *           job
 */
public interface JobPostService {

	/**
	 * Gets the employer info.
	 *
	 * @param userId the user id
	 * @param roleName the role name
	 * @return the employer info
	 */
	EmployerInfoDTO getEmployerInfo(int userId, String roleName);

	// List<StateDTO> getStateList();
	/**
	 * Save post job.
	 *
	 * @param dto the dto
	 * @return true, if successful
	 */
	boolean savePostJob(JobPostDTO dto);

	/**
	 * Method to retrieve all job Posted by the employer and sub facilities
	 * 
	 * @param companyList 
	 * @param offset
	 * @param noOfRecords
	 * @param sortBy
	 * @return
	 */
	List<JobPostDTO> retrieveAllJobPost(List<DropDownDTO> companyList, int offset,
			int noOfRecords,String sortBy);
	
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
			List<DropDownDTO> companyList, int offset,
			int noOfRecords);
	
	/**
	 * Method helps to get the employer total number of jobs count by selected status
	 * 
	 * @param jobStatus
	 * @param companyList
	 * @return
	 */
	int getEmpJobsCountByStatus(String jobStatus, List<DropDownDTO> companyList);
	
	/**
	 * Method helps to get the employer total number of jobs 
	 * 
	 * @param companyList
	 * @return
	 */
	int getEmpJobsCount(List<DropDownDTO> companyList);
	
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
	boolean deleteJob(int jobId, int userId);

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

	//boolean repostJob(int jobId, int userId);
	
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
	 * Gets the nS customer details.
	 *
	 * @param nsCustomerID the ns customer id
	 * @return the nS customer details
	 */
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
	boolean jobSaveByAdmin(JobPostDTO apd, int jobId);

	/**
	 * 
	 * @param jobId
	 * @param facilityId
	 * @param userId
	 * @return
	 */
	int getinvDetIdByJobId(int jobId, int facilityId, int userId);
	
	/**
	 * Gets the inv dtl by job id.
	 *
	 * @param jobId the job id
	 * @return the inv dtl by job id
	 */
	AdmFacilityJpAudit getinvDtlByJobId(int jobId);
	
	/**
	 * Check draft and schedule.
	 *
	 * @param avdSearchId the avd search id
	 * @return true, if successful
	 */
	public boolean checkDraftAndSchedule(int avdSearchId);
	
	/**
	 * Validate city state and zip code
	 * @param city
	 * @param state
	 * @param Zip
	 * @return
	 * @throws JobBoardServiceException
	 */
	boolean validateLocationdetails(String city,String state,String zip,String country)  throws JobBoardServiceException;
}
