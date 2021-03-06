/*
 * Copyright (c) 2013. Nous info system for JobBoard.
 * All rights reserved. 
 * @author Nous
 * 
 * @version 1.0
 */
package com.advanceweb.afc.jb.employer.helper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AddOnDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.JobPostingPlanDTO;
import com.advanceweb.afc.jb.common.util.MMJBCommonConstants;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpAddon;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobAddon;
import com.advanceweb.afc.jb.data.entities.JpJobAddonPK;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpJobLocationPK;
import com.advanceweb.afc.jb.data.entities.JpJobType;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.data.entities.JpTypeAddonXref;


/**
 * @param <JobPostForm>
 * @Author : Prince Mathew
   @Version: 1.0
   @Created: Jul 18, 2012
   @Purpose: This class will work as a converter from EmployerJobPostDTO to Entity class Object or from Entity class Object to EmployerJobPostDTO
 */
@Repository("jobPostConversionHelper")
public class JobPostConversionHelper<JobPostForm> {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(JobPostConversionHelper.class);
	 
 	/**
 	 * Transform job dto to jp job.
 	 *
 	 * @param jpJob the jp job
 	 * @param dto the dto
 	 * @param template the template
 	 * @param admFacility the adm facility
 	 * @return the jp job
 	 */
 	public JpJob  transformJobDtoToJpJob(JpJob jpJob, JobPostDTO dto, 
			 JpTemplate template, AdmFacility admFacility){
		
		 
		 //Post New Job
		 if(dto.getJobId()>0){
			 jpJob.setJobId(dto.getJobId());
		 }
		 jpJob.setName(dto.getCompanyName());
		 jpJob.setAccountNum(dto.getCustomerNo());
		 jpJob.setJobNumber(dto.getJobNumber());
		 
		 if(dto.isbHideCompName()){
			 jpJob.setFacility(MMJBCommonConstants.EMPTY);
		 }else{
			 jpJob.setFacility(dto.getDisCompanyName().isEmpty()?admFacility.getName():dto.getDisCompanyName());
		 }
		 
		 jpJob.setAdminUserId(Integer.valueOf(dto.getJobOwner()));
		 jpJob.setBlindAd(dto.isbHideCompName()?1:0);
		 
		 //Job Posting Details
		// jpJob.setJpJobType(null);
		 
		 //Job Title and Number
		 jpJob.setJobtitle(dto.getJobTitle());
					
		 //Location
		 //Handled separately
		 
		 //Job Details
		 jpJob.setSkills(dto.getReqSkills());
		 jpJob.setTrackingPixel(dto.getTrackPixel());
		 jpJob.setAdtext(dto.getJobDesc());
		 if(!dto.getScheduleStartDt().isEmpty() && !dto.getScheduleExpiryDt().isEmpty()){
		 jpJob.setStartDt(CommonUtil.convtStringToSQLDate(dto.getScheduleStartDt()));
		 jpJob.setEndDt(CommonUtil.convtStringToSQLDate(dto.getScheduleExpiryDt()));
		 }
		 jpJob.setPositionType(dto.getEmploymentType());
		 if(null != template && template.getTemplateId() !=0 ){
			 jpJob.setJpTemplate(template);
		 }else{
			 jpJob.setJpTemplate(null); 
		 }
		 
		 //Auto Renew
		 jpJob.setAutoRenew(dto.isAutoRenew()?1:0);				
		 
		if (MMJBCommonConstants.POST_NEW_JOB.equals(dto.getJobStatus()) && jpJob.getJobId()<=0) {
			jpJob.setStartDt(new Date());
		}
		if ((dto.getJobId() > 0 && dto.isbActive()) && (MMJBCommonConstants.POST_JOB_SCHEDULED.equals(dto.getJobStatus())
				|| MMJBCommonConstants.POST_JOB_DRAFT
						.equals(dto.getJobStatus()))) {
			Calendar now = Calendar.getInstance();
			jpJob.setStartDt(now.getTime());
			now.add(Calendar.DAY_OF_MONTH, 30);
			jpJob.setEndDt(now.getTime());
		}
			
		 jpJob.setAdmFacility(admFacility);
		 jpJob.setActive((byte)(dto.isbActive()?1:0));
		 jpJob.setTemplateOverride(dto.isbTemplateOverride()?1:0);
		 jpJob.setFeatured(dto.isbFeatured()?(byte)1:0);
		 jpJob.setCreateUserId(dto.getUserId());
		 
		 if (MMJBCommonConstants.POST_JOB_INACTIVE.equals(dto.getJobStatus())) {
			 jpJob.setActive((byte)0); 
		 }
		 
		if (dto.getJobCountry().equals("USA")) {
			jpJob.setIsNational((byte) 1);
		}
		if (dto.getJobCountry().equals("CA")) {
			jpJob.setIsInternational((byte) 1);
		}
		 return jpJob;
	 }
	 
