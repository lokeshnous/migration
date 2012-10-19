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
import com.advanceweb.afc.jb.common.JobPostDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
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
	public SearchedJobDTO transformJpJobToSearchedJobDTO(JpJob entity) {
		SearchedJobDTO searchedJobDTO = new SearchedJobDTO();
		if (entity != null) {
			// get detail from JpJob entity
			searchedJobDTO.setJobTitle(entity.getJobtitle());
			searchedJobDTO.setJobDesc(entity.getAdtext());
			searchedJobDTO.setJobID(entity.getJobId());
			searchedJobDTO.setFeatureEmployer(entity.getFeatured() == 1 ? true
					: false);

			// get detail from admFacility entity
			AdmFacility admFacility = entity.getAdmFacility();
			searchedJobDTO.setCompanyName(admFacility.getName());
			searchedJobDTO.setFacilityId(admFacility.getFacilityId());
			int blindAd = entity.getBlindAd();
			if (blindAd == 1) {
				searchedJobDTO.setCompanyNameDisp(admFacility.getNameDisplay());
			}

			transformJpLocationtosearchedJobDTO(entity, searchedJobDTO);

			// get the template details
//			searchedJobDTO.setCompanyOverview(entity.getKeywords());
//			searchedJobDTO.setImagePath(entity.getImagePath());
//			searchedJobDTO.setLogo(entity.getLogo());
			
			JpTemplate jpTemplate = entity.getJpTemplate();
			if(null != jpTemplate)
			{
				searchedJobDTO.setTemplateId(jpTemplate.getTemplateId());
				searchedJobDTO.setCompanyOverview(jpTemplate.getCompanyOverview());
				searchedJobDTO.setImagePath(jpTemplate.getMainImagePath());
				searchedJobDTO.setLogo(jpTemplate.getLogoPath());
				
				if(null==jpTemplate.getColorPalette() || jpTemplate.getColorPalette().isEmpty())
				{
					searchedJobDTO.setColor(defaultColor);
				}
				else
				{
					searchedJobDTO.setColor(jpTemplate.getColorPalette());
				}
				
//				Multimedia section
				searchedJobDTO.setListTestimony(brandTemplateConversionHelper.transformTemplateTestimonyToDTO(jpTemplate));
				searchedJobDTO.setListAddImages(brandTemplateConversionHelper.transformAddImageToDTO(jpTemplate));
				searchedJobDTO.setListVideos(brandTemplateConversionHelper.transformVideoToDTO(jpTemplate));
				
			}
			
			searchedJobDTO.setEmployerEmailAddress(entity.getEmail());
			

		}
		return searchedJobDTO;
	}

	/**
	 * Transform the 
	 * 
	 * @param entity
	 * @param searchedJobDTO
	 */
	public void transformJpLocationtosearchedJobDTO(JpJob entity,
			SearchedJobDTO searchedJobDTO) {
		// get detail from JpLocation entity
		int hideCity = 1;
		int hideState = 1;
		int hideCountry = 1;
		JpLocation jpLocation = null;
		try{
			List<JpJobLocation> jobLocations = entity.getJpJobLocations();
			JpJobLocation jobJobLocation = jobLocations.get(0);
			jpLocation = jobJobLocation.getJpLocation();
			hideCity = jobJobLocation.getHideCity();
			hideState = jobJobLocation.getHideState();
			hideCountry = jobJobLocation.getHideCountry();
		}catch (Exception e) {
			LOGGER.info("Locations not found for Job Id :"+searchedJobDTO.getJobID());
		}

		if (hideCity != 1) {
			searchedJobDTO.setCity(jpLocation.getCity());
		}
		if (hideState != 1) {
			searchedJobDTO.setStateFullName(jpLocation.getStateFullname());
		}
		if (hideCountry != 1) {
			searchedJobDTO.setCountry(jpLocation.getCountry());
		}
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
			
			System.out.println("strDeleteDt==="+strDeleteDt);
			
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
	public AdmSaveJob transformSearchedJobDTOtoJpSaveJob(
			SearchedJobDTO searchedJobDTO) {
		AdmSaveJob jpSaveJob = new AdmSaveJob();
		jpSaveJob.setUserId(searchedJobDTO.getUserID());
		jpSaveJob.setSaveJobId(searchedJobDTO.getJobID());
		// jpSaveJob.setJobTitle(searchedJobDTO.getJobTitle());
		// jpSaveJob.setCompanyName(searchedJobDTO.getCompanyName());
		jpSaveJob.setCreateDt(searchedJobDTO.getCreatedDate());
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
