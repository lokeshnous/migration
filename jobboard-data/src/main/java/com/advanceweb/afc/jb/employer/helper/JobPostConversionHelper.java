package com.advanceweb.afc.jb.employer.helper;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobLocationPK;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;

/**
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will work as a converter from EmployerJobPostDTO to Entity class Object or from Entity class Object to EmployerJobPostDTO
 */
@Repository("jobPostConversionHelper")
public class JobPostConversionHelper {
	
	 public JpJob  transformJobDtoToJpJob(JobPostDTO dto, JpLocation location, 
			 JpTemplate template, JpJobType jobType){
		
		 JpJob jpJob=new JpJob();
		 //Post New Job
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setJobNumber(dto.getCustomerNo());
		 jpJob.setFacility(dto.getDisCompanyName());
		 jpJob.setAdminUserId(Integer.valueOf(dto.getJobOwner()));
		 jpJob.setBlindAd(dto.isbHideCompName()?1:0);
		 
		 //Job Posting Details
		 jpJob.setJpJobType(jobType);
		 
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
		 JpJobLocation jobLocation = new JpJobLocation();
		 JpJobLocationPK pKey = new JpJobLocationPK();
		 pKey.setJobId(dto.getJobId());
		 pKey.setLocationId(location.getLocationId());
		 
		 jobLocation.setHideCity(dto.isbHideCity()?1:0);
		 jobLocation.setHideCountry(dto.isbHideCountry()?1:0);
		 jobLocation.setHidePostcode(dto.isbHideZipCode()?1:0);
		 jobLocation.setHideState(dto.isbHideState()?1:0);
		 jobLocation.setJpLocation(location);
		 jobLocation.setId(pKey);
		 		 
		 //Job Details
		 jpJob.setSkills(dto.getReqSkills());
		 jpJob.setTrackingPixel(dto.getTrackPixel());
		 jpJob.setAdtext(dto.getJobDesc());
		 
		 jpJob.setJpTemplate(template);
		 
		 //Auto Renew
		 jpJob.setAutoRenew(dto.isAutoRenew()?1:0);		 
		 
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