	 /**
 	 * Transform job post dto to jp jb location.
 	 *
 	 * @param dto the dto
 	 * @param jpJob the jp job
 	 * @param location the location
 	 * @return the list
 	 */
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
		 jobLocation.setJpJob(jpJob);
		 locList.add(jobLocation);
		 
		return locList;
	 }
	 
	 /**
 	 * Transform job post dto to jp job apply.
 	 *
 	 * @param dto the dto
 	 * @param jpJob the jp job
 	 * @return the list
 	 */
 	public List<JpJobApply> transformJobPostDTOToJpJobApply(JobPostDTO dto, JpJob jpJob){
		 //Application Method
		 List<JpJobApply> jobList = new ArrayList<JpJobApply>();
		 JpJobApply jobApply = new JpJobApply();
		 if(jpJob.getJpJobApplies()!=null){
		 jobApply.setJobApplyId(jpJob.getJpJobApplies().get(0).getJobApplyId());
		 }
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
	 
	 
	 /**
 	 * Transform to jp job dto.
 	 *
 	 * @param dto the dto
 	 * @return the job post dto
 	 */
 	public JobPostDTO  transformToJpJobDTO(JpJob dto){
			
		 JobPostDTO jobPostDTO=new JobPostDTO();
		 jobPostDTO.setCompanyName(dto.getFacility());
		 jobPostDTO.setJobNumber(dto.getJobNumber());
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
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.DISP_DATE_PATTERN, Locale.US);
		String location=null;
		if (null != jobs) {
			for (JpJob job : jobs) {
				JobPostDTO jobPostDTO = new JobPostDTO();
				jobPostDTO.setJobId(job.getJobId());
				jobPostDTO.setJobTitle(job.getJobtitle());
				jobPostDTO.setCompanyName(job.getName());
				jobPostDTO.setDisCompanyName(job.getFacility());
				jobPostDTO.setJobNumber(job.getJobNumber());
				jobPostDTO.setAutoRenew(job.getAutoRenew() == 0 ? false : true);
				jobPostDTO.setbActive(job.getActive()== 0 ? false : true);
				jobPostDTO.setStartDt(job.getStartDt()==null?null:job.getStartDt().toString());
				//jobPostDTO.setJobPostingType(job.getJpJobType().getName());
				if (null != job.getJpTemplate()) {
					jobPostDTO.setBrandTemplate(job
							.getJpTemplate().getTemplateId());
				}

				setJobStatus(job, jobPostDTO);
				List<JpJobLocation> jobLocationList = job.getJpJobLocations();
				if (null != jobLocationList) {
					for (JpJobLocation jobLocation : jobLocationList) {
						location = jobLocation.getJpLocation().getCity() + ","
								+ jobLocation.getJpLocation().getState();
						jobPostDTO.setLocation(location);
					}
				}
				jobPostDTO.setFacilityId(job.getAdmFacility().getFacilityId());
				jobPostDTO
						.setbTemplateOverride(job.getTemplateOverride() == 1 ? true
								: false);
				if (null != job.getEndDt() && null == jobPostDTO.getEndDt()) {
					jobPostDTO.setEndDt(formatter.format(job.getEndDt()));
				}
				jobPostDTOList.add(jobPostDTO);
			}
		}

		return jobPostDTOList;

	}

	/**
	 * Method to set the status of a job by start date, end date and the active flag
	 *  
	 * activeflag = 0 && startDate > currentdate ------> Scheduled
	 * activeflag= 1 && endtDate < endtDate ------> Expired
	 * activeflag= 1 && startDate <= currentdate &&  endtDate > currentdate ------> Active
	 * activeflag = 0 && startDate=null and enddate=null draft
	 * 
	 * @param job
	 * @param jobPostDTO
	 */
	private void setJobStatus(JpJob job, JobPostDTO jobPostDTO) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				MMJBCommonConstants.DISP_DATE_PATTERN, Locale.US);
		if (null != job.getStartDt()) {
			jobPostDTO.setStartDt(formatter.format(job.getStartDt()));
			Calendar startDate = Calendar.getInstance();
			startDate.setTime(job.getStartDt());
			Calendar currentDate = Calendar.getInstance();
			Calendar endDt = Calendar.getInstance();
			if (job.getActive() == 0 && startDate.after(currentDate)) {
				jobPostDTO.setJobStatus(MMJBCommonConstants.POST_JOB_SCHEDULED);
			} else if (null != job.getEndDt()) {
				jobPostDTO.setEndDt(formatter.format(job.getEndDt()));
				// End date with out time
				endDt.setTime(job.getEndDt());
				
				//endDt.add(Calendar.HOUR, 24);
				//endDt.add(Calendar.MINUTE, 59);
				//endDt.add(Calendar.SECOND, 59);

				if (job.getActive() == 1
						&& endDt.getTime().before(currentDate.getTime())) {
					jobPostDTO
							.setJobStatus(MMJBCommonConstants.POST_JOB_EXPIRED);
				}

			}
			if (startDate.compareTo(currentDate) <= 0) {
				if (null == job.getEndDt()) {
					Calendar now = Calendar.getInstance();
					now.setTime(job.getStartDt());
					now.add(Calendar.DAY_OF_MONTH, 30);
					job.setEndDt(now.getTime());
				}

				if (job.getActive() == 1
						&& endDt.getTime().compareTo(currentDate.getTime()) >= 0) {
					jobPostDTO.setJobStatus(MMJBCommonConstants.POST_NEW_JOB);
				}
			}

		}
		if ((job.getActive() == 0 && null == job.getStartDt() && null == job
				.getEndDt())) {
			jobPostDTO.setJobStatus(MMJBCommonConstants.POST_JOB_DRAFT);
		}
		if ((null == jobPostDTO.getJobStatus() || jobPostDTO.getJobStatus()
				.isEmpty()) && (job.getActive() == 0)) {
			jobPostDTO.setJobStatus(MMJBCommonConstants.POST_JOB_INACTIVE);
		}
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
		//jobPostDTO.setCustomerNo(jpJob.getJobNumber());
		jobPostDTO.setJobNumber(jpJob.getJobNumber());
		jobPostDTO.setAutoRenew(jpJob.getAutoRenew()== 1?true:false);
		jobPostDTO.setDisCompanyName(jpJob.getFacility());
		jobPostDTO.setJobOwner(String.valueOf(jpJob.getAdminUserId()));
		jobPostDTO.setJobTitle(jpJob.getJobtitle());
		jobPostDTO.setJobId(jpJob.getJobId());
		jobPostDTO.setUserId(jpJob.getCreateUserId());
		if (null != jpJob.getJpJobApplies()
				&& jpJob.getJpJobApplies().size() > 0) {
			jobPostDTO.setApplicationMethod(jpJob.getJpJobApplies().get(0)
					.getApplyMethod());

			if (jpJob.getJpJobApplies().get(0).getApplyMethod()
					.equalsIgnoreCase(MMJBCommonConstants.APPLY_TO_ATS)) {
				 jobPostDTO.setAtsUrl(jpJob.getJpJobApplies().get(0).getApplyLink());
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
			
			// get detail from JpLocation entity
			int hideCity = 1;
			int hideState = 1;
			int hideCountry = 1;
			int hidePostcode = 1;
			int hideCompName = 1;
			try{
				List<JpJobLocation> jobLocations = jpJob.getJpJobLocations();
				JpJobLocation jobJobLocation = jobLocations.get(0);
				hideCity = jobJobLocation.getHideCity();
				hideState = jobJobLocation.getHideState();
				hideCountry = jobJobLocation.getHideCountry();
				hidePostcode = jobJobLocation.getHidePostcode();
			}catch (Exception e) {
				LOGGER.error("Locations not found for Job Id", e);
			}

				jobPostDTO.setbHideCity(hideCity == 1?true:false);
				jobPostDTO.setbHideState(hideState == 1?true:false);
				jobPostDTO.setbHideCountry(hideCountry == 1?true:false);
				jobPostDTO.setbHideZipCode(hidePostcode == 1?true:false);
				
				hideCompName = jpJob.getBlindAd();
				jobPostDTO.setbHideCompName(hideCompName == 1?true:false);
			
		}
		 jobPostDTO.setEmploymentType(jpJob.getPositionType());
		jobPostDTO.setReqSkills(jpJob.getSkills());
		jobPostDTO.setJobDesc(jpJob.getAdtext());
		jobPostDTO.setFacilityId(jpJob.getAdmFacility().getFacilityId());
		jobPostDTO.setTrackPixel(jpJob.getTrackingPixel());
		if (null != jpJob.getJpTemplate()) {
			jobPostDTO.setBrandTemplate(jpJob.getJpTemplate()
					.getTemplateId());
		}

		setJobStatus(jpJob, jobPostDTO);
		jobPostDTO.setbTemplateOverride(jpJob.getTemplateOverride()==1?true:false);
		jobPostDTO.setSourceId(jpJob.getJpSource().getSourceId());
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
		return addOnDTO;
	}
	
	/**
	 * Transform xref to jp job.
	 *
	 * @param xrefList the xref list
	 * @param jpJob the jp job
	 * @param dto the dto
	 * @return the list
	 */
	public List<JpJobAddon> transformXrefToJpJob(
			List<JpTypeAddonXref> xrefList, JpJob jpJob, JobPostDTO dto) {

		List<JpJobAddon> jpJobAddonList = new ArrayList<JpJobAddon>();
		if (null != xrefList && !xrefList.isEmpty()) {
			for (JpTypeAddonXref xref : xrefList) {
				JpJobAddonPK jpJobAddonPK = new JpJobAddonPK();
				jpJobAddonPK.setAddonId(xref.getAddonId());
				jpJobAddonPK.setJobId(jpJob.getJobId());

				JpJobAddon jpJobAddon = new JpJobAddon();
				jpJobAddon.setActive(dto.isbActive() ? (byte) 1 : 0);
				jpJobAddon.setCreateDt(new Timestamp(new Date().getTime()));
				jpJobAddon.setJobAddonPK(jpJobAddonPK);
				jpJobAddon.setJpJob(jpJob);
				jpJobAddonList.add(jpJobAddon);
			}
			// jpJob.setJpJobAddons(jpJobAddonList);
		}
		return jpJobAddonList;
	}
}
class AddOnComparable implements Comparator<AddOnDTO>{
    @Override
    public int compare(AddOnDTO obj1, AddOnDTO obj2) {
        return (Integer.parseInt(obj1.getAddOnId()) < Integer.parseInt(obj2.getAddOnId()) ? -1 : (Integer.parseInt(obj1.getAddOnId()) == Integer.parseInt(obj2.getAddOnId())) ? 0 : 1);
    }
}
