package com.advanceweb.afc.jb.employer.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will be used to transform the EmployerJobPostForm to EmployerJobPostDTO
 */

@Repository("transformJobPost")
public class TransformJobPost {

	/**
	 * This method will transform JobPostForm to JobPostDTO
	 * @param form
	 * @return jobPostDTO
	 */
	public JobPostDTO jobPostFormToJobPostDTO(JobPostForm form){
		
		//Post New Job
		JobPostDTO jobPostDTO=new JobPostDTO();
		jobPostDTO.setJobOwner(form.getJobOwner());
		jobPostDTO.setCustomerNo(form.getCustomerNo());
		// Add the facility Id of selected company
		jobPostDTO.setFacilityId(form.getFacilityId());
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
		jobPostDTO.setbTemplateOverride(form.isbTemplateOverride());
		
		//Auto Renew
		jobPostDTO.setAutoRenew(form.isAutoRenew());
		jobPostDTO.setScheduleStartDt(form.getScheduleStartDate());
		jobPostDTO.setScheduleExpiryDt(form.getScheduleEndDate());
		
		jobPostDTO.setJobStatus(form.getJobStatus());
		jobPostDTO.setXmlStartEndDateEnabled(form.isXmlStartEndDateEnabled());
		
		return jobPostDTO;
	}
	
