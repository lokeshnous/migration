/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.DropDownDTO;
import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.data.exception.JobBoardDataException;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.job.service.JobPostDelegate;
import com.advanceweb.afc.jb.job.service.JobPostService;
import com.advanceweb.afc.jb.service.exception.JobBoardServiceException;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will implement all the methods of EmployerJobPost interface 
 */
@Service("JobPostService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class JobPostServiceImpl implements JobPostService {
	
	/** The employer job post dao. */
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
	/** The job post delegate. */
	@Autowired
	private JobPostDelegate jobPostDelegate;
	
	/**
	   @Author :Prince Mathew
	   @Purpose:To get the information of the employer while posting the job
	   @Created:Jul 19, 2012
	   @Param  :userId
	   @Return :EmployerInfoDTO which contain the employer information
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getEmployerInfo(int)
	 */
	@Override
	public EmployerInfoDTO getEmployerInfo(int userId, String roleName) {
		return employerJobPostDAO.getEmployerInfo(userId, roleName);
		
	}

	/**
	   @Author :Prince Mathew
	   @Purpose:To get the List of States 
	   @Created:Jul 19, 2012
	   @Param  :not required
	   @Return :List of the StateDTO
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getStateList()
	 */
	/*@Override
	public List<StateDTO> getStateList() {
		return employerJobPostDAO.getStateList();
		
	}*/

	/**
	 * @Author :Prince Mathew
	 * @Purpose:To save the new job
	 * @Created:Jul 20, 2012
	 * @Param :EmployerInfoDTO object
	 * @Return :boolean result
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#savePostJob(com.advanceweb.afc.jb.common.EmployerInfoDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean savePostJob(JobPostDTO dto) {
		return employerJobPostDAO.savePostJob(dto);
	}

	/**
	 * Method to retrieve all job Posted by the employer and sub facilities
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPost(List<DropDownDTO> companyList,
			int offset, int noOfRecords, String sortBy) {
		return employerJobPostDAO.retrieveAllJobPost(
				companyList, offset, noOfRecords, sortBy);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#retrieveJobById(int)
	 */
	@Override
	public JobPostDTO retrieveJobById(int jobId) {
		return employerJobPostDAO.retrieveJobById(jobId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#deleteJob(int, int)
	 */
	@Override
	public boolean deleteJob(int jobId, int userId) {
		return employerJobPostDAO.deleteJob(jobId, userId);
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#updateManageJob(boolean, int, int, int)
	 */
	@Override
	public boolean updateManageJob(boolean autoRenew, int brandTemplate,
			int jobId, int userId) {
		return employerJobPostDAO.updateManageJob(autoRenew,brandTemplate,jobId, userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#deactivateJob(int, int)
	 */
	@Override
	public boolean deactivateJob(int jobId, int userId) {
		return employerJobPostDAO.deactivateJob(jobId, userId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#repostJob(int, int)
	 */
	@Override
	public boolean repostJob(int jobId, int extendDays) {
		return employerJobPostDAO.repostJob(jobId, extendDays);
	}

	/**
	 * Method to retrieve all job Posted by the employer and sub facilities by filtering 
	 * on job status
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobByStatus(String jobStatus,
			List<DropDownDTO> companyList, int offset,
			int noOfRecords) {
		return employerJobPostDAO.retrieveAllJobByStatus(jobStatus, companyList,
				offset, noOfRecords);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getJobPostingPlans()
	 */
	@Override
	public List<JobPostingPlanDTO> getJobPostingPlans() {
		return employerJobPostDAO.getJobPostingPlans();
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getEmpJobsCount(java.util.List)
	 */
	@Override
	public int getEmpJobsCount(List<DropDownDTO> companyList) {
		return employerJobPostDAO.getEmpJobsCount(companyList);
		
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getEmpJobsCountByStatus(java.lang.String, java.util.List)
	 */
	@Override
	public int getEmpJobsCountByStatus(String jobStatus, List<DropDownDTO> companyList) {
		return employerJobPostDAO.getEmpJobsCountByStatus(jobStatus, companyList);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#validateAndDecreaseAvailableCredits(int, int)
	 */
	@Override
	public boolean validateAndDecreaseAvailableCredits(int invDtlId, int facilityId) {

		return employerJobPostDAO.validateAndDecreaseAvailableCredits(invDtlId, facilityId);
	}

	
	/**
	 * This method is used to get the net suite customer details based on
	 * customer id.
	 * @param int admFacilityID
	 * @return int nsCustomerID
	 */
	@Override
	public UserDTO getNSCustomerDetails(int nsCustomerID){
		return  jobPostDelegate.getNSCustomerDetails(nsCustomerID);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#retrieveAllJobPostByADvSearch(int)
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPostByADvSearch(int advSearchId){
		return employerJobPostDAO.retrieveAllJobPostByADvSearch(advSearchId);
	}
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#jobSaveByAdmin(com.advanceweb.afc.jb.common.JobPostDTO, int)
	 */
	@Override
	public boolean jobSaveByAdmin(JobPostDTO apd, int jobId){
		return employerJobPostDAO.jobSaveByAdmin(apd,jobId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#validateAvailableCredits(int, int)
	 */
	@Override
	public boolean validateAvailableCredits(int invDtlId, int facilityId) {
		return employerJobPostDAO.validateAvailableCredits(invDtlId, facilityId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getinvDetIdByJobId(int, int, int)
	 */
	@Override
	public int getinvDetIdByJobId(int jobId, int facilityId, int userId) {

		return employerJobPostDAO.getinvDetIdByJobId(jobId, facilityId, userId);
	}

	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#getinvDtlByJobId(int)
	 */
	@Override
	public AdmFacilityJpAudit getinvDtlByJobId(int jobId) {
		return employerJobPostDAO.getinvDtlByJobId(jobId);
	}

	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#checkDraftAndSchedule(int)
	 */
	@Override
	public boolean checkDraftAndSchedule(int avdSearchId) {
		return employerJobPostDAO.checkDraftAndSchedule(avdSearchId);
	} 
	
	/* (non-Javadoc)
	 * @see com.advanceweb.afc.jb.job.service.JobPostService#validateLocationdetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateLocationdetails(String city,String state,String zip,String country) throws JobBoardServiceException{
		boolean valied=false;
		try {
			valied= employerJobPostDAO.validateCityStateZip(city,state,zip,country);
		} catch (JobBoardDataException e) {
			e.printStackTrace();
		}
		return valied;
	}
}
