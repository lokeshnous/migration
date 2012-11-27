package com.advanceweb.afc.jb.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanceweb.afc.jb.common.EmployerInfoDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.SchedulerDTO;
import com.advanceweb.afc.jb.common.UserDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacilityJpAudit;
import com.advanceweb.afc.jb.employer.dao.JobPostDAO;
import com.advanceweb.afc.jb.job.service.JobPostDelegate;
import com.advanceweb.afc.jb.job.service.JobPostService;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will implement all the methods of EmployerJobPost interface 
 */
@Service("JobPostService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class JobPostServiceImpl implements JobPostService {
	
	@Autowired
	private JobPostDAO employerJobPostDAO;
	
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
	 * Method to retrieve all job Posted by the Employer
	 * 
	 * @param : employerId
	 */
	@Override
	public List<JobPostDTO> retrieveAllJobPost(int employerId, int offset, int noOfRecords) {
		return employerJobPostDAO.retrieveAllJobPost(employerId,offset,noOfRecords);
	}
	
	@Override
	public JobPostDTO retrieveJobById(int jobId) {
		return employerJobPostDAO.retrieveJobById(jobId);
	}

	@Override
	public boolean deleteJob(int jobId, int userId) {
		return employerJobPostDAO.deleteJob(jobId, userId);
		
	}

	@Override
	public boolean updateManageJob(boolean autoRenew, int brandTemplate,
			int jobId, int userId) {
		return employerJobPostDAO.updateManageJob(autoRenew,brandTemplate,jobId, userId);
	}

	@Override
	public boolean deactivateJob(int jobId, int userId) {
		return employerJobPostDAO.deactivateJob(jobId, userId);
	}

	@Override
	public boolean repostJob(int jobId) {
		return employerJobPostDAO.repostJob(jobId);
	}

	@Override
	public List<JobPostDTO> retrieveAllJobByStatus(String jobStatus, int userId, int offset, int noOfRecords) {
		return employerJobPostDAO.retrieveAllJobByStatus(jobStatus, userId,offset,noOfRecords);
	}
	
	@Override
	public List<JobPostingPlanDTO> getJobPostingPlans() {
		return employerJobPostDAO.getJobPostingPlans();
	}

	@Override
	public int getTotalNumberOfJobRecords(int userId) {
		return employerJobPostDAO.getTotalNumberOfJobRecords(userId);
		
	}

	@Override
	public int getTotalNumberOfJobRecordsByStatus() {
		return employerJobPostDAO.getTotalNumberOfJobRecordsByStatus();
	}

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

	@Override
	public List<JobPostDTO> retrieveAllJobPostByADvSearch(int advSearchId){
		return employerJobPostDAO.retrieveAllJobPostByADvSearch(advSearchId);
	}
	@Override
	public boolean jobSaveByAdmin(JobPostDTO apd, int jobId){
		return employerJobPostDAO.jobSaveByAdmin(apd,jobId);
	}

	@Override
	public boolean validateAvailableCredits(int invDtlId, int facilityId) {
		return employerJobPostDAO.validateAvailableCredits(invDtlId, facilityId);
	}

	@Override
	public int getinvDetIdByJobId(int jobId, int facilityId, int userId) {

		return employerJobPostDAO.getinvDetIdByJobId(jobId, facilityId, userId);
	}

	
	@Override
	public AdmFacilityJpAudit getinvDtlByJobId(int jobId) {
		return employerJobPostDAO.getinvDtlByJobId(jobId);
	} 

}
