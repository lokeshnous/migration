package com.advanceweb.afc.jb.data.common.helpers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.advanceweb.afc.jb.common.SearchedJobDTO;
import com.advanceweb.afc.jb.data.entities.AdmFacility;
import com.advanceweb.afc.jb.data.entities.JpJob;
import com.advanceweb.afc.jb.data.entities.JpJobLocation;
import com.advanceweb.afc.jb.data.entities.JpLocation;


/**
 * <code> JobSearchActivityConversionHelper </code> is a Conversion Helper class for jobs search. 
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

		}
		return searchedJobDTO;
	}

}
