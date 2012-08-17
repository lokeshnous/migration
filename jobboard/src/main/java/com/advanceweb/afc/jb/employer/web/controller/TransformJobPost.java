package com.advanceweb.afc.jb.employer.web.controller;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.MerUserDTO;
import com.advanceweb.afc.jb.jobseeker.web.controller.JobSeekerRegistrationForm;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will be used to transform the EmployerJobPostForm to EmployerJobPostDTO
 */

@Repository("transformJobPost")
public class TransformJobPost {
	
	
	public JobPostDTO jobPostFormToJobPostDTO(JobPostForm form){
		
		JobPostDTO jobPostDTO=new JobPostDTO();
		/*		jobPostDTO.setJobOwner(form.getJobOwner());
		jobPostDTO.setCustomerNo(form.getCustomerNo());
		jobPostDTO.setCompanyName(form.getCompanyName());
		jobPostDTO.setDisCompanyName(form.getDisCompanyName());
		jobPostDTO.setJobNumber(form.getJobNumber());
		jobPostDTO.setJobTitle(form.getJobTitle());
		jobPostDTO.setJobCity(form.getJobCity());*/
//		jobPostDTO.setJobState(form.getJobState());
//		jobPostDTO.setJobCountry(form.getJobCountry());
//		jobPostDTO.setJobZip(form.getJobZip());
//		jobPostDTO.setEmpTypeId(form.getEmpTypeId());
//		jobPostDTO.setReqSkills(form.getReqSkills());
//		jobPostDTO.setJobDesc(form.getJobDesc());
//		jobPostDTO.setTrackPixel(form.getTrackingPixel());
//		jobPostDTO.setJpBrdTemp(form.getJpBrdTemp());
//		jobPostDTO.setAutoRenew(form.getAutoRenew());
		
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
}