	/**
	 * This method is added to convert Job Post DTO to Form  
	 * @param jobPostform
	 * @param jobPostDTO
	 * @return jobPostform
	 */
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
		jobPostform.setAtsUrl(jobPostDTO.getAtsUrl());
		jobPostform.setJobCity(jobPostDTO.getJobCity());
		jobPostform.setJobState(jobPostDTO.getJobState());
		jobPostform.setJobZipCode(jobPostDTO.getJobZip());
		jobPostform.setJobCountry(jobPostDTO.getJobCountry());
		jobPostform.setEmploymentType(jobPostDTO.getEmploymentType());
		jobPostform.setReqSkills(jobPostDTO.getReqSkills());
		jobPostform.setJobDesc(jobPostDTO.getJobDesc());
		jobPostform.setTrackPixel(jobPostDTO.getTrackPixel());
		jobPostform.setBrandTemplate(jobPostDTO.getBrandTemplate());
		jobPostform.setbHideCity(jobPostDTO.isbHideCity());
		jobPostform.setbHideState(jobPostDTO.isbHideState());
		jobPostform.setbHideCountry(jobPostDTO.isbHideCountry());
		jobPostform.setbHideCompName(jobPostDTO.isbHideCompName());
		jobPostform.setbHideZipCode(jobPostDTO.isbHideZipCode());
		jobPostform.setbTemplateOverride(jobPostDTO.isbTemplateOverride());
		jobPostform.setFacilityId(jobPostDTO.getFacilityId());
		return jobPostform;
		
	}
	
	/**
	 * This method will convert the list of Job Postings DTO to Job posting form list 
	 * @param jobPostingPlanDTOList
	 * @return jobPostingsFormList
	 */
	public List<JobPostingsForm> transformToJobPostingsFormList(List<JobPostingPlanDTO> jobPostingPlanDTOList)
	{
		List<JobPostingsForm> jobPostingsFormList = new ArrayList<JobPostingsForm>();
		JobPostingsForm jobPostingsForm = null;
		List<AddOnForm> addOnFormList = null;
		for(JobPostingPlanDTO jobPostingPlanDTO : jobPostingPlanDTOList){
			jobPostingsForm = new JobPostingsForm();
			jobPostingsForm.setJobPostPlanId(jobPostingPlanDTO.getJobPostPlanId());
			jobPostingsForm.setJobPostPlanName(jobPostingPlanDTO.getJobPostPlanName());
			jobPostingsForm.setJobPostPlanDescr(jobPostingPlanDTO.getJobPostPlanDescr());
			jobPostingsForm.setJobPostPlanCretitAmt(jobPostingPlanDTO.getJobPostPlanCretitAmt());
			addOnFormList = new ArrayList<AddOnForm>();
			for(AddOnDTO addOnDTO : jobPostingPlanDTO.getAddOnDTOList()){
				addOnFormList.add(transformAddOnDTOToAddOnForm(addOnDTO));
			}
			jobPostingsForm.setAddOnForm(addOnFormList);
			jobPostingsFormList.add(jobPostingsForm);
		}
		return jobPostingsFormList;
		
	}
	
	/**
	 * This method will convert the AddOnDTO to AddOnForm
	 * @param addOnDTO
	 * @return addOnForm
	 */
	private AddOnForm transformAddOnDTOToAddOnForm(AddOnDTO addOnDTO) {
		AddOnForm addOnForm = new AddOnForm();
		addOnForm.setAddOnId(addOnDTO.getAddOnId());
		addOnForm.setAddOnName(addOnDTO.getAddOnName());
		addOnForm.setAddOnDescription(addOnDTO.getAddOnDescription());
		addOnForm.setAddOnCreditAmt(addOnDTO.getAddOnCreditAmt());
		return addOnForm;
	}
	
	/**
	 * This method will transform the JobPostingForm list to JobPostingsDTO list
	 * @param jobPostingsFormList
	 * @return jobPostingPlanDTOList
	 */
	public List<JobPostingPlanDTO> transformToJobPostingsDTOList(List<JobPostingsForm> jobPostingsFormList)
	{
		List<JobPostingPlanDTO> jobPostingPlanDTOList = new ArrayList<JobPostingPlanDTO>();
		
		JobPostingPlanDTO jobPostingPlanDTO = null;
		List<AddOnDTO> addOnDTOList = null; 
		for(JobPostingsForm jobPostingsForm : jobPostingsFormList){
			jobPostingPlanDTO = new JobPostingPlanDTO();
			jobPostingPlanDTO.setJobPostPlanId(jobPostingsForm.getJobPostPlanId());
			jobPostingPlanDTO.setJobPostPlanId(jobPostingsForm.getJobPostPlanId());
			jobPostingPlanDTO.setJobPostPlanName(jobPostingsForm.getJobPostPlanName());
			jobPostingPlanDTO.setJobPostPlanDescr(jobPostingsForm.getJobPostPlanDescr());
			jobPostingPlanDTO.setJobPostPlanCretitAmt(jobPostingsForm.getJobPostPlanCretitAmt());
			jobPostingPlanDTO.setQuanity(jobPostingsForm.getQuantity());
			jobPostingPlanDTO.setPackageSubTotal(jobPostingsForm.getPackageSubTotal());
			addOnDTOList = new ArrayList<AddOnDTO>();
			AddOnDTO addOnDTO = null;
			for(AddOnForm addOnForm : jobPostingsForm.getAddOnForm()){
				addOnDTO = transformAddOnFormToAddOnDTO(addOnForm);
				addOnDTOList.add(addOnDTO);
			}
			jobPostingPlanDTO.setAddOnDTOList(addOnDTOList);
			jobPostingPlanDTOList.add(jobPostingPlanDTO);
		}
		return jobPostingPlanDTOList;
	}

	/**
	 * This method will transform AddOnForm to AddOnDTO
	 * @param addOnForm
	 * @return addOnDTO
	 */
	private AddOnDTO transformAddOnFormToAddOnDTO(AddOnForm addOnForm) {
    	AddOnDTO addOnDTO = new AddOnDTO();
		addOnDTO.setAddOnId(addOnForm.getAddOnId());
		addOnDTO.setAddOnName(addOnForm.getAddOnName());
		addOnDTO.setAddOnDescription(addOnForm.getAddOnDescription());
		addOnDTO.setAddOnCreditAmt(addOnForm.getAddOnCreditAmt());
		return addOnDTO;
	}
}
