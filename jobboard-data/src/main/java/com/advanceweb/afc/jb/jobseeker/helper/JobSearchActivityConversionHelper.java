package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.AppliedJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.common.util.DateUtils;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.AdmSaveJob;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpLocation;

/**
 * <code> JobSearchActivityConversionHelper </code> is a Conversion Helper class
 * for jobs search.
 * 
 * @author Pramoda Patil
 * @version 1.0
 * @since 10 July 2012
 * 
 */
@Repository("jobSearchActivityConversionHelper")
public class JobSearchActivityConversionHelper {

	/**
	 * Entity to view job dto
	 * 
	 * @param entity
	 * @return
	 */
	public SearchedJobDTO transformJpJobToSearchedJobDTO(JpJob entity) {
		SearchedJobDTO searchedJobDTO = new SearchedJobDTO();
		if (entity != null) {
			/**
			 * get detail from JpJob entity
			 */
			searchedJobDTO.setJobTitle(entity.getJobtitle());
			searchedJobDTO.setJobDesc(entity.getAdtext());
			searchedJobDTO.setJobID(entity.getJobId());

			/**
			 * get detail from admFacility entity
			 */
			AdmFacility admFacility = entity.getAdmFacility();
			searchedJobDTO.setCompanyName(admFacility.getName());
			int blindAd = entity.getBlindAd();
			if (blindAd == 1) {
				searchedJobDTO.setCompanyNameDisp(admFacility.getNameDisplay());
			}

			/**
			 * get detail from JpLocation entity
			 */
			List<JpJobLocation> jobLocations = entity.getJpJobLocations();
			JpJobLocation jobJobLocation = jobLocations.get(0);
			JpLocation jpLocation = jobJobLocation.getJpLocation();
			int hideCity = jobJobLocation.getHideCity();
			int hideState = jobJobLocation.getHideState();
			int hideCountry = jobJobLocation.getHideCountry();

			if (hideCity != 1) {
				searchedJobDTO.setCity(jpLocation.getCity());
			}
			if (hideState != 1) {
				searchedJobDTO.setStateFullName(jpLocation.getStateFullname());
			}
			if (hideCountry != 1) {
				searchedJobDTO.setCountry(jpLocation.getCountry());
			}

			/**
			 * get the template details
			 */
			searchedJobDTO.setCompanyOverview(entity.getKeywords());
			searchedJobDTO.setImagePath(entity.getImagePath());
			searchedJobDTO.setLogo(entity.getLogo());
			searchedJobDTO.setEmployerEmailAddress(entity.getEmail());

		}
		return searchedJobDTO;
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
				createdDate = DateUtils
						.convertDateStringToSQLDate(strCreatedDate);
			}
			admSaveJob.setCreateDt(createdDate);
			String strAppliedDate = jobDTO.getAppliedDt();
			@SuppressWarnings("unused")
			java.sql.Date appliedDate = null;
			if (strAppliedDate != null) {
				appliedDate = DateUtils
						.convertDateStringToSQLDate(strAppliedDate);
			}
//			admSaveJob.setAppliedDt(appliedDate);
			String strDeleteDt = jobDTO.getDeleteDt();
			java.sql.Date deleteDtDate = null;
			if (strDeleteDt != null) {
				deleteDtDate = DateUtils
						.convertDateStringToSQLDate(strDeleteDt);
			}
//			admSaveJob.setAppliedDt(appliedDate);
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

}
