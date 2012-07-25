package com.advanceweb.afc.jb.jobseeker.helper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.SaveOrApplyJobDTO;
import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpLocation;
import com.advanceweb.afc.jb.data.entities.JpSaveJob;

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
			 * get detail from admFacility entity
			 */
			AdmFacility admFacility = entity.getAdmFacility();
			searchedJobDTO.setCompanyName(admFacility.getName());

			/**
			 * get detail from JpJob entity
			 */
			searchedJobDTO.setJobTitle(entity.getJobtitle());
			searchedJobDTO.setJobDesc(entity.getAdtext());

			/**
			 * get detail from JpLocation entity
			 */
			List<JpJobLocation> jobLocations = entity.getJpJobLocations();
			JpJobLocation jobJobLocation = jobLocations.get(0);
			JpLocation jpLocation = jobJobLocation.getJpLocation();
			searchedJobDTO.setCity(jpLocation.getCity());
			searchedJobDTO.setState(jpLocation.getState());

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
	 * This method is called to convert ApplyJobDTO to JpSaveJob Entity
	 * 
	 * @param entity
	 * @return
	 */
	public JpSaveJob transformJobDTOToJpSaveJob(SaveOrApplyJobDTO jobDTO) {
		JpSaveJob jpSaveJob = new JpSaveJob();
		if (jobDTO != null) {
			jpSaveJob.setUserId(jobDTO.getUserId());
			jpSaveJob.setJobId(jobDTO.getJobId());
			jpSaveJob.setCreateDt(jobDTO.getCreateDate());
			jpSaveJob.setAppliedDate(jobDTO.getAppliedDate());
			jpSaveJob.setIsApplied(jobDTO.getIsApplied());
		}
		return jpSaveJob;
	}

	/**
	 * This method is called to convert saveSearchedJobsDTO to Save Search
	 * Entity
	 * 
	 * @param saveSearchedJobsDTO
	 * @return JpSaveSearch
	 */
	public JpSaveJob transformSearchedJobDTOtoJpSaveJob(
			SearchedJobDTO searchedJobDTO) {
		JpSaveJob jpSaveJob = new JpSaveJob();
		jpSaveJob.setUserId(searchedJobDTO.getUserID());
		jpSaveJob.setJobId(searchedJobDTO.getJobID());
		//jpSaveJob.setJobTitle(searchedJobDTO.getJobTitle());
		//jpSaveJob.setCompanyName(searchedJobDTO.getCompanyName());
		jpSaveJob.setCreateDt(searchedJobDTO.getCreatedDate());
		return jpSaveJob;
	}

}
