package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.UserDTO;

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
		jobPostDTO.setXmlStartEndDateEnabled(form.isXmlStartEndDateEnabled());
		
		return jobPostDTO;
		
	}
	
	/**
	 * Converting Job Seeker Registration Form to MerUserDTO
	 * 
	 * @param form
	 * @return
	 */
	public UserDTO createUserDTO(JobPostForm form){
		
		UserDTO dto = new UserDTO();		
/*		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setMiddleName(form.getMiddleName());
		dto.setPassword(form.getPassword());
		dto.setEmailId(form.getEmailId());*/
		
		return dto;
	}
	public JobPostForm  transformJobPostDTOToForm(JobPostForm jobPostform,JobPostDTO jobPostDTO){
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
	
	public List<JobPostingsForm> transformToJobPostingsFormList(List<JobPostingPlanDTO> jobPostingPlanDTOList)
	{
		List<JobPostingsForm> jobPostingsFormList = new ArrayList<JobPostingsForm>();
		
		for(JobPostingPlanDTO jobPostingPlanDTO : jobPostingPlanDTOList){
			JobPostingsForm jobPostingsForm = new JobPostingsForm();
			jobPostingsForm.setJobPostPlanId(jobPostingPlanDTO.getJobPostPlanId());
			jobPostingsForm.setJobPostPlanName(jobPostingPlanDTO.getJobPostPlanName());
			jobPostingsForm.setJobPostPlanDescr(jobPostingPlanDTO.getJobPostPlanDescr());
			jobPostingsForm.setJobPostPlanCretitAmt(jobPostingPlanDTO.getJobPostPlanCretitAmt());
			List<AddOnForm> AddOnFormList = new ArrayList<AddOnForm>();
			for(AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()){
				AddOnFormList.add(transformAddOnDTOToAddOnForm(addOnDTO));
			}
			jobPostingsForm.setAddOnForm(AddOnFormList);
			jobPostingsFormList.add(jobPostingsForm);
		}
		return jobPostingsFormList;
		
	}
	
	private AddOnForm transformAddOnDTOToAddOnForm(AddOnDTO addOnDTO) {
		AddOnForm addOnForm = new AddOnForm();
		addOnForm.setAddOnId(addOnDTO.getAddOnId());
		addOnForm.setAddOnName(addOnDTO.getAddOnName());
		addOnForm.setAddOnDescription(addOnDTO.getAddOnDescription());
		addOnForm.setAddOnCreditAmt(addOnDTO.getAddOnCreditAmt());
		return addOnForm;
	}
}
