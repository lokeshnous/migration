package com.advanceweb.afc.jb.job.service;

import java.util.List;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;


/**
 * @Author : Prince Mathew
 * @Version: 1.0
 * @Created: Jul 18, 2012
 * @Purpose: This will work as a service interface for Employer for posting new
 *           job
 */
public interface JobPostService {

	EmployerInfoDTO getEmployerInfo(int userId, String roleName);

	// List<StateDTO> getStateList();
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
	
	JobPostDTO retrieveJobById(int jobId);

	boolean deleteJob(int jobId, int userId);

	boolean updateManageJob(boolean autoRenew, int brandTemplate, int jobId,
			int userId);

	boolean deactivateJob(int jobId, int userId);

	//boolean repostJob(int jobId, int userId);
	
	boolean repostJob(int jobId, int extendDays);

	List<JobPostingPlanDTO> getJobPostingPlans();

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
	boolean jobSaveByAdmin(JobPostDTO apd, int jobId);

	/**
	 * 
	 * @param jobId
	 * @param facilityId
	 * @param userId
	 * @return
	 */
	int getinvDetIdByJobId(int jobId, int facilityId, int userId);
	
	AdmFacilityJpAudit getinvDtlByJobId(int jobId);
}
