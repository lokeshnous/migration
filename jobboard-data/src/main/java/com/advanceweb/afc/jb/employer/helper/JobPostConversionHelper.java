package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will work as a converter from EmployerJobPostDTO to Entity class Object or from Entity class Object to EmployerJobPostDTO
 */
@Repository("jobPostConversionHelper")
public class JobPostConversionHelper {
	
	 public JpJob  transformJobDtoToJpJob(JobPostDTO dto){
		
		 JpJob jpJob=new JpJob();
		 //Post New Job
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setJobNumber(dto.getJobNumber());
		 jpJob.setFacility(dto.getDisCompanyName());
		 jpJob.setJobNumber(dto.getJobNumber());
		 jpJob.setAdminUserId(Integer.valueOf(dto.getJobOwner()));
		 jpJob.setBlindAd(dto.isbHideCompName()?1:0);
		 
		 //Job Posting Details
//		 jpJob.setJpJobType(jpJobType)
		 
		 //Job Title and Number
		 jpJob.setJobtitle(dto.getJobTitle());
		 
		 //Application Method
		 JpJobApply jobApply = new JpJobApply();
		 jobApply.setApplyMethod(dto.getApplicationMethod());
		 if(null != dto.getApplyEmail() && dto.getApplyEmail().length() != 0)
			 jobApply.setApplyLink(dto.getApplyEmail());
		 if(null != dto.getApplyUrl() && dto.getApplyUrl().length() != 0)
			 jobApply.setApplyLink(dto.getApplyUrl());
		 if(null != dto.getAtsUrl() && dto.getAtsUrl().length() != 0)
			 jobApply.setApplyLink(dto.getAtsUrl());
			 
		 //Location
		 JpJobLocation location = new JpJobLocation();
		 location.setHideCity(dto.isbHideCity()?1:0);
		 location.setHideCountry(dto.isbHideCountry()?1:0);
		 location.setHidePostcode(dto.isbHideZipCode()?1:0);
		 location.setHideState(dto.isbHideState()?1:0);
		 
//		 jpJob.set
		 
		 jpJob.setSkills(dto.getReqSkills());
		 jpJob.setTrackingPixel(dto.getTrackPixel());
		 jpJob.setAutoRenew(dto.isAutoRenew()?1:0);
//		 jpJob.setJpTemplate(Integer.valueOf(dto.getBrandTemplate()));
		 
		 
		 return jpJob;
	 }
	 
	 public JobPostDTO  transformToJpJobDTO(JpJob dto){
			
		 JobPostDTO jobPostDTO=new JobPostDTO();
		 jobPostDTO.setCompanyName(dto.getFacility());
		 jobPostDTO.setJobTitle(dto.getJobtitle());
		 jobPostDTO.setJobNumber(dto.getJobNumber());
		 jobPostDTO.setJobId(dto.getJobId());
		 return jobPostDTO;
	 }


}
