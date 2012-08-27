package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will be used to transform the EmployerJobPostForm to EmployerJobPostDTO
 */

@Repository("transformJobPost")
public class TransformJobPost {
	
	
	/**
	 * Transforming JobPostForm to JobPostDTO 
	 * 
	 * @param form
	 * @return
	 */
	public JobPostDTO jobPostFormToJobPostDTO(JobPostForm form){
		
		//Post New Job
		JobPostDTO jobPostDTO=new JobPostDTO();
		jobPostDTO.setJobOwner(form.getJobOwner());
		jobPostDTO.setCustomerNo(form.getCustomerNo());
		jobPostDTO.setCompanyName(form.getCompanyName());
		jobPostDTO.setbHideCompName(form.isbHideCompName());
		jobPostDTO.setDisCompanyName(form.getDisCompanyName());

		//Job Posting Details
		jobPostDTO.setJobNumber(form.getJobNumber());
		jobPostDTO.setJobTitle(form.getJobTitle());
		jobPostDTO.setJobId(form.getJobId());
		jobPostDTO.setJobPostingType(form.getJobPostingType());
		
		//Post Title and Number
		jobPostDTO.setApplyEmail(form.getApplyEmail());
		jobPostDTO.setApplyUrl(form.getApplyUrl());
		jobPostDTO.setAtsUrl(form.getAtsUrl());
		jobPostDTO.setApplicationMethod(form.getApplMethod());
		
		//Location
		jobPostDTO.setJobCity(form.getJobCity());
		jobPostDTO.setbHideCity(form.isbHideCity());
		jobPostDTO.setJobState(form.getJobState());
		jobPostDTO.setbHideState(form.isbHideState());
		jobPostDTO.setJobCountry(form.getJobCountry());
		jobPostDTO.setbHideCountry(form.isbHideCountry());
		jobPostDTO.setJobZip(form.getJobZipCode());
		jobPostDTO.setbHideZipCode(form.isbHideZipCode());
		
		//Employment Type
		jobPostDTO.setEmploymentType(form.getEmploymentType());
		
		//Job Details
		jobPostDTO.setReqSkills(form.getReqSkills());
		jobPostDTO.setJobDesc(form.getJobDesc());
		jobPostDTO.setTrackPixel(form.getTrackPixel());
		
		//Job Posting Branding Template
		jobPostDTO.setBrandTemplate(form.getBrandTemplate());
		
		//Auto Renew
		jobPostDTO.setAutoRenew(form.isAutoRenew());
		jobPostDTO.setScheduleStartDt(form.getScheduleStartDate());
		jobPostDTO.setScheduleExpiryDt(form.getScheduleEndDate());
		
		jobPostDTO.setJobStatus(form.getJobStatus());
		
		return jobPostDTO;
		
	}
	
	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public MerUserDTO createUserDTO(JobPostForm form){
		
		MerUserDTO dto = new MerUserDTO();		
/*		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());*/
		
		return dto;
	}
	public JobPostForm  transformJobPostDTOToCreateResume(JobPostForm jobPostform,JobPostDTO jobPostDTO){
		jobPostform.setJobOwner(jobPostDTO.getJobOwner());
		jobPostform.setCustomerNo(jobPostDTO.getCustomerNo());
		jobPostform.setCompanyName(jobPostDTO.getCompanyName());
		jobPostform.setDisCompanyName(jobPostDTO.getDisCompanyName());
		jobPostform.setJobPostingType(jobPostDTO.getJobPostingType());
		jobPostform.setJobNumber(jobPostDTO.getJobNumber());
		jobPostform.setJobTitle(jobPostDTO.getJobTitle());
		jobPostform.setApplMethod(jobPostDTO.getApplicationMethod());
		jobPostform.setApplyEmail(jobPostDTO.getApplyEmail());
		jobPostform.setApplyUrl(jobPostDTO.getApplyUrl());
		jobPostform.setJobCity(jobPostDTO.getJobCity());
		jobPostform.setJobState(jobPostDTO.getJobState());
		jobPostform.setJobZipCode(jobPostDTO.getJobZip());
		jobPostform.setJobCountry(jobPostDTO.getJobCountry());
		jobPostform.setEmploymentType(jobPostDTO.getEmploymentType());
		jobPostform.setReqSkills(jobPostDTO.getReqSkills());
		jobPostform.setJobDesc(jobPostDTO.getJobDesc());
		jobPostform.setTrackPixel(jobPostDTO.getTrackPixel());
		jobPostform.setBrandTemplate(jobPostDTO.getBrandTemplate());
		return jobPostform;
		
	}
}
