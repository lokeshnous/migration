package com.advanceweb.afc.jb.employer.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpAddon;
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
			 JpTemplate template, AdmFacility admFacility){
		
		 JpJob jpJob=new JpJob();
		 //Post New Job
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setAccountNum(dto.getCustomerNo());
		 jpJob.setJobNumber(dto.getJobNumber());
		 jpJob.setFacility(dto.getDisCompanyName());
		 jpJob.setAdminUserId(Integer.valueOf(dto.getJobOwner()));
		 jpJob.setBlindAd(dto.isbHideCompName()?1:0);
		 
		 //Job Posting Details
		 jpJob.setJpJobType(null);
		 
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
		 
		 if(null != template && template.getTemplateId() !=0 ){
			 jpJob.setJpTemplate(template);
		 }else{
			 jpJob.setJpTemplate(null); 
		 }
		 
		 //Auto Renew
		 jpJob.setAutoRenew(dto.isAutoRenew()?1:0);				
		 
		 if(MMJBCommonConstants.POST_NEW_JOB.equals(dto.getJobStatus())){
			   jpJob.setStartDt(new Date());
		 }
		 
		 jpJob.setAdmFacility(admFacility);
		 jpJob.setActive((byte)(dto.isbActive()?1:0));
		 
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
		 jobLocation.setLocationPK(pKey);
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
			 {jobApply.setApplyLink(dto.getApplyEmail());}
		 if(null != dto.getApplyUrl() && dto.getApplyUrl().length() != 0)
			 {jobApply.setApplyLink(dto.getApplyUrl());}
		 if(null != dto.getAtsUrl() && dto.getAtsUrl().length() != 0)
			 {jobApply.setApplyLink(dto.getAtsUrl());}
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
		 * This method transforms JpJob list to JobPostDTO list		 * 
		 * @param resumes
		 * @return resumeDTOList
		 */
	public List<JobPostDTO> transformJpJobListToJobPostDTOList(List<JpJob> jobs) {
		List<JobPostDTO> jobPostDTOList = new ArrayList<JobPostDTO>();
		SimpleDateFormat formatter = new SimpleDateFormat(MMJBCommonConstants.DISP_DATE_PATTERN,Locale.US);
		String location=null;
		if (null != jobs) {
			for (JpJob job : jobs) {
				JobPostDTO jobPostDTO = new JobPostDTO();
				jobPostDTO.setJobId(job.getJobId());
				jobPostDTO.setJobTitle(job.getJobtitle());
				jobPostDTO.setAutoRenew(job.getAutoRenew() == 0 ? false : true);
				if (null != job.getJpTemplate()) {
					jobPostDTO.setBrandTemplate(String.valueOf(job
							.getJpTemplate().getTemplateId()));
				}
				if (null != job.getStartDt()) {
					jobPostDTO.setStartDt(formatter.format(job.getStartDt()));
					long startDateAsTimestamp = job.getStartDt().getTime();
					long currentTimestamp = System.currentTimeMillis();
					long getRidOfTime = 1000 * 60 * 60 * 24;
					long startDate = startDateAsTimestamp / getRidOfTime;
					long currentTimestampWithoutTime = currentTimestamp
							/ getRidOfTime;
					if (job.getActive() == 1
							&& startDate > currentTimestampWithoutTime) {
						jobPostDTO
								.setJobStatus(MMJBCommonConstants.POST_JOB_DRAFT);
					} else if (job.getActive() == 0
							&& startDate > currentTimestampWithoutTime) {
						jobPostDTO
								.setJobStatus(MMJBCommonConstants.POST_JOB_SCHEDULED);
					} else if (null != job.getEndDt()) {
						jobPostDTO.setEndDt(formatter.format(job.getEndDt()));
						long endtDateAsTimestamp = job.getEndDt().getTime();
						long endDate = endtDateAsTimestamp / getRidOfTime;

						if (job.getActive() == 1
								&& endDate < currentTimestampWithoutTime) {
							jobPostDTO
									.setJobStatus(MMJBCommonConstants.POST_JOB_EXPIRED);
						}

					} // TODO Need to check the end date condition once the
						// Package and plan functionality finalized. for the
						// time
						// being, as we need end date to check the status,I have
						// added 30 day to the start date.
					if (startDate <= currentTimestampWithoutTime) {
						if (null == job.getEndDt()) {
							Calendar now = Calendar.getInstance();
							now.setTime(job.getStartDt());
							now.add(Calendar.DAY_OF_MONTH, 30);
							job.setEndDt(now.getTime());
						}

						long endtDateAsTimestamp = job.getEndDt().getTime();
						long endDate = endtDateAsTimestamp / getRidOfTime;

						if (job.getActive() == 1

						&& endDate > currentTimestampWithoutTime) {
							jobPostDTO
									.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
						}
					}

				}

				if ((null == jobPostDTO.getJobStatus() || jobPostDTO
						.getJobStatus().isEmpty()) && (job.getActive() == 0)) {
					jobPostDTO
							.setJobStatus(MMJBCommonConstants.POST_JOB_INACTIVE);
				}
				List<JpJobLocation> jobLocationList = job.getJpJobLocations();
				if (null != jobLocationList) {
					for (JpJobLocation jobLocation : jobLocationList) {
						location = jobLocation.getJpLocation().getCity() + ","
								+ jobLocation.getJpLocation().getState();
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
		jobPostDTO.setCustomerNo(jpJob.getJobNumber());
		jobPostDTO.setDisCompanyName(jpJob.getFacility());
		jobPostDTO.setJobOwner(String.valueOf(jpJob.getAdminUserId()));
		jobPostDTO.setJobPostingType(jpJob.getJpJobType().getName());
		jobPostDTO.setJobTitle(jpJob.getJobtitle());
		jobPostDTO.setJobId(jpJob.getJobId());
		if (null != jpJob.getJpJobApplies()
				&& jpJob.getJpJobApplies().size() > 0) {
			jobPostDTO.setApplicationMethod(jpJob.getJpJobApplies().get(0)
					.getApplyMethod());

			if (jpJob.getJpJobApplies().get(0).getApplyMethod()
					.equalsIgnoreCase(MMJBCommonConstants.APPLY_TO_ATS)) {
				
				 //jobPostDTO.setApp(jpJob.getJpJobApplies().get(0).getApplyLink());
				 
			} else if (jpJob.getJpJobApplies().get(0).getApplyMethod()
					.equalsIgnoreCase(MMJBCommonConstants.APPLY_TO_EMAIL)) {
				jobPostDTO.setApplyEmail(jpJob.getJpJobApplies().get(0)
						.getApplyLink());
			} else if (jpJob.getJpJobApplies().get(0).getApplyMethod()
					.equalsIgnoreCase(MMJBCommonConstants.APPLY_TO_URL)) {
				jobPostDTO.setApplyUrl(jpJob.getJpJobApplies().get(0)
						.getApplyLink());
			}

		}
		jobPostDTO.setJobNumber(jpJob.getJobNumber());
		if (null != jpJob.getJpJobLocations() && jpJob.getJpJobLocations().size()>0) {
			jobPostDTO.setJobCity(jpJob.getJpJobLocations().get(0)
					.getJpLocation().getCity());
			jobPostDTO.setJobState(jpJob.getJpJobLocations().get(0)
					.getJpLocation().getState());
			jobPostDTO.setJobCountry(jpJob.getJpJobLocations().get(0)
					.getJpLocation().getCountry());
			jobPostDTO.setJobZip(jpJob.getJpJobLocations().get(0)
					.getJpLocation().getPostcode());
		}
		// jobPostDTO.setEmploymentType(jpJob.get)
		jobPostDTO.setReqSkills(jpJob.getSkills());
		jobPostDTO.setJobDesc(jpJob.getAdtext());
		jobPostDTO.setTrackPixel(jpJob.getTrackingPixel());
		if (null != jpJob.getJpTemplate()) {
			jobPostDTO.setBrandTemplate(String.valueOf(jpJob.getJpTemplate()
					.getTemplateId()));
		}

		return jobPostDTO;

	}
	
	/**
	 * This method transforms JpJob list to JobPostDTO list
	 * 
	 * @param resumes
	 * @return resumeDTOList
	 */
	public List<JobPostingPlanDTO> transformToJobPostingPlanDTOList(List<JpJobType> jobTypes) {
		List<JobPostingPlanDTO> jobPostingPlanDTOList = new ArrayList<JobPostingPlanDTO>();
		
		for(JpJobType jobType : jobTypes){
			JobPostingPlanDTO jobPostingPlanDTO = new JobPostingPlanDTO();
			jobPostingPlanDTO.setJobPostPlanId(String.valueOf(jobType.getJobTypeId()));
			jobPostingPlanDTO.setJobPostPlanName(jobType.getName());
			jobPostingPlanDTO.setJobPostPlanDescr(jobType.getDescription());
			jobPostingPlanDTO.setJobPostPlanCretitAmt(String.valueOf(jobType.getCreditAmt()));
			jobPostingPlanDTO.setJobPostNetSuiteId(String.valueOf(jobType.getNetSuiteId()));
			List<AddOnDTO> addOnDTOList = new ArrayList<AddOnDTO>();
			for(JpAddon jpAddon : jobType.getJpAddons()){
				addOnDTOList.add(transformJpAddOnToAddOnDTO(jpAddon));
			}
			Collections.sort(addOnDTOList,new AddOnComparable());
			jobPostingPlanDTO.setAddOnDTOList(addOnDTOList);
			jobPostingPlanDTOList.add(jobPostingPlanDTO);
		}
		return jobPostingPlanDTOList;
	}

	/**
	 * This method transforms JpJob list to JobPostDTO list
	 * 
	 * @param resumes
	 * @return resumeDTOList
	 */
	private AddOnDTO transformJpAddOnToAddOnDTO(JpAddon jpAddon) {
		AddOnDTO addOnDTO = new AddOnDTO();
		addOnDTO.setAddOnId(String.valueOf(jpAddon.getAddonId()));
		addOnDTO.setAddOnName(jpAddon.getName());
		addOnDTO.setAddOnDescription(jpAddon.getDescription());
		addOnDTO.setAddOnCreditAmt(String.valueOf(jpAddon.getCreditAmt()));
		addOnDTO.setAddOnNetSuiteId(String.valueOf(jpAddon.getNetSuiteId()));
		return addOnDTO;
	}
}
class AddOnComparable implements Comparator<AddOnDTO>{
    @Override
    public int compare(AddOnDTO obj1, AddOnDTO obj2) {
        return (Integer.parseInt(obj1.getAddOnId()) < Integer.parseInt(obj2.getAddOnId()) ? -1 : (Integer.parseInt(obj1.getAddOnId()) == Integer.parseInt(obj2.getAddOnId())) ? 0 : 1);
    }
}
