package com.advanceweb.afc.jb.employer.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobLocationPK;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;


/**
 * @param <JobPostForm>
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will work as a converter from EmployerJobPostDTO to Entity class Object or from Entity class Object to EmployerJobPostDTO
 */
@Repository("jobPostConversionHelper")
public class JobPostConversionHelper<JobPostForm> {
	
	 public JpJob  transformJobDtoToJpJob(JobPostDTO dto, 
			 JpTemplate template, JpJobType jobType, AdmFacility admFacility){
		
		 JpJob jpJob=new JpJob();
		 //Post New Job
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setAccountNum(dto.getCustomerNo());
		 jpJob.setJobNumber(dto.getJobNumber());
		 jpJob.setFacility(dto.getDisCompanyName());
		 jpJob.setAdminUserId(Integer.valueOf(dto.getJobOwner()));
		 jpJob.setBlindAd(dto.isbHideCompName()?1:0);
		 
		 //Job Posting Details
		 jpJob.setJpJobType(jobType);
		 
		 //Job Title and Number
		 jpJob.setJobtitle(dto.getJobTitle());
					
		 //Location
		 //Handled separately
		 
		 //Job Details
		 jpJob.setSkills(dto.getReqSkills());
		 jpJob.setTrackingPixel(dto.getTrackPixel());
		 jpJob.setAdtext(dto.getJobDesc());
		 jpJob.setStartDt(DateUtils.convertStringToSQLDate(dto.getScheduleStartDt()));
		 jpJob.setEndDt(DateUtils.convertStringToSQLDate(dto.getScheduleExpiryDt()));
		 
		 jpJob.setJpTemplate(template);
		 
		 //Auto Renew
		 jpJob.setAutoRenew(dto.isAutoRenew()?1:0);		
		 
		 jpJob.setJobStatus(dto.getJobStatus());
		 jpJob.setAdmFacility(admFacility);
		 
		 return jpJob;
	 }
	 
	 public List<JpJobLocation> transformJobPostDTOToJpJbLocation(JobPostDTO dto, 
			 JpJob jpJob, JpLocation location){
		 List<JpJobLocation> locList = new ArrayList<JpJobLocation>();
		 
		 JpJobLocation jobLocation = new JpJobLocation();
		 JpJobLocationPK pKey = new JpJobLocationPK();
		 pKey.setJobId(jpJob.getJobId());
		 pKey.setLocationId(location.getLocationId());
		 
		 jobLocation.setHideCity(dto.isbHideCity()?1:0);
		 jobLocation.setHideCountry(dto.isbHideCountry()?1:0);
		 jobLocation.setHidePostcode(dto.isbHideZipCode()?1:0);
		 jobLocation.setHideState(dto.isbHideState()?1:0);
		 jobLocation.setJpLocation(location);
		 jobLocation.setId(pKey);
		 locList.add(jobLocation);
		 
		return locList;
	 }
	 
	 public List<JpJobApply> transformJobPostDTOToJpJobApply(JobPostDTO dto, JpJob jpJob){
		 //Application Method
		 List<JpJobApply> jobList = new ArrayList<JpJobApply>();
		 JpJobApply jobApply = new JpJobApply();
		 jobApply.setApplyMethod(dto.getApplicationMethod());
		 jobApply.setJpJob(jpJob);
		 if(null != dto.getApplyEmail() && dto.getApplyEmail().length() != 0)
			 jobApply.setApplyLink(dto.getApplyEmail());
		 if(null != dto.getApplyUrl() && dto.getApplyUrl().length() != 0)
			 jobApply.setApplyLink(dto.getApplyUrl());
		 if(null != dto.getAtsUrl() && dto.getAtsUrl().length() != 0)
			 jobApply.setApplyLink(dto.getAtsUrl());
		 jobList.add(jobApply);
		 
		return jobList;
	 }
	 
	 
	 public JobPostDTO  transformToJpJobDTO(JpJob dto){
			
		 JobPostDTO jobPostDTO=new JobPostDTO();
		 jobPostDTO.setCompanyName(dto.getFacility());
		 jobPostDTO.setJobTitle(dto.getJobtitle());
		 jobPostDTO.setJobNumber(dto.getJobNumber());
		 jobPostDTO.setJobId(dto.getJobId());
		 return jobPostDTO;
	 }

	 /**
		 * This method transforms JpJob list to JobPostDTO list
		 * 
		 * @param resumes
		 * @return resumeDTOList
		 */
	public List<JobPostDTO> transformJpJobListToJobPostDTOList(List<JpJob> jobs) {
		List<JobPostDTO> jobPostDTOList = new ArrayList<JobPostDTO>();
		SimpleDateFormat formatter = new SimpleDateFormat(MMJBCommonConstants.DISP_DATE_PATTERN);
		String location=null;
		if(null !=jobs){
			for (JpJob job : jobs) {
				JobPostDTO jobPostDTO = new JobPostDTO();
				jobPostDTO.setJobId(job.getJobId());
				jobPostDTO.setJobTitle(job.getJobtitle());
				jobPostDTO.setStartDt(formatter.format(job.getStartDt()));
				jobPostDTO.setEndDt(formatter.format(job.getEndDt()));
				jobPostDTO.setAutoRenew(job.getAutoRenew()==0?false:true);
				jobPostDTO.setJobStatus(job.getJobStatus());
				if(null !=job.getJpTemplate() && null != job.getJpTemplate().getTemplateName()){
				jobPostDTO.setBrandTemplate(job.getJpTemplate().getTemplateName());
				}
				/*int compareEndDate = job.getEndDt().compareTo(new Date());
				int compareStartDate = job.getStartDt().compareTo(new Date());
				if (compareEndDate >= 0) {
					jobPostDTO.setJobStatus("Active");
				} else {
					jobPostDTO.setJobStatus("Expired");
				}
				if (compareStartDate > 0) {
					jobPostDTO.setJobStatus("Scheduled");
				}*/
				 List<JpJobLocation> jobLocationList= job.getJpJobLocations();
				 if(null !=jobLocationList){
					 for(JpJobLocation jobLocation:jobLocationList){
						 location = jobLocation.getJpLocation().getCity() +","+ jobLocation.getJpLocation().getState();
						 jobPostDTO.setLocation(location);
					 }
				 }
				if (null != job.getJpJobStat()) {
					
						jobPostDTO.setViews(job.getJpJobStat().getViews());
						jobPostDTO.setApplies(job.getJpJobStat().getApplies());
						jobPostDTO.setClicks(job.getJpJobStat().getClicks());
					
				}
				jobPostDTOList.add(jobPostDTO);
			}
		}

		return jobPostDTOList;

	}
	 
	/**
	 * This method transforms JpJob list to JobPostDTO list
	 * 
	 * @param resumes
	 * @return resumeDTOList
	 */
	public JobPostDTO transformJpJobToJobPostDTO(JpJob jpJob) {
		JobPostDTO jobPostDTO = new JobPostDTO();
		jobPostDTO.setCompanyName(jpJob.getName());
		jobPostDTO.setCustomerNo( jpJob.getJobNumber());
		jobPostDTO.setDisCompanyName(jpJob.getFacility());
		jobPostDTO.setJobOwner(String.valueOf(jpJob.getAdminUserId()));
		
		jobPostDTO.setJobTitle(jpJob.getJobtitle());
			 		
		return jobPostDTO;

	}
}
