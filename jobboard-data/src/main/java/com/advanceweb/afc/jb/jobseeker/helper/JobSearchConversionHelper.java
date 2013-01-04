package com.advanceweb.afc.jb.jobseeker.helper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.CommonUtil;
import com.advanceweb.afc.jb.common.JobApplyTypeDTO;
import com.advanceweb.afc.jb.common.JobDTO;
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobApply;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpTemplate;
import com.advanceweb.afc.jb.employer.helper.BrandTemplateConversionHelper;

/**
 * <code> JobSearchConversionHelper </code> is a Conversion Helper class for
 * jobs search.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Repository("jobSearchConversionHelper")
public class JobSearchConversionHelper {
	
	private static final Logger LOGGER = Logger
			.getLogger(JobSearchConversionHelper.class);

	private @Value("${defaultColor}")
	String defaultColor;
	
	@Autowired
	private BrandTemplateConversionHelper brandTemplateConversionHelper;
	/**
	 * Entity to view job dto
	 * 
	 * @param entity
	 * @return
	 */
	public JobDTO transformJpJobToJobDTO(JpJob entity) {
		JobDTO jobDTO = new JobDTO();
		if (entity != null) {
			// get detail from JpJob entity
			jobDTO.setJobTitle(entity.getJobtitle());
			jobDTO.setAdText(entity.getAdtext());
			jobDTO.setJobId(entity.getJobId());
			jobDTO.setFeatured(entity.getFeatured() == 1 ? true
					: false);

			// get detail from admFacility entity
			AdmFacility admFacility = entity.getAdmFacility();
			jobDTO.setCompany(admFacility.getName());
			jobDTO.setFacilityId(admFacility.getFacilityId());
			int blindAd = entity.getBlindAd();
			if (blindAd == 0) {
				jobDTO.setCompanyNameDisp(entity.getFacility());
			}

			transformJpLocationtojobDTO(entity, jobDTO);

			// get the template details
//			jobDTO.setCompanyOverview(entity.getKeywords());
//			jobDTO.setImagePath(entity.getImagePath());
//			jobDTO.setLogo(entity.getLogo());
			
			JpTemplate jpTemplate = entity.getJpTemplate();
			if(null != jpTemplate && jpTemplate.getTemplateId()>0)
			{
				jobDTO.setTemplateId(jpTemplate.getTemplateId());
				jobDTO.setCompanyOverview(jpTemplate.getCompanyOverview());
				jobDTO.setImagePath(jpTemplate.getMainImagePath());
				jobDTO.setLogo(jpTemplate.getLogoPath());
				
				if(null==jpTemplate.getColorPalette() || jpTemplate.getColorPalette().isEmpty())
				{
					jobDTO.setColor(defaultColor);
				}
				else
				{
					jobDTO.setColor(jpTemplate.getColorPalette());
				}
				
//				Multimedia section
				jobDTO.setListTestimony(brandTemplateConversionHelper.transformTemplateTestimonyToDTO(jpTemplate));
				jobDTO.setListAddImages(brandTemplateConversionHelper.transformAddImageToDTO(jpTemplate));
				jobDTO.setListVideos(brandTemplateConversionHelper.transformVideoToDTO(jpTemplate));
				
			}
			
			jobDTO.setEmail(entity.getEmail());
			

		}
		return jobDTO;
	}

	/**
	 * Transform the 
	 * 
	 * @param entity
	 * @param jobDTO
	 */
	public void transformJpLocationtojobDTO(JpJob entity,
			JobDTO jobDTO) {
		// get detail from JpLocation entity
		int hideCity = 1;
		int hideState = 1;
		int hideCountry = 1;
		int hidePosCode = 1;
		JpLocation jpLocation = null;
		try{
			List<JpJobLocation> jobLocations = entity.getJpJobLocations();
			JpJobLocation jobJobLocation = jobLocations.get(0);
			jpLocation = jobJobLocation.getJpLocation();
			hideCity = jobJobLocation.getHideCity();
			hideState = jobJobLocation.getHideState();
			hideCountry = jobJobLocation.getHideCountry();
			hidePosCode= jobJobLocation.getHidePostcode();
		}catch (Exception e) {
			LOGGER.info("Locations not found for Job Id :"+jobDTO.getJobId());
		}
		if(null != jpLocation){
		jobDTO.setCity(jpLocation.getCity());
		jobDTO.setState(jpLocation.getState());
		jobDTO.setCountry(jpLocation.getCountry());
		jobDTO.setPostCode(jpLocation.getPostcode());
		}
		jobDTO.setHideCity(hideCity);
		jobDTO.setHideState(hideState);
		jobDTO.setHideCountry(hideCountry);
		jobDTO.setHidePostcode(hidePosCode);
	}

	/**
	 * This method is called to convert ApplyJobDTO to AdmSaveJob Entity
	 * 
	 * @param entity
	 * @return
	 */
	public AdmSaveJob transformJobDTOToAdmSaveJob(AppliedJobDTO jobDTO) {
		AdmSaveJob admSaveJob = new AdmSaveJob();
		if (jobDTO != null) {
			admSaveJob.setUserId(jobDTO.getUserId());
			JpJob jpJob = new JpJob();
			int jobId = jobDTO.getJpJob().getJobId();
			jpJob.setJobId(jobId);
			admSaveJob.setJpJob(jpJob);
			admSaveJob.setJobtitle(jobDTO.getJobTitle());
			admSaveJob.setFacilityName(jobDTO.getFacilityName());
			String strCreatedDate = jobDTO.getCreateDt();
			java.sql.Date createdDate = null;
			if (strCreatedDate != null) {
				createdDate = (Date) CommonUtil.strDateToSQLDate(strCreatedDate);
			}
			admSaveJob.setCreateDt(createdDate);
			String strAppliedDate = jobDTO.getAppliedDt();
			java.sql.Date appliedDate = null;
			if (strAppliedDate != null) {
				appliedDate = (Date) CommonUtil
						.strDateToSQLDate(strAppliedDate);
			}
			admSaveJob.setAppliedDt(appliedDate);
			String strDeleteDt = jobDTO.getDeleteDt();
			java.sql.Date deleteDtDate = null;
			if (strDeleteDt != null) {
				deleteDtDate = (Date) CommonUtil
						.strDateToSQLDate(strDeleteDt);
			}
			// admSaveJob.setAppliedDt(appliedDate);
			admSaveJob.setDeleteDt(deleteDtDate);
		}
		return admSaveJob;
	}

	/**
	 * This method is called to convert saveSearchedJobsDTO to Save Search
	 * Entity
	 * 
	 * @param saveSearchedJobsDTO
	 * @return JpSaveSearch
	 */
	public AdmSaveJob transformJobDTOtoJpSaveJob(
			JobDTO jobDTO) {
		AdmSaveJob jpSaveJob = new AdmSaveJob();
		jpSaveJob.setUserId(jobDTO.getUserID());
		jpSaveJob.setSaveJobId(jobDTO.getJobId());
		// jpSaveJob.setJobTitle(jobDTO.getJobTitle());
		// jpSaveJob.setCompanyName(jobDTO.getCompanyName());
		jpSaveJob.setCreateDt(jobDTO.getCreatedDate());
		return jpSaveJob;
	}

	/**
	 * This method is called to convert JpJobApply entity to JpJobApply DTO
	 * 
	 * @param jpJobApplys
	 * @return List<JobApplyTypeDTO>
	 */
	public List<JobApplyTypeDTO> transformJpJobApplytoJobApplyTypeDTO(
			List<JpJobApply> jpJobApplys) {
		List<JobApplyTypeDTO> jobApplyTypeDTOs = new ArrayList<JobApplyTypeDTO>();
		for (JpJobApply jpJobApply : jpJobApplys) {
			JobApplyTypeDTO jobApplyTypeDTO = new JobApplyTypeDTO();
			JobPostDTO job = new JobPostDTO();
			job.setJobId(jpJobApply.getJpJob().getJobId());
			jobApplyTypeDTO.setJobID(job);
			jobApplyTypeDTO.setJobApplyID(jpJobApply.getJobApplyId());
			jobApplyTypeDTO.setApplyMethod(jpJobApply.getApplyMethod());
			jobApplyTypeDTO.setApplyLink(jpJobApply.getApplyLink());
			boolean active = false;
			if(jpJobApply.getActive() == 1){
				active = true;
			}
			jobApplyTypeDTO.setbIsActive(active);
			jobApplyTypeDTOs.add(jobApplyTypeDTO);
		}
		return jobApplyTypeDTOs;
	}

}
